package pl.conmir.cararchive.exception;

import lombok.Getter;

@Getter
public class FileStorageException extends RuntimeException{

    private final int errorCode;

    public FileStorageException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public FileStorageException(int errorCode, String message, Throwable cause) {
       super(message, cause);
        this.errorCode = errorCode;
    }

}
