package deque;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeArrayDeque {
    private static void printTimingTable(
            ArrayDeque<Integer> ns, ArrayDeque<Double> times, ArrayDeque<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < ns.size(); i += 1) {
            int N = ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeArrayDequeConstruction();
    }

    public static void timeArrayDequeConstruction() {
        System.out.println("Timing table for addLast");
        ArrayDeque<Double> times = new ArrayDeque<>();
        ArrayDeque<Integer> ns = new ArrayDeque<>();

        for (int n = 1000; n < 2000000; n = 2 * n) {
            ns.addLast(n);
            ArrayDeque<Integer> testList = new ArrayDeque<>();

            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < n; i++) {
                testList.addLast(0);
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }

        printTimingTable(ns, times, ns);

    }
}
