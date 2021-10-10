package pl.conmir.cararchive.api.validator;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TorqueValidator {

    public enum Error implements ValidationError {
        TORQUE_LESS_THAN_ZERO ("Torque cannot be lass than zero");

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


    public List<ValidationError> validate(int torque){
        var errors = new ArrayList<ValidationError>();

        if (!isMoreThanZero(torque))
            errors.add(Error.TORQUE_LESS_THAN_ZERO);

        return errors;
    }

    private boolean isMoreThanZero(int value){
        return value > 0;
    }

}
