package DynamicProgramming;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

class ReducingDishesDP {

    // -------------------------------
    // 1. Naive Recursion
    // -------------------------------
    public int maxSatisfactionRecursive(int[] satisfaction, int index, int time) {
        if (index == satisfaction.length) return 0;

        // Include current dish
        int include = satisfaction[index] * (time + 1) +
                maxSatisfactionRecursive(satisfaction, index + 1, time + 1);

        // Skip current dish
        int skip = maxSatisfactionRecursive(satisfaction, index + 1, time);

        return Math.max(include, skip);
    }

    // -------------------------------
    // 2. Memoization (Top-down DP)
    // -------------------------------
    public int maxSatisfactionMemo(int[] satisfaction, int index, int time, int[][] dp) {
        if (index == satisfaction.length) return 0;
        if (dp[index][time] != Integer.MIN_VALUE) return dp[index][time];

        int include = satisfaction[index] * time +
                maxSatisfactionMemo(satisfaction, index + 1, time + 1, dp);
        int skip = maxSatisfactionMemo(satisfaction, index + 1, time, dp);

        return dp[index][time] = Math.max(include, skip);
    }

    // -------------------------------
    // 3. Tabulation (Bottom-up DP)
    // -------------------------------
    public int maxSatisfactionTabulation(int[] satisfaction) {
        int n = satisfaction.length;
        int[][] dp = new int[n + 1][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int time = n; time >= 1; time--) {
                int include = satisfaction[i] * time + dp[i + 1][time + 1];
                int skip = dp[i + 1][time];
                dp[i][time] = Math.max(include, skip);
            }
        }
        return dp[0][1];
    }

    // -------------------------------
    // 4. Greedy (Optimized O(n log n))
    // -------------------------------
    public int maxSatisfactionGreedy(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int total = 0, suffixSum = 0;

        for (int i = satisfaction.length - 1; i >= 0; i--) {
            if (suffixSum + satisfaction[i] > 0) {
                suffixSum += satisfaction[i];
                total += suffixSum;
            } else {
                break;
            }
        }
        return total;
    }

    // -------------------------------
    // Run all methods for comparison
    // -------------------------------
    public static void main(String[] args) {
        ReducingDishesDP rd = new ReducingDishesDP();
        Vector<Integer> satVec = new Vector<>();
        Collections.addAll(satVec, -1, -8, 0, 5, -9);

        // Convert Vector to array for easier processing
        int[] satisfaction = satVec.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(satisfaction); // Sorting is required for all approaches

        // 1. Recursive
        System.out.println("Recursive: " +
                rd.maxSatisfactionRecursive(satisfaction, 0, 1));

        // 2. Memoization
        int[][] dp = new int[satisfaction.length][satisfaction.length + 2];
        for (int[] row : dp) Arrays.fill(row, Integer.MIN_VALUE);
        System.out.println("Memoization: " +
                rd.maxSatisfactionMemo(satisfaction, 0, 1, dp));

        // 3. Tabulation
        System.out.println("Tabulation: " +
                rd.maxSatisfactionTabulation(satisfaction));

        // 4. Greedy
        System.out.println("Greedy: " +
                rd.maxSatisfactionGreedy(satisfaction));
    }
}
