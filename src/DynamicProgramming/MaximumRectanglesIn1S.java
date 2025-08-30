package DynamicProgramming;

import java.util.*;

public class MaximumRectanglesIn1S {

    // ---------------- Histogram + Stack ----------------
    int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int h = (i == n ? 0 : heights[i]);
            while (!st.isEmpty() && h < heights[st.peek()]) {
                int height = heights[st.pop()];
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            st.push(i);
        }
        return maxArea;
    }

    int maximalRectangleHistogram(Vector<Vector<Character>> matrix) {
        if (matrix.isEmpty() || matrix.get(0).isEmpty()) return 0;

        int n = matrix.size();
        int m = matrix.get(0).size();
        int[] heights = new int[m];
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix.get(i).get(j) == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    // ---------------- Pure DP ----------------
    int maximalRectangleDP(Vector<Vector<Character>> matrix) {
        if (matrix.isEmpty() || matrix.get(0).isEmpty()) return 0;

        int n = matrix.size(), m = matrix.get(0).size();
        int[] height = new int[m];
        int[] left = new int[m];
        int[] right = new int[m];
        Arrays.fill(right, m);

        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            int curLeft = 0, curRight = m;

            // update height
            for (int j = 0; j < m; j++) {
                if (matrix.get(i).get(j) == '1') height[j]++;
                else height[j] = 0;
            }

            // update left boundary
            for (int j = 0; j < m; j++) {
                if (matrix.get(i).get(j) == '1') {
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }

            // update right boundary
            for (int j = m - 1; j >= 0; j--) {
                if (matrix.get(i).get(j) == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = m;
                    curRight = j;
                }
            }

            // compute area
            for (int j = 0; j < m; j++) {
                maxArea = Math.max(maxArea, (right[j] - left[j]) * height[j]);
            }
        }

        return maxArea;
    }

    // ---------------- Main ----------------
    public static void main(String[] args) {
        MaximumRectanglesIn1S obj = new MaximumRectanglesIn1S();

        Vector<Vector<Character>> matrix = new Vector<>();
        matrix.add(new Vector<>(Arrays.asList('1','0','1','0','0')));
        matrix.add(new Vector<>(Arrays.asList('1','0','1','1','1')));
        matrix.add(new Vector<>(Arrays.asList('1','1','1','1','1')));
        matrix.add(new Vector<>(Arrays.asList('1','0','0','1','0')));

        System.out.println("Max Rectangle (Histogram): " + obj.maximalRectangleHistogram(matrix));
        System.out.println("Max Rectangle (DP): " + obj.maximalRectangleDP(matrix));
    }
}
