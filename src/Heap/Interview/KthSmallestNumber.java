package Heap.Interview;

import java.util.PriorityQueue;

public class KthSmallestNumber {

    // Approach 1: Min-Heap (Default PriorityQueue)
    // Time Complexity: O(n + k log n)
    void Approach1() {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int k = 3;

        // Create a min-heap (PriorityQueue in Java is a min-heap by default)
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : arr) {
            queue.add(num); // Insert all elements into the heap
        }

        // Poll (remove) the smallest element k times
        int kthSmallest = -1;
        for (int i = 0; i < k; i++) {
            kthSmallest = queue.poll(); // Each poll gives the next smallest
        }

        System.out.println(k + "th smallest element is: " + kthSmallest);
    }

    // Approach 2: Max-Heap of size k
    // Efficient when k is much smaller than n
    // Time Complexity: O(k + (n-k) * log k)
    int approach2(int[] arr, int l, int r, int k) {
        // Create a max-heap by reversing the comparator
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);

        // Insert first k elements into max-heap
        for (int i = 0; i < k; i++) {
            queue.add(arr[i]);
        }

        // Traverse the rest of the array
        for (int i = k; i <= r; i++) {
            // If the current element is smaller than the root (max in heap)
            if (queue.peek() > arr[i]) {
                queue.poll();       // Remove the largest of the k elements
                queue.add(arr[i]); // Insert the smaller current element
            }
        }

        // Top of max-heap will now be the k-th smallest element
        return queue.peek();
    }

    public static void main(String[] args) {
        KthSmallestNumber obj = new KthSmallestNumber();

        // Run Approach 1
        obj.Approach1();

        // Run Approach 2
        int[] arr = {7, 10, 3, 20, 15};
        int k = 4;
        int kthSmallest = obj.approach2(arr, 0, arr.length - 1, k);
        System.out.println(k + "th smallest element using Approach2 is: " + kthSmallest);
    }
}
