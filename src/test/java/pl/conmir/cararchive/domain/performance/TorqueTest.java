package pl.conmir.cararchive.domain.performance;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class TorqueTest {

    @ParameterizedTest
    @ArgumentsSource(TestDataSet.class)
    void shouldCreateObject(int argument, boolean expectedResult ){
        if (expectedResult){
            assertThatCode(() ->
                    Torque.of(argument)
            ).doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() ->
                    Torque.of(argument)
            ).isInstanceOf(IllegalArgumentException.class);
        }

    }


    static class TestDataSet implements ArgumentsProvider {
        private final static boolean VALID = true;
        private final static boolean INVALID = false;

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith(1, VALID),
                    caseWith(10000, VALID),
                    caseWith(-100, INVALID),
                    caseWith(0, INVALID)
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