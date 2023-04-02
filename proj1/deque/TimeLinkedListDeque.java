package deque;
import afu.org.checkerframework.checker.igj.qual.I;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeLinkedListDeque {
    private static void printTimingTable(LinkedListDeque<Integer> Ns, LinkedListDeque<Double> times, LinkedListDeque<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeLinkedListDequeConstruction();
    }

    public static void timeLinkedListDequeConstruction() {
        // TODO: YOUR CODE HERE
        System.out.println("Timing table for addLast");
        LinkedListDeque<Double> times = new LinkedListDeque<>();
        LinkedListDeque<Integer> Ns = new LinkedListDeque<>();

        for (int n = 1000; n <2000000; n = 2 * n){
            Ns.addLast(n);
            LinkedListDeque<Integer> testList = new LinkedListDeque<>();

            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < n; i++){
                testList.addLast(0);
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }

        printTimingTable(Ns, times, Ns);

    }
}