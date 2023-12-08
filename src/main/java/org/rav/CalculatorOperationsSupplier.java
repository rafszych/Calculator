package org.rav;

import java.util.List;

public interface CalculatorOperationsSupplier {

  List<Operation> readOperations(String source);
}
