package org.rav;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.util.Precision;

@RequiredArgsConstructor
public class Calculator {

  private final CalculatorOperationsSupplier operationsSupplier;
  private static final int ROUNDING_SCALE = 3;

  public double calculateFromSource(String source) {
    List<Operation> operations = operationsSupplier.readOperations(source);
    return performOperations(operations);
  }

  private double performOperations(List<Operation> operations) {
    // math operations are being performed starting from the value provided in the last operation
    double result = operations.get(operations.size() - 1).getVariable();

    for (Operation operation : operations) {
      double operationResult = doMath(operation, result);
      result = Precision.round(operationResult, ROUNDING_SCALE);
    }
    return result;
  }

  private double doMath(Operation operation, double a) {
    var operationType = operation.getCalculatorOperation();
    var b = operation.getVariable();

    return switch (operationType) {
      case add -> a + b;
      case subtract -> a - b;
      case multiply -> a * b;
      case divide -> a / b;
      case apply -> a;
    };
  }
}
