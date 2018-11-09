# API Template: With Spring Boot

> Spring Boot기반으로 기본적인 설정을 자동화하고 JPA를 통해 Domain Model의 영속화(Persistence)에 대한 코딩 및 오류를 줄입니다. 
Web(App) - Service - Model - Repository(+Infrastracture) 로 각 Layer의 역할에 필요한 구현만 합니다.

## Features
- **[Spring Boot](https://spring.io/projects/spring-boot)**: configuration 자동화, spring-boot-starter-*
- **Spring MVC**: Spring MVC pattern
- **JPA**: MySql, Couchbase connection, Repository, Entity
- **Spring Validator**: Request validation 수행, [Sping MVC Custom Validation](https://www.baeldung.com/spring-mvc-custom-validator)
- **[Lombok](https://projectlombok.org/features/all)**: 코드자동화 라이브러리, @Data, @Slf4j...
- **Gradle**: 빌드 관리 툴, 가독성, 빌드 속도 좋음 [Maven vs Gradle](https://bkim.tistory.com/13)
- **Swagger**: API문서 자동화. http://localhost:8080/swagger-ui.html
- **Hystrix** (sample에 있는 pattern으로 젹용할지는 미확정)
    - resources-per-operation : 단위명령에 대해 쓰레드로 자원을 분리.
    - resilency: circuit breaker, fallback, timeout 

## Setup
1. IntelliJ에 Lombok Plugin을 설치한다.
    ```
    Preferences > Plugins > Browse repositories.. > Lombok 검색 > Install > restart
    ```
2. Gradle 창에서 Refresh를 클릭힌다. 인고의 시간이 지나면 모든 라이브러리가 download되고 **Run** 가능한 상태가 된다.

## TODO
- **Request Logging**: Request의 url, header, body의 내용을 저장소(파일, nosql 등)에 logging 한다. (구현 중...)
- **Parent Module**: (조금은 먼미래에) 기본이 되는 Module과 설정을 Parent Module로 제공한다. 설정관리플랫폼과 연동하여 기본이 되는 인프라 설정을 자동화 한다.
- **프로젝트 계층화**: 한 project에 상하위 Module을 설정하여 협업에 용이하게 한다.

## 더 고려해야할 사항
template에 domain model쪽은 package만 있고 비어 있습니다. domain model을 어떻게 구현하느냐는 업무에 대한 이해도와 각 업무별 특성에 따라 매우 달라집니다.
담당하는 서비스별로 스터디하고 합의가 되야 합니다.
####도메인 설계 관련 참고서적
- [DDD Start!](http://www.aladin.co.kr/shop/wproduct.aspx?ItemId=84000742): DDD 책중에 제일 쉽습니다. Java로 된 예제가 있어요!!
- [DDD 기본정보 정리](https://nesoy.github.io/articles/2018-08/DDD-Architecture)  
- [도메인 주도 설계의 본질](https://www.slideshare.net/baejjae93/ss-27536729)