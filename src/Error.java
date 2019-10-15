/**
 * An error that can be found by the interpreter
 */
enum Error {

    // Values

    FILE_NOT_FOUND("The specified file has not been found"),
    FILE_CANNOT_BE_READ("The file cannot be read"),
    INVALID_COMMAND("The specified command is not defined"),
    REDEFINED_VARIABLE("The specified variable has already been defined"),
    UNDEFINED_VARIABLE("The specified variable does not exist"),
    REDEFINED_LOOP("The specified loop has already been defined"),
    UNDEFINED_LOOP("The specified loop does not exist"),
    INVALID_SYNTAX("Invalid syntax used");

    // Properties

    /**
     * The error message to be displayed
     */
    private final String message;

    // Constructor

    /**
     * Creates a new Error
     * @param message The error message to be displayed
     */
    Error(String message) {
        this.message = message;
    }

    // Getters

    /**
     * Gets the error message
     * @return The message to be displayed
     */
    String getMessage() {
        return this.message;
    }

}
