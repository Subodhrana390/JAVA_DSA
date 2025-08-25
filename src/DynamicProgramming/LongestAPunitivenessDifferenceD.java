package DynamicProgramming;

import java.util.HashMap;

public class LongestAPunitivenessDifferenceD {

    int LongestSubSequence(int[] A, int difference) {
        int n = A.length;
        if (n == 0) return 0;

        int ans = 1;
        HashMap<Integer, Integer> dp = new HashMap<>();

        for (int x : A) {
            int prev = dp.getOrDefault(x - difference, 0);
            int curr = prev + 1;
            dp.put(x, curr);
            ans = Math.max(ans, curr);
        }
        return ans;
    }

    public static void main(String[] args) {
        LongestAPunitivenessDifferenceD obj = new LongestAPunitivenessDifferenceD();
        int[] A = {1, 5, 7, 8, 5, 3, 4, 2, 1};
        int difference = -2;
        System.out.println(obj.LongestSubSequence(A, difference));
    }
}
