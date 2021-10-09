package pl.conmir.cararchive.exception;

import lombok.Getter;

@Getter
public class ResourseNotFoundException extends RuntimeException{

    private final int HTTP_ERROR_CODE = 404;

    public ResourseNotFoundException(String message) {
        super(message);
    }

    public ResourseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
