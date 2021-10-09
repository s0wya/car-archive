package pl.conmir.cararchive.dto;

import pl.conmir.cararchive.domain.performance.Performance;

public class PerformanceMapper {

    public static GetPerformanceDto toGetPerformanceDto(Performance performance){
        return GetPerformanceDto.builder()
                .id(performance.getId())
                .power(performance.getPower().getValue())
                .torque(performance.getTorque().getValue())
                .build();
    }
}
