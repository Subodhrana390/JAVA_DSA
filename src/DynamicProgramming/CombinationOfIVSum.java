package DynamicProgramming;

import java.util.*;

public class CombinationOfIVSum {

    // 1. Recursive (Brute Force)
    public static int countWaysRecursive(List<Integer> num, int target) {
        if (target == 0) return 1;
        if (target < 0) return 0;
        int count = 0;
        for (int n : num) {
            count += countWaysRecursive(num, target - n);
        }
        return count;
    }

    // 2. Memoization (Top-down DP)
    public static int countWaysMemo(List<Integer> num, int target, Map<Integer, Integer> memo) {
        if (target == 0) return 1;
        if (target < 0) return 0;
        if (memo.containsKey(target)) return memo.get(target);
        int count = 0;
        for (int n : num) {
            count += countWaysMemo(num, target - n, memo);
        }
        memo.put(target, count);
        return count;
    }

    // 3. Tabulation (Bottom-up DP)
    public static int countWaysDP(List<Integer> num, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int n : num) {
                if (i - n >= 0) {
                    dp[i] += dp[i - n];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        List<Integer> num = Arrays.asList(1, 2, 3);
        int target = 4;

        System.out.println("Recursive: " + countWaysRecursive(num, target));
        System.out.println("Memoization: " + countWaysMemo(num, target, new HashMap<>()));
        System.out.println("Tabulation: " + countWaysDP(num, target));
    }
}
