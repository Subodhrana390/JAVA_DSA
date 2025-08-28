package DynamicProgramming;

import java.util.Arrays;

public class BuyAndSellStockIV {

    // ---------- 1. Recursive ----------
    public int solveRec(int index, int canBuy, int k, int[] prices) {
        if (index == prices.length || k == 0) return 0;

        if (canBuy == 1) {
            int buy = -prices[index] + solveRec(index + 1, 0, k, prices);
            int skip = solveRec(index + 1, 1, k, prices);
            return Math.max(buy, skip);
        } else {
            int sell = prices[index] + solveRec(index + 1, 1, k - 1, prices);
            int skip = solveRec(index + 1, 0, k, prices);
            return Math.max(sell, skip);
        }
    }

    // ---------- 2. Memoization ----------
    public int solveMemo(int index, int canBuy, int k, int[] prices, int[][][] dp) {
        if (index == prices.length || k == 0) return 0;
        if (dp[index][canBuy][k] != -1) return dp[index][canBuy][k];

        int profit;
        if (canBuy == 1) {
            int buy = -prices[index] + solveMemo(index + 1, 0, k, prices, dp);
            int skip = solveMemo(index + 1, 1, k, prices, dp);
            profit = Math.max(buy, skip);
        } else {
            int sell = prices[index] + solveMemo(index + 1, 1, k - 1, prices, dp);
            int skip = solveMemo(index + 1, 0, k, prices, dp);
            profit = Math.max(sell, skip);
        }

        return dp[index][canBuy][k] = profit;
    }

    // ---------- 3. Tabulation ----------
    public int solveTab(int[] prices, int k) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][k + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int trans = 1; trans <= k; trans++) {
                    if (canBuy == 1) {
                        dp[index][canBuy][trans] = Math.max(
                                -prices[index] + dp[index + 1][0][trans],
                                dp[index + 1][1][trans]
                        );
                    } else {
                        dp[index][canBuy][trans] = Math.max(
                                prices[index] + dp[index + 1][1][trans - 1],
                                dp[index + 1][0][trans]
                        );
                    }
                }
            }
        }

        return dp[0][1][k];
    }

    // ---------- 4. Space Optimized ----------
    public int solveOptimized(int[] prices, int k) {
        int n = prices.length;
        if (k >= n / 2) {
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1])
                    maxProfit += prices[i] - prices[i - 1];
            }
            return maxProfit;
        }

        int[][] ahead = new int[2][k + 1];
        int[][] current = new int[2][k + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int trans = 1; trans <= k; trans++) {
                    if (canBuy == 1) {
                        current[canBuy][trans] = Math.max(
                                -prices[index] + ahead[0][trans],
                                ahead[1][trans]
                        );
                    } else {
                        current[canBuy][trans] = Math.max(
                                prices[index] + ahead[1][trans - 1],
                                ahead[0][trans]
                        );
                    }
                }
            }

            for (int b = 0; b <= 1; b++) {
                for (int t = 0; t <= k; t++) {
                    ahead[b][t] = current[b][t];
                }
            }
        }

        return ahead[1][k];
    }

    // ---------- Main Method ----------
    public static void main(String[] args) {
        BuyAndSellStockIV obj = new BuyAndSellStockIV();

        int[] prices = {3, 2, 6, 5, 0, 3};
        int k = 2;

        System.out.println("Recursive: " + obj.solveRec(0, 1, k, prices));

        int[][][] dp = new int[prices.length][2][k + 1];
        for (int[][] mat : dp)
            for (int[] row : mat)
                Arrays.fill(row, -1);
        System.out.println("Memoized: " + obj.solveMemo(0, 1, k, prices, dp));

        System.out.println("Tabulated: " + obj.solveTab(prices, k));
        System.out.println("Space Optimized: " + obj.solveOptimized(prices, k));
    }
}
