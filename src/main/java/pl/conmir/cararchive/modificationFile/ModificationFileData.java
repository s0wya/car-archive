package pl.conmir.cararchive.modificationFile;

import lombok.*;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModificationFileData {

    @Lob
    private byte[] value;

    private ModificationFileData(byte[] value){
        if (!isCorrect(value))
            throw new IllegalArgumentException("File data cannot be null and empty!");

        this.value = value;
    }

    public static ModificationFileData of(byte[] value){
        return new ModificationFileData(value);
    }

    private boolean isCorrect(byte[] value){
        return value != null
                && value.length > 0;
    }
}
