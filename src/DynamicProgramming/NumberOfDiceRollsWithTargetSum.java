package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class NumberOfDiceRollsWithTargetSum {

    private static final long MOD = 1000000007L;
    private Map<String, Long> Memo = new HashMap<>();

    long solveRec(int dice, int faces, int target) {
        if (target < 0) return 0;
        if (dice == 0 && target == 0) return 1;
        if (dice == 0 || target == 0) return 0;

        long ways = 0;
        for (int i = 1; i <= faces; i++) {
            ways = (ways + solveRec(dice - 1, faces, target - i)) % MOD;
        }
        return ways;
    }

    long noOfWaysRec(int M, int N, int X) {
        return solveRec(M, N, X);
    }

    long solveMemo(int dice, int faces, int target) {
        if (target < 0) return 0;
        if (dice == 0 && target == 0) return 1;
        if (dice == 0 || target == 0) return 0;

        String key = dice + "-" + target;
        if (Memo.containsKey(key)) return Memo.get(key);

        long ways = 0;
        for (int i = 1; i <= faces; i++) {
            ways = (ways + solveMemo(dice - 1, faces, target - i)) % MOD;
        }

        Memo.put(key, ways);
        return ways;
    }

    long solveTab(int dice, int faces, int target) {
        long[][] dp = new long[dice + 1][target + 1];
        dp[0][0] = 1; // base case

        for (int d = 1; d <= dice; d++) {
            for (int t = 1; t <= target; t++) {
                long ways = 0;
                for (int f = 1; f <= faces; f++) {
                    if (t - f >= 0) {
                        ways = (ways + dp[d - 1][t - f]) % MOD;
                    }
                }
                dp[d][t] = ways;
            }
        }

        return dp[dice][target];
    }

    long solveTabSO(int dice, int faces, int target) {
        long[] prev = new long[target + 1];
        long[] curr = new long[target + 1];

        prev[0] = 1; // base case: 0 dice → sum 0

        for (int d = 1; d <= dice; d++) {
            curr = new long[target + 1]; // reset for this dice count
            for (int t = 1; t <= target; t++) {
                long ways = 0;
                for (int f = 1; f <= faces; f++) {
                    if (t - f >= 0) {
                        ways = (ways + prev[t - f]) % MOD;
                    }
                }
                curr[t] = ways;
            }
            prev = curr; // move current row to prev for next iteration
        }

        return prev[target];
    }

    long noOfWaysMemo(int M, int N, int X) {
        Memo.clear();
        return solveMemo(M, N, X);
    }

    public static void main(String[] args) {
        NumberOfDiceRollsWithTargetSum obj = new NumberOfDiceRollsWithTargetSum();

        System.out.println("Pure Recursion:");
        System.out.println(obj.noOfWaysRec(2, 6, 7));   // 6
        // System.out.println(obj.noOfWaysRec(30, 6, 100)); // ❌ Too slow

        System.out.println("Recursion + Memoization:");
        System.out.println(obj.noOfWaysMemo(2, 6, 7));   // 6
        System.out.println(obj.noOfWaysMemo(30, 6, 100)); // Efficient with memoization
        System.out.println("Tabulation:");
        System.out.println(obj.solveTab(2, 6, 7));
        System.out.println("Space optimized:");
        System.out.println(obj.solveTabSO(2, 6, 7));
    }
}
