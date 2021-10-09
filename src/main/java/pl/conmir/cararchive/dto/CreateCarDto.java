package pl.conmir.cararchive.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCarDto {

    private String registration;
    private String model;
    private String make;
    private int year;
    private CreatePerformanceDto performance;
    private CreatePerformanceModificationDto performanceModification;
    private MultipartFile originalFile;
    private List<MultipartFile> modificationFiles;

}
