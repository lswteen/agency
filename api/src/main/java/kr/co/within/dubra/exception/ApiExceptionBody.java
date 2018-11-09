package kr.co.within.dubra.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiExceptionBody {
    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    private String stacktrace;

    private ApiExceptionBody() {
        timestamp = LocalDateTime.now();
    }

    public ApiExceptionBody(HttpStatus status, Throwable ex, String path) {
        this();
        this.status = status;
        this.message = ex.getLocalizedMessage();
        this.stacktrace = ExceptionUtils.getStackTrace(ex);
    }

    public ApiExceptionBody(HttpStatus status, Throwable ex, String path, String message) {
        this(status, ex, path);
        this.message = message;
    }
}
