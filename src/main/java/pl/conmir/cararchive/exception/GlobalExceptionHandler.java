package pl.conmir.cararchive.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<List<ExceptionResponse>> handleValidationException(ValidationException exception){

        LocalDateTime time = LocalDateTime.now();

        List<ExceptionResponse> errors = exception.getErrors()
                .stream()
                .map( e -> ExceptionResponse.builder()
                        .message(e.getMessage())
                        .errorCode(e.getCode())
                        .timestamp(time)
                        .build())
                .toList();

        return ResponseEntity
                .status(exception.getHttpErrorCode())
                .body(errors);
    }

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceFoundException(ResourseNotFoundException exception){

        LocalDateTime time = LocalDateTime.now();
        int errorCode = exception.getHTTP_ERROR_CODE();
        String message = exception.getMessage();

        var error = ExceptionResponse.builder()
                .message(message)
                .timestamp(time)
                .build();

        return ResponseEntity
                .status(errorCode)
                .body(error);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ExceptionResponse> handleFileStorageException(FileStorageException exception){

        LocalDateTime time = LocalDateTime.now();
        int errorCode = exception.getHTTP_ERROR_CODE();
        String message = exception.getMessage();

        var error = ExceptionResponse.builder()
                .message(message)
//                .status(errorCode)
                .timestamp(time)
                .build();

        return ResponseEntity
                .status(errorCode)
                .body(error);
    }

}
