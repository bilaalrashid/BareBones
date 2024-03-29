import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

/**
 * A line of code that uses a boolean condition
 */
class ConditionLine extends Line {

    // Properties

    /**
     * The primary item to compare in the condition
     */
    private SimpleEntry<String, Integer> primaryComparee;

    /**
     * The comparator to compare the two elements by
     */
    private Comparator comparator;

    /**
     * The secondary item to compare in the condition
     */
    private SimpleEntry<String, Integer> secondaryComparee;

    // Constructor

    /**
     * Creates a new ConditionLine
     * @param command The command used in the line
     * @param text The text of the line of code
     * @throws RuntimeException Invalid syntax
     */
    ConditionLine(int lineIndex, Command command, String text) throws RuntimeException {
        super(lineIndex, command, text);

        String[] components = text.split(" ");

        if (components.length == 5 && components[0].equals(command.getName())) {
            this.primaryComparee = getFormattedElement(components[1]);
            this.comparator = Comparator.fromLine(components[2]);
            this.secondaryComparee = getFormattedElement(components[3]);
        } else {
            throw new RuntimeException("Invalid syntax");
        }
    }

    // Public methods

    /**
     * Gets all of the variables that need to be fetched from memory
     * @return The variables to be submitted for evaluating the condition
     * @throws NullPointerException Key does not exist
     */
    String[] getVariablesToSubmit() throws NullPointerException {
        List<String> emptyVariables = new ArrayList<String>();

        String primaryKey = this.primaryComparee.getKey();

        if (primaryKey != null) {
            emptyVariables.add(primaryKey);
        }

        String secondaryKey = this.secondaryComparee.getKey();

        if (secondaryKey != null) {
            emptyVariables.add(secondaryKey);
        }

        String[] variablesToSubmit = new String[emptyVariables.size()];
        variablesToSubmit = emptyVariables.toArray(variablesToSubmit);
        return variablesToSubmit;
    }

    /**
     * Sets the value of a variable
     * @param variable The name of the variable
     * @param value The value of the variable
     * @throws NullPointerException Variable not found
     */
    void setVariable(String variable, Integer value) throws NullPointerException {
        if (this.primaryComparee.getKey().equals(variable)) {
            this.primaryComparee.setValue(value);
        } else if (this.secondaryComparee.getKey().equals(variable)) {
            this.secondaryComparee.setValue(value);
        } else {
            throw new NullPointerException("Variable not found");
        }
    }

    /**
     * Does the condition in the line of code evaluate to true
     * @return If the condition is true
     * @throws NullPointerException Values not set
     */
    boolean isConditionTrue() throws NullPointerException {
        Integer primaryValue = this.primaryComparee.getValue();
        Integer secondaryValue = this.secondaryComparee.getValue();

        switch (this.comparator) {
            case EQUALS:
                return primaryValue.equals(secondaryValue);
            case NOT_EQUALS:
                return !primaryValue.equals(secondaryValue);
            case GREATER_THAN:
                return primaryValue > secondaryValue;
            case LESS_THAN:
                return primaryValue < secondaryValue;
        }

        throw new NullPointerException("Invalid syntax");
    }

    // Private methods

    /**
     * Formats a comparee in the condition into a formatted element
     * @param value The value in the condition
     * @return The formatted element
     */
    private SimpleEntry<String, Integer> getFormattedElement(String value) {
        boolean isVariable = isVariable(value);

        if (isVariable) {
            return new SimpleEntry<String, Integer>(value, null);
        } else {
            return new SimpleEntry<String, Integer>(null, Integer.parseInt(value));
        }
    }

    /**
     * Determine if a comparee is a variable, otherwise a value
     * @param comparee The item to test
     * @return If the item is a variable, otherwise a value
     */
    private boolean isVariable(String comparee) {
        boolean isVariable = false;

        for (int i = 0; i < comparee.length(); i++) {
            char character = comparee.charAt(i);

            if (Character.isLetter(character)) {
                isVariable = true;
            }
        }

        return isVariable;
    }

}
