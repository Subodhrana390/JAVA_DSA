package greedyAlgorithm;

import java.util.PriorityQueue;
import java.util.Vector;

public class HuffmanCoding {

    static class Node implements Comparable<Node> {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            left = right = null;
        }

        @Override
        public int compareTo(Node other) {
            return this.data - other.data;
        }
    }

    void traverse(Node root, Vector<String> ans, String temp) {
        if (root.left == null && root.right == null) {
            ans.add(temp);
            return;
        }

        if (root.left != null)
            traverse(root.left, ans, temp + "0");

        if (root.right != null)
            traverse(root.right, ans, temp + "1");
    }

    Vector<String> huffmanCodes(String S, Vector<Integer> f, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.add(new Node(f.get(i)));
        }

        while (pq.size() > 1) {
            Node n1 = pq.poll();
            Node n2 = pq.poll();

            Node newNode = new Node(n1.data + n2.data);
            newNode.left = n1;
            newNode.right = n2;
            pq.add(newNode);
        }

        Node root = pq.poll();
        Vector<String> ans = new Vector<>();
        traverse(root, ans, "");
        return ans;
    }

    public static void main(String[] args) {
        HuffmanCoding h = new HuffmanCoding();
        Vector<Integer> f = new Vector<>();
        f.add(5);
        f.add(9);
        f.add(12);
        f.add(13);
        f.add(16);
        f.add(45);
        String s = "abcdef";
        Vector<String> ans = h.huffmanCodes(s, f, s.length());

        System.out.println("Huffman Codes:");
        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.charAt(i) + ": " + ans.get(i));
        }
    }
}
