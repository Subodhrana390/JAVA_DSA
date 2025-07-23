package DynamicProgramming;

import java.util.Arrays;

public class MaxSumNonAdjacent {

    // 1. Recursive + Memoization
    public static int maxSumMemo(int[] arr) {
        int n = arr.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return helper(arr, n - 1, memo);
    }

    private static int helper(int[] arr, int i, int[] memo) {
        if (i < 0) return 0;
        if (i == 0) return arr[0];
        if (memo[i] != -1) return memo[i];

        int include = arr[i] + helper(arr, i - 2, memo);
        int exclude = helper(arr, i - 1, memo);
        memo[i] = Math.max(include, exclude);
        return memo[i];
    }

    // 2. Tabulation (Bottom-Up)
    public static int maxSumTabulation(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        if (n == 1) return arr[0];

        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + arr[i]);
        }

        return dp[n - 1];
    }

    // 3. Space Optimized Tabulation
    public static int maxSumOptimized(int[] arr) {
        int prev1 = 0, prev2 = 0;

        for (int num : arr) {
            int temp = prev1;
            prev1 = Math.max(prev1, prev2 + num);
            prev2 = temp;
        }

        return prev1;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 10, 7};

        System.out.println("Recursive + Memoization: " + maxSumMemo(arr));
        System.out.println("Tabulation: " + maxSumTabulation(arr));
        System.out.println("Space Optimized Tabulation: " + maxSumOptimized(arr));
    }
}
