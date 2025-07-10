package Heap.Interview;

import java.util.Vector;

public class MergeTwoBinaryMaxHeap {

    // Heapify a subtree rooted with index `i` in a 0-based max-heap
    void heapify(Vector<Integer> arr, int size, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && arr.get(left) > arr.get(largest)) {
            largest = left;
        }

        if (right < size && arr.get(right) > arr.get(largest)) {
            largest = right;
        }

        if (largest != i) {
            int temp = arr.get(i);
            arr.set(i, arr.get(largest));
            arr.set(largest, temp);
            heapify(arr, size, largest);
        }
    }

    // Merge two binary max heaps into a new max heap
    public Vector<Integer> mergeHeaps(int[] a, int[] b, int n, int m) {
        Vector<Integer> merged = new Vector<>();

        // Add all elements from a[]
        for (int i = 0; i < n; i++) {
            merged.add(a[i]);
        }

        // Add all elements from b[]
        for (int i = 0; i < m; i++) {
            merged.add(b[i]);
        }

        int size = merged.size();

        //heapify the array from last non-leaf node to root
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapify(merged, size, i);
        }

        return merged;
    }

    public static void main(String[] args) {
        MergeTwoBinaryMaxHeap merger = new MergeTwoBinaryMaxHeap();

        int[] a = {10, 5, 6, 2};
        int[] b = {12, 7, 9};

        Vector<Integer> mergedHeap = merger.mergeHeaps(a, b, a.length, b.length);

        System.out.println("Merged Max Heap: " + mergedHeap);
    }
}
