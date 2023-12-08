package org.rav;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.rav.CalculatorOperation.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {

  @Mock CalculatorOperationsSupplier operationsSupplier;
  @InjectMocks Calculator calculator;

  @ParameterizedTest
  @MethodSource("provideCorrectOperations")
  void calculates(List<Operation> operations, double expectedResult) {
    // given
    when(operationsSupplier.readOperations(any())).thenReturn(operations);

    // when
    double result = calculator.calculateFromSource("sourceFile.txt");

    // then
    assertEquals(expectedResult, result);
  }

  static Stream<Arguments> provideCorrectOperations() {
    return Stream.of(
        Arguments.of(
            List.of(
                new Operation(add, 5.0),
                new Operation(divide, 5.0),
                new Operation(add, 3.0),
                new Operation(subtract, 1.0),
                new Operation(apply, 7.0)),
            4.4),
        Arguments.of(
            List.of(
                new Operation(multiply, 123.0),
                new Operation(subtract, 82.0),
                new Operation(apply, 31.42)),
            3782.66),
        Arguments.of(
            List.of(
                new Operation(divide, 3.8),
                new Operation(add, 1200.756),
                new Operation(subtract, 234.0),
                new Operation(apply, 456.123)),
            1086.788),
        Arguments.of(List.of(new Operation(apply, 8.0)), 8.0));
  }
}
