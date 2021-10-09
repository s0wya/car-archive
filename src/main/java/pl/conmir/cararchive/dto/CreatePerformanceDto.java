package pl.conmir.cararchive.dto;

import lombok.*;
import pl.conmir.cararchive.domain.performance.Power;
import pl.conmir.cararchive.domain.performance.Torque;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePerformanceDto {
    private int power;
    private int torque;
}
