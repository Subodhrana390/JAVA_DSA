package DynamicProgramming;

import java.util.Arrays;

public class EditDistance {

    // ----------- 1. Pure Recursion -----------
    int solveRec(String a, String b, int i, int j) {
        if (i == a.length()) return b.length() - j;  // need to insert remaining
        if (j == b.length()) return a.length() - i;  // need to delete remaining

        if (a.charAt(i) == b.charAt(j)) {
            return solveRec(a, b, i + 1, j + 1);
        } else {
            int insert = 1 + solveRec(a, b, i, j + 1);       // insert b[j] into a
            int delete = 1 + solveRec(a, b, i + 1, j);       // delete a[i]
            int replace = 1 + solveRec(a, b, i + 1, j + 1);  // replace a[i] with b[j]
            return Math.min(insert, Math.min(delete, replace));
        }
    }

    // ----------- 2. Memoization (Top-Down DP) -----------
    int solveMemo(String a, String b, int i, int j, int[][] dp) {
        if (i == a.length()) return b.length() - j;
        if (j == b.length()) return a.length() - i;

        if (dp[i][j] != -1) return dp[i][j];

        if (a.charAt(i) == b.charAt(j)) {
            dp[i][j] = solveMemo(a, b, i + 1, j + 1, dp);
        } else {
            int insert = 1 + solveMemo(a, b, i, j + 1, dp);
            int delete = 1 + solveMemo(a, b, i + 1, j, dp);
            int replace = 1 + solveMemo(a, b, i + 1, j + 1, dp);
            dp[i][j] = Math.min(insert, Math.min(delete, replace));
        }
        return dp[i][j];
    }

    int minDistanceMemo(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        for (int[] row : dp) Arrays.fill(row, -1);
        return solveMemo(word1, word2, 0, 0, dp);
    }

    // ----------- 3. Tabulation (Bottom-Up DP) -----------
    int minDistanceTab(String a, String b) {
        int n = a.length(), m = b.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) dp[i][m] = n - i;  // need to delete
        for (int j = 0; j <= m; j++) dp[n][j] = m - j;  // need to insert

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (a.charAt(i) == b.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    int insert = 1 + dp[i][j + 1];
                    int delete = 1 + dp[i + 1][j];
                    int replace = 1 + dp[i + 1][j + 1];
                    dp[i][j] = Math.min(insert, Math.min(delete, replace));
                }
            }
        }
        return dp[0][0];
    }

    // ----------- 4. Space Optimized Tabulation -----------
    int minDistanceSO(String a, String b) {
        int n = a.length(), m = b.length();
        int[] next = new int[m + 1];
        int[] curr = new int[m + 1];

        for (int j = 0; j <= m; j++) next[j] = m - j;

        for (int i = n - 1; i >= 0; i--) {
            curr[m] = n - i;
            for (int j = m - 1; j >= 0; j--) {
                if (a.charAt(i) == b.charAt(j)) {
                    curr[j] = next[j + 1];
                } else {
                    int insert = 1 + curr[j + 1];
                    int delete = 1 + next[j];
                    int replace = 1 + next[j + 1];
                    curr[j] = Math.min(insert, Math.min(delete, replace));
                }
            }
            next = curr.clone();
        }
        return next[0];
    }

    public static void main(String[] args) {
        EditDistance ed = new EditDistance();
        String w1 = "horse", w2 = "ros";

        System.out.println("Recursion: " + ed.solveRec(w1, w2, 0, 0));
        System.out.println("Memoization: " + ed.minDistanceMemo(w1, w2));
        System.out.println("Tabulation: " + ed.minDistanceTab(w1, w2));
        System.out.println("Space Optimized: " + ed.minDistanceSO(w1, w2));
    }
}
