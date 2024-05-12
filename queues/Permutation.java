import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k, i;
        RandomizedQueue<String> rq;
        if (args.length != 1) {
            System.out.println("Usage: java-algs4 Permutation k");
            return;
        }
        k = Integer.parseInt(args[0]);
        rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty())
            rq.enqueue(StdIn.readString());
        for (i = 0; i < k; i++)
            StdOut.println(rq.dequeue());
    }
}
