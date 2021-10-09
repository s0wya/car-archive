package pl.conmir.cararchive.dto;

import pl.conmir.cararchive.domain.modificationFile.ModificationFile;

public class ModificationFileMapper {

    public static GetModificationFile toModificationFileResponse(ModificationFile file){
        return GetModificationFile
                .builder()
                .id(file.getId())
                .name(file.getName().getValue())
                .data(file.getData().getValue())
                .type(file.getType())
                .build();

    }

}
