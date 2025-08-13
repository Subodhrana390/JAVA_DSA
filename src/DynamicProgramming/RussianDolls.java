package DynamicProgramming;

import java.util.*;

public class RussianDolls {

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });

        int[] heights = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            heights[i] = envelopes[i][1];
        }

        return lengthOfLIS(heights);
    }

    private int lengthOfLIS(int[] nums) {
        List<Integer> lis = new ArrayList<>();
        for (int num : nums) {
            int pos = Collections.binarySearch(lis, num);
            if (pos < 0) pos = -(pos + 1);
            if (pos == lis.size()) {
                lis.add(num);
            } else {
                lis.set(pos, num);
            }
        }
        return lis.size();
    }

    public static void main(String[] args) {
        RussianDolls rd = new RussianDolls();

        int[][] envelopes = {
                {5, 4},
                {6, 4},
                {6, 7},
                {2, 3}
        };

        System.out.println(rd.maxEnvelopes(envelopes));
    }

}
