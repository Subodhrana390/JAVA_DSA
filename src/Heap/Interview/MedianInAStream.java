package Heap.Interview;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Vector;

public class MedianInAStream {

    Vector<Integer> findMedian(Vector<Integer> arr, int n) {
        Vector<Integer> ans = new Vector<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // left
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // right

        for (int i = 0; i < n; i++) {
            int num = arr.get(i);

            // Step 1: Insert into maxHeap or minHeap
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }

            // Step 2: Balance the heaps
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }

            // Step 3: Find median
            if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.size() == minHeap.size()) {
                int median = (maxHeap.peek() + minHeap.peek()) / 2;
                ans.add(median);
            } else if (!maxHeap.isEmpty()) {
                ans.add(maxHeap.peek());
            }

        }

        return ans;
    }

    public static void main(String[] args) {
        MedianInAStream stream = new MedianInAStream();
        Vector<Integer> input = new Vector<>();
        input.add(5);
        input.add(15);
        input.add(1);
        input.add(3);

        Vector<Integer> result = stream.findMedian(input, input.size());

        System.out.println("Medians: " + result);
    }
}
