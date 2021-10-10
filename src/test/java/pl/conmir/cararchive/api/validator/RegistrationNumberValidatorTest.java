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
import static pl.conmir.cararchive.api.validator.RegistrationNumberValidator.Error.*;

class RegistrationNumberValidatorTest {

    private final static RegistrationNumberValidator validator = new RegistrationNumberValidator();

    @DisplayName("Should return error list for incorrect registration number")
    @ParameterizedTest(name = "{index} -> For: \"{0}\", error code: \"{1}\" ")
    @ArgumentsSource(TestSet.class)
    void shouldValidateRegistrationNumber(String argument, List<ValidationError> expectedErrorCode){
        var errorList = validator.validate(argument);
        if (expectedErrorCode.isEmpty()){
            assertThat(errorList).isEmpty();
        } else {
            assertThat(errorList)
                    .containsAll(expectedErrorCode);
        }
    }

    static class TestSet implements ArgumentsProvider {


        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith("", List.of(REGISTRATION_NUMBER_BLANK, REGISTRATION_NUMBER_INCORRECT_FORMAT)),
                    caseWith("      ", List.of(REGISTRATION_NUMBER_WHITESPACES, REGISTRATION_NUMBER_INCORRECT_FORMAT, REGISTRATION_NUMBER_BLANK)),
                    caseWith("12 124 ", List.of(REGISTRATION_NUMBER_WHITESPACES)),
                    caseWith(" 124f s", List.of(REGISTRATION_NUMBER_WHITESPACES)),
                    caseWith("123fsf32d", List.of(REGISTRATION_NUMBER_INCORRECT_FORMAT)),
                    caseWith("123fd", List.of(REGISTRATION_NUMBER_INCORRECT_FORMAT)),
                    caseWith("fawvwg3", List.of())
            );
        }

        private Arguments caseWith (String argument, List<ValidationError> expectedErrors) {
            return Arguments.of(
                    argument,
                    expectedErrors

            );
        }

    }

}