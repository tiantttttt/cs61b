package deque;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;

public class EaqualTest {
    @Test
    public void arrayEqualArray() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        ArrayDeque<Integer> b = new ArrayDeque<>();
        int ranAdd = StdRandom.uniform(500, 10000);
        int ranRem = StdRandom.uniform(0, 20);
        for (int i = 0; i < ranAdd; i++) {
            a.addFirst(i);
            b.addLast(ranAdd - 1 - i);
        }
        for (int i = 0; i < ranAdd; i++) {
            a.addLast(ranAdd - 1 - i);
            b.addFirst(i);
        }
        for (int i = 0; i < ranRem; i++) {
            a.removeLast();
            b.removeLast();
        }
        assertEquals(true, a.equals(b));
    }
    @Test
    public void arrayEqualLinkedList() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        LinkedListDeque<Integer> b = new LinkedListDeque<>();
        int ranAdd = StdRandom.uniform(50, 10000);
        int ranRem = StdRandom.uniform(0, 20);
        for (int i = 0; i < ranAdd; i++) {
            a.addFirst(i);
            b.addLast(ranAdd - 1 - i);
        }
        for (int i = 0; i < ranRem; i++) {
            a.removeLast();
            b.removeLast();
        }
        assertEquals(true, a.equals(b));
        assertEquals(true, b.equals(a));
    }
}
