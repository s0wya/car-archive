package pl.conmir.cararchive.modificationFile;

import pl.conmir.cararchive.modificationFile.dto.ModificationFileResponse;

public class ModificationFileMapper {

    public static ModificationFileResponse toModificationFileResponse(ModificationFile file){
        return ModificationFileResponse
                .builder()
                .id(file.getId())
                .name(file.getName().getValue())
                .data(file.getData().getValue())
                .type(file.getType())
                .build();

    }

}
