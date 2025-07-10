package Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MyPriorityQueue {
    public static void main(String[] args) {

        // ✅ Min-Heap (Default behavior)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(20);

        System.out.println("Min-Heap:");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " "); // Output: 5 10 20
        }
        System.out.println(); // Line break

        // ✅ Max-Heap (Using reverseOrder comparator)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(20);

        System.out.println("Max-Heap:");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " "); // Output: 20 10 5
        }
    }
}
