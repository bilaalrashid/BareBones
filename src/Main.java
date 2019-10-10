import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

/**
 * The BareBones interpreter
 */
public class Main {

    /**
     * Interprets the specified BareBones file
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        if (args != null && args.length != 0) {
            Path path = Paths.get(args[0]);

            try {
                String content = Files.readString(path, StandardCharsets.UTF_8);
            } catch(Exception e) {
                ErrorHandler.crash();
            }
        }

        ErrorHandler.crash();
    }

}
