package pl.conmir.cararchive.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetPerformanceDto {

    private long id;
    private int power;
    private int torque;
    private int powerAfter;
    private int torqueAfter;

}
