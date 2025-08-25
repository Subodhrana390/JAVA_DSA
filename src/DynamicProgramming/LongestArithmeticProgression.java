package DynamicProgramming;

import java.util.HashMap;

public class LongestArithmeticProgression {

    int extendRec(int idx, int diff, int[] A) {
        for (int i = idx - 1; i >= 0; i--)
            if (A[idx] - A[i] == diff)
                return 1 + extendRec(i, diff, A);
        return 0;
    }

    int lengthOfLongestAPRec(int[] A, int n) {
        if (n <= 2) return n;
        int ans = 2;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                ans = Math.max(ans, 2 + extendRec(i, A[j] - A[i], A));
        return ans;
    }

    int extendDP(int idx, int diff, int[] A, HashMap<String, Integer> dp) {
        String key = idx + "," + diff;
        if (dp.containsKey(key)) return dp.get(key);

        int best = 0;
        for (int i = idx - 1; i >= 0; i--) {
            if (A[idx] - A[i] == diff) {
                best = 1 + extendDP(i, diff, A, dp);
                break;
            }
        }
        dp.put(key, best);
        return best;
    }

    int lengthOfLongestAPDP(int[] A, int n) {
        if (n <= 2) return n;
        int ans = 2;
        HashMap<String, Integer> dp = new HashMap<>();

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                int diff = A[j] - A[i];
                ans = Math.max(ans, 2 + extendDP(i, diff, A, dp));
            }
        return ans;
    }

    int lengthOfLongestAPTab(int[] A, int n) {
        if (n <= 2) return n;
        int ans = 2;
        HashMap<Integer, Integer> dp = new HashMap<>();

        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++) {
                int diff = A[i] - A[j];
                int cnt = 1;
                if (dp.containsKey(diff)) cnt = dp.get(diff);
                dp.put(diff, 1 + cnt);
                ans = Math.max(ans, dp.get(diff));
            }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = {1, 7, 10, 15, 27, 29};
        LongestArithmeticProgression lap = new LongestArithmeticProgression();
        System.out.println("Pure Recursion: " + lap.lengthOfLongestAPRec(A, A.length));
        System.out.println("Recursion + Memoization: " + lap.lengthOfLongestAPDP(A, A.length));
        System.out.println("Tabulation: " + lap.lengthOfLongestAPTab(A, A.length));
    }
}
