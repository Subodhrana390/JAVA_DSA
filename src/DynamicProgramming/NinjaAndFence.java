package DynamicProgramming;

import java.util.Arrays;

public class NinjaAndFence {

    static final int MOD = 1_000_000_007;

    public static int add(int a, int b) {
        return ((a % MOD + MOD) % MOD + (b % MOD + MOD) % MOD) % MOD;
    }

    public static int multiply(int a, int b) {
        return (int) (((long) (a % MOD + MOD) % MOD * (b % MOD + MOD) % MOD) % MOD);
    }

    // 1. Recursive (Brute Force) - Exponential Time
    int recursive(int n, int k) {
        if (n == 1) return k;
        if (n == 2) return add(k, multiply(k, k - 1));

        return add(multiply(recursive(n - 1, k), k - 1), multiply(recursive(n - 2, k), k - 1));
    }

    // 2. Memoization (Top-Down DP)
    int memoization(int n, int k, int[] dp) {
        if (n == 1) return k;
        if (n == 2) return add(k, multiply(k, k - 1));
        if (dp[n] != -1) return dp[n];

        dp[n] = add(multiply(memoization(n - 1, k, dp), k - 1), multiply(memoization(n - 2, k, dp), k - 1));
        return dp[n];
    }

    // 3. Tabulation (Bottom-Up DP)
    int tabulation(int n, int k) {
        if (n == 1) return k;
        int[] dp = new int[n + 1];
        dp[1] = k;
        dp[2] = add(k, multiply(k, k - 1));

        for (int i = 3; i <= n; i++) {
            dp[i] = add(multiply(dp[i - 1], k - 1), multiply(dp[i - 2], k - 1));
        }
        return dp[n];
    }

    // 4. Space Optimization
    int spaceOptimized(int n, int k) {
        if (n == 1) return k;
        int prev2 = k;
        int prev1 = add(k, multiply(k, k - 1));
        int curr = 0;

        for (int i = 3; i <= n; i++) {
            curr = add(multiply(prev1, k - 1), multiply(prev2, k - 1));
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    public static void main(String[] args) {
        NinjaAndFence nf = new NinjaAndFence();
        int n = 5;
        int k = 3;

        // 1. Recursive
        System.out.println("Recursive: " + nf.recursive(n, k));

        // 2. Memoization
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        System.out.println("Memoization: " + nf.memoization(n, k, dp));

        // 3. Tabulation
        System.out.println("Tabulation: " + nf.tabulation(n, k));

        // 4. Space Optimized
        System.out.println("Space Optimized: " + nf.spaceOptimized(n, k));
    }
}
