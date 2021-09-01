package pl.conmir.cararchive.car;


import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CarResponse {

    private long id;
    private String registration;
    private String make;
    private String model;
    private int year;

}
