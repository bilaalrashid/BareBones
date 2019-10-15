import java.util.Map;

/**
 * Interprets the BareBones code
 */
class Interpreter {

    // Variables

    /**
     * The lines of code
     */
    private final String[] lines;

    /**
     * The interpreter memory
     */
    private Memory memory = new Memory();

    // Constructor

    /**
     * Creates a new interpreter
     * @param code The BareBones code to interpret
     */
    Interpreter(String code) {
        this.lines = this.splitCodeIntoLines(code);
    }

    // Public methods

    /**
     * Executes the code
     */
    Map<String, Integer> exec() {
        for (int i = 0; i < this.lines.length; i++) {
            String line = this.lines[i];
            int lineNumber = i + 1;

            try {
                Command command = Command.fromLine(line);

                switch (command) {
                    case SET:
                        this.runSetLine(line, lineNumber);
                        break;
                    case INCREMENT:
                        this.runIncrementLine(line, lineNumber);
                        break;
                    case DECREMENT:
                        break;
                    case WHILE:
                        break;
                    case END:
                        break;
                }
            } catch(Exception e) {
                ErrorHandler.crash(Error.INVALID_COMMAND, lineNumber);
            }
        }

        return this.memory.getAllVariables();
    }

    // Private methods

    /**
     * Parses the code into individual lines to be executed
     * @param code The raw code
     * @return The individual lines of code
     */
    private String[] splitCodeIntoLines(String code) {
        String removeWhitespace = "(?m)^[ \t]*\r?\n";
        code = code.replaceAll(removeWhitespace, "").trim();

        String[] lines = code.split(";");

        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].trim();
        }

        return lines;
    }

    /**
     * Runs a line of code with a set command
     * @param line The line of code
     * @param lineNumber The line number
     */
    private void runSetLine(String line, int lineNumber) {
        try {
            SimpleLine simpleLine = new SimpleLine(Command.SET, line);
            String variable = simpleLine.getVariable();

            try {
                this.memory.setVariable(variable, 0);
            } catch(Exception e) {
                ErrorHandler.crash(Error.REDEFINED_VARIABLE, lineNumber);
            }
        } catch(Exception e) {
            ErrorHandler.crash(Error.INVALID_SYNTAX, lineNumber);
        }
    }

    /**
     * Runs a line of code with an increment command
     * @param line The line of code
     * @param lineNumber The line number
     */
    private void runIncrementLine(String line, int lineNumber) {
        try {
            SimpleLine simpleLine = new SimpleLine(Command.INCREMENT, line);
            String variable = simpleLine.getVariable();

            try {
                this.memory.incrementVariable(variable, 1);
            } catch(Exception e) {
                ErrorHandler.crash(Error.UNDEFINED_VARIABLE, lineNumber);
            }
        } catch(Exception e) {
            ErrorHandler.crash(Error.INVALID_SYNTAX, lineNumber);
        }
    }

}
