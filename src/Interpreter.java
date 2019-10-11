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
     * Parses the code into individual lines to be executed
     * @param code The raw code
     * @return The individual lines of code
     */
    private String[] splitCodeIntoLines(String code) {
        String[] lines = code.split(";");

        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].trim();
        }

        return lines;
    }

}
