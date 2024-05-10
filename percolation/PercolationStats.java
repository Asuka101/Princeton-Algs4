import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double mean, stddev, confidenceLo, confidenceHi;
    private double[] x;
    private int t;
    private static final double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        int row, col, size, i;
        Percolation p;
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        x = new double[trials];
        t = trials;
        size = n * n;
        for (i = 0; i < trials; i++) {
            p = new Percolation(n);
            while (!p.percolates()) {
                row = StdRandom.uniformInt(n) + 1;
                col = StdRandom.uniformInt(n) + 1;
                if (p.isOpen(row, col))
                    continue;
                p.open(row, col);
            }
            x[i] = (double) p.numberOfOpenSites() / size;
        }
        mean = mean();
        stddev = stddev();
        confidenceLo = confidenceLo();
        confidenceHi = confidenceHi();
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(x);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return x.length == 1 ? Double.NaN : StdStats.stddev(x);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - (CONFIDENCE_95 * stddev) / Math.sqrt(t);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + (CONFIDENCE_95 * stddev) / Math.sqrt(t);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n, trials;
        PercolationStats ps;
        if (args.length != 2) {
            System.out.println("Usage: java-algs4 PercolationStats n T");
            return;
        }
        n = Integer.parseInt(args[0]);
        trials = Integer.parseInt(args[1]);
        ps = new PercolationStats(n, trials);
        System.out.println("mean                    = " + ps.mean);
        System.out.println("stddev                  = " + ps.stddev);
        System.out.println(
                "95% confidence interval = [" + ps.confidenceLo + ", " + ps.confidenceHi + "]");
    }
}
