package greedyAlgorithm;

import java.util.Collections;
import java.util.Vector;

public class ChocolateDistribution {

    long findMinDiff(Vector<Integer> a, int n, int m) {
        // Edge case: Not enough packets
        if (m == 0 || n == 0 || m > n) return -1;

        // Sort the vector
        Collections.sort(a);

        // Initialize minDiff to a large value
        long minDiff = Long.MAX_VALUE;

        // Traverse and find the minimum difference
        for (int i = 0; i + m - 1 < n; i++) {
            long diff = a.get(i + m - 1) - a.get(i);
            if (diff < minDiff) {
                minDiff = diff;
            }
        }

        return minDiff;
    }

    public static void main(String[] args) {
        ChocolateDistribution obj = new ChocolateDistribution();

        Vector<Integer> packets = new Vector<>();
        packets.add(7);
        packets.add(3);
        packets.add(2);
        packets.add(4);
        packets.add(9);
        packets.add(12);
        packets.add(56);

        int n = packets.size();
        int m = 3;

        long result = obj.findMinDiff(packets, n, m);
        System.out.println("Minimum difference: " + result);  // Expected: 2
    }
}
