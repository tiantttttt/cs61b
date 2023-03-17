package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */



public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        BuggyAList<Integer> a = new BuggyAList<>();
        AListNoResizing<Integer> b = new AListNoResizing<>();
        for (int i = 4; i < 7; i++){
            a.addLast(i);
            b.addLast(i);
        }
        for (int j = 0; j < 3; j++){
            assertEquals(a.removeLast(), b.removeLast());
        }
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                assertEquals(L.size(), B.size());
            } else if (operationNumber == 2 & L.size() > 0) {
                assertEquals(L.getLast(), B.getLast());
            } else if (operationNumber == 3 & L.size() > 0) {
                assertEquals(L.removeLast(), B.removeLast());
            }
        }
    }
}
