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

class ModelValidatorTest {

    private final static ModelValidator validator = new ModelValidator();

    @DisplayName("Should return error list for incorrect model")
    @ParameterizedTest(name = "{index} -> For: \"{0}\" expected result: \"{1}\", error code: \"{2}\" ")
    @ArgumentsSource(TestSet.class)
    void shouldValidateModel(String argument, boolean expectedResult, List<ValidationError> expectedErrorCode){
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

}