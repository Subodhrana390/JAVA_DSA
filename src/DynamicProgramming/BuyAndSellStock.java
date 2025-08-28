package DynamicProgramming;

import java.util.Vector;

public class BuyAndSellStock {

    int maxProfitSingleTransaction(Vector<Integer> prices) {
        if (prices == null || prices.isEmpty()) return 0;

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            }
            int profit = price - minPrice;
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }
        return maxProfit;
    }

    int maxProfitMultipleTransactions(Vector<Integer> prices) {
        int n = prices.size();
        Integer[][] dp = new Integer[n][2];  // dp[day][buy]
        return helper(prices, 0, 1, dp);
    }

    int helper(Vector<Integer> prices, int index, int buy, Integer[][] dp) {
        if (index == prices.size()) return 0;

        if (dp[index][buy] != null) return dp[index][buy];

        int profit;
        if (buy == 1) {
            profit = Math.max(
                    -prices.get(index) + helper(prices, index + 1, 0, dp),
                    helper(prices, index + 1, 1, dp)
            );
        } else {
            profit = Math.max(
                    prices.get(index) + helper(prices, index + 1, 1, dp),
                    helper(prices, index + 1, 0, dp)
            );
        }

        dp[index][buy] = profit;
        return profit;
    }

    int maxProfitMultipleTransactionsTab(Vector<Integer> prices) {
        int n = prices.size();
        if (n == 0) return 0;

        int[][] dp = new int[n + 1][2];

        // Bottom-up DP
        for (int index = n - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 1) {
                    dp[index][buy] = Math.max(
                            -prices.get(index) + dp[index + 1][0],
                            dp[index + 1][1]
                    );
                } else {
                    dp[index][buy] = Math.max(
                            prices.get(index) + dp[index + 1][1],
                            dp[index + 1][0]
                    );
                }
            }
        }

        return dp[0][1]; // Start from day 0 with ability to buy
    }

    int maxProfitMultipleTransactionsSO(Vector<Integer> prices) {
        int n = prices.size();
        if (n == 0) return 0;

        int[] ahead = new int[2]; // ahead[0] = next day's sell state, ahead[1] = next day's buy state
        int[] current = new int[2];

        for (int index = n - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 1) {
                    current[buy] = Math.max(
                            -prices.get(index) + ahead[0],
                            ahead[1]
                    );
                } else {
                    current[buy] = Math.max(
                            prices.get(index) + ahead[1],
                            ahead[0]
                    );
                }
            }
            // Move current state to ahead for the next iteration
            ahead[0] = current[0];
            ahead[1] = current[1];
        }

        return ahead[1]; // Start from day 0 with ability to buy
    }



    public static void main(String[] args) {
        BuyAndSellStock obj = new BuyAndSellStock();
        Vector<Integer> prices = new Vector<>();
        prices.add(7);
        prices.add(1);
        prices.add(5);
        prices.add(3);
        prices.add(6);
        prices.add(4);

        System.out.println("Maximum Profit (Single Transaction): " +
                obj.maxProfitSingleTransaction(prices));

        System.out.println("Maximum Profit (Multiple Transactions - Top Down): " +
                obj.maxProfitMultipleTransactions(prices));
        System.out.println("Maximum Profit (Multiple Transactions - Bottom up): " +
                obj.maxProfitMultipleTransactionsTab(prices));
        System.out.println("Maximum Profit (Multiple Transactions -  Space optimized): " +
                obj.maxProfitMultipleTransactionsSO(prices));
    }
}
