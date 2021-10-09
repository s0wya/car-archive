package pl.conmir.cararchive.api.validator;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pl.conmir.cararchive.dto.CreateCarDto;
import pl.conmir.cararchive.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class CarValidator {

    private final MakeValidator makeValidator;
    private final ModelValidator modelValidator;
    private final YearValidator yearValidator;
    private final RegistrationNumberValidator registrationNumberValidator;


    public void validate(CreateCarDto dto){
        var errors = new ArrayList<ValidationError>();

        var makeErrors = makeValidator.validate(dto.getMake());
        errors.addAll(makeErrors);

        var modelErrors = modelValidator.validate(dto.getModel());
        errors.addAll(modelErrors);

        var yearErrors = yearValidator.validate(dto.getYear());
        errors.addAll(yearErrors);

        var registrationNumberErrors = registrationNumberValidator.validate(dto.getRegistration());
        errors.addAll(registrationNumberErrors);

        if(!errors.isEmpty())
            throw new ValidationException(errors);

    }





}
