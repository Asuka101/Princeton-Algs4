import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion;
        String tmp;
        int iterator;

        champion = null;
        iterator = 1;

        while (!StdIn.isEmpty()) {
            tmp = StdIn.readString();
            if (StdRandom.bernoulli(1.0 / (iterator++)))
                champion = tmp;
        }
        StdOut.println(champion);
    }
}
