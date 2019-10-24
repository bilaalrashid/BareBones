/**
 * A comparator in a boolean expression defined in the BareBones language
 */
enum Comparator {

    // Values

    EQUALS("is"),
    NOT_EQUALS("not"),
    GREATER_THAN("above"),
    LESS_THAN("under");

    // Properties

    /**
     * The name of the comparator
     */
    private String name;

    // Constructor

    /**
     * Creates a new comparator
     * @param name The name of the comparator
     */
    Comparator(String name) {
        this.name = name;
    }

    // Getters

    /**
     * Gets the name of the comparator
     * @return The name of the comparator
     */
    public String getName() {
        return this.name;
    }

    // Static methods

    /**
     * Gets the comparator used in a specific line
     * @param line The line of code
     * @return The comparator used
     * @throws IllegalArgumentException A comparator was not found
     */
    public static Comparator fromLine(String line) throws IllegalArgumentException {
        for (Comparator comparator : Comparator.values()) {
            if (line.contains(comparator.name)) {
                return comparator;
            }
        }

        throw new IllegalArgumentException("Comparator not found");
    }

}