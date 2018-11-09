# gradle로 멀티 프로젝트 구성하기

gradle로 멀티/서브 프로젝트를 구성하는 방법은 순서대로 정리하였다. 
***Intellij***를 사용하여 웹프로젝트를 만드는 샘플이며, 하나의 git repository내에서 계층 모듈을 구성하여 참조하는 예이다.

#### 만들고자 하는 프로젝트 구성 예
- [root] vegeta : project의 설정만 가지고 있는 root프로젝트
  - [sub] cms-core : 업무처리와 저장소 연결등의 대부분의 내용을 담고 있는 core 모듈 (더 세분화 해도 된다.)
  - [sub] cms-api : presentation만을 담당하는 모듈 (cms-core의 service를 이용해 api를 제공, ui가 필요하면 cms-web등의 sub모듈을 추가한다)
  

#### 1.Root 프로젝트 만들기
intellij의 메뉴 [File > New > Project...] 을 실행해서 프로젝트 생성창을 띄운 후,
왼쪽 리스트박스의 'Gradle' 선택 후, 우측 Additional Libraries and Frameworks 에서는 'Java'를 선택한다.

다음 화면에서 아래 내용 입력한다.
- GroupId : kr.co.within.vegeta
- ArtifactId : vegeta

다음 화면에서 아래 2개 체크 해제되있는것을 체크한다.
- Use auto-import
- Create directories for empty content roots automatically

생성된 root 프로젝트의 build.gradle 에서 아래 두줄 빼고 모두 삭제한다.(version은 number 3개 추천)
- group 'kr.co.within.vegeta'
- version '1.0.0-SNAPSHOT' 


만약 프로젝트 폴더에 src 폴더가 생성되있다면 이 폴더도 삭제한다.

#### 2.Sub 프로젝트 만들기
intellij의 메뉴 [File > New > Module...] 을 실행해서 프로젝트 생성창을 띄운 후,
왼쪽 리스트박스의 'Gradle > Java' 모듈을를 생성한다.
다음 화면에서 ArtifactId에 'core'를 입력한다.
다음 화면에서 module name에 'cms-core'를 입력한다. ('업무-계층이름'이며 ArtifactId과 그냥 같게 써도 무방하다)
같은 방법으로 'api'모듈을 생성한다.

cms-core, cms-api 이렇게 두 개의 프로젝트를 생성하면, 프로젝트의 폴더 구조는 다음과 같을 것이다.

- vegeta
  - cms-core
  - cms-api

아직까지는 폴더 구조만 루트, 서브 프로젝트 처럼 보일 뿐이지 아직 서로간에 종속 관계가 없다.

#### 3. 프로젝트 관계 정하기
루트 프로젝트의 settings.gradle 파일을 열면 아래 한줄만 있을텐데

rootProject.name = 'vegeta'

여기에 아래 줄을 추가한다.

include 'cms-core', 'cms-api'

#### 4. Root build.gradle 파일 수정
``` groovy
//build 관련 설정
buildscript {
    ext {
        springBootVersion = '2.0.4.RELEASE'
    }
    repositories {
        jcenter()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}

//모든프로젝트에 해당하는 통합 설정
allprojects {
    group 'kr.co.within.vegeta'
    version '1.0.0-SNAPSHOT'
}

//subproject들 공통 설정
subprojects {
    apply plugin: 'java'
    apply plugin: 'org.springframework.boot'
    apply plugin: 'io.spring.dependency-management'

    sourceCompatibility = 1.8

    repositories {
        jcenter()
    }

    //sub 프로젝트 공통 적용되는 dependency
    dependencies {

        compileOnly('org.projectlombok:lombok')
        testCompile('org.projectlombok:lombok')

        testCompile('org.springframework.boot:spring-boot-starter-test')
    }

}
```


#### 5. sub모듈의 build.gradle 파일들 각각 수정

이제 각 'cms-core', 'cms-api'하위의 build.gradle을 수정하자.

- cms-core의 build.gradle

    ``` groovy
    
    dependencies {
        compile('org.springframework.boot:spring-boot-starter-json')
        compile('org.springframework.boot:spring-boot-starter-data-couchbase')
    }
    
    ```
    
- cms-api의 build.gradle

    ``` groovy
     
    dependencies {
        compile('org.springframework.boot:spring-boot-starter-web')
        compile('org.springframework.boot:spring-boot-starter-actuator')
        compile('io.springfox:springfox-swagger2:2.9.2')
        compile('io.springfox:springfox-swagger-ui:2.9.2')
        
        //submodule인 cms-core을 하위모듈로 참조
        compile(project(':cms-core'))
    }
    
    ```

#### 6. 기타

root 프로젝트의 subprojects 의 dependencies 에는 모든 서브 프로젝트 공통 dependencies를 넣고
특정 sub 프로젝트에서만 사용하는 dependency 는 해당 build.gradle의 dependencies에 넣으면 된다.

필요한 sub 모듈에서 src/main 밑에 패키지 및 자바 파일을 만들어서 아래 내용 넣는다
``` java
@SpringBootApplication
public class MyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}
}
```

