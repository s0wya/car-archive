package pl.conmir.cararchive.api;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pl.conmir.cararchive.api.validator.*;
import pl.conmir.cararchive.domain.ProductionYear;
import pl.conmir.cararchive.dto.CreateCarDto;
import pl.conmir.cararchive.exception.ValidationException;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static pl.conmir.cararchive.api.validator.MakeValidator.Error.MAKE_BLANK;
import static pl.conmir.cararchive.api.validator.MakeValidator.Error.MAKE_WHITESPACES;
import static pl.conmir.cararchive.api.validator.ModelValidator.Error.MODEL_BLANK;
import static pl.conmir.cararchive.api.validator.RegistrationNumberValidator.Error.REGISTRATION_NUMBER_INCORRECT_FORMAT;
import static pl.conmir.cararchive.api.validator.YearValidator.Error.YEAR_INCORRECT_FORMAT;

class CarValidatorTest {

    private final MakeValidator makeValidator = new MakeValidator();
    private final ModelValidator modelValidator = new ModelValidator();
    private final YearValidator yearValidator = new YearValidator();
    private final RegistrationNumberValidator registrationNumberValidator = new RegistrationNumberValidator();

    private final CarValidator carValidator = new CarValidator(makeValidator, modelValidator, yearValidator, registrationNumberValidator);


    @DisplayName("Should throw exception for incorrect model in dto")
    @ParameterizedTest(name = "{index} -> For model:\"{0}\", make:\"{1}\", registration:\"{2}\", year:\"{3}\" " +
            "---> EXPECTED ERRORS: {4}")
    @ArgumentsSource(TestYearDataSet.class)
    void shouldThrowExceptionForModel(String modelArgument, String makeArgument, String registrationArgument,
                                      int yearArgument, List<ValidationError> expectedError) {
        if (expectedError.isEmpty()) {
            var toTest = createCarDto(modelArgument, makeArgument, registrationArgument, yearArgument);
            assertThatCode(() -> {
                carValidator.validate(toTest);
            }).doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() -> {
                var toTest = createCarDto(modelArgument, makeArgument, registrationArgument, yearArgument);
                carValidator.validate(toTest);
            }).isInstanceOf(ValidationException.class)
                    .extracting("errors",  InstanceOfAssertFactories.ITERABLE)
                    .containsAll(expectedError);
        }
    }

    static class TestYearDataSet implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith(" ",
                            " ",
                            "fasvw342",
                            111,
                            List.of(YEAR_INCORRECT_FORMAT, MAKE_BLANK, MODEL_BLANK, REGISTRATION_NUMBER_INCORRECT_FORMAT)
                    ),
                    caseWith("fasd",
                            "ad ud",
                            "fasvw342",
                            111,
                            List.of(YEAR_INCORRECT_FORMAT, MAKE_WHITESPACES, REGISTRATION_NUMBER_INCORRECT_FORMAT)
                    ),
                    caseWith("fdasf",
                            "audi",
                            "krs31fw",
                            2010,
                            List.of()
                    )
            );
        }

        private Arguments caseWith (String modelArgument, String makeArgument, String registrationArgument,
                                    int yearArgument, List<ValidationError> expectedError) {
            return Arguments.of(
                    modelArgument,
                    makeArgument,
                    registrationArgument,
                    yearArgument,
                    expectedError
            );
        }
    }

    private CreateCarDto createCarDto(String model, String make, String registration, int year) {
        return CreateCarDto.builder()
                .model(model)
                .make(make)
                .registration(registration)
                .year(year)
                .build();
    }

}