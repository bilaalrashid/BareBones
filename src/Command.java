/**
 * A command keyword defined in the BareBones language
 */
enum Command {

    // Values

    SET("clear"),
    INCREMENT("incr"),
    DECREMENT("decr"),
    WHILE("while"),
    END("end");

    // Properties

    /**
     * The name of the command
     */
    private String name;

    // Constructor

    /**
     * Creates a new command
     * @param name The name of the command
     */
    Command(String name) {
        this.name = name;
    }

    // Getters

    /**
     * Gets the name of the command
     * @return The name of the command
     */
    public String getName() {
        return this.name;
    }

    // Static methods

    /**
     * Gets the command used in a specific line
     * @param line The line of code
     * @return The command used
     * @throws IllegalArgumentException A command was not found
     */
    public static Command fromLine(String line) {
        for (Command command : Command.values()) {
            if (line.contains(command.name)) {
                return command;
            }
        }

        throw new IllegalArgumentException("Command not found");
    }

}