package pl.conmir.cararchive;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pl.conmir.cararchive.originalModificationFile.FileName;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ModifiedFileNameTest {

    @ParameterizedTest
    @ArgumentsSource(FileNameTest.TestDataSet.class)
    void shouldCreateObject(String argument, boolean expectedResult){
        if (expectedResult){
            assertThatCode(() ->
                    ModifiedFileName.of(argument)
            ).doesNotThrowAnyException();
        } else {
            assertThatThrownBy(() ->
                    ModifiedFileName.of(argument)
            ).isInstanceOf(IllegalArgumentException.class);
        }
    }


    static class TestDataSet implements ArgumentsProvider {

        private final static boolean VALID = true;
        private final static boolean INVALID = false;

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    caseWith("", INVALID),
                    caseWith(" ", INVALID),
                    caseWith(null, INVALID),
                    caseWith("testFilename", VALID),
                    caseWith("test File name", INVALID),
                    caseWith("testF  ilename", INVALID)
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