package pl.conmir.cararchive.car;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class RegistrationNumberTest {

    @ParameterizedTest
    @ArgumentsSource(TestDataSet.class)
    void shouldCreateObject(String argument, boolean expectedResult ){
        if (expectedResult){
            assertThatCode(() ->
                    RegistrationNumber.of(argument)
            ).doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() ->
                    RegistrationNumber.of(argument)
            ).isInstanceOf(IllegalArgumentException.class);
        }

    }


    static class TestDataSet implements ArgumentsProvider{
        private final static boolean VALID = true;
        private final static boolean INVALID = false;


        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith("", INVALID),
                    caseWith("       ", INVALID),
                    caseWith(" 131fsf", INVALID),
                    caseWith("fs  afw", INVALID),
                    caseWith("3fa4fw ", INVALID),
                    caseWith(null, INVALID),
                    caseWith("fwf2gsc", VALID)
                    );
        }

        private Arguments caseWith(String argument, boolean expectedResult){
            return Arguments.of(
                    argument,
                    expectedResult
            );
        }
    }
}