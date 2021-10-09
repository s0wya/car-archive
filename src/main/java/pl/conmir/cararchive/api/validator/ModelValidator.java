package pl.conmir.cararchive.api.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelValidator {

    public enum Error implements ValidationError {
        MODEL_BLANK ("Model cannot contain whitespaces"),
        MODEL_WHITESPACES ("Model cannot contain whitespaces");

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


    public List<ValidationError> validate(String model){
        var errors = new ArrayList<ValidationError>();

        if (isModelBlank(model))
            errors.add(Error.MODEL_BLANK);

        if (hasModelContainWhitespaces(model))
            errors.add(Error.MODEL_WHITESPACES);

        return errors;
    }

    private boolean isModelBlank(String value){
        return StringUtils.isBlank(value);
    }

    private boolean hasModelContainWhitespaces(String value){
        return StringUtils.containsWhitespace(value);
    }

}
