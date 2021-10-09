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

class CarValidatorTest {

    private final MakeValidator makeValidator = new MakeValidator();
    private final ModelValidator modelValidator = new ModelValidator();
    private final YearValidator yearValidator = new YearValidator();
    private final RegistrationNumberValidator registrationNumberValidator = new RegistrationNumberValidator();

    private final CarValidator carValidator = new CarValidator(makeValidator, modelValidator, yearValidator, registrationNumberValidator);


    @DisplayName("Should throw exception for incorrect model in dto")
    @ParameterizedTest(name = "{index} -> For: \"{0}\" expected result: \"{1}\", error code: \"{2}\" ")
    @ArgumentsSource(TestModelDataSet.class)
    void shouldThrowExceptionForModel(String argument, boolean expectedResult, List<ValidationException> expectedErrorCode) {
        if (expectedResult) {
            var toTest = createCarDtoWithModel(argument);
            assertThatCode(() -> {
                carValidator.validate(toTest);
            }).doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() -> {
                var toTest = createCarDtoWithModel(argument);
                carValidator.validate(toTest);
            }).isInstanceOf(ValidationException.class)
                    .extracting("errors",  InstanceOfAssertFactories.ITERABLE)
                    .containsAll(expectedErrorCode);
        }
    }

    @DisplayName("Should throw exception for incorrect make in dto")
    @ParameterizedTest(name = "{index} -> For: \"{0}\" expected result: \"{1}\", error code: \"{2}\" ")
    @ArgumentsSource(TestMakeDataSet.class)
    void shouldThrowExceptionForMake(String argument, boolean expectedResult, List<ValidationException> expectedErrorCode) {
        if (expectedResult) {
            var toTest = createCarDtoWithMake(argument);
            assertThatCode(() -> {
                carValidator.validate(toTest);
            }).doesNotThrowAnyException();
        } else {
            var thrownException = assertThatThrownBy(() -> {
                var toTest = createCarDtoWithMake(argument);
                carValidator.validate(toTest);
            }).isInstanceOf(ValidationException.class)
                    .extracting("errors",  InstanceOfAssertFactories.ITERABLE)
                    .containsAll(expectedErrorCode);


        }
    }


    @DisplayName("Should throw exception for incorrect registration number in dto")
    @ParameterizedTest(name = "{index} -> For: \"{0}\" expected result: \"{1}\", error code: \"{2}\" ")
    @ArgumentsSource(TestRegistrationDataSet.class)
    void shouldThrowExceptionForRegistration(String argument, boolean expectedResult, List<ValidationError> expectedErrorCode) {
        if (expectedResult) {
            var toTest = createCarDtoWithRegistration(argument);
            assertThatCode(() -> {
                carValidator.validate(toTest);
            }).doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() -> {
                var toTest = createCarDtoWithRegistration(argument);
                carValidator.validate(toTest);
            }).isInstanceOf(ValidationException.class)
                    .extracting("errors",  InstanceOfAssertFactories.ITERABLE)
                    .containsAll(expectedErrorCode);
        }
    }

    @DisplayName("Should throw exception for incorrect production year in dto")
    @ParameterizedTest(name = "{index} -> For: \"{0}\" expected result: \"{1}\", error code: \"{2}\" ")
    @ArgumentsSource(TestYearDataSet.class)
    void shouldThrowExceptionForProductionYear(int argument, boolean expectedResult, List<ValidationError> expectedErrorCode) {
        if (expectedResult) {
            var toTest = createCarDtoWithYear(argument);
            assertThatCode(() -> {
                carValidator.validate(toTest);
            }).doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() -> {
                var toTest = createCarDtoWithYear(argument);
                carValidator.validate(toTest);
            }).isInstanceOf(ValidationException.class)
                    .extracting("errors",  InstanceOfAssertFactories.ITERABLE)
                    .containsExactlyElementsOf(expectedErrorCode);

        }
    }

    static class TestModelDataSet implements ArgumentsProvider {
        private static final boolean VALID = true;
        private static final boolean INVALID = false;
        private static final ValidationError MODEL_BLANK = ModelValidator.Error.MODEL_BLANK;
        private static final ValidationError MODEL_WHITESPACES = ModelValidator.Error.MODEL_WHITESPACES;

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith("", INVALID, List.of(MODEL_BLANK)),
                    caseWith(" ", INVALID, List.of(MODEL_BLANK)),
                    caseWith("a di", INVALID, List.of(MODEL_WHITESPACES)),
                    caseWith(" udi", INVALID, List.of(MODEL_WHITESPACES)),
                    caseWith("aud ", INVALID, List.of(MODEL_WHITESPACES)),
                    caseWith("audi", VALID, List.of())
            );
        }

        private Arguments caseWith (String argument, boolean expectedResult, List<ValidationError> expectedError) {
            return Arguments.of(
                    argument,
                    expectedResult,
                    expectedError

            );
        }
    }

    static class TestMakeDataSet implements ArgumentsProvider {
        private static final boolean VALID = true;
        private static final boolean INVALID = false;
        private static final ValidationError MAKE_BLANK = MakeValidator.Error.MAKE_BLANK;
        private static final ValidationError MAKE_WHITESPACES = MakeValidator.Error.MAKE_WHITESPACES;

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith("", INVALID, List.of(MAKE_BLANK)),
                    caseWith(" ", INVALID, List.of(MAKE_BLANK)),
                    caseWith("a di", INVALID, List.of(MAKE_WHITESPACES)),
                    caseWith(" udi", INVALID, List.of(MAKE_WHITESPACES)),
                    caseWith("aud ", INVALID, List.of(MAKE_WHITESPACES)),
                    caseWith("audi", VALID, List.of())
            );
        }

        private Arguments caseWith (String argument, boolean expectedResult, List<ValidationError> expectedError) {
            return Arguments.of(
                    argument,
                    expectedResult,
                    expectedError

            );
        }
    }

    static class TestRegistrationDataSet implements ArgumentsProvider {
        private static final boolean VALID = true;
        private static final boolean INVALID = false;
        private static final ValidationError REGISTRATION_NUMBER_BLANK = RegistrationNumberValidator.Error.REGISTRATION_NUMBER_BLANK;
        private static final ValidationError REGISTRATION_NUMBER_WHITESPACES = RegistrationNumberValidator.Error.REGISTRATION_NUMBER_WHITESPACES;
        private static final ValidationError REGISTRATION_NUMBER_INCORRECT_FORMAT = RegistrationNumberValidator.Error.REGISTRATION_NUMBER_INCORRECT_FORMAT;

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith("", INVALID, List.of(REGISTRATION_NUMBER_BLANK, REGISTRATION_NUMBER_INCORRECT_FORMAT)),
                    caseWith("      ", INVALID, List.of(REGISTRATION_NUMBER_WHITESPACES, REGISTRATION_NUMBER_INCORRECT_FORMAT, REGISTRATION_NUMBER_BLANK)),
                    caseWith("12 124 ", INVALID, List.of(REGISTRATION_NUMBER_WHITESPACES)),
                    caseWith(" 124f s", INVALID, List.of(REGISTRATION_NUMBER_WHITESPACES)),
                    caseWith("123fsf32d", INVALID, List.of(REGISTRATION_NUMBER_INCORRECT_FORMAT)),
                    caseWith("123fd", INVALID, List.of(REGISTRATION_NUMBER_INCORRECT_FORMAT)),
                    caseWith("fawvwg3", VALID, List.of())
            );
        }

        private Arguments caseWith (String argument, boolean expectedResult, List<ValidationError> expectedErrors) {
            return Arguments.of(
                    argument,
                    expectedResult,
                    expectedErrors

            );
        }
    }

    static class TestYearDataSet implements ArgumentsProvider {
        private static final boolean VALID = true;
        private static final boolean INVALID = false;
        private static final ValidationError YEAR_INCORRECT_FORMAT = YearValidator.Error.YEAR_INCORRECT_FORMAT;

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith(123, INVALID, List.of(YEAR_INCORRECT_FORMAT)),
                    caseWith(12, INVALID, List.of(YEAR_INCORRECT_FORMAT)),
                    caseWith(1, INVALID, List.of(YEAR_INCORRECT_FORMAT)),
                    caseWith(12345, INVALID, List.of(YEAR_INCORRECT_FORMAT)),
                    caseWith(0, INVALID, List.of(YEAR_INCORRECT_FORMAT)),
                    caseWith(1990, VALID, List.of())
            );
        }

        private Arguments caseWith (int argument, boolean expectedResult, List<ValidationError> expectedError) {
            return Arguments.of(
                    argument,
                    expectedResult,
                    expectedError

            );
        }
    }

    private CreateCarDto createCarDtoWithModel(String model) {
        return CreateCarDto.builder()
                .model(model)
                .make("make")
                .registration("133f141")
                .year(1990)
                .build();
    }

    private CreateCarDto createCarDtoWithMake(String make) {
        return CreateCarDto.builder()
                .model("model")
                .make(make)
                .registration("133f141")
                .year(1990)
                .build();
    }

    private CreateCarDto createCarDtoWithRegistration(String registration) {
        return CreateCarDto.builder()
                .model("model")
                .make("make")
                .registration(registration)
                .year(1990)
                .build();
    }

    private CreateCarDto createCarDtoWithYear(int year) {
        return CreateCarDto.builder()
                .model("model")
                .make("make")
                .registration("12f2tsr")
                .year(year)
                .build();
    }
}