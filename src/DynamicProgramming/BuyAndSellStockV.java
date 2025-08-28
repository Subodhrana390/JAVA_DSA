package DynamicProgramming;

import java.util.*;

public class BuyAndSellStockV {

    // ------------------ 1. Pure Recursion ------------------
    int solveRec(int day, int canBuy, Vector<Integer> prices, int fee) {
        if (day == prices.size()) return 0;

        if (canBuy == 1) {
            int buy = -prices.get(day) + solveRec(day + 1, 0, prices, fee);
            int skip = solveRec(day + 1, 1, prices, fee);
            return Math.max(buy, skip);
        } else {
            int sell = prices.get(day) - fee + solveRec(day + 1, 1, prices, fee);
            int hold = solveRec(day + 1, 0, prices, fee);
            return Math.max(sell, hold);
        }
    }

    // ------------------ 2. Memoization ------------------
    int solveMemo(int day, int canBuy, Vector<Integer> prices, int fee, int[][] dp) {
        if (day == prices.size()) return 0;
        if (dp[day][canBuy] != -1) return dp[day][canBuy];

        if (canBuy == 1) {
            int buy = -prices.get(day) + solveMemo(day + 1, 0, prices, fee, dp);
            int skip = solveMemo(day + 1, 1, prices, fee, dp);
            return dp[day][canBuy] = Math.max(buy, skip);
        } else {
            int sell = prices.get(day) - fee + solveMemo(day + 1, 1, prices, fee, dp);
            int hold = solveMemo(day + 1, 0, prices, fee, dp);
            return dp[day][canBuy] = Math.max(sell, hold);
        }
    }

    // ------------------ 3. Tabulation ------------------
    int solveTab(Vector<Integer> prices, int fee) {
        int n = prices.size();
        int[][] dp = new int[n + 1][2];

        for (int day = n - 1; day >= 0; day--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (canBuy == 1) {
                    int buy = -prices.get(day) + dp[day + 1][0];
                    int skip = dp[day + 1][1];
                    dp[day][canBuy] = Math.max(buy, skip);
                } else {
                    int sell = prices.get(day) - fee + dp[day + 1][1];
                    int hold = dp[day + 1][0];
                    dp[day][canBuy] = Math.max(sell, hold);
                }
            }
        }
        return dp[0][1]; // start at day 0 with buying option
    }

    // ------------------ 4. Space Optimized ------------------
    int solveSpaceOptimized(Vector<Integer> prices, int fee) {
        int n = prices.size();
        int cash = 0;            // not holding stock
        int hold = -prices.get(0); // holding stock

        for (int i = 1; i < n; i++) {
            cash = Math.max(cash, hold + prices.get(i) - fee);
            hold = Math.max(hold, cash - prices.get(i));
        }
        return cash;
    }

    public static void main(String[] args) {
        BuyAndSellStockV obj = new BuyAndSellStockV();
        Vector<Integer> prices = new Vector<>(Arrays.asList(1, 3, 2, 8, 4, 9));
        int fee = 2;

        // Method 1: Recursion
        System.out.println("Recursion: " + obj.solveRec(0, 1, prices, fee));

        // Method 2: Memoization
        int[][] dp = new int[prices.size()][2];
        for (int[] row : dp) Arrays.fill(row, -1);
        System.out.println("Memoization: " + obj.solveMemo(0, 1, prices, fee, dp));

        // Method 3: Tabulation
        System.out.println("Tabulation: " + obj.solveTab(prices, fee));

        // Method 4: Space Optimized
        System.out.println("Space Optimized: " + obj.solveSpaceOptimized(prices, fee));
    }
}
