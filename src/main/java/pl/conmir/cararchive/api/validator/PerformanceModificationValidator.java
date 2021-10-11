package pl.conmir.cararchive.api.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.conmir.cararchive.dto.CreatePerformanceModificationDto;
import pl.conmir.cararchive.exception.ValidationException;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class PerformanceModificationValidator {

    private final PowerValidator powerValidator;
    private final TorqueValidator torqueValidator;

    public void validate(CreatePerformanceModificationDto dto){
        var errors = new ArrayList<ValidationError>();

        var power = dto.getPowerAfterModification();
        var powerErrors = powerValidator.validate(power);
        errors.addAll(powerErrors);

        var torque = dto.getTorqueAfterModification();
        var torqueErrors = torqueValidator.validate(torque);
        errors.addAll(torqueErrors);

        if (!errors.isEmpty())
            throw new ValidationException(errors);
    }

}
