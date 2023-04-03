package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */



public class RandomLinkedList {
    @Test
    public void randomLinkedResizeNull() {
        LinkedListDeque<Integer> a = new LinkedListDeque<>();
        for (int i = 0; i < 20000; i++) {
            int opNum = StdRandom.uniform(0, 4);
            if (opNum == 0) {
                a.addLast(i);
                int aindex = a.size() - 1;
                int alast = a.get(aindex);
                assertEquals(i, alast);
            } else if (opNum == 1) {
                a.addFirst(i);
                int afirst = a.get(0);
                assertEquals(i, afirst);
            } else if (opNum == 2) {
                a.removeFirst();
            } else {
                a.removeLast();
            }
        }
    }
}
