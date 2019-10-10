/**
 * Handles errors found by the interpreter
 */
class ErrorHandler {

    /**
     * Crashes the execution of the BareBones script
     */
    static void crash(Error error) {
        System.out.println("************************");
        System.out.print("Error encountered: " + error.getMessage());
        System.exit(0);
    }

}
