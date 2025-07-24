package DynamicProgramming;

import java.util.Arrays;

public class CountingDearrangement {

    // 1. Recursive (Not recommended for large n)
    static long countDerangeRecursive(int n) {
        if (n == 0) return 1;
        if (n == 1) return 0;
        if (n == 2) return 1;

        return (n - 1) * (countDerangeRecursive(n - 1) + countDerangeRecursive(n - 2));
    }

    // 2. Memoization (Top-down)
    static long countDerangeMemo(int n, long[] dp) {
        if (n == 0) return 1;
        if (n == 1) return 0;
        if (n == 2) return 1;

        if (dp[n] != -1) return dp[n];

        dp[n] = (n - 1) * (countDerangeMemo(n - 1, dp) + countDerangeMemo(n - 2, dp));
        return dp[n];
    }

    // 3. Tabulation (Bottom-up)
    static long countDerangeTabulation(int n) {
        if (n == 0) return 1;
        if (n == 1) return 0;
        if (n == 2) return 1;

        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]);
        }

        return dp[n];
    }

    // 4. Space Optimized
    static long countDerangeSpaceOptimized(int n) {
        if (n == 0) return 1;
        if (n == 1) return 0;
        if (n == 2) return 1;

        long prev2 = 0; // D(1)
        long prev1 = 1; // D(2)
        long curr = 0;

        for (int i = 3; i <= n; i++) {
            curr = (i - 1) * (prev1 + prev2);
            prev2 = prev1;
            prev1 = curr;
        }

        return curr;
    }

    public static void main(String[] args) {
        int n = 5;

        System.out.println("1. Recursive:           " + countDerangeRecursive(n));

        long[] memo = new long[n + 1];
        Arrays.fill(memo, -1);
        System.out.println("2. Memoization:         " + countDerangeMemo(n, memo));

        System.out.println("3. Tabulation:          " + countDerangeTabulation(n));

        System.out.println("4. Space Optimized:     " + countDerangeSpaceOptimized(n));
    }
}
