package pl.conmir.cararchive.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.conmir.cararchive.domain.originalFile.OriginalFile;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OriginalFileMapper {

    public static GetOriginalFile toOriginalFileResponse(OriginalFile file){
        return GetOriginalFile.builder()
                .id(file.getId())
                .name(file.getName().getValue())
                .type(file.getType())
                .data(file.getData().getValue())
                .build();

    }
}
