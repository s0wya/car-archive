package pl.conmir.cararchive.api.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RegistrationNumberValidator {

    public enum Error implements ValidationError {
        REGISTRATION_NUMBER_BLANK ("Registration number cannot be blank!"),
        REGISTRATION_NUMBER_WHITESPACES ("Registration number cannot contain whitespaces!"),
        REGISTRATION_NUMBER_INCORRECT_FORMAT ("Registration number must contain 7 letters");

        private final String message;

        Error(String errorMessage) {
            this.message = errorMessage;
        }

        @Override
        public String getMessage() {
            return this.message;
        }

        @Override
        public String getCode() {
            return this.name();
        }

    }

    //Error/ValidationError
    public List<ValidationError> validate(String registrationNumber){
        var errors = new ArrayList<ValidationError>();

        if (isRegistrationNumberBlank(registrationNumber))
            errors.add(Error.REGISTRATION_NUMBER_BLANK);


        if (hasRegistrationNumberContainWhitespaces(registrationNumber))
            errors.add(Error.REGISTRATION_NUMBER_WHITESPACES);


        if (hasRegistrationNumberIncorrectFormat(registrationNumber))
            errors.add(Error.REGISTRATION_NUMBER_INCORRECT_FORMAT);

        return errors;
    }



    private boolean isRegistrationNumberBlank(String value){
        return StringUtils.isBlank(value);
    }

    private boolean hasRegistrationNumberContainWhitespaces(String value){
        return StringUtils.containsWhitespace(value);
    }

    private boolean hasRegistrationNumberIncorrectFormat(String value){
        return value.length() != 7;
    }
}
