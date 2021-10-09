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
import static org.junit.jupiter.api.Assertions.*;

class YearValidatorTest {

    private final static YearValidator validator = new YearValidator();

    @DisplayName("Should return error list for incorrect year")
    @ParameterizedTest(name = "{index} -> For: \"{0}\" expected result: \"{1}\", error code: \"{2}\" ")
    @ArgumentsSource(TestSet.class)
    void shouldValidateYear(int argument, boolean expectedResult, List<ValidationError> expectedErrorCode){
        var errorList = validator.validate(argument);
        if (expectedResult){
            assertThat(errorList).isEmpty();
        } else {
            assertThat(errorList)
                    .isNotEmpty()
                    .containsAll(expectedErrorCode);
        }

    }

    static class TestSet implements ArgumentsProvider {

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

}