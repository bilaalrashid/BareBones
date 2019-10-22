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
            String line = this.lines[this.currentLineIndex];
            int nextLineIndex = this.currentLineIndex + 1;

            this.executeLine(line, this.currentLineIndex);

            // Checks a jump has not occurred
            if (this.currentLineIndex + 1 == nextLineIndex) {
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
    private String[] splitCodeIntoLines(String code) {
        String removeWhitespace = "(?m)^[ \t]*\r?\n";
        code = code.replaceAll(removeWhitespace, "").trim();

        String[] lines = code.split(";");

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            lines[i] = line.trim();

            try {
                Command command = Command.fromLine(line);

                if (command == Command.WHILE) {
                    this.markLoop(i);
                } else if (command == Command.END) {
                    this.markEnd(i);
                }
            } catch(Exception e) {
                ErrorHandler.crash(Error.INVALID_COMMAND, i);
            }
        }

        return lines;
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
     * @param lineIndex The number of the line
     */
    private void executeLine(String line, int lineIndex) {
        try {
            Command command = Command.fromLine(line);

            switch (command) {
                case SET:
                    this.runSetLine(line, lineIndex);
                    break;
                case INCREMENT:
                    this.runIncrementLine(line, lineIndex);
                    break;
                case DECREMENT:
                    this.runDecrementLine(line, lineIndex);
                    break;
                case WHILE:
                    this.runWhileLine(line, lineIndex);
                    break;
                case END:
                    this.runEndLine(lineIndex);
                    break;
            }
        } catch(Exception e) {
            ErrorHandler.crash(Error.INVALID_COMMAND, lineIndex);
        }
    }

    /**
     * Runs a line of code with a set command
     * @param line The line of code
     * @param lineIndex The line number
     */
    private void runSetLine(String line, int lineIndex) {
        try {
            SimpleLine simpleLine = new SimpleLine(Command.SET, line);
            String variable = simpleLine.getVariable();

            try {
                this.variableMemory.setVariable(variable, 0);
            } catch(Exception e) {
                ErrorHandler.crash(Error.REDEFINED_VARIABLE, lineIndex);
            }
        } catch(Exception e) {
            ErrorHandler.crash(Error.INVALID_SYNTAX, lineIndex);
        }
    }

    /**
     * Runs a line of code with an increment command
     * @param line The line of code
     * @param lineIndex The line number
     */
    private void runIncrementLine(String line, int lineIndex) {
        try {
            SimpleLine simpleLine = new SimpleLine(Command.INCREMENT, line);
            String variable = simpleLine.getVariable();

            try {
                this.variableMemory.increaseVariable(variable, 1);
            } catch(Exception e) {
                ErrorHandler.crash(Error.UNDEFINED_VARIABLE, lineIndex);
            }
        } catch(Exception e) {
            ErrorHandler.crash(Error.INVALID_SYNTAX, lineIndex);
        }
    }

    /**
     * Runs a line of code with an decrement command
     * @param line The line of code
     * @param lineIndex The line number
     */
    private void runDecrementLine(String line, int lineIndex) {
        try {
            SimpleLine simpleLine = new SimpleLine(Command.DECREMENT, line);
            String variable = simpleLine.getVariable();

            try {
                this.variableMemory.decreaseVariable(variable, 1);
            } catch(Exception e) {
                ErrorHandler.crash(Error.UNDEFINED_VARIABLE, lineIndex);
            }
        } catch(Exception e) {
            ErrorHandler.crash(Error.INVALID_SYNTAX, lineIndex);
        }
    }

    /**
     * Runs a line of code with a while command
     * @param lineIndex The line number
     */
    private void runWhileLine(String line, int lineIndex) {
        try {
            ConditionLine conditionLine = new ConditionLine(Command.WHILE, line);

            try {
                String[] variablesToSubmit = conditionLine.getVariablesToSubmit();

                for (String variable: variablesToSubmit) {
                    Integer value = this.variableMemory.getValueForVariable(variable);
                    conditionLine.setVariable(variable, value);
                }

                try {
                    if (!conditionLine.isConditionTrue()) {
                        // Skip to the first line after the end of the loop
                        this.currentLineIndex = this.loopMemory.getLoopEnd(lineIndex) + 1;
                    }
                } catch(Exception e) {
                    ErrorHandler.crash(Error.INVALID_SYNTAX, lineIndex);
                }
            } catch(Exception e) {
                ErrorHandler.crash(Error.UNDEFINED_VARIABLE, lineIndex);
            }
        } catch(Exception e) {
            ErrorHandler.crash(Error.INVALID_SYNTAX, lineIndex);
        }
    }

    /**
     * Runs a line of code with an end command
     * @param lineIndex The line number
     */
    private void runEndLine(int lineIndex) {
        this.currentLineIndex = this.loopMemory.getLoopStart(lineIndex);
    }

}
