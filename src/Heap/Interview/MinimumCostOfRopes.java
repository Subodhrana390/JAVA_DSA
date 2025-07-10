package Heap.Interview;

import java.util.PriorityQueue;

public class MinimumCostOfRopes {

    public static int minCost(int[] ropes) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add all ropes to min-heap
        for (int rope : ropes) {
            minHeap.add(rope);
        }

        int totalCost = 0;

        // Combine two smallest ropes until one rope is left
        while (minHeap.size() > 1) {
            int first = minHeap.poll(); // smallest
            int second = minHeap.poll(); // second smallest

            int cost = first + second;
            totalCost += cost;

            minHeap.add(cost); // add the new combined rope
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int[] ropes = {4, 3, 2, 6};
        System.out.println("Minimum cost to connect ropes: " + minCost(ropes));
    }
}
