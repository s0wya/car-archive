package pl.conmir.cararchive.car;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pl.conmir.cararchive.car.dto.CreateCarDto;
import pl.conmir.cararchive.exception.ValidationException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatorTest {

    private static Validator validator;

    @BeforeAll
    static void setUp(){
        validator = new Validator();
    }



    @DisplayName("Should throw exception for incorrect model in dto")
    @ParameterizedTest(name = "{index} -> For: \"{0}\" expected result: {1}")
    @ArgumentsSource(TestModelDataSet.class)
    void shouldThrowExceptionForModel(String argument, boolean expectedResult){
        if (expectedResult){
            var toTest = createCarDtoWithModel(argument);
            assertThatCode(() -> {
                validator.validate(toTest);
            }).doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() -> {
                var toTest = createCarDtoWithModel(argument);
                validator.validate(toTest);
            }).isInstanceOf(ValidationException.class);
        }
    }

    @DisplayName("Should throw exception for incorrect make in dto")
    @ParameterizedTest(name = "{index} -> For: \"{0}\" expected result: {1}")
    @ArgumentsSource(TestMakeDataSet.class)
    void shouldThrowExceptionForMake(String argument, boolean expectedResult){
        if (expectedResult){
            var toTest = createCarDtoWithMake(argument);
            assertThatCode(() -> {
                validator.validate(toTest);
            }).doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() -> {
                var toTest = createCarDtoWithMake(argument);
                validator.validate(toTest);
            }).isInstanceOf(ValidationException.class);
        }
    }

    @DisplayName("Should throw exception for incorrect registration number in dto")
    @ParameterizedTest(name = "{index} -> For: \"{0}\" expected result: {1}")
    @ArgumentsSource(TestRegistrationDataSet.class)
    void shouldThrowExceptionForRegistration(String argument, boolean expectedResult){
        if (expectedResult){
            var toTest = createCarDtoWithRegistration(argument);
            assertThatCode(() -> {
                validator.validate(toTest);
            }).doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() -> {
                var toTest = createCarDtoWithRegistration(argument);
                validator.validate(toTest);
            }).isInstanceOf(ValidationException.class);
        }
    }

    @DisplayName("Should throw exception for incorrect production year in dto")
    @ParameterizedTest(name = "{index} -> For: \"{0}\" expected result: {1}")
    @ArgumentsSource(TestYearDataSet.class)
    void shouldThrowExceptionForProductionYear(int argument, boolean expectedResult){
        if (expectedResult){
            var toTest = createCarDtoWithYear(argument);
            assertThatCode(() -> {
                validator.validate(toTest);
            }).doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() -> {
                var toTest = createCarDtoWithYear(argument);
                validator.validate(toTest);
            }).isInstanceOf(ValidationException.class);
        }
    }






    static class TestModelDataSet implements ArgumentsProvider {
        private final static boolean VALID = true;
        private final static boolean INVALID = false;

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith(
                            "",
                            INVALID
                    ),
                    caseWith(
                            " ",
                            INVALID
                    ),
                    caseWith(
                            "a di",
                            INVALID
                    ),
                    caseWith(
                            " udi",
                            INVALID
                    ),
                    caseWith(
                            "aud ",
                            INVALID
                    ),
                    caseWith(
                            "audi",
                            VALID
                    )
            );
        }

        private Arguments caseWith(String argument, boolean expectedResult){
            return Arguments.of(
                    argument,
                    expectedResult
            );
        }

    }

    static class TestMakeDataSet implements ArgumentsProvider {
        private final static boolean VALID = true;
        private final static boolean INVALID = false;

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith(
                            "",
                            INVALID
                    ),
                    caseWith(
                            " ",
                            INVALID
                    ),
                    caseWith(
                            "a di",
                            INVALID
                    ),
                    caseWith(
                            " udi",
                            INVALID
                    ),
                    caseWith(
                            "aud ",
                            INVALID
                    ),
                    caseWith(
                            "audi",
                            VALID
                    )
            );

        }
        private Arguments caseWith(String argument, boolean expectedResult){
            return Arguments.of(
                    argument,
                    expectedResult
            );
        }
    }

    static class TestRegistrationDataSet implements ArgumentsProvider {
        private final static boolean VALID = true;
        private final static boolean INVALID = false;

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith(
                            "",
                            INVALID
                    ),
                    caseWith(
                            "      ",
                            INVALID
                    ),
                    caseWith(
                            "12 124 ",
                            INVALID
                    ),
                    caseWith(
                            " 124f s",
                            INVALID
                    ),
                    caseWith(
                            "123fsf ",
                            INVALID
                    ),
                    caseWith(
                            "fawvwg3",
                            VALID
                    )
            );

        }
        private Arguments caseWith(String argument, boolean expectedResult){
            return Arguments.of(
                    argument,
                    expectedResult
            );
        }
    }

    static class TestYearDataSet implements ArgumentsProvider {
        private final static boolean VALID = true;
        private final static boolean INVALID = false;

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith(
                            123,
                            INVALID
                    ),
                    caseWith(
                            12,
                            INVALID
                    ),
                    caseWith(
                            1,
                            INVALID
                    ),
                    caseWith(
                            12345,
                            INVALID
                    ),
                    caseWith(
                            0,
                            INVALID
                    ),
                    caseWith(
                            1990,
                            VALID
                    )
            );

        }
        private Arguments caseWith(int argument, boolean expectedResult){
            return Arguments.of(
                    argument,
                    expectedResult
            );
        }
    }

    private CreateCarDto createCarDtoWithModel(String model){
        return CreateCarDto.builder()
                .model(model)
                .make("make")
                .registration("133f141")
                .year(1990)
                .build();
    }

    private CreateCarDto createCarDtoWithMake(String make){
        return CreateCarDto.builder()
                .model("model")
                .make(make)
                .registration("133f141")
                .year(1990)
                .build();
    }

    private CreateCarDto createCarDtoWithRegistration(String registration){
        return CreateCarDto.builder()
                .model("model")
                .make("make")
                .registration(registration)
                .year(1990)
                .build();
    }

    private CreateCarDto createCarDtoWithYear(int year){
        return CreateCarDto.builder()
                .model("model")
                .make("make")
                .registration("12f2tsr")
                .year(year)
                .build();
    }


}