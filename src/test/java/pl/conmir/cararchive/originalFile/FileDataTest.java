package pl.conmir.cararchive.originalFile;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pl.conmir.cararchive.originalFile.FileData;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileDataTest {

    @ParameterizedTest
    @ArgumentsSource(TestDataSet.class)
    void shouldCreateObject(byte[] argument, boolean expectedResult){
        if (expectedResult){
            assertThatCode(() ->
                    FileData.of(argument)
            ).doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() ->
                    FileData.of(argument)
            ).isInstanceOf(IllegalArgumentException.class);
        }
    }

    static class TestDataSet implements ArgumentsProvider{

        private static final boolean VALID = true;
        private static final boolean INVALID = false;

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith(null, INVALID),
                    caseWith(new byte[0], INVALID),
                    caseWith(new byte[]{1, 2, 3, 4}, VALID)


            );
        }

        private Arguments caseWith(byte[] argument, boolean expectedResult){
            return Arguments.of(
                    argument,
                    expectedResult
            );
        }
    }

}