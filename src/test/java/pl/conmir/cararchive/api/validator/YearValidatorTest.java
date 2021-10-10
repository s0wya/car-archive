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
import static pl.conmir.cararchive.api.validator.YearValidator.Error.YEAR_INCORRECT_FORMAT;

class YearValidatorTest {

    private final static YearValidator validator = new YearValidator();

    @DisplayName("Should return error list for incorrect year")
    @ParameterizedTest(name = "{index} -> For: \"{0}\" expected result: \"{1}\", error code: \"{2}\" ")
    @ArgumentsSource(TestSet.class)
    void shouldValidateYear(int argument, List<ValidationError> expectedErrorCode){
        var errorList = validator.validate(argument);
        if (expectedErrorCode.isEmpty()){
            assertThat(errorList).isEmpty();
        } else {
            assertThat(errorList)
                    .isNotEmpty()
                    .containsAll(expectedErrorCode);
        }

    }

    static class TestSet implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith(123, List.of(YEAR_INCORRECT_FORMAT)),
                    caseWith(12, List.of(YEAR_INCORRECT_FORMAT)),
                    caseWith(1, List.of(YEAR_INCORRECT_FORMAT)),
                    caseWith(12345, List.of(YEAR_INCORRECT_FORMAT)),
                    caseWith(0, List.of(YEAR_INCORRECT_FORMAT)),
                    caseWith(1990, List.of())
            );
        }

        private Arguments caseWith (int argument, List<ValidationError> expectedError) {
            return Arguments.of(
                    argument,
                    expectedError

            );
        }

    }

}