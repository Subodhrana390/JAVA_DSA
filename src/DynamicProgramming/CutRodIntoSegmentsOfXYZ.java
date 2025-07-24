package DynamicProgramming;

import java.util.Arrays;

public class CutRodIntoSegmentsOfXYZ {

    int solveRecursive(int n, int x, int y, int z) {
        if (n == 0) return 0;
        if (n < 0) return Integer.MIN_VALUE;

        int a = solveRecursive(n - x, x, y, z);
        int b = solveRecursive(n - y, x, y, z);
        int c = solveRecursive(n - z, x, y, z);

        int res = Math.max(a, Math.max(b, c));
        return (res == Integer.MIN_VALUE) ? Integer.MIN_VALUE : 1 + res;
    }

    int solveMemo(int n, int x, int y, int z, int[] dp) {
        if (n == 0) return 0;
        if (n < 0) return Integer.MIN_VALUE;
        if (dp[n] != -1) return dp[n];

        int a = solveMemo(n - x, x, y, z, dp);
        int b = solveMemo(n - y, x, y, z, dp);
        int c = solveMemo(n - z, x, y, z, dp);

        int res = Math.max(a, Math.max(b, c));
        dp[n] = (res == Integer.MIN_VALUE) ? Integer.MIN_VALUE : 1 + res;
        return dp[n];
    }

    int solveTab(int n, int x, int y, int z) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            if (i - x >= 0 && dp[i - x] != Integer.MIN_VALUE)
                dp[i] = Math.max(dp[i], dp[i - x] + 1);
            if (i - y >= 0 && dp[i - y] != Integer.MIN_VALUE)
                dp[i] = Math.max(dp[i], dp[i - y] + 1);
            if (i - z >= 0 && dp[i - z] != Integer.MIN_VALUE)
                dp[i] = Math.max(dp[i], dp[i - z] + 1);
        }

        return Math.max(dp[n], 0);
    }

    int cutSegment(int n, int x, int y, int z, String strategy) {
        if (strategy.equals("recursive")) {
            int ans = solveRecursive(n, x, y, z);
            return Math.max(ans, 0);
        } else if (strategy.equals("memo")) {
            int[] dp = new int[n + 1];
            Arrays.fill(dp, -1);
            int ans = solveMemo(n, x, y, z, dp);
            return Math.max(ans, 0);
        } else if (strategy.equals("tab")) {
            return solveTab(n, x, y, z);
        } else {
            throw new IllegalArgumentException("Unknown strategy: " + strategy);
        }
    }

    public static void main(String[] args) {
        CutRodIntoSegmentsOfXYZ cutter = new CutRodIntoSegmentsOfXYZ();
        int n = 7, x = 5, y = 2, z = 2;

        System.out.println("Recursive: " + cutter.cutSegment(n, x, y, z, "recursive"));
        System.out.println("Memoized: " + cutter.cutSegment(n, x, y, z, "memo"));
        System.out.println("Tabulation: " + cutter.cutSegment(n, x, y, z, "tab"));
    }
}
