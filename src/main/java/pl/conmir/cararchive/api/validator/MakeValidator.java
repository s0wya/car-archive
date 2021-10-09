package pl.conmir.cararchive.api.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.conmir.cararchive.dto.CreateCarDto;
import pl.conmir.cararchive.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class MakeValidator {

    public enum Error implements ValidationError {
        MAKE_BLANK("Make cannot be blank!"),
        MAKE_WHITESPACES("Make cannot contain whitespaces");

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


    public List<ValidationError> validate(String make){
        var errors = new ArrayList<ValidationError>();

        if (isMakeBlank(make))
            errors.add(Error.MAKE_BLANK);

        if (hasMakeContainWhitespaces(make))
            errors.add(Error.MAKE_WHITESPACES);

        return errors;
    }

    private boolean isMakeBlank(String value){
        return StringUtils.isBlank(value);
    }

    private boolean hasMakeContainWhitespaces(String value){
        return StringUtils.containsWhitespace(value);
    }



}
