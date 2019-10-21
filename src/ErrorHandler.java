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
     * @param lineIndex The pointer to the line number the error occurred on
     */
    static void crash(Error error, int lineIndex) {
        Console.write("Error encountered on line " + (lineIndex+1) + ": " + error.getMessage());
        System.exit(0);
    }

}
