/**
 * Interprets the BareBones code
 */
class Interpreter {

    /**
     * The lines of code
     */
    private final String[] lines;

    /**
     * Creates a new interpreter
     * @param code The BareBones code to interpret
     */
    Interpreter(String code) {
        this.lines = splitCodeIntoLines(code);
    }

    /**
     * Executes the code
     */
    void exec() {
        for (int i = 0; i < this.lines.length; i++) {
            String line = this.lines[i];

            try {
                Command command = Command.fromLine(line);
            } catch(Exception e) {
                int lineNumber = i + 1;
                ErrorHandler.crash(Error.INVALID_COMMAND, lineNumber);
            }
        }
    }

    /**
     * Parses the code into individual lines to be executed
     * @param code The raw code
     * @return The individual lines of code
     */
    private String[] splitCodeIntoLines(String code) {
        String removeWhitespace = "(?m)^[ \t]*\r?\n";
        code = code.replaceAll(removeWhitespace, "").trim();
        return code.split(";");
    }

}
