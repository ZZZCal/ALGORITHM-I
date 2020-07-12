## week 1 assignment
> task description [https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php]

## Percolation

+ `backwash`当`bottom`和最后一行联结时,和`bottom`联结的白色点(非`full`)会在`union`后被判定为`full`,使用两个`WeightedQuickUnionUF`时,`memory`中的`test2(bonus)`无法通过

`Percolation.java`

```java
/* *****************************************************************************
 *  Name:             
 *  Coursera User ID:  
 *  Last modified:     
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF unionUF;
    private WeightedQuickUnionUF unionUFBackwash;
    private int size;
    private boolean[][] openSites;
    private int openCount;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        } else {
            size = n;
            unionUF = new WeightedQuickUnionUF(n * n + 2);
            unionUFBackwash = new WeightedQuickUnionUF(n * n + 1);
            openSites = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    openSites[i][j] = false;
                }
            }
            openCount = 0;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (openSites[row-1][col-1]) {
            return;
        }
        openSites[row-1][col-1] = true;
        openCount++;
        // 第一行的点和虚拟最高行联结,最后一行的点和虚拟最低行联结
        if (row == 1) {
            unionUF.union(0, col);
            unionUFBackwash.union(0, col);
        }
        if (row == size) {
            unionUF.union((row-1) * size + col, size * size + 1);
        }
        // 其余每行的点和上下左右open的点联结
        if (row > 1 && openSites[row-2][col-1]) {
            unionUF.union((row-1) * size + col, (row-2) * size + col);
            unionUFBackwash.union((row-1) * size + col, (row-2) * size + col);
        }
        if (row < size && openSites[row][col-1]) {
            unionUF.union((row-1) * size + col, row * size + col);
            unionUFBackwash.union((row-1) * size + col, row * size + col);
        }
        if (col > 1 && openSites[row-1][col-2]) {
            unionUF.union((row-1) * size + col, (row-1) * size + col - 1);
            unionUFBackwash.union((row-1) * size + col, (row-1) * size + col - 1);
        }
        if (col < size && openSites[row-1][col]) {
            unionUF.union((row-1) * size + col, (row-1) * size + col + 1);
            unionUFBackwash.union((row-1) * size + col, (row-1) * size + col + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return openSites[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        if (unionUFBackwash.find((row-1) * size + col) == unionUFBackwash.find(0)) {
            return true;
        }
        return false;
    }

    // validate that row col is valid
    private void validate(int row, int col) {
        if (row > size || row <= 0 || col > size || col <= 0) {
            throw new IllegalArgumentException();
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return unionUF.find(0) == unionUF.find(size * size + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        // something to do optional
    }
}

```

`PercolationStats.java`

```java
/* *****************************************************************************
 *  Name:              
 *  Coursera User ID:  
 *  Last modified:     
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double S = 1.96;
    private final double[] threshold;
    private final int t;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        t = trials;
        threshold = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                }
            }
            threshold[i] = 1.0 * percolation.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - S * stddev() / Math.sqrt(t);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + S * stddev() / Math.sqrt(t);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", "
                               + percolationStats.confidenceHi() + "]");
    }

}

```

