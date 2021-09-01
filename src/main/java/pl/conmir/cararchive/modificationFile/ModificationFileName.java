package pl.conmir.cararchive.modificationFile;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModificationFileName {

    private String value;

    private ModificationFileName(String value){
        if (!isCorrect(value))
            throw new IllegalArgumentException("File name cannot be null!");

        this.value = value;
    }

    public static ModificationFileName of(String value){
        return new ModificationFileName(value);
    }

    private boolean isCorrect(String value){
        return !StringUtils.isAllBlank(value);
    }
}
