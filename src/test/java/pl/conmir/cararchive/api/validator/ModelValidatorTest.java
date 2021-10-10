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
import static pl.conmir.cararchive.api.validator.ModelValidator.Error.MODEL_BLANK;
import static pl.conmir.cararchive.api.validator.ModelValidator.Error.MODEL_WHITESPACES;

class ModelValidatorTest {

    private final static ModelValidator validator = new ModelValidator();

    @DisplayName("Should return error list for incorrect model")
    @ParameterizedTest(name = "{index} -> For: \"{0}\" error code: \"{1}\" ")
    @ArgumentsSource(TestSet.class)
    void shouldValidateModel(String argument, List<ValidationError> expectedErrorCode){
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
                    caseWith("", List.of(MODEL_BLANK)),
                    caseWith(" ", List.of(MODEL_BLANK)),
                    caseWith("a di", List.of(MODEL_WHITESPACES)),
                    caseWith(" udi", List.of(MODEL_WHITESPACES)),
                    caseWith("aud ", List.of(MODEL_WHITESPACES)),
                    caseWith("audi", List.of())
            );
        }

        private Arguments caseWith (String argument, List<ValidationError> expectedError) {
            return Arguments.of(
                    argument,
                    expectedError

            );
        }

    }

}