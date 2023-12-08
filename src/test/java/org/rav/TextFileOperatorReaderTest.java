package org.rav;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.rav.CalculatorOperation.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TextFileOperatorReaderTest {

  TextFileOperationsReader reader;

  @BeforeEach
  void setup() {
    reader = new TextFileOperationsReader();
  }

  @Test
  void isAbleToRead_correctFiles() {
    // when
    List<Operation> operations = reader.readOperations("correctOperations.txt");

    // then
    assertOperations(operations);
  }

  private void assertOperations(List<Operation> operations) {
    assertEquals(6, operations.size());
    List<Operation> expectedOperations =
        List.of(
            new Operation(multiply, 24.3),
            new Operation(add, 71),
            new Operation(subtract, 29),
            new Operation(add, 842),
            new Operation(divide, 971.2),
            new Operation(apply, 2));

    assertThat(operations).containsExactlyElementsOf(expectedOperations);
  }

  @Test
  void endsReadingFile_atFirstEncounteredApply() {
    // given
    var expectedList = List.of(new Operation(add, 4.0), new Operation(apply, 7));

    // when
    List<Operation> operations = reader.readOperations("doubleApplyOperations.txt");

    // then
    assertEquals(2, operations.size());
    assertThat(operations).containsExactlyElementsOf(expectedList);
  }

  @ParameterizedTest
  @MethodSource("provideArgumentsForExcepionChecks")
  void throwsException_whenReadFile_hasUnsupportedOperation(Class exceptionClass, String fileName) {
    // expect
    assertThrows(exceptionClass, () -> reader.readOperations(fileName));
  }

  private static Stream<Arguments> provideArgumentsForExcepionChecks() {
    return Stream.of(
        Arguments.of(IllegalArgumentException.class, "unsupportedOperation.txt"),
        Arguments.of(IllegalArgumentException.class, "invalidLine.txt"),
        Arguments.of(IllegalArgumentException.class, "emptyFile.txt"),
        Arguments.of(NullPointerException.class, "nonExistingFile.txt"));
  }
}
