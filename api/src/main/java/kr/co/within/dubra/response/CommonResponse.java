package kr.co.within.dubra.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

public class CommonResponse<T> extends ResponseEntity<CommonResponseBody<T>> {

    public CommonResponse(){
        super(new CommonResponseBody<T>(HttpStatus.OK, null, null), HttpStatus.OK);
    }

    public CommonResponse(T body) {
        super(new CommonResponseBody<>(HttpStatus.OK, null, body), HttpStatus.OK);
    }

    public CommonResponse(@Nullable T body, HttpStatus status) {
        super(new CommonResponseBody<>(status, null, body), status);
    }

    public CommonResponse(String message){
        super(new CommonResponseBody<>(HttpStatus.OK, message, null), HttpStatus.OK);
    }

    public CommonResponse(@Nullable T body, String message) {
        super(new CommonResponseBody<>(HttpStatus.OK, message, body), HttpStatus.OK);
    }

    public CommonResponse(@Nullable T body, HttpStatus status, String message) {
        super(new CommonResponseBody<>(status, message, body), status);
    }
}