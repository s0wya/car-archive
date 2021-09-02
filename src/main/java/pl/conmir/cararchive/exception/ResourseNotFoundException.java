package pl.conmir.cararchive.exception;

import lombok.Getter;

@Getter
public class ResourseNotFoundException extends RuntimeException{

    private final int errorCode;

    public ResourseNotFoundException(int erroCode, String message) {
        super(message);
        this.errorCode = erroCode;
    }

    public ResourseNotFoundException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

}
