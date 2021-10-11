package pl.conmir.cararchive.api.validator;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pl.conmir.cararchive.dto.CreatePerformanceDto;
import pl.conmir.cararchive.exception.ValidationException;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static pl.conmir.cararchive.api.validator.PowerValidator.Error.POWER_LESS_THAN_ZERO;
import static pl.conmir.cararchive.api.validator.TorqueValidator.Error.TORQUE_LESS_THAN_ZERO;

class PerformanceModificationValidatorTest {

    private final PowerValidator powerValidator = new PowerValidator();
    private final TorqueValidator torqueValidator = new TorqueValidator();

    private final PerformanceValidator carValidator = new PerformanceValidator(powerValidator, torqueValidator);


    @DisplayName("Should throw exception for incorrect model in dto")
    @ParameterizedTest(name = "{index} -> For: \"{0}\" expected result: \"{1}\", error code: \"{2}\" ")
    @ArgumentsSource(TestData.class)
    void shouldThrowExceptionForModel(int powerArgument, int torqueArgument,
                                      List<ValidationException> expectedErrorCode) {
        if (expectedErrorCode.isEmpty()) {
            var toTest = createPerformanceDto(powerArgument, torqueArgument);
            assertThatCode(() -> {
                carValidator.validate(toTest);
            }).doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() -> {
                var toTest = createPerformanceDto(powerArgument, torqueArgument);
                carValidator.validate(toTest);
            }).isInstanceOf(ValidationException.class)
                    .extracting("errors",  InstanceOfAssertFactories.ITERABLE)
                    .containsAll(expectedErrorCode);
        }
    }

    static class TestData implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith(-100, -100, List.of(POWER_LESS_THAN_ZERO, TORQUE_LESS_THAN_ZERO)),
                    caseWith(100, -100, List.of(TORQUE_LESS_THAN_ZERO)),
                    caseWith(-100, 100, List.of(POWER_LESS_THAN_ZERO)),
                    caseWith(0, 0, List.of(TORQUE_LESS_THAN_ZERO, POWER_LESS_THAN_ZERO)),
                    caseWith(100, 100, List.of())
            );
        }

        private Arguments caseWith (int powerArgument, int torqueArgument, List<ValidationError> expectedError) {
            return Arguments.of(
                    powerArgument,
                    torqueArgument,
                    expectedError

            );
        }
    }


    private CreatePerformanceDto createPerformanceDto(int power, int torque){
        return CreatePerformanceDto.builder()
                .power(power)
                .torque(torque)
                .build();
    }

}