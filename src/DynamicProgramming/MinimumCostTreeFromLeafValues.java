package DynamicProgramming;

import java.util.HashMap;
import java.util.Vector;

public class MinimumCostTreeFromLeafValues {

    static class Pair {
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return first == p.first && second == p.second;
        }

        @Override
        public int hashCode() {
            return 31 * first + second;
        }
    }

    HashMap<Pair, Integer> maxLeaf = new HashMap<>();
    HashMap<Pair, Integer> dp = new HashMap<>();

    int mctFromLeafValues(Vector<Integer> arr) {
        int n = arr.size();

        // Precompute maxLeaf[i][j]
        for (int i = 0; i < n; i++) {
            int maxVal = arr.get(i);
            for (int j = i; j < n; j++) {
                maxVal = Math.max(maxVal, arr.get(j));
                maxLeaf.put(new Pair(i, j), maxVal);
            }
        }

        return solve(arr, 0, n - 1);
    }

    private int solve(Vector<Integer> arr, int i, int j) {
        if (i == j) return 0; // single leaf = no cost

        Pair key = new Pair(i, j);
        if (dp.containsKey(key)) return dp.get(key);

        int ans = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            int left = solve(arr, i, k);
            int right = solve(arr, k + 1, j);

            int rootCost = maxLeaf.get(new Pair(i, k)) * maxLeaf.get(new Pair(k + 1, j));
            ans = Math.min(ans, left + right + rootCost);
        }

        dp.put(key, ans);
        return ans;
    }

    public static void main(String[] args) {
        MinimumCostTreeFromLeafValues obj = new MinimumCostTreeFromLeafValues();
        Vector<Integer> arr = new Vector<>();
        arr.add(6);
        arr.add(2);
        arr.add(4);

        System.out.println("Minimum Cost = " + obj.mctFromLeafValues(arr));
    }
}
