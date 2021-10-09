package pl.conmir.cararchive.exception;


import lombok.Getter;
import pl.conmir.cararchive.api.validator.ValidationError;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ValidationException extends RuntimeException {
    private static final int HTTP_ERROR_CODE = 400;
    private final List<ValidationError> errors;

    public ValidationException(List<ValidationError> errors) {
        this.errors= errors;
    }

    public int getHttpErrorCode(){
        return HTTP_ERROR_CODE;
    }
//
//    public List<String> getErrorCode(){
//        return errors.stream()
//                .map(ValidationError::getCode)
//                .collect(Collectors.toList());
//    }
//
//    public List<String> getMessages(){
//        return errors.stream()
//                .map(ValidationError::getMessage)
//                .collect(Collectors.toList());
//    }
}
