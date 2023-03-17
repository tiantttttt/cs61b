package timingtest;
import afu.org.checkerframework.checker.igj.qual.I;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        System.out.println("Timing table for getLast");
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> mLists = new AList<>();
        int M = 10000;

        for (int n = 1000; n < 128001; n = n * 2){
            SLList<Integer> testList = new SLList<>();
            Ns.addLast(n);
            mLists.addLast(M);

            for (int i = 0; i < n; i++){
                testList.addLast(0);
            }

            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < M; j++){
                testList.getLast();
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }
        printTimingTable(Ns, times, mLists);
    }

}
