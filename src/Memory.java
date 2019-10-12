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

}
