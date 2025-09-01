package greedyAlgorithm;

import java.util.PriorityQueue;

public class MinimumCostOfRopes {

    long minCost(long[] arr, long n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.add(arr[i]);
        }

        int cost = 0;
        while (pq.size() > 1) {
            long first = pq.poll();
            long second = pq.poll();
            pq.add(first + second);
            cost += (int) (first + second);
        }
        return cost;
    }

    public static void main(String[] args) {
        long[] arr = {4, 3, 2, 6};
        MinimumCostOfRopes minimumCostOfRopes = new MinimumCostOfRopes();
        System.out.println(minimumCostOfRopes.minCost(arr, 4));

    }
}
