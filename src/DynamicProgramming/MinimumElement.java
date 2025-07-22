package DynamicProgramming;

import java.util.Arrays;
import java.util.Vector;

public class MinimumElement {

    // 1. Pure Recursive
    int solveRec(Vector<Integer> num, int x) {
        if (x == 0) return 0;
        if (x < 0) return Integer.MAX_VALUE;

        int mini = Integer.MAX_VALUE;
        for (int i = 0; i < num.size(); i++) {
            int res = solveRec(num, x - num.get(i));
            if (res != Integer.MAX_VALUE) {
                mini = Math.min(mini, 1 + res);
            }
        }
        return mini;
    }

    // 2. Top-Down DP (Memoization)
    int solveMemo(Vector<Integer> num, int x, int[] dp) {
        if (x == 0) return 0;
        if (x < 0) return Integer.MAX_VALUE;

        if (dp[x] != -1) return dp[x];

        int mini = Integer.MAX_VALUE;
        for (int i = 0; i < num.size(); i++) {
            int res = solveMemo(num, x - num.get(i), dp);
            if (res != Integer.MAX_VALUE) {
                mini = Math.min(mini, 1 + res);
            }
        }

        dp[x] = mini;
        return dp[x];
    }

    // 3. Bottom-Up DP (Tabulation)
    int solveTab(Vector<Integer> num, int x) {
        int[] dp = new int[x + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= x; i++) {
            for (int j = 0; j < num.size(); j++) {
                if (i - num.get(j) >= 0 && dp[i - num.get(j)] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - num.get(j)]);
                }
            }
        }

        return (dp[x] == Integer.MAX_VALUE) ? -1 : dp[x];
    }

    // Wrapper functions
    int minElementRec(Vector<Integer> num, int x) {
        int ans = solveRec(num, x);
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }

    int minElementMemo(Vector<Integer> num, int x) {
        int[] dp = new int[x + 1];
        Arrays.fill(dp, -1);
        int ans = solveMemo(num, x, dp);
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }

    int minElementTab(Vector<Integer> num, int x) {
        return solveTab(num, x);
    }

    // Main
    public static void main(String[] args) {
        MinimumElement obj = new MinimumElement();
        Vector<Integer> num = new Vector<>(Arrays.asList(1, 2, 3));
        int x = 7;

        System.out.println("Recursive: " + obj.minElementRec(num, x));
        System.out.println("Memoization: " + obj.minElementMemo(num, x));
        System.out.println("Tabulation: " + obj.minElementTab(num, x));
    }
}
