package kr.co.within.dubra.exception;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestPartException.class,
            BindException.class,
            NoHandlerFoundException.class,
            AsyncRequestTimeoutException.class,
            Exception.class
    })
    @Nullable
    public final ResponseEntity<Object> defaultException(Exception ex, WebRequest request) {

        HttpStatus httpStatus = getHttpExceptionStatus(ex);
        String path = request.getContextPath();
        ApiExceptionBody body = new ApiExceptionBody(httpStatus, ex, path);
        return new ResponseEntity<>(body, httpStatus);
    }

    private HttpStatus getHttpExceptionStatus(Exception ex){

        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return HttpStatus.METHOD_NOT_ALLOWED;
        }
        else if (ex instanceof HttpMediaTypeNotSupportedException) {
            return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        }
        else if (ex instanceof HttpMediaTypeNotAcceptableException) {
            return  HttpStatus.NOT_ACCEPTABLE;

        }
        else if (ex instanceof MissingPathVariableException) {
            return  HttpStatus.INTERNAL_SERVER_ERROR;

        }
        else if (ex instanceof MissingServletRequestParameterException) {
            return  HttpStatus.BAD_REQUEST;

        }
        else if (ex instanceof ServletRequestBindingException) {
            return  HttpStatus.BAD_REQUEST;

        }
        else if (ex instanceof ConversionNotSupportedException) {
            return  HttpStatus.INTERNAL_SERVER_ERROR;

        }
        else if (ex instanceof TypeMismatchException) {
            return  HttpStatus.BAD_REQUEST;

        }
        else if (ex instanceof HttpMessageNotReadableException) {
            return  HttpStatus.BAD_REQUEST;

        }
        else if (ex instanceof HttpMessageNotWritableException) {
            return  HttpStatus.INTERNAL_SERVER_ERROR;

        }
        else if (ex instanceof MethodArgumentNotValidException) {
            return  HttpStatus.BAD_REQUEST;

        }
        else if (ex instanceof MissingServletRequestPartException) {
            return  HttpStatus.BAD_REQUEST;

        }
        else if (ex instanceof BindException) {
            return  HttpStatus.BAD_REQUEST;

        }
        else if (ex instanceof NoHandlerFoundException) {
            return  HttpStatus.NOT_FOUND;

        }
        else if (ex instanceof AsyncRequestTimeoutException) {
            return  HttpStatus.SERVICE_UNAVAILABLE;

        }
        else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
