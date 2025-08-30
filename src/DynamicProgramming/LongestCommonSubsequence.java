package DynamicProgramming;

import java.util.Arrays;

public class LongestCommonSubsequence {

    // ----------- 1. Pure Recursion -----------
    int solveRec(String a, String b, int i, int j) {
        if (i == a.length() || j == b.length())
            return 0;

        if (a.charAt(i) == b.charAt(j)) {
            return 1 + solveRec(a, b, i + 1, j + 1);
        } else {
            return Math.max(solveRec(a, b, i + 1, j),
                    solveRec(a, b, i, j + 1));
        }
    }

    // ----------- 2. Memoization (Top-Down DP) -----------
    int solveMemo(String a, String b, int i, int j, int[][] dp) {
        if (i == a.length() || j == b.length())
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        if (a.charAt(i) == b.charAt(j)) {
            dp[i][j] = 1 + solveMemo(a, b, i + 1, j + 1, dp);
        } else {
            dp[i][j] = Math.max(solveMemo(a, b, i + 1, j, dp),
                    solveMemo(a, b, i, j + 1, dp));
        }
        return dp[i][j];
    }

    int lcsMemo(String a, String b) {
        int n1 = a.length(), n2 = b.length();
        int[][] dp = new int[n1][n2];
        for (int[] row : dp) Arrays.fill(row, -1);

        return solveMemo(a, b, 0, 0, dp);
    }

    // ----------- 3. Tabulation (Bottom-Up DP) -----------
    int lcsTab(String a, String b) {
        int n1 = a.length(), n2 = b.length();
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                if (a.charAt(i) == b.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }

    // ----------- 4. Space Optimized Tabulation -----------
    int lcsSpaceOpt(String a, String b) {
        int n1 = a.length(), n2 = b.length();
        int[] next = new int[n2 + 1];
        int[] curr = new int[n2 + 1];

        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                if (a.charAt(i) == b.charAt(j)) {
                    curr[j] = 1 + next[j + 1];
                } else {
                    curr[j] = Math.max(next[j], curr[j + 1]);
                }
            }
            next = curr.clone();
        }
        return next[0];
    }

    // ----------- Main Testing -----------
    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();

        String a = "abcde", b = "ace";

        System.out.println("Recursion: " + lcs.solveRec(a, b, 0, 0));
        System.out.println("Memoization: " + lcs.lcsMemo(a, b));
        System.out.println("Tabulation: " + lcs.lcsTab(a, b));
        System.out.println("Space Optimized: " + lcs.lcsSpaceOpt(a, b));
    }
}
