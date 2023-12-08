package org.rav;

import static org.rav.CalculatorOperation.apply;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TextFileOperationsReader implements CalculatorOperationsSupplier {

  private static final String OPERATION_AND_VARIABLE_DIVIDER = " ";

  @Override
  public List<Operation> readOperations(String source) {
    List<Operation> operations = new ArrayList<>();

    // first get the InputStream for the specified file
    try (InputStream inputFile =
        TextFileOperationsReader.class.getClassLoader().getResourceAsStream(source)) {

      // then use it to open BufferedReader
      // TODO better handle nullPointerException when file is not existing
      try (BufferedReader reader =
          new BufferedReader(new InputStreamReader(inputFile, StandardCharsets.UTF_8))) {

        Operation operation = new Operation();
        String line = reader.readLine();

        // read whole file or until the operation in the last line was 'apply'
        while ((line != null) && (operation.getCalculatorOperation() != apply)) {
          operation = translateLineToOperation(line);
          operations.add(operation);
          line = reader.readLine();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    verifyOperations(operations);
    return operations;
  }

  private Operation translateLineToOperation(String line) {
    String[] words = extractWords(line);

    var mathOperation = CalculatorOperation.valueOf(words[0]);
    var mathVariable = Double.parseDouble(words[1]);
    return new Operation(mathOperation, mathVariable);
  }

  private String[] extractWords(String line) {
    String[] words = line.trim().split(OPERATION_AND_VARIABLE_DIVIDER);
    if (words.length != 2) {
      throw new IllegalArgumentException(
          "Each line in source file must contain only operation keyword and a variable");
    }

    return words;
  }

  private void verifyOperations(List<Operation> operations) {
    if (operations.isEmpty()) {
      throw new IllegalArgumentException(
          "File did not contain any mathematical operations to be performed");
    }
    // TODO more checks for input can be done here like verifying at least one apply operation
  }
}
