package DynamicProgramming;

import java.util.Collections;
import java.util.Vector;

public class MinimumSwapsToMakeSubsequencesIncreasing {

    int solve(Vector<Integer> num1, Vector<Integer> num2, int index, boolean swapped) {
        if (index == num1.size()) {
            return 0;
        }

        int prev1 = num1.get(index - 1);
        int prev2 = num2.get(index - 1);

        if (swapped) {
            int temp = prev1;
            prev1 = prev2;
            prev2 = temp;
        }

        int ans = Integer.MAX_VALUE;

        if (num1.get(index) > prev1 && num2.get(index) > prev2) {
            ans = Math.min(ans, solve(num1, num2, index + 1, false));
        }

        if (num1.get(index) > prev2 && num2.get(index) > prev1) {
            ans = Math.min(ans, 1 + solve(num1, num2, index + 1, true));
        }

        return ans;
    }

    int solveMemo(Vector<Integer> num1, Vector<Integer> num2, int index, boolean swapped, int[][] dp) {
        if (index == num1.size()) {
            return 0;
        }

        if (dp[index][swapped ? 1 : 0] != -1) {
            return dp[index][swapped ? 1 : 0];
        }

        int prev1 = num1.get(index - 1);
        int prev2 = num2.get(index - 1);

        if (swapped) {
            int temp = prev1;
            prev1 = prev2;
            prev2 = temp;
        }

        int ans = Integer.MAX_VALUE;

        // No swap
        if (num1.get(index) > prev1 && num2.get(index) > prev2) {
            ans = Math.min(ans, solveMemo(num1, num2, index + 1, false, dp));
        }

        // Swap
        if (num1.get(index) > prev2 && num2.get(index) > prev1) {
            ans = Math.min(ans, 1 + solveMemo(num1, num2, index + 1, true, dp));
        }

        return dp[index][swapped ? 1 : 0] = ans;
    }

    int solveTab(Vector<Integer> num1, Vector<Integer> num2) {
        int n = num1.size();
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            dp[i][0] = dp[i][1] = Integer.MAX_VALUE;
        }

        dp[0][0] = 0; // No swap at index 0
        dp[0][1] = 1; // Swap at index 0

        for (int i = 1; i < n; i++) {
            // No swap case
            if (num1.get(i) > num1.get(i - 1) && num2.get(i) > num2.get(i - 1)) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][0]);
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][1] + 1);
            }
            // Swap case
            if (num1.get(i) > num2.get(i - 1) && num2.get(i) > num1.get(i - 1)) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][1]);       // previous was swapped
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + 1);   // swap here
            }
        }

        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }

    int solveTabSO(Vector<Integer> num1, Vector<Integer> num2) {
        int n = num1.size();

        // Base cases for index 0
        int noswap = 0;  // no swap at index 0
        int swap = 1;    // swap at index 0

        for (int i = 1; i < n; i++) {
            int curNoswap = Integer.MAX_VALUE;
            int curSwap = Integer.MAX_VALUE;

            // No swap case
            if (num1.get(i) > num1.get(i - 1) && num2.get(i) > num2.get(i - 1)) {
                curNoswap = Math.min(curNoswap, noswap);
                curSwap = Math.min(curSwap, swap + 1);
            }

            // Swap case
            if (num1.get(i) > num2.get(i - 1) && num2.get(i) > num1.get(i - 1)) {
                curNoswap = Math.min(curNoswap, swap);       // previous was swapped
                curSwap = Math.min(curSwap, noswap + 1);     // swap here
            }

            // Update for next iteration
            noswap = curNoswap;
            swap = curSwap;
        }

        return Math.min(noswap, swap);
    }

    int minSwap(Vector<Integer> num1, Vector<Integer> num2) {
        num1.add(0, -1);
        num2.add(0, -1);

        int[][] dp = new int[num1.size()][2];
        for (int i = 0; i < num1.size(); i++) {
            dp[i][0] = dp[i][1] = -1;
        }

//        return solve(num1, num2, 1, false);
//        return solveMemo(num1, num2, 1, false, dp);

//        return solveTab(num1, num2);
        return solveTabSO(num1, num2);
    }

    public static void main(String[] args) {
        MinimumSwapsToMakeSubsequencesIncreasing obj = new MinimumSwapsToMakeSubsequencesIncreasing();
        Vector<Integer> num1 = new Vector<>();
        Vector<Integer> num2 = new Vector<>();
        Collections.addAll(num1, 1, 3, 5, 4);
        Collections.addAll(num2, 1, 2, 3, 7);
        System.out.println("Minimum Swaps: " + obj.minSwap(num1, num2));
    }
}
