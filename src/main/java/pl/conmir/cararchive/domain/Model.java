package pl.conmir.cararchive.domain;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Model {

    private String value;

    private Model(String value){
        if (!isCorrect(value))
            throw new IllegalArgumentException("Model number cannot be blank!");

        this.value = value;
    }

    public static Model of(String value){
        return new Model(value);

    }

    private boolean isCorrect(String value){
        return !StringUtils.isBlank(value)
                && !StringUtils.containsWhitespace(value);
    }
}
