package support;

public final class CycleInfo {
    public final int entryIndex;   // 0-based index from head to entry node; -1 if no cycle
    public final int cycleLength;  // number of nodes in the cycle; 0 if no cycle

    public CycleInfo(int entryIndex, int cycleLength) {
        this.entryIndex = entryIndex;
        this.cycleLength = cycleLength;
    }
}
