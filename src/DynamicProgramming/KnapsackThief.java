package DynamicProgramming;

import java.util.Vector;

public class KnapsackThief {

    // 1. Recursive (Plain)
    int solve(Vector<Integer> weight, Vector<Integer> value, int index, int capacity) {
        if (index == 0) {
            if (weight.getFirst() <= capacity)
                return value.getFirst();
            else
                return 0;
        }

        int include = 0;
        if (weight.get(index) <= capacity) {
            include = value.get(index) + solve(weight, value, index - 1, capacity - weight.get(index));
        }

        int exclude = solve(weight, value, index - 1, capacity);
        return Math.max(include, exclude);
    }

    // 2. Memoized (Top-Down DP)
    int solveMemo(Vector<Integer> weight, Vector<Integer> value, int index, int capacity, int[][] dp) {
        if (index == 0) {
            if (weight.getFirst() <= capacity)
                return value.getFirst();
            else
                return 0;
        }

        if (dp[index][capacity] != -1)
            return dp[index][capacity];

        int include = 0;
        if (weight.get(index) <= capacity) {
            include = value.get(index) + solveMemo(weight, value, index - 1, capacity - weight.get(index), dp);
        }

        int exclude = solveMemo(weight, value, index - 1, capacity, dp);
        return dp[index][capacity] = Math.max(include, exclude);
    }

    // 3. Tabulation (Bottom-Up DP)
    int solveTab(Vector<Integer> weight, Vector<Integer> value, int n, int capacity) {
        int[][] dp = new int[n][capacity + 1];

        // Initialize for first item
        for (int w = weight.getFirst(); w <= capacity; w++) {
            dp[0][w] = value.getFirst();
        }

        // Fill table
        for (int i = 1; i < n; i++) {
            for (int w = 0; w <= capacity; w++) {
                int include = 0;
                if (weight.get(i) <= w) {
                    include = value.get(i) + dp[i - 1][w - weight.get(i)];
                }
                int exclude = dp[i - 1][w];
                dp[i][w] = Math.max(include, exclude);
            }
        }

        return dp[n - 1][capacity];
    }

    // 4. Space Optimized Tabulation (1D DP)
    int solveTabOptimized(Vector<Integer> weight, Vector<Integer> value, int n, int capacity) {
        int[] prev = new int[capacity + 1];

        // Initialize for first item
        for (int w = weight.getFirst(); w <= capacity; w++) {
            prev[w] = value.getFirst();
        }

        // Update rows iteratively
        for (int i = 1; i < n; i++) {
            int[] curr = new int[capacity + 1];

            for (int w = 0; w <= capacity; w++) {
                int include = 0;
                if (weight.get(i) <= w) {
                    include = value.get(i) + prev[w - weight.get(i)];
                }
                int exclude = prev[w];
                curr[w] = Math.max(include, exclude);
            }

            prev = curr;
        }

        return prev[capacity];
    }

    // Entry Point - Choose which method to use
    int knapsack(Vector<Integer> weight, Vector<Integer> value, int n, int maxWeight) {
        // 1. Recursive
        // return solve(weight, value, n - 1, maxWeight);

        // 2. Memoization
        // int[][] dp = new int[n][maxWeight + 1];
        // for (int i = 0; i < n; i++)
        //     Arrays.fill(dp[i], -1);
        // return solveMemo(weight, value, n - 1, maxWeight, dp);

        // 3. Tabulation
        // return solveTab(weight, value, n, maxWeight);

        // 4. Space Optimized
        return solveTabOptimized(weight, value, n, maxWeight);
    }

    public static void main(String[] args) {
        KnapsackThief kt = new KnapsackThief();

        Vector<Integer> weight = new Vector<>();
        Vector<Integer> value = new Vector<>();

        // Sample data
        weight.add(1);
        weight.add(3);
        weight.add(4);
        weight.add(5);

        value.add(1);
        value.add(4);
        value.add(5);
        value.add(7);

        int maxWeight = 7;
        int n = weight.size();

        System.out.println("Maximum value in Knapsack = " + kt.knapsack(weight, value, n, maxWeight));
    }
}
