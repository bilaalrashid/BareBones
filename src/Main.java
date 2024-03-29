import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * The runs the BareBones interpreter
 */
public class Main {

    /**
     * Finds the code and runs the interpreter
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        if (args != null && args.length != 0) {
            Path path = Paths.get(args[0]);

            try {
                String code = Files.readString(path, StandardCharsets.UTF_8);
                Interpreter interpreter = new Interpreter(code);

                try {
                    Map<String, Integer> variables = interpreter.exec();
                    Console.write("Execution finished: " + variables);
                } catch(Exception e) {
                    ErrorHandler.crash(Error.INVALID_SYNTAX);
                }
            } catch(Exception e) {
                ErrorHandler.crash(Error.FILE_CANNOT_BE_READ);
            }
        } else {
            ErrorHandler.crash(Error.FILE_NOT_FOUND);
        }
    }

}
