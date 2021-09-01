package pl.conmir.cararchive.exception;


import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException{
    private final int errorCode;

    public ValidationException(int errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

}
