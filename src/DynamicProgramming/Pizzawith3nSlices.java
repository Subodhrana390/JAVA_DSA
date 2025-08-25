package DynamicProgramming;

import java.util.Arrays;
import java.util.Vector;

public class Pizzawith3nSlices {

    int solveRec(int index, int endIndex, Vector<Integer> slices, int n) {
        if (n == 0 || index > endIndex) return 0;
        int take = slices.get(index) + solveRec(index + 2, endIndex, slices, n - 1);
        int notTake = solveRec(index + 1, endIndex, slices, n);
        return Math.max(take, notTake);
    }

    int solveMemo(int index, int endIndex, Vector<Integer> slices, int n, int[][] dp) {
        if (n == 0 || index > endIndex) return 0;
        if (dp[index][n] != -1) return dp[index][n];
        int take = slices.get(index) + solveMemo(index + 2, endIndex, slices, n - 1, dp);
        int notTake = solveMemo(index + 1, endIndex, slices, n, dp);
        return dp[index][n] = Math.max(take, notTake);
    }

    int solveTab(Vector<Integer> slices) {
        int k = slices.size();
        int n = k / 3;
        int[][] dp1 = new int[k + 2][n + 1];
        int[][] dp2 = new int[k + 2][n + 1];

        for (int index = k - 2; index >= 0; index--) {
            for (int j = 1; j <= n; j++) {
                int take = slices.get(index) + dp1[index + 2][j - 1];
                int notTake = dp1[index + 1][j];
                dp1[index][j] = Math.max(take, notTake);
            }
        }

        for (int index = k - 1; index >= 1; index--) {
            for (int j = 1; j <= n; j++) {
                int take = slices.get(index) + dp2[index + 2][j - 1];
                int notTake = dp2[index + 1][j];
                dp2[index][j] = Math.max(take, notTake);
            }
        }

        return Math.max(dp1[0][n], dp2[1][n]);
    }

    int solveSpaceOptimized(Vector<Integer> slices) {
        int k = slices.size();
        int n = k / 3;

        int case1 = maxNonAdjacentSum(slices, 0, k - 2, n);
        int case2 = maxNonAdjacentSum(slices, 1, k - 1, n);

        return Math.max(case1, case2);
    }

    int maxNonAdjacentSum(Vector<Integer> slices, int start, int end, int n) {
        int[] next1 = new int[n + 1];
        int[] next2 = new int[n + 1];
        int[] curr = new int[n + 1];

        for (int index = end; index >= start; index--) {
            for (int j = 1; j <= n; j++) {
                int take = slices.get(index) + next2[j - 1];
                int notTake = next1[j];
                curr[j] = Math.max(take, notTake);
            }
            next2 = next1.clone();
            next1 = curr.clone();
        }

        return curr[n];
    }

    int maxSizeSlices(Vector<Integer> slices) {
        int k = slices.size();
        int n = k / 3;

        int[][] dp1 = new int[k][n + 1];
        for (int[] row : dp1) Arrays.fill(row, -1);
        int case1 = solveMemo(0, k - 2, slices, n, dp1);

        int[][] dp2 = new int[k][n + 1];
        for (int[] row : dp2) Arrays.fill(row, -1);
        int case2 = solveMemo(1, k - 1, slices, n, dp2);

        return Math.max(case1, case2);
    }

    public static void main(String[] args) {
        Vector<Integer> slices = new Vector<>();
        slices.add(1);
        slices.add(2);
        slices.add(3);
        slices.add(4);
        slices.add(5);
        slices.add(6);

        Pizzawith3nSlices obj = new Pizzawith3nSlices();
        System.out.println(obj.maxSizeSlices(slices));       // Memoized recursion
        System.out.println(obj.solveTab(slices));            // Tabulation
        System.out.println(obj.solveSpaceOptimized(slices)); // Space optimized
    }
}
