package DynamicProgramming;

import java.util.Arrays;

public class UniqueBinarySearchTree {

    // 1. Recursive (Brute Force)
    int solveRecursive(int n) {
        if (n <= 1) return 1;

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += solveRecursive(i - 1) * solveRecursive(n - i);
        }
        return ans;
    }

    // 2. Recursion + Memoization
    int solveMemo(int n, int[] dp) {
        if (n <= 1) return 1;
        if (dp[n] != -1) return dp[n];

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += solveMemo(i - 1, dp) * solveMemo(n - i, dp);
        }
        return dp[n] = ans;
    }

    // 3. Tabulation (Bottom-Up)
    int solveTab(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;

        for (int nodes = 2; nodes <= n; nodes++) {
            for (int root = 1; root <= nodes; root++) {
                dp[nodes] += dp[root - 1] * dp[nodes - root];
            }
        }
        return dp[n];
    }

    // 4. Space Optimized (Catalan Formula)
    int solveCatalan(int n) {
        long res = 1;
        for (int i = 0; i < n; i++) {
            res = res * (2L * (2 * i + 1)) / (i + 2);
        }
        return (int) res;
    }

    // Wrapper function to test all methods
    int numTrees(int n) {
        // Recursive (brute force) - not efficient for large n
        System.out.println("Recursive: " + solveRecursive(n));

        // Memoization
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        System.out.println("Memoization: " + solveMemo(n, dp));

        // Tabulation
        System.out.println("Tabulation: " + solveTab(n));

        // Catalan Formula
        System.out.println("Catalan Formula: " + solveCatalan(n));

        return solveTab(n); // return using tabulation (efficient & safe)
    }

    public static void main(String[] args) {
        UniqueBinarySearchTree obj = new UniqueBinarySearchTree();
        int n = 5; // Example input
        int result = obj.numTrees(n);
        System.out.println("Final Answer for n = " + n + " -> " + result);
    }
}
