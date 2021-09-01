package pl.conmir.cararchive.originalFile;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileName {

    private String value;

    private FileName(String value){
        if (!isCorrect(value))
            throw new IllegalArgumentException("File name cannot be null!");

        this.value = value;
    }

    public static FileName of(String value){
        return new FileName(value);
    }

    private boolean isCorrect(String value){
        return !StringUtils.isBlank(value);
    }

}
