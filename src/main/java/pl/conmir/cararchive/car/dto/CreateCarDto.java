package pl.conmir.cararchive.car.dto;


import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCarDto {

    private String registration;
    private String model;
    private String make;
    private int year;

}
