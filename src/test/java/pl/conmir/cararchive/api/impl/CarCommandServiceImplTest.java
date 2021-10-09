package pl.conmir.cararchive.api.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.conmir.cararchive.domain.CarFactory;
import pl.conmir.cararchive.api.validator.CarValidator;
import pl.conmir.cararchive.domain.Make;
import pl.conmir.cararchive.domain.Model;
import pl.conmir.cararchive.domain.ProductionYear;
import pl.conmir.cararchive.domain.RegistrationNumber;
import pl.conmir.cararchive.dto.CreateCarDto;
import pl.conmir.cararchive.domain.CarRepository;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CarCommandServiceImplTest {

    @Mock
    private CarRepository repository;

    @Mock
    private CarFactory carFactory;

    @Mock
    private CarValidator carValidator;

    @InjectMocks
    private CarCommandServiceImpl carCommandService;


    @Test
    void exampleTest(){
        //given
        var dto = createCarDto();
        var make = Make.of(dto.getMake());
        var model = Model.of(dto.getModel());
        var productionYear = ProductionYear.of(dto.getYear());
        var registrationNumber = RegistrationNumber.of(dto.getRegistration());


        carCommandService.save(dto);



    }

    private CreateCarDto createCarDto(){
        return CreateCarDto.builder()
                .registration("1234567")
                .make("make")
                .model("model")
                .year(1223)
                .build();
    }

}