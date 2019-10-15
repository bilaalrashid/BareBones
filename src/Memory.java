import java.util.HashMap;
import java.util.Map;

/**
 * The interpreter memory
 */
class Memory {

    // Variables

    /**
     * The variables store
     */
    private Map<String, Integer> variables = new HashMap<String, Integer>();

    // Methods

    /**
     * Creates a variable with a set value
     * @param variable The name of the variable
     * @param value The value of the variable
     * @throws RuntimeException Variable is already set
     */
    void setVariable(String variable, int value) {
        if (this.variables.containsKey(variable)) {
            throw new RuntimeException("Variable is already set");
        }

        this.variables.put(variable, value);
    }

    /**
     * Increments a variable by a specified value
     * @param variable The name of the variable
     * @param increment The value to increment by
     * @throws RuntimeException Variable is not defined
     */
    void incrementVariable(String variable, int increment) {
        if (!this.variables.containsKey(variable)) {
            throw new RuntimeException("Variable is not defined");
        }

        int currentValue = this.variables.get(variable);
        int newValue = currentValue + increment;
        this.variables.put(variable, newValue);
    }

    /**
     * Gets all of the variables stored in memory
     * @return All of the variable names and values
     */
    Map<String, Integer> getAllVariables() {
        return this.variables;
    }

}
