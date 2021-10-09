package pl.conmir.cararchive.exception;

import lombok.Getter;

@Getter
public class FileStorageException extends RuntimeException{

    private final int HTTP_ERROR_CODE = 500;

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
       super(message, cause);
    }

}
