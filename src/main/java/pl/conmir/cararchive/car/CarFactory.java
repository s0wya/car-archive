package pl.conmir.cararchive.car;

import org.springframework.stereotype.Component;
import pl.conmir.cararchive.car.domain.*;

@Component
public class CarFactory {

    public Car create(Make make, Model model, ProductionYear productionYear, RegistrationNumber registrationNumber){

        return Car.builder()
                .make(make)
                .model(model)
                .year(productionYear)
                .registration(registrationNumber)
                .build();
    }

}
