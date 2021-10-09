package pl.conmir.cararchive.domain.originalFile;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileData {

    @Lob
    private byte[] value;

    private FileData(byte[] value){
        if (!isCorrect(value))
            throw new IllegalArgumentException("File data cannot be null and empty!");

        this.value = value;
    }

    public static FileData of(byte[] value){
        return new FileData(value);
    }

    private boolean isCorrect(byte[] value){
        return value != null
                && value.length > 0;
    }

}
