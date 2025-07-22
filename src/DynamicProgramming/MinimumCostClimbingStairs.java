package DynamicProgramming;

import java.util.Arrays;
import java.util.Vector;

public class MinimumCostClimbingStairs {

    // Count distinct ways to climb stairs (Top-Down DP)
    int solve(int nStairs, int i, int[] dp) {
        if (i == nStairs) return 1;
        if (i > nStairs) return 0;

        if (dp[i] != -1) return dp[i];

        dp[i] = solve(nStairs, i + 1, dp) + solve(nStairs, i + 2, dp);
        return dp[i];
    }

    int countDistinctWayToClimbStairs(int nStairs) {
        int[] dp = new int[nStairs + 1];
        Arrays.fill(dp, -1);
        return solve(nStairs, 0, dp);
    }

    // Minimum cost climbing stairs (Top-Down)
    int solveMinCost(Vector<Integer> cost, int n, int[] dp) {
        if (n == 0) return cost.get(0);
        if (n == 1) return cost.get(1);

        if (dp[n] != -1) return dp[n];

        dp[n] = cost.get(n) + Math.min(solveMinCost(cost, n - 1, dp), solveMinCost(cost, n - 2, dp));
        return dp[n];
    }

    // Minimum cost climbing stairs (Bottom-Up Tabulation)
    int solveCost(Vector<Integer> cost) {
        int n = cost.size();
        if (n == 0) return 0;
        if (n == 1) return cost.getFirst();

        int[] dp = new int[n];
        dp[0] = cost.get(0);
        dp[1] = cost.get(1);

        for (int i = 2; i < n; i++) {
            dp[i] = cost.get(i) + Math.min(dp[i - 1], dp[i - 2]);
        }

        return Math.min(dp[n - 1], dp[n - 2]);
    }

    int minCostClimbingStairs(Vector<Integer> cost) {
        // return solveCost(cost); // Uncomment to use Bottom-Up
        int n = cost.size();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return Math.min(solveMinCost(cost, n - 1, dp), solveMinCost(cost, n - 2, dp)); // Top-down
    }

    public static void main(String[] args) {
        MinimumCostClimbingStairs obj = new MinimumCostClimbingStairs();

        // Test 1: Count ways
        int nStairs = 5;
        int ways = obj.countDistinctWayToClimbStairs(nStairs);
        System.out.println("Total distinct ways to climb " + nStairs + " stairs: " + ways);

        // Test 2: Minimum cost
        Vector<Integer> cost = new Vector<>(Arrays.asList(10, 15, 20));
        int minCost = obj.minCostClimbingStairs(cost);
        System.out.println("Minimum cost to climb stairs: " + minCost);
    }
}
