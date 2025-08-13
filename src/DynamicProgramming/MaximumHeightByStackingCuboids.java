package DynamicProgramming;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class MaximumHeightByStackingCuboids {

    int maxHeight(Vector<Vector<Integer>> cuboids) {
        // Step 1: Sort each cuboid's dimensions individually
        for (Vector<Integer> c : cuboids) {
            Collections.sort(c);
        }

        // Step 2: Sort cuboids in descending order (largest first)
        Collections.sort(cuboids, new Comparator<Vector<Integer>>() {
            @Override
            public int compare(Vector<Integer> a, Vector<Integer> b) {
                if (!a.get(0).equals(b.get(0))) return b.get(0) - a.get(0);
                if (!a.get(1).equals(b.get(1))) return b.get(1) - a.get(1);
                return b.get(2) - a.get(2);
            }
        });

        int n = cuboids.size();
        int[] dp = new int[n];
        int max = 0;

        // Step 3: DP similar to LIS
        for (int i = 0; i < n; i++) {
            dp[i] = cuboids.get(i).get(2); // start with its own height
            for (int j = 0; j < i; j++) {
                if (canStack(cuboids.get(i), cuboids.get(j))) {
                    dp[i] = Math.max(dp[i], dp[j] + cuboids.get(i).get(2));
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    private boolean canStack(Vector<Integer> a, Vector<Integer> b) {
        return a.get(0) <= b.get(0) &&
                a.get(1) <= b.get(1) &&
                a.get(2) <= b.get(2);
    }

    public static void main(String[] args) {
        MaximumHeightByStackingCuboids solver = new MaximumHeightByStackingCuboids();
        Vector<Vector<Integer>> cuboids = new Vector<>();

        cuboids.add(new Vector<Integer>() {{
            add(50);
            add(45);
            add(20);
        }});
        cuboids.add(new Vector<Integer>() {{
            add(95);
            add(37);
            add(53);
        }});
        cuboids.add(new Vector<Integer>() {{
            add(45);
            add(23);
            add(12);
        }});

        System.out.println(solver.maxHeight(cuboids));
    }
}
