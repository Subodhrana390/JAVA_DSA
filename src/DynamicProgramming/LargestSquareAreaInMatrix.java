package DynamicProgramming;

import java.util.Vector;

public class LargestSquareAreaInMatrix {

    // 1. Pure Recursive Method
    // Returns the size of largest square with bottom-right corner at (i,j)
    private int maxSquareRecursive(Vector<Vector<Integer>> matrix, int i, int j, int n, int m) {
        // Base case: outside boundaries
        if (i < 0 || j < 0) return 0;

        // If matrix[i][j] is 0, no square can end here
        if (matrix.get(i).get(j) == 0) return 0;

        // Recursively find the sizes for left, top, and top-left neighbors
        int left = maxSquareRecursive(matrix, i, j - 1, n, m);
        int top = maxSquareRecursive(matrix, i - 1, j, n, m);
        int diagonal = maxSquareRecursive(matrix, i - 1, j - 1, n, m);

        // Current cell's max square side length
        return 1 + Math.min(Math.min(left, top), diagonal);
    }

    public int maxSquarePureRecursive(int n, int m, Vector<Vector<Integer>> matrix) {
        int maxSide = 0;

        // Check for all cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int side = maxSquareRecursive(matrix, i, j, n, m);
                if (side > maxSide) maxSide = side;
            }
        }
        return maxSide * maxSide;
    }

    // 2. Recursive + Memoization
    private int maxSquareMemo(Vector<Vector<Integer>> matrix, int i, int j, int n, int m, int[][] memo) {
        if (i < 0 || j < 0) return 0;
        if (matrix.get(i).get(j) == 0) return 0;

        if (memo[i][j] != -1) return memo[i][j];

        int left = maxSquareMemo(matrix, i, j - 1, n, m, memo);
        int top = maxSquareMemo(matrix, i - 1, j, n, m, memo);
        int diagonal = maxSquareMemo(matrix, i - 1, j - 1, n, m, memo);

        memo[i][j] = 1 + Math.min(Math.min(left, top), diagonal);
        return memo[i][j];
    }

    public int maxSquareMemoization(int n, int m, Vector<Vector<Integer>> matrix) {
        int maxSide = 0;
        int[][] memo = new int[n][m];

        // Initialize memo with -1
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                memo[i][j] = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int side = maxSquareMemo(matrix, i, j, n, m, memo);
                if (side > maxSide) maxSide = side;
            }
        }

        return maxSide * maxSide;
    }

    // 3. Tabulation (Bottom-Up DP)
    public int maxSquareTabulation(int n, int m, Vector<Vector<Integer>> matrix) {
        int maxSide = 0;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix.get(i).get(j);
                } else if (matrix.get(i).get(j) == 1) {
                    dp[i][j] = 1 + Math.min(
                            Math.min(dp[i - 1][j], dp[i][j - 1]),
                            dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = 0;
                }
                if (dp[i][j] > maxSide) maxSide = dp[i][j];
            }
        }

        return maxSide * maxSide;
    }

    // 4. Space Optimized DP
    public int maxSquareSpaceOptimized(int n, int m, Vector<Vector<Integer>> matrix) {
        int maxSide = 0;

        // We only need previous row and current row
        int[] prev = new int[m];
        int[] curr = new int[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    curr[j] = matrix.get(i).get(j);
                } else if (matrix.get(i).get(j) == 1) {
                    curr[j] = 1 + Math.min(Math.min(prev[j], curr[j - 1]), prev[j - 1]);
                } else {
                    curr[j] = 0;
                }

                if (curr[j] > maxSide) maxSide = curr[j];
            }
            // Copy current row to previous row for next iteration
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        Vector<Vector<Integer>> matrix = new Vector<>();
        matrix.add(new Vector<>(java.util.Arrays.asList(1, 1, 0, 1)));
        matrix.add(new Vector<>(java.util.Arrays.asList(1, 1, 1, 1)));
        matrix.add(new Vector<>(java.util.Arrays.asList(1, 1, 1, 0)));

        LargestSquareAreaInMatrix solver = new LargestSquareAreaInMatrix();

        System.out.println("Pure Recursive: " + solver.maxSquarePureRecursive(3, 4, matrix));
        System.out.println("Memoization: " + solver.maxSquareMemoization(3, 4, matrix));
        System.out.println("Tabulation: " + solver.maxSquareTabulation(3, 4, matrix));
        System.out.println("Space Optimized: " + solver.maxSquareSpaceOptimized(3, 4, matrix));
    }
}
