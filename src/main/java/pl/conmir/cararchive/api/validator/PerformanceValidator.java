package pl.conmir.cararchive.api.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.conmir.cararchive.dto.CreatePerformanceDto;
import pl.conmir.cararchive.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PerformanceValidator {

    private final PowerValidator powerValidator;
    private final TorqueValidator torqueValidator;

    public void validate(CreatePerformanceDto dto){
        var errors = new ArrayList<ValidationError>();

        var powerErrors = powerValidator.validate(dto.getPower());
        errors.addAll(powerErrors);

        var torqueErrors = torqueValidator.validate(dto.getTorque());
        errors.addAll(torqueErrors);

        if (!errors.isEmpty())
            throw new ValidationException(errors);
    }

}
