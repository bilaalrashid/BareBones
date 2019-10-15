/**
 * Handles errors found by the interpreter
 */
class ErrorHandler {

    /**
     * Crashes the execution of the BareBones script
     * @param error The error that has been encountered
     */
    static void crash(Error error) {
        Console.write("Error encountered: " + error.getMessage());
        System.exit(0);
    }

    /**
     * Crashes the execution of the BareBones script
     * @param error The error that has been encountered
     * @param line The line number the error occured on
     */
    static void crash(Error error, int line) {
        Console.write("Error encountered on line " + line + ": " + error.getMessage());
        System.exit(0);
    }

}
