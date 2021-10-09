package pl.conmir.cararchive.api.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RegistrationNumberValidatorTest {

    private final static RegistrationNumberValidator validator = new RegistrationNumberValidator();

    @DisplayName("Should return error list for incorrect registration number")
    @ParameterizedTest(name = "{index} -> For: \"{0}\" expected result: \"{1}\", error code: \"{2}\" ")
    @ArgumentsSource(TestSet.class)
    void shouldValidateRegistrationNumber(String argument, boolean expectedResult, List<ValidationError> expectedErrorCode){
        var errorList = validator.validate(argument);
        if (expectedResult){
            assertThat(errorList).isEmpty();
        } else {
            assertThat(errorList)
                    .containsAll(expectedErrorCode);
        }
    }

    static class TestSet implements ArgumentsProvider {

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

}