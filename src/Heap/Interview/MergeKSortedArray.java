package Heap.Interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;


public class MergeKSortedArray {
    static class Node {
        int data;
        int i;
        int j;

        Node(int data, int i, int j) {
            this.data = data;
            this.i = i;
            this.j = j;
        }
    }

    Vector<Integer> mergeKSortedArrays(Vector<Vector<Integer>> kArrays, int k) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return a.data - b.data;
            }
        });

        for (int i = 0; i < k; i++) {
            if (!kArrays.get(i).isEmpty()) {
                pq.add(new Node(kArrays.get(i).getFirst(), i, 0));
            }
        }

        Vector<Integer> result = new Vector<>();
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            result.add(curr.data);
            int i = curr.i;
            int j = curr.j;
            if (j + 1 < kArrays.get(i).size()) {
                pq.add(new Node(kArrays.get(i).get(j + 1), i, j + 1));
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Vector<Vector<Integer>> arr = new Vector<>();

        Vector<Integer> a = new Vector<>(Arrays.asList(1, 4, 5));
        Vector<Integer> b = new Vector<>(Arrays.asList(1, 3, 4));
        Vector<Integer> c = new Vector<>(Arrays.asList(2, 6));

        arr.add(a);
        arr.add(b);
        arr.add(c);

        MergeKSortedArray mergeKSortedArray = new MergeKSortedArray();

        Vector<Integer> result = mergeKSortedArray.mergeKSortedArrays(arr, arr.size());

        System.out.println("Merged Array: " + result);
    }
}
