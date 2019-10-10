/**
 * An error that can be found by the interpreter
 */
enum Error {

    // Values

    FILE_NOT_FOUND("The specified file has not been found"),
    FILE_CANNOT_BE_READ("The file cannot be read");

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
