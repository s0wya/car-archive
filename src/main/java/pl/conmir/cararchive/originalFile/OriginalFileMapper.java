package pl.conmir.cararchive.originalFile;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.conmir.cararchive.originalFile.dto.OriginalFileResponse;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OriginalFileMapper {

    public static OriginalFileResponse toOriginalFileResponse(OriginalFile file){
        return OriginalFileResponse.builder()
                .id(file.getId())
                .name(file.getName().getValue())
                .type(file.getType())
                .data(file.getData().getValue())
                .build();

    }
}
