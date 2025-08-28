package DynamicProgramming;

public class BuyAndSellStockIII {

    // 1. Recursive (Exponential Time)
    int maxProfitRecursive(int[] prices) {
        return helper(prices, 0, 1, 2);
    }

    int helper(int[] prices, int index, int canBuy, int transactionsLeft) {
        if (index == prices.length || transactionsLeft == 0) return 0;

        if (canBuy == 1) {
            return Math.max(
                    -prices[index] + helper(prices, index + 1, 0, transactionsLeft),
                    helper(prices, index + 1, 1, transactionsLeft)
            );
        } else {
            return Math.max(
                    prices[index] + helper(prices, index + 1, 1, transactionsLeft - 1),
                    helper(prices, index + 1, 0, transactionsLeft)
            );
        }
    }

    // 2. Top-Down DP with Memoization
    int maxProfitTopDown(int[] prices) {
        Integer[][][] dp = new Integer[prices.length][2][3];
        return helperTD(prices, 0, 1, 2, dp);
    }

    int helperTD(int[] prices, int index, int canBuy, int transactionsLeft, Integer[][][] dp) {
        if (index == prices.length || transactionsLeft == 0) return 0;

        if (dp[index][canBuy][transactionsLeft] != null) {
            return dp[index][canBuy][transactionsLeft];
        }

        int profit;
        if (canBuy == 1) {
            profit = Math.max(
                    -prices[index] + helperTD(prices, index + 1, 0, transactionsLeft, dp),
                    helperTD(prices, index + 1, 1, transactionsLeft, dp)
            );
        } else {
            profit = Math.max(
                    prices[index] + helperTD(prices, index + 1, 1, transactionsLeft - 1, dp),
                    helperTD(prices, index + 1, 0, transactionsLeft, dp)
            );
        }

        dp[index][canBuy][transactionsLeft] = profit;
        return profit;
    }

    // 3. Bottom-Up DP (Tabulation)
    int maxProfitBottomUp(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3];

        for (int index = n - 1; index >= 0; index--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int t = 1; t <= 2; t++) {
                    if (canBuy == 1) {
                        dp[index][canBuy][t] = Math.max(
                                -prices[index] + dp[index + 1][0][t],
                                dp[index + 1][1][t]
                        );
                    } else {
                        dp[index][canBuy][t] = Math.max(
                                prices[index] + dp[index + 1][1][t - 1],
                                dp[index + 1][0][t]
                        );
                    }
                }
            }
        }

        return dp[0][1][2];
    }

    // 4. Space-Optimized DP
    int maxProfitSpaceOptimized(int[] prices) {
        int n = prices.length;
        int[][] ahead = new int[2][3];
        int[][] current = new int[2][3];

        for (int index = n - 1; index >= 0; index--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int t = 1; t <= 2; t++) {
                    if (canBuy == 1) {
                        current[canBuy][t] = Math.max(
                                -prices[index] + ahead[0][t],
                                ahead[1][t]
                        );
                    } else {
                        current[canBuy][t] = Math.max(
                                prices[index] + ahead[1][t - 1],
                                ahead[0][t]
                        );
                    }
                }
            }
            // Update for next iteration
            for (int b = 0; b <= 1; b++) {
                for (int t = 0; t <= 2; t++) {
                    ahead[b][t] = current[b][t];
                }
            }
        }

        return ahead[1][2];
    }

    // Main method to test all versions
    public static void main(String[] args) {
        BuyAndSellStockIII obj = new BuyAndSellStockIII();

        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};

        System.out.println("Recursive: " + obj.maxProfitRecursive(prices));
        System.out.println("Top-Down DP: " + obj.maxProfitTopDown(prices));
        System.out.println("Bottom-Up DP: " + obj.maxProfitBottomUp(prices));
        System.out.println("Space Optimized DP: " + obj.maxProfitSpaceOptimized(prices));
    }
}
