package DynamicProgramming;

public class PerfectSquareProblem {

    // 1. Recursion
    int solveRec(int n) {
        if (n == 0) return 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            ans = Math.min(ans, 1 + solveRec(n - i * i));
        }
        return ans;
    }

    // 2. Recursion + Memoization
    int solveMem(int n, int[] dp) {
        if (n == 0) return 0;
        if (dp[n] != -1) return dp[n];
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            ans = Math.min(ans, 1 + solveMem(n - i * i, dp));
        }
        return dp[n] = ans;
    }

    // 3. Tabulation
    int solveTab(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            int ans = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                ans = Math.min(ans, 1 + dp[i - j * j]);
            }
            dp[i] = ans;
        }

        return dp[n];
    }

    int minSquares(int n) {
        // return solveRec(n);

        // Memoization
        // int[] dp = new int[n + 1];
        // Arrays.fill(dp, -1);
        // return solveMem(n, dp);

        // Tabulation
        return solveTab(n);
    }

    public static void main(String[] args) {
        PerfectSquareProblem obj = new PerfectSquareProblem();
        int n = 12;
        System.out.println("Min squares for " + n + " = " + obj.minSquares(n));  // Output: 3 (4 + 4 + 4)
    }
}
