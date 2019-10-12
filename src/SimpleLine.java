/**
 * A line of code that uses a simple command
 */
class SimpleLine {

    // Variables

    /**
     * The command contained in the line
     */
    private Command command;

    /**
     * The variable contained in the line
     */
    private String variable;

    // Constructor

    /**
     * Creates a new SimpleLine
     * @param command The command contained in the line
     * @param line The line of code
     */
    SimpleLine(Command command, String line) {
        this.command = command;
        this.variable = getVariableFromLine(command, line);
    }

    // Getters

    /**
     * Gets the command contained in the line
     * @return The command
     */
    Command getCommand() {
        return this.command;
    }

    /**
     * Gets the variable that the command acts on
     * @return The variable
     */
    String getVariable() {
        return this.variable;
    }

    // Private methods

    /**
     * Gets the variable from a simple line of code
     * @param command The command in the line
     * @param line The line of code
     * @return The variable
     */
    private String getVariableFromLine(Command command, String line) {
        String[] components = line.split(" ");

        if (components.length == 2 && components[0].equals(command.getName())) {
            return components[1];
        }

        throw new RuntimeException("Invalid syntax");
    }

}
