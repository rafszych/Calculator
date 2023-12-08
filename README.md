# Calculator
Project created for a recruitment process.
Basic functionality of calculator supporting add, subtract, multiply and divide operations.

The operations are being loaded from the file and the result is printed out in the console.

In order to run the project, either run Main.main() from IDE or create a jar file and then run it.

To create jar and run it from the command line:

1. cd into Calculator folder.
2. run command: mvn clean package (requires maven)
3. the jar file has been created in the target folder
4. run it using command: java -jar target/Calculator-0.0.1-SNAPSHOT.jar exampleOperations.txt

To run calculator with other operations either change the exampleOperations.txt file in Calculator folder or change the argument for command above to direct to other file.
