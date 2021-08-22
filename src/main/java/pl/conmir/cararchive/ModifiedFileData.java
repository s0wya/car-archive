package pl.conmir.cararchive;

import lombok.*;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModifiedFileData {

    @Lob
    private byte[] value;

    private ModifiedFileData(byte[] value){
        if (!isCorrect(value))
            throw new IllegalArgumentException("File data cannot be null and empty!");

        this.value = value;
    }

    public static ModifiedFileData of(byte[] value){
        return new ModifiedFileData(value);
    }

    private boolean isCorrect(byte[] value){
        return value != null
                && value.length > 0;
    }
}
