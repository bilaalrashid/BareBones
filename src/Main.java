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
            String path = args[0];
        }

        ErrorHandler.crash();
    }

}
