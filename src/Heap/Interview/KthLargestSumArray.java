package Heap.Interview;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Vector;

public class KthLargestSumArray {

    int getKthLargest(Vector<Integer> arr, int k) {
        Vector<Integer> sumStore = new Vector<>();
        int n = arr.size();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr.get(j);
                sumStore.add(sum);
            }
        }
        Collections.sort(sumStore);
        return sumStore.get(sumStore.size() - k);
    }

    public int maxSubArray(Vector<Integer> nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int size = nums.size();
        for (int i = 0; i < size; i++) {
            int sum = 0;
            for (int j = i; j < size; j++) {
                sum += nums.get(j);
                if (pq.size() < k) {
                    pq.add(sum);
                } else {
                    if (pq.peek() < sum) {
                        pq.poll();
                        pq.add(sum);
                    }
                }
            }
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        Vector<Integer> arr = new Vector<>();
        Collections.addAll(arr, 2, 1, 3);
        int k = 2;
        KthLargestSumArray obj = new KthLargestSumArray();
        int result = obj.maxSubArray(arr, k);
        System.out.println("K-th Largest Subarray Sum = " + result);
    }
}
