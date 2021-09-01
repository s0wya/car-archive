package pl.conmir.cararchive.car;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.conmir.cararchive.car.dto.CreateCarDto;
import pl.conmir.cararchive.exception.ValidationException;

@Component
public class Validator {

    private enum Error {
        MAKE_BLANK (400,"Make cannot be blank!"),
        MAKE_WHITESPACES (400,"Make cannot contain whitespaces"),
        MODEL_BLANK (400,"Model cannot contain whitespaces"),
        MODEL_WHITESPACES (400, "Model cannot contain whitespaces"),
        YEAR_INCORRECT_FORMAT (400, "Year must have 4 digits"),
        REGISTRATION_NUMBER_BLANK (400, "Registration number cannot be blank!"),
        REGISTRATION_NUMBER_WHITESPACES (400, "Registration number cannot contain whitespaces!"),
        REGISTRATION_NUMBER_INCORRECT_FORMAT (400, "Registration number must contain 7 letters");

        private final int code;
        private final String message;

        Error(int code, String errorMessage) {
            this.code = code;
            this.message = errorMessage;
        }

        public String getMessage() {
            return message;
        }

        public int getCode(){
            return code;
        }

    }

    public void validate(CreateCarDto dto){
        if (isMakeBlank(dto.getMake()))
            throw new ValidationException(
                    Error.MAKE_BLANK.getCode(),
                    Error.MAKE_BLANK.getMessage()
            );

        if (hasMakeContainWhitespaces(dto.getMake()))
            throw new ValidationException(
                    Error.MAKE_WHITESPACES.getCode(),
                    Error.MAKE_WHITESPACES.getMessage()
            );

        if (isModelBlank(dto.getModel()))
            throw new ValidationException(
                    Error.MODEL_BLANK.getCode(),
                    Error.MODEL_BLANK.getMessage()
            );

        if (hasModelContainWhitespaces(dto.getModel()))
            throw new ValidationException(
                    Error.MODEL_WHITESPACES.getCode(),
                    Error.MODEL_WHITESPACES.getMessage()
            );

        if (hasProductionYearIncorrectFormat(dto.getYear()))
            throw new ValidationException(
                    Error.YEAR_INCORRECT_FORMAT.getCode(),
                    Error.YEAR_INCORRECT_FORMAT.getMessage()
            );

        if (isRegistrationNumberBlank(dto.getRegistration()))
            throw new ValidationException(
                    Error.REGISTRATION_NUMBER_BLANK.getCode(),
                    Error.REGISTRATION_NUMBER_BLANK.getMessage()
            );

        if (hasRegistrationNumberContainWhitespaces(dto.getRegistration()))
            throw new ValidationException(
                    Error.REGISTRATION_NUMBER_WHITESPACES.getCode(),
                    Error.REGISTRATION_NUMBER_WHITESPACES.getMessage()
            );

        if (hasRegistrationNumberIncorrectFormat(dto.getRegistration()))
            throw new ValidationException(
                    Error.REGISTRATION_NUMBER_INCORRECT_FORMAT.getCode(),
                    Error.REGISTRATION_NUMBER_INCORRECT_FORMAT.getMessage()
            );

    }

    private boolean isMakeBlank(String value){
        return StringUtils.isBlank(value);
    }

    private boolean hasMakeContainWhitespaces(String value){
        return StringUtils.containsWhitespace(value);
    }

    private boolean isModelBlank(String value){
        return StringUtils.isBlank(value);
    }

    private boolean hasModelContainWhitespaces(String value){
        return StringUtils.containsWhitespace(value);
    }

    private boolean hasProductionYearIncorrectFormat(int value){
        return String.valueOf(value).length() != 4;
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
