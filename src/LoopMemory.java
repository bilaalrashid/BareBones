import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.Set;
import java.util.ArrayList;

/**
 * The interpreter memory for loop indexes
 */
class LoopMemory {

    // Variables

    /**
     * The loop indexes store
     */
    private Map<Integer, Integer> loops = new HashMap<Integer, Integer>();

    // Public methods

    /**
     * Stores the starting line of a loop
     * @param lineNumber The line number
     */
    void setLoopStart(int lineNumber) {
        if (this.loops.containsKey(lineNumber)) {
            throw new RuntimeException("Loops already defined");
        }

        this.loops.put(lineNumber, null);
    }

    /**
     * Stores the ending line of a loop
     * @param lineNumber The line number
     */
    void setLoopEnd(int lineNumber) {
        List<Integer> nonEndedLoops = this.getNonEndedLoops();

        Collections.reverse(nonEndedLoops);

        if (!nonEndedLoops.isEmpty()) {
            int loopToEnd = nonEndedLoops.get(0);
            this.loops.put(loopToEnd, lineNumber);
        } else {
            throw new RuntimeException("Loop does not exist");
        }
    }

    // Private methods

    /**
     * Gets all loops that do not have a stored end line number
     * @return All non ended loops
     */
    private List<Integer> getNonEndedLoops() {
        Set<Integer> loopStarts = this.loops.keySet();
        List<Integer> nonEndedLoops = new ArrayList<Integer>();

        for (int start: loopStarts) {
            Integer loopEnd = this.loops.get(start);

            if (loopEnd == null) {
                nonEndedLoops.add(start);
            }
        }

        return nonEndedLoops;
    }

}
