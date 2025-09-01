package DynamicProgramming;

public class WildCardPatternMatching {

    boolean solveRec(String s, String p, int i, int j) {
        if (i < 0 && j < 0) return true;

        if (i >= 0 && j < 0) return false;

        if (i < 0 && j >= 0) {
            for (int k = 0; k <= j; k++) {
                if (p.charAt(k) != '*') return false;
            }
            return true;
        }

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            return solveRec(s, p, i - 1, j - 1);
        } else if (p.charAt(j) == '*') {
            return solveRec(s, p, i - 1, j) || solveRec(s, p, i, j - 1);
        } else {
            return false;
        }
    }

    boolean solveMemo(String s, String p, int i, int j, Boolean[][] dp) {
        if (i < 0 && j < 0) return true;
        if (i >= 0 && j < 0) return false;
        if (i < 0 && j >= 0) {
            for (int k = 0; k <= j; k++) {
                if (p.charAt(k) != '*') return false;
            }
            return true;
        }

        if (dp[i][j] != null) return dp[i][j];

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            return dp[i][j] = solveMemo(s, p, i - 1, j - 1, dp);
        } else if (p.charAt(j) == '*') {
            return dp[i][j] = solveMemo(s, p, i - 1, j, dp) || solveMemo(s, p, i, j - 1, dp);
        } else {
            return dp[i][j] = false;
        }
    }

    boolean isMatchMemo(String s, String p) {
        int n = s.length(), m = p.length();
        Boolean[][] dp = new Boolean[n][m];
        return solveMemo(s, p, n - 1, m - 1, dp);
    }


    boolean isMatchTab(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][0] = true; // empty string matches empty pattern

        // initialize first row
        for (int j = 1; j <= m; j++) {
            if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 1];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }

        return dp[n][m];
    }


    boolean isMatchSpaceOptimized(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[] prev = new boolean[m + 1];
        boolean[] curr = new boolean[m + 1];

        prev[0] = true;

        for (int j = 1; j <= m; j++) {
            if (p.charAt(j - 1) == '*') prev[j] = prev[j - 1];
        }

        for (int i = 1; i <= n; i++) {
            curr[0] = false;
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    curr[j] = prev[j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    curr[j] = prev[j] || curr[j - 1];
                } else {
                    curr[j] = false;
                }
            }
            prev = curr.clone();
        }

        return prev[m];
    }

    boolean isMatch(String s, String p) {
        return solveRec(s, p, s.length() - 1, p.length() - 1);
    }

    public static void main(String[] args) {
        WildCardPatternMatching w = new WildCardPatternMatching();
        System.out.println(w.isMatch("abcde", "a*c?e"));              // Recursive
        System.out.println(w.isMatchMemo("abcde", "a*c?e"));          // Memoization
        System.out.println(w.isMatchTab("abcde", "a*c?e"));           // Tabulation
        System.out.println(w.isMatchSpaceOptimized("abcde", "a*c?e"));// Space Optimized
    }

}
