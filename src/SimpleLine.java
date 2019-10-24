/**
 * A line of code that uses a simple command
 */
class SimpleLine extends Line {

    // Properties

    /**
     * The variable contained in the line
     */
    private String variable;

    // Constructor

    /**
     * Creates a new SimpleLine
     * @param lineIndex The line number
     * @param command The command used in the line
     * @param text The text of the line
     * @throws RuntimeException Invalid syntax
     */
    SimpleLine(int lineIndex, Command command, String text) throws RuntimeException {
        super(lineIndex, command, text);

        this.variable = getVariableFromLine(command, text);
    }

    // Getters

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
