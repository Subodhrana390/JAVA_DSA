package DynamicProgramming;

import java.util.Collections;
import java.util.Vector;

public class MinScoreTriangulationOfPolygon {

    // --- Pure Recursive Solution ---
    int solveRec(Vector<Integer> v, int i, int j) {
        if (i + 1 == j) return 0;

        int ans = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            int cost = v.get(i) * v.get(j) * v.get(k);
            int left = solveRec(v, i, k);
            int right = solveRec(v, k, j);
            ans = Math.min(ans, cost + left + right);
        }
        return ans;
    }

    // --- Recursive + Memoization Solution ---
    int solveMemo(Vector<Integer> v, int i, int j, Vector<Vector<Integer>> dp) {
        if (i + 1 == j) return 0;

        if (dp.get(i).get(j) != -1) return dp.get(i).get(j);

        int ans = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            int cost = v.get(i) * v.get(j) * v.get(k);
            int left = solveMemo(v, i, k, dp);
            int right = solveMemo(v, k, j, dp);
            ans = Math.min(ans, cost + left + right);
        }

        dp.get(i).set(j, ans);
        return ans;
    }

    // --- Tabulation / Bottom-Up DP Solution ---
    int solveTab(Vector<Integer> v) {
        int n = v.size();
        Vector<Vector<Integer>> dp = new Vector<>();
        for (int i = 0; i < n; i++) {
            Vector<Integer> row = new Vector<>(Collections.nCopies(n, 0));
            dp.add(row);
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                int ans = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    int cost = v.get(i) * v.get(j) * v.get(k);
                    int left = dp.get(i).get(k);
                    int right = dp.get(k).get(j);
                    ans = Math.min(ans, cost + left + right);
                }
                dp.get(i).set(j, ans);
            }
        }

        return dp.get(0).get(n - 1);
    }

    // --- Master Function ---
    void minScoreTriangulationAll(Vector<Integer> values) {
        int n = values.size();

        // 1️⃣ Pure Recursion
        int resRec = solveRec(values, 0, n - 1);
        System.out.println("Recursion Only: " + resRec);

        // 2️⃣ Recursion + Memoization
        Vector<Vector<Integer>> dpMemo = new Vector<>();
        for (int i = 0; i < n; i++) {
            dpMemo.add(new Vector<>(Collections.nCopies(n, -1)));
        }
        int resMemo = solveMemo(values, 0, n - 1, dpMemo);
        System.out.println("Memoization: " + resMemo);

        // 3️⃣ Tabulation / Bottom-Up
        int resTab = solveTab(values);
        System.out.println("Tabulation: " + resTab);
    }

    public static void main(String[] args) {
        MinScoreTriangulationOfPolygon obj = new MinScoreTriangulationOfPolygon();

        Vector<Integer> values = new Vector<>();
        values.add(1);
        values.add(3);
        values.add(1);
        values.add(4);
        values.add(1);
        values.add(5);

        obj.minScoreTriangulationAll(values);
    }
}
