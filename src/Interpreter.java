import java.util.Map;

/**
 * Interprets the BareBones code
 */
class Interpreter {

    // Variables

    /**
     * The lines of code
     */
    private final Line[] lines;

    /**
     * The interpreter memory for storing variables
     */
    private VariableMemory variableMemory = new VariableMemory();

    /**
     * The interpreter memory for storing loops
     */
    private LoopMemory loopMemory = new LoopMemory();

    /**
     * The index of the current line to execute
     */
    private int currentLineIndex = 0;

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
        while (this.currentLineIndex < this.lines.length) {
            Line line = this.lines[this.currentLineIndex];
            int currentLine = this.currentLineIndex;

            this.executeLine(line);

            // Checks a jump has not occurred
            if (this.currentLineIndex == currentLine) {
                this.currentLineIndex += 1;
            }
        }

        return this.variableMemory.getAllVariables();
    }

    // Private methods

    /**
     * Parses the code into individual lines to be executed
     * @param code The raw code
     * @return The individual lines of code
     */
    private Line[] splitCodeIntoLines(String code) {
        String removeWhitespace = "(?m)^[ \t]*\r?\n";
        code = code.replaceAll(removeWhitespace, "").trim();

        String[] lines = code.split(";");
        Line[] parsedLines = new Line[lines.length];

        for (int i = 0; i < lines.length; i++) {
            String lineContent = lines[i];

            try {
                Command command = Command.fromLine(lineContent);

                switch (command) {
                    case SET:
                    case INCREMENT:
                    case DECREMENT:
                        parsedLines[i] = new SimpleLine(i, command, lineContent.trim());
                        break;
                    case WHILE:
                        parsedLines[i] = new ConditionLine(i, command, lineContent.trim());
                        this.markLoop(i);
                        break;
                    case END:
                        parsedLines[i] = new Line(i, command, lineContent.trim());
                        this.markEnd(i);
                        break;
                }
            } catch(Exception e) {
                ErrorHandler.crash(Error.INVALID_COMMAND, i);
            }
        }

        return parsedLines;
    }

    /**
     * Marks the start of a loop
     * @param lineIndex The line number of the starting line
     */
    private void markLoop(int lineIndex) {
        try {
            this.loopMemory.setLoopStart(lineIndex);
        } catch(Exception e) {
            ErrorHandler.crash(Error.REDEFINED_LOOP, lineIndex);
        }
    }

    /**
     * Marks the end of a loop
     * @param lineIndex The line number of the ending line
     */
    private void markEnd(int lineIndex) {
        try {
            this.loopMemory.setLoopEnd(lineIndex);
        } catch(Exception e) {
            ErrorHandler.crash(Error.UNDEFINED_LOOP, lineIndex);
        }
    }

    /**
     * Executes an individual line of code
     * @param line The line of code
     */
    private void executeLine(Line line) {
        switch (line.getCommand()) {
            case SET:
                this.runSetLine((SimpleLine) line);
                break;
            case INCREMENT:
                this.runIncrementLine((SimpleLine) line);
                break;
            case DECREMENT:
                this.runDecrementLine((SimpleLine) line);
                break;
            case WHILE:
                this.runWhileLine((ConditionLine) line);
                break;
            case END:
                this.runEndLine(line);
                break;
        }
    }

    /**
     * Runs a line of code with a set command
     * @param line The line of code
     */
    private void runSetLine(SimpleLine line) {
        String variable = line.getVariable();

        try {
            this.variableMemory.setVariable(variable, 0);
        } catch(Exception e) {
            ErrorHandler.crash(Error.REDEFINED_VARIABLE, line.getLineIndex());
        }
    }

    /**
     * Runs a line of code with an increment command
     * @param line The line of code
     */
    private void runIncrementLine(SimpleLine line) {
        String variable = line.getVariable();

        try {
            this.variableMemory.increaseVariable(variable, 1);
        } catch(Exception e) {
            ErrorHandler.crash(Error.UNDEFINED_VARIABLE, line.getLineIndex());
        }
    }

    /**
     * Runs a line of code with an decrement command
     * @param line The line of code
     */
    private void runDecrementLine(SimpleLine line) {
        String variable = line.getVariable();

        try {
            this.variableMemory.decreaseVariable(variable, 1);
        } catch(Exception e) {
            ErrorHandler.crash(Error.UNDEFINED_VARIABLE, line.getLineIndex());
        }
    }

    /**
     * Runs a line of code with a while command
     * @param line The line of code
     */
    private void runWhileLine(ConditionLine line) {
        try {
            String[] variablesToSubmit = line.getVariablesToSubmit();

            for (String variable: variablesToSubmit) {
                Integer value = this.variableMemory.getValueForVariable(variable);
                line.setVariable(variable, value);
            }

            try {
                if (!line.isConditionTrue()) {
                    // Skip to the first line after the end of the loop
                    this.currentLineIndex = this.loopMemory.getLoopEnd(line.getLineIndex()) + 1;
                }
            } catch(Exception e) {
                ErrorHandler.crash(Error.INVALID_SYNTAX, line.getLineIndex());
            }
        } catch(Exception e) {
            ErrorHandler.crash(Error.UNDEFINED_VARIABLE, line.getLineIndex());
        }
    }

    /**
     * Runs a line of code with an end command
     * @param line The line of code
     */
    private void runEndLine(Line line) {
        this.currentLineIndex = this.loopMemory.getLoopStart(line.getLineIndex());
    }

}
