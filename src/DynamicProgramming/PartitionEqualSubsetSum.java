package DynamicProgramming;

import java.util.Arrays;

public class PartitionEqualSubsetSum {

    boolean solveRec(int index, int[] arr, int N, int target) {
        if (target == 0) return true;
        if (index >= N || target < 0) return false;
        boolean include = solveRec(index + 1, arr, N, target - arr[index]);
        boolean exclude = solveRec(index + 1, arr, N, target);
        return include || exclude;
    }

    boolean solveMemo(int index, int[] arr, int N, int target, int[][] dp) {
        if (target == 0) return true;
        if (index >= N || target < 0) return false;
        if (dp[index][target] != -1) return dp[index][target] == 1;

        boolean include = solveMemo(index + 1, arr, N, target - arr[index], dp);
        boolean exclude = solveMemo(index + 1, arr, N, target, dp);

        dp[index][target] = (include || exclude) ? 1 : 0;
        return dp[index][target] == 1;
    }

    boolean solveTab(int N, int[] arr, int target) {
        boolean[][] dp = new boolean[N + 1][target + 1];

        for (int i = 0; i <= N; i++) {
            dp[i][0] = true;
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int t = 1; t <= target; t++) {
                boolean include = false;
                if (t - arr[i] >= 0) {
                    include = dp[i + 1][t - arr[i]];
                }
                boolean exclude = dp[i + 1][t];
                dp[i][t] = include || exclude;
            }
        }

        return dp[0][target];
    }

    boolean solveSpaceOpt(int N, int[] arr, int target) {
        boolean[] prev = new boolean[target + 1];
        boolean[] curr = new boolean[target + 1];

        prev[0] = true;

        for (int i = N - 1; i >= 0; i--) {
            curr[0] = true;
            for (int t = 1; t <= target; t++) {
                boolean include = false;
                if (t - arr[i] >= 0) {
                    include = prev[t - arr[i]];
                }
                boolean exclude = prev[t];
                curr[t] = include || exclude;
            }
            prev = Arrays.copyOf(curr, curr.length);
        }

        return prev[target];
    }

    int equalPartition(int N, int[] arr) {
        int total = 0;
        for (int i = 0; i < N; i++) total += arr[i];
        if (total % 2 != 0) return 0;
        int target = total / 2;

        // Uncomment the approach you want:

        // return solveRec(0, arr, N, target) ? 1 : 0;

        // int[][] dp = new int[N][target + 1];
        // for (int[] row : dp) Arrays.fill(row, -1);
        // return solveMemo(0, arr, N, target, dp) ? 1 : 0;

        // return solveTab(N, arr, target) ? 1 : 0;

        return solveSpaceOpt(N, arr, target) ? 1 : 0;
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum obj = new PartitionEqualSubsetSum();
        int[] arr = {1, 5, 11, 5};
        System.out.println(obj.equalPartition(arr.length, arr)); // 1
        int[] arr2 = {1, 2, 3, 5};
        System.out.println(obj.equalPartition(arr2.length, arr2)); // 0
    }
}
