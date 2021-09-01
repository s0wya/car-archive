package pl.conmir.cararchive.originalFile;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OriginalFileResponse {

    private Long id;
    private String name;
    private String type;
    private byte[] data;

}
