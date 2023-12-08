package org.rav;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorIntegrationTest {

  Calculator calculator;

  @BeforeEach
  void setup() {
    calculator = new Calculator(new TextFileOperationsReader());
  }

  @Test
  void readsFile_andReturnsCorrectResult() {
    // when
    double result = calculator.calculateFromSource("correctOperations.txt");

    // then
    assertEquals(0.96, result);
  }
}
