package pl.conmir.cararchive.domain;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Make {

    private String value;

    private Make(String value){
        if (!isCorrect(value))
            throw new IllegalArgumentException("Make number cannot be blank!");

        this.value = value;
    }

    public static Make of(String value){
        return new Make(value);

    }

    private boolean isCorrect(String value){
        return !StringUtils.isBlank(value)
                && !StringUtils.containsWhitespace(value);
    }
}
