import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufisFull;
    private int count;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        int i, j;
        if (n <= 0)
            throw new IllegalArgumentException("n must be postive");
        grid = new boolean[n][n];
        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++)
                grid[i][j] = false;
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufisFull = new WeightedQuickUnionUF(n * n + 1);
        count = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int id, n, i, j;
        if (!validate(row, col))
            throw new IllegalArgumentException("the given site (row, col) must in the n-by-n grid");
        n = grid.length;
        i = row - 1;
        j = col - 1;
        id = xy2id(row, col);
        if (grid[i][j])
            return;
        grid[i][j] = true;
        if (row == 1) {
            uf.union(0, id);
            ufisFull.union(0, id);
        }
        if (row == n)
            uf.union(n * n + 1, id);
        if (j > 0 && grid[i][j - 1]) {
            uf.union(id, xy2id(row, col - 1));
            ufisFull.union(id, xy2id(row, col - 1));
        }
        if (j < n - 1 && grid[i][j + 1]) {
            uf.union(id, xy2id(row, col + 1));
            ufisFull.union(id, xy2id(row, col + 1));
        }
        if (i > 0 && grid[i - 1][j]) {
            uf.union(id, xy2id(row - 1, col));
            ufisFull.union(id, xy2id(row - 1, col));
        }
        if (i < n - 1 && grid[i + 1][j]) {
            uf.union(id, xy2id(row + 1, col));
            ufisFull.union(id, xy2id(row + 1, col));
        }
        count++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!validate(row, col))
            throw new IllegalArgumentException("the given site (row, col) must in the n-by-n grid");
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!validate(row, col))
            throw new IllegalArgumentException("the given site (row, col) must in the n-by-n grid");
        return ufisFull.find(0) == ufisFull.find(xy2id(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(0) == uf.find(grid.length * grid.length + 1);
    }

    // maps from 2D to 1D indices
    private int xy2id(int row, int col) {
        if (!validate(row, col))
            throw new IllegalArgumentException("the given site (row, col) must in the n-by-n grid");
        return (row - 1) * grid.length + col;
    }

    // check whether the input is valid
    private boolean validate(int row, int col) {
        if (row <= 0 || row > grid.length || col <= 0 || col > grid.length)
            return false;
        return true;
    }
}
