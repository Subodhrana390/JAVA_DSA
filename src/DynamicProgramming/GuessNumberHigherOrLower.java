package DynamicProgramming;

import java.util.Arrays;

public class GuessNumberHigherOrLower {

    int solveRec(int start, int end) {
        if (start >= end) {
            return 0;
        }
        int mini = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            // cost = pick i + worst of left/right
            int cost = i + Math.max(solveRec(start, i - 1), solveRec(i + 1, end));
            mini = Math.min(mini, cost);
        }
        return mini;
    }

    int solveMemo(int start, int end, int[][] dp) {
        if (start >= end) {
            return 0;
        }
        if (dp[start][end] != -1) {
            return dp[start][end];
        }
        int mini = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            int cost = i + Math.max(solveMemo(start, i - 1, dp), solveMemo(i + 1, end, dp));
            mini = Math.min(mini, cost);
        }
        dp[start][end] = mini;
        return mini;
    }

    int solveTab(int n) {
        int[][] dp = new int[n + 2][n + 2]; // +2 to prevent out-of-bound

        for (int len = 2; len <= n; len++) { // interval length
            for (int start = 1; start + len - 1 <= n; start++) {
                int end = start + len - 1;
                dp[start][end] = Integer.MAX_VALUE;

                for (int i = start; i <= end; i++) {
                    int cost = i + Math.max(
                            (i - 1 >= start ? dp[start][i - 1] : 0),
                            (i + 1 <= end ? dp[i + 1][end] : 0)
                    );
                    dp[start][end] = Math.min(dp[start][end], cost);
                }
            }
        }

        return dp[1][n];
    }

    int solveSpaceOptimized(int n) {
        int[][] dp = new int[n + 2][n + 2];

        for (int len = 2; len <= n; len++) {
            for (int start = n - len + 1; start >= 1; start--) {
                int end = start + len - 1;
                dp[start][end] = Integer.MAX_VALUE;

                for (int i = start; i <= end; i++) {
                    int cost = i + Math.max(
                            (i > start ? dp[start][i - 1] : 0),
                            (i < end ? dp[i + 1][end] : 0)
                    );
                    dp[start][end] = Math.min(dp[start][end], cost);
                }
            }
        }
        return dp[1][n];
    }

    int getMoneyAmount(int n) {
//        return solveRec(1, n);
//        return solveMemo(1, n, new int[n + 1][n + 1]);
//        return solveTab(n);
        return solveSpaceOptimized(n);
    }

    public static void main(String[] args) {
        GuessNumberHigherOrLower obj = new GuessNumberHigherOrLower();
        System.out.println(obj.getMoneyAmount(10)); // Expected: 16
    }
}
