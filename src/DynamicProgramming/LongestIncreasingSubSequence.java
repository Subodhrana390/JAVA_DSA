package DynamicProgramming;

import java.util.Arrays;
import java.util.Vector;

public class LongestIncreasingSubSequence {


    int solve(int n, int[] a, int curr, int prev) {
        if (curr == n) {
            return 0;
        }

        int take = 0;
        if (prev == -1 || a[curr] > a[prev]) {
            take = 1 + solve(n, a, curr + 1, curr);
        }
        int noTake = solve(n, a, curr + 1, prev);

        return Math.max(take, noTake);
    }

    int solveMemo(int n, int[] a, int curr, int prev, int[][] dp) {
        if (curr == n) {
            return 0;
        }

        if (dp[curr][prev + 1] != -1) {
            return dp[curr][prev + 1];
        }

        int take = 0;
        if (prev == -1 || a[curr] > a[prev]) {
            take = 1 + solveMemo(n, a, curr + 1, curr, dp);
        }
        int noTake = solveMemo(n, a, curr + 1, prev, dp);

        return dp[curr][prev + 1] = Math.max(take, noTake);
    }

    int solveTab(int n, int[] a) {
        int[][] dp = new int[n + 1][n + 1];

        for (int curr = n - 1; curr >= 0; curr--) {
            for (int prev = curr - 1; prev >= -1; prev--) {
                int take = 0;
                if (prev == -1 || a[curr] > a[prev]) {
                    take = 1 + dp[curr + 1][curr + 1];
                }
                int noTake = dp[curr + 1][prev + 1];
                dp[curr][prev + 1] = Math.max(take, noTake);
            }
        }
        return dp[0][0];
    }

    int solveSpaceOptimized(int n, int[] a) {
        int[] currRow = new int[n + 1];
        int[] nextRow = new int[n + 1];

        for (int curr = n - 1; curr >= 0; curr--) {
            for (int prev = curr - 1; prev >= -1; prev--) {
                int take = 0;
                if (prev == -1 || a[curr] > a[prev]) {
                    take = 1 + nextRow[curr + 1];
                }
                int noTake = nextRow[prev + 1];
                currRow[prev + 1] = Math.max(take, noTake);
            }
            nextRow = currRow;
        }
        return nextRow[0];
    }

    int lowerBound(Vector<Integer> v, int target) {
        int low = 0, high = v.size() - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (v.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    int solveOptimal(int n, int[] a) {
        if (n == 0) return 0;

        Vector<Integer> ans = new Vector<>();
        ans.add(a[0]);

        for (int i = 1; i < n; i++) {
            if (a[i] > ans.lastElement()) {
                ans.add(a[i]);
            } else {
                int index = lowerBound(ans, a[i]);
                ans.set(index, a[i]);
            }
        }
        return ans.size();
    }

    int longestSubsequence(int n, int[] a) {
//        return solve(n, a, 0, -1);

//        int[][] dp = new int[n][n + 1];
//        for (int[] row : dp) {
//            Arrays.fill(row, -1);
//        }
//        return solveMemo(n, a, 0, -1, dp);

//        return solveTab(n, a);

//        return solveSpaceOptimized(n, a);
        return solveOptimal(n, a);
    }


    public static void main(String[] args) {
        LongestIncreasingSubSequence lis = new LongestIncreasingSubSequence();
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        int n = arr.length;
        System.out.println("Length of LIS: " + lis.longestSubsequence(n, arr));
    }

}
