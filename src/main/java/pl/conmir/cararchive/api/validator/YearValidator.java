package pl.conmir.cararchive.api.validator;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class YearValidator {

    public enum Error implements ValidationError {
        YEAR_INCORRECT_FORMAT ("Year must have 4 digits");

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


    public List<ValidationError> validate(int model){
        var errors = new ArrayList<ValidationError>();

        if (hasProductionYearIncorrectFormat(model))
            errors.add(Error.YEAR_INCORRECT_FORMAT);


        return errors;
    }

    private boolean hasProductionYearIncorrectFormat(int value){
        return String.valueOf(value).length() != 4;
    }


}
