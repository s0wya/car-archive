package pl.conmir.cararchive.dto;

import pl.conmir.cararchive.domain.*;

public class CarMapper {

    public static Car fromCreateCarDto(CreateCarDto request){
        return Car.builder()
                .make(Make.of(request.getMake()))
                .model(Model.of(request.getModel()))
                .year(ProductionYear.of(request.getYear()))
                .registration(RegistrationNumber.of(request.getRegistration()))
                .build();
    }

    public static GetCarDto toCarResponse(Car car){
        return GetCarDto.builder()
                .id(car.getId())
                .make(car.getMake().getValue())
                .model(car.getModel().getValue())
                .registration(car.getRegistration().getValue())
                .year(car.getYear().getValue())
                .createdAt(car.getCreatedAt())
                .build();
    }

    public static GetCarCount toGetCarCount(long carId, long count){
        return GetCarCount.builder()
                .id(carId)
                .count(count)
                .build();
    }

}
