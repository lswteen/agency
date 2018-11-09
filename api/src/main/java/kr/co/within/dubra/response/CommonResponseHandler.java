package kr.co.within.dubra.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@RestControllerAdvice(basePackages = "kr.co.within.dubra.controller")
public class CommonResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if(selectedContentType == MediaType.APPLICATION_JSON) {
            //공통 json body wrapper
            CommonResponseBody responseBody = new CommonResponseBody<>();
            responseBody.setData(body);

            HttpMethod requestHttpMethod = request.getMethod();
            HttpStatus httpStatus = HttpStatus.OK;
            if (requestHttpMethod.equals(HttpMethod.POST)) {
                httpStatus = HttpStatus.CREATED;
            } else if (requestHttpMethod.equals(HttpMethod.DELETE)) {
                httpStatus = HttpStatus.NO_CONTENT;
            }
            responseBody.setStatus(httpStatus);

            return responseBody;
        }else{
            return body;
        }
    }
}
