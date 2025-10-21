package linkedlist;

import org.junit.jupiter.api.Test;
import support.CycleInfo;
import support.LLNode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DetectCycleInfoTest  {
    private LLNode<Integer> build(int[] vals, int pos) {
        if (vals.length == 0) return null;
        LLNode<Integer> head = new LLNode<>(vals[0]);
        LLNode<Integer> tail = head, entry = (pos == 0 ? head : null);
        for (int i = 1; i < vals.length; i++) {
            tail.setLink(new LLNode<>(vals[i]));
            tail = tail.getLink();
            if (i == pos) entry = tail;
        }
        if (pos >= 0) tail.setLink(entry); // create cycle
        return head;
    }

    @Test void example1() {
        LLNode<Integer> head = build(new int[]{3,2,0,-4}, 1);
        CycleInfo info = LinkedListCycleAnalyzer.detectCycleInfo(head);
        assertEquals(1, info.entryIndex);
        assertEquals(3, info.cycleLength);
    }

    @Test void example2() {
        LLNode<Integer> head = build(new int[]{1,2}, 0);
        CycleInfo info = LinkedListCycleAnalyzer.detectCycleInfo(head);
        assertEquals(0, info.entryIndex);
        assertEquals(2, info.cycleLength);
    }

    @Test void example3_noCycle() {
        LLNode<Integer> head = build(new int[]{1}, -1);
        CycleInfo info = LinkedListCycleAnalyzer.detectCycleInfo(head);
        assertEquals(-1, info.entryIndex);
        assertEquals(0, info.cycleLength);
    }

    @Test void selfLoop() {
        LLNode<Integer> head = build(new int[]{7}, 0);
        CycleInfo info = LinkedListCycleAnalyzer.detectCycleInfo(head);
        assertEquals(0, info.entryIndex);
        assertEquals(1, info.cycleLength);
    }
}
