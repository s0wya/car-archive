package pl.conmir.cararchive.car;

import pl.conmir.cararchive.car.domain.*;
import pl.conmir.cararchive.car.dto.CreateCarDto;

public class CarMapper {

    public static Car fromCreateCarDto(CreateCarDto request){
        return Car.builder()
                .make(Make.of(request.getMake()))
                .model(Model.of(request.getModel()))
                .year(ProductionYear.of(request.getYear()))
                .registration(RegistrationNumber.of(request.getRegistration()))
                .build();
    }

    public static CarResponse toCarResponse(Car car){
        //to get get troche smierdzi -> nie lepiej w car od razu zwracac value?
        return CarResponse.builder()
                .id(car.getId())
                .make(car.getMake().getValue())
                .model(car.getModel().getValue())
                .registration(car.getRegistration().getValue())
                .year(car.getYear().getValue())
                .build();
    }

}
