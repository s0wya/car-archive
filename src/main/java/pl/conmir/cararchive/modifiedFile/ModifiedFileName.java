package pl.conmir.cararchive.modifiedFile;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModifiedFileName {

    private String value;

    private ModifiedFileName(String value){
        if (!isCorrect(value))
            throw new IllegalArgumentException("File name cannot be null!");

        this.value = value;
    }

    public static ModifiedFileName of(String value){
        return new ModifiedFileName(value);
    }

    private boolean isCorrect(String value){
        return !StringUtils.isBlank(value)
                && !StringUtils.containsWhitespace(value);
    }
}
