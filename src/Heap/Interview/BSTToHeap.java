package Heap.Interview;

import java.util.Vector;

public class BSTToHeap {

    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    // Step 1: Store inorder traversal (sorted values)
    void inorder(Node root, Vector<Integer> ans) {
        if (root == null) return;
        inorder(root.left, ans);
        ans.add(root.data);
        inorder(root.right, ans);
    }

    // Step 2: Preorder traversal to replace node data with heapified order
    void convertToMinHeap(Node root, Vector<Integer> ans, int[] index) {
        if (root == null) return;
        root.data = ans.get(index[0]);
        index[0]++;
        convertToMinHeap(root.left, ans, index);
        convertToMinHeap(root.right, ans, index);
    }

    // Final conversion method
    void convert(Node root) {
        Vector<Integer> inorderList = new Vector<>();
        inorder(root, inorderList);

        int[] index = {0};
        convertToMinHeap(root, inorderList, index);
    }

    // Utility to print tree in level order (for verification)
    void printLevelOrder(Node root) {
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp.data + " ");
            if (temp.left != null) queue.add(temp.left);
            if (temp.right != null) queue.add(temp.right);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BSTToHeap tree = new BSTToHeap();

        // Manually creating a BST
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(6);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right.left = new Node(5);
        root.right.right = new Node(7);

        // Convert BST to Min Heap
        tree.convert(root);

        // Print result
        tree.printLevelOrder(root); // Output should be level-order of a valid Min-Heap
    }
}
