package pl.conmir.cararchive;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;


import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ProductionYearTest {

    @ParameterizedTest
    @ArgumentsSource(TestDataSet.class)
    void shouldCreateObject(int argument, boolean expectedResult ){
        if (expectedResult){
            assertThatCode(() ->
                    ProductionYear.of(argument)
            ).doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() ->
                    ProductionYear.of(argument)
            ).isInstanceOf(IllegalArgumentException.class);
        }

    }


    static class TestDataSet implements ArgumentsProvider {
        private final static boolean VALID = true;
        private final static boolean INVALID = false;


        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith(1, INVALID),
                    caseWith(12, INVALID),
                    caseWith(123, INVALID),
                    caseWith(12345, INVALID),
                    caseWith(123456, INVALID),
                    caseWith(1234, VALID)
            );
        }

        private Arguments caseWith(int argument, boolean expectedResult){
            return Arguments.of(
                    argument,
                    expectedResult
            );
        }
    }

}