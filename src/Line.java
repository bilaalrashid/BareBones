/**
 * A line of code
 */
class Line {

    // Properties

    /**
     * The line number that the line exists on
     */
    private int lineIndex;

    /**
     * The command contained in the line
     */
    private Command command;

    /**
     * The full text of the line
     */
    private String text;

    // Constructor

    /**
     * Creates a new line
     * @param lineIndex The line number
     * @param command The command used in the line
     * @param text The text of the line
     */
    Line(int lineIndex, Command command, String text) {
        this.lineIndex = lineIndex;
        this.command = command;
        this.text = text;
    }

    // Getters

    /**
     * Gets the line number that the line is written on
     * @return The line number
     */
    int getLineIndex() {
        return this.lineIndex;
    }

    /**
     * Gets the command used in the line
     * @return The command used
     */
    Command getCommand() {
        return this.command;
    }

    /**
     * Gets the text of the line
     * @return The full text
     */
    String getText() {
        return this.text;
    }

    // Setters

    /**
     * Sets the line number for the line
     * @param lineIndex The new line number
     */
    void setLineIndex(int lineIndex) {
        this.lineIndex = lineIndex;
    }

    /**
     * Sets the command used in the line
     * @param command The new command
     */
    void setCommand(Command command) {
        this.command = command;
    }

    /**
     * Sets the text used in the line
     * @param text The text in the line
     */
    void setText(String text) {
        this.text = text;
    }

}
