package Heap.Interview;

import java.util.*;

public class SmallestRangeInKSortedList {

    static class Node {
        int data;
        int row;
        int col;

        public Node(int data, int row, int col) {
            this.data = data;
            this.row = row;
            this.col = col;
        }
    }

    int kSorted(Vector<Vector<Integer>> a, int k) {
        int mini = Integer.MAX_VALUE;
        int maxi = Integer.MIN_VALUE;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.data));

        // Initialize the heap with the first element from each list
        for (int i = 0; i < k; i++) {
            int val = a.get(i).getFirst();
            mini = Math.min(mini, val);
            maxi = Math.max(maxi, val);
            pq.add(new Node(val, i, 0));
        }

        int start = mini;
        int end = maxi;

        while (true) {
            Node temp = pq.poll();
            assert temp != null;
            mini = temp.data;

            // Update the range if it's smaller
            if (maxi - mini < end - start) {
                start = mini;
                end = maxi;
            }

            // Move to next element in the same row
            if (temp.col + 1 < a.get(temp.row).size()) {
                int nextVal = a.get(temp.row).get(temp.col + 1);
                maxi = Math.max(maxi, nextVal);
                pq.add(new Node(nextVal, temp.row, temp.col + 1));
            } else {
                break; // One list is exhausted
            }
        }

        // Range is [start, end]
        return end - start + 1; // or return new int[]{start, end};
    }

    public static void main(String[] args) {
        Vector<Vector<Integer>> lists = new Vector<>();
        lists.add(new Vector<>(Arrays.asList(4, 10, 15, 24, 26)));
        lists.add(new Vector<>(Arrays.asList(0, 9, 12, 20)));
        lists.add(new Vector<>(Arrays.asList(5, 18, 22, 30)));

        SmallestRangeInKSortedList obj = new SmallestRangeInKSortedList();
        int range = obj.kSorted(lists, 3);
        System.out.println(range); // Output: 6 (range [20, 24])

    }
}
