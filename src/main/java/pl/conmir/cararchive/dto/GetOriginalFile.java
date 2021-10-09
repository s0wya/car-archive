package pl.conmir.cararchive.dto;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetOriginalFile {

    private Long id;
    private String name;
    private String type;
    private byte[] data;

}
