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
     * @param lineIndex The line number
     */
    void setLoopStart(int lineIndex) {
        if (this.loops.containsKey(lineIndex)) {
            throw new RuntimeException("Loops already defined");
        }

        this.loops.put(lineIndex, null);
    }

    /**
     * Stores the ending line of a loop
     * @param lineIndex The line number
     */
    void setLoopEnd(int lineIndex) {
        List<Integer> nonEndedLoops = this.getNonEndedLoops();

        Collections.reverse(nonEndedLoops);

        if (!nonEndedLoops.isEmpty()) {
            int loopToEnd = nonEndedLoops.get(0);
            this.loops.put(loopToEnd, lineIndex);
        } else {
            throw new RuntimeException("Loop does not exist");
        }
    }

    /**
     * Gets the starting line of a loop
     * @param endLineIndex The line number of the loop end
     * @return The line number of the start of the loop
     * @throws NullPointerException Loop does not exist
     */
    int getLoopStart(int endLineIndex) throws NullPointerException {
        for (Map.Entry<Integer, Integer> entry: this.loops.entrySet()) {
            if (entry.getValue().equals(endLineIndex)) {
                return entry.getKey();
            }
        }

        throw new NullPointerException("Loop does not exist");
    }

    /**
     * Gets the ending line of a loop
     * @param startLineIndex The line number of the loop end
     * @return The line number of the end of the loop
     */
    int getLoopEnd(int startLineIndex) {
        return this.loops.get(startLineIndex);
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
