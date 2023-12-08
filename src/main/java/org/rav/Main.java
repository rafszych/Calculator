package org.rav;

public class Main {
  public static void main(String[] args) {
    CalculatorOperationsSupplier fileReader = new TextFileOperationsReader();
    Calculator calculator = new Calculator(fileReader);

    if (args.length > 0) {
      System.out.println(calculator.calculateFromSource(args[0]));
    } else {
      // expected value: -40.0
      System.out.println(calculator.calculateFromSource("exampleOperations.txt"));
    }
  }
}
