package pl.conmir.cararchive.car.domain;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegistrationNumber {

    private String value;

    private RegistrationNumber(String value){
        if (!isCorrect(value))
            throw new IllegalArgumentException("Registration number cannot be blank!");

        this.value = value;
    }

    public static RegistrationNumber of(String value){
        return new RegistrationNumber(value);

    }

    private boolean isCorrect(String value){
        return !StringUtils.isBlank(value)
                && !StringUtils.containsWhitespace(value)
                && value.length() == 7;
    }
}
