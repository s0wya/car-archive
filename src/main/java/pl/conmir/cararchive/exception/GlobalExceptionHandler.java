package pl.conmir.cararchive.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(ValidationException exception){

        LocalDateTime time = LocalDateTime.now();
        int errorCode = exception.getErrorCode();
        String message = exception.getMessage();

        var error = ExceptionResponse.builder()
                .message(message)
                .status(errorCode)
                .timestamp(time)
                .build();

        return ResponseEntity
                .status(errorCode)
                .body(error);
    }

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceFoundException(ResourseNotFoundException exception){

        LocalDateTime time = LocalDateTime.now();
        int errorCode = exception.getErrorCode();
        String message = exception.getMessage();

        var error = ExceptionResponse.builder()
                .message(message)
                .status(errorCode)
                .timestamp(time)
                .build();

        return ResponseEntity
                .status(errorCode)
                .body(error);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ExceptionResponse> handleFileStorageException(FileStorageException exception){

        LocalDateTime time = LocalDateTime.now();
        int errorCode = exception.getErrorCode();
        String message = exception.getMessage();

        var error = ExceptionResponse.builder()
                .message(message)
                .status(errorCode)
                .timestamp(time)
                .build();

        return ResponseEntity
                .status(errorCode)
                .body(error);
    }

}
