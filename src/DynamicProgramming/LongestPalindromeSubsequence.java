package DynamicProgramming;

public class LongestPalindromeSubsequence {

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

    int lps(String a) {
        StringBuilder sb = new StringBuilder(a);
        String revStr = sb.reverse().toString();
        return lcsSpaceOpt(a, revStr);
    }

    public static void main(String[] args) {
        LongestPalindromeSubsequence lps = new LongestPalindromeSubsequence();
        System.out.println(lps.lps("bbbab"));
        System.out.println(lps.lps("cbbd"));
    }
}
