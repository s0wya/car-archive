package pl.conmir.cararchive.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetCarDto {

    private long id;
    private String registration;
    private String make;
    private String model;
    private int year;
    private LocalDateTime createdAt;

}
