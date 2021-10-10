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
import static pl.conmir.cararchive.api.validator.PowerValidator.*;
import static pl.conmir.cararchive.api.validator.PowerValidator.Error.POWER_LESS_THAN_ZERO;

class PowerValidatorTest {

    private final static PowerValidator validator = new PowerValidator();

    @DisplayName("Should return error list for incorrect model")
    @ParameterizedTest(name = "{index} -> For: \"{0}\" expected result: \"{1}\", error code: \"{2}\" ")
    @ArgumentsSource(TestSet.class)
    void shouldValidateMake(int argument, List<ValidationError> expectedErrorCode){
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
                    caseWith(0 , List.of(POWER_LESS_THAN_ZERO)),
                    caseWith(-1 , List.of(POWER_LESS_THAN_ZERO)),
                    caseWith(-1200 , List.of(POWER_LESS_THAN_ZERO)),
                    caseWith(1 , List.of()),
                    caseWith(1200 , List.of())
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