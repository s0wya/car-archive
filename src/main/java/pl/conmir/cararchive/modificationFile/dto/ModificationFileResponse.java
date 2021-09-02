package pl.conmir.cararchive.modificationFile.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ModificationFileResponse {

    private Long id;
    private String name;
    private String type;
    private byte[] data;

}
