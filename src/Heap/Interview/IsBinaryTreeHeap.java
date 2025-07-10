package Heap.Interview;

public class IsBinaryTreeHeap {

    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = right = null;
        }
    }

    // Count total nodes in the tree
    int countNode(Node root) {
        if (root == null) return 0;
        return 1 + countNode(root.left) + countNode(root.right);
    }

    // Check if tree is a Complete Binary Tree (CBT)
    boolean isCBT(Node root, int index, int nodeCount) {
        if (root == null) return true;
        if (index >= nodeCount) return false;

        return isCBT(root.left, 2 * index + 1, nodeCount) &&
                isCBT(root.right, 2 * index + 2, nodeCount); // fixed index
    }

    // Check if tree follows Max-Heap property
    boolean isMaxOrder(Node root) {
        // Leaf node
        if (root.left == null && root.right == null) {
            return true;
        }

        // Only left child exists
        if (root.right == null) {
            return (root.data >= root.left.data) && isMaxOrder(root.left);
        }

        // Both children exist
        if (root.left != null && root.right != null) {
            return (root.data >= root.left.data &&
                    root.data >= root.right.data &&
                    isMaxOrder(root.left) &&
                    isMaxOrder(root.right));
        }

        return false; // Right child exists but left does not (invalid for heap)
    }

    // Check if tree is a Max-Heap
    boolean isHeap(Node root) {
        int index = 0;
        int totalCount = countNode(root);
        return isCBT(root, index, totalCount) && isMaxOrder(root);
    }

    public static void main(String[] args) {
        IsBinaryTreeHeap tree = new IsBinaryTreeHeap();

        Node root = new Node(10);
        root.left = new Node(9);
        root.right = new Node(8);
        root.left.left = new Node(7);
        root.left.right = new Node(6);
        root.right.left = new Node(5);
        root.right.right = new Node(4);

        System.out.println("Is Binary Tree a Max Heap? " + tree.isHeap(root)); // true
    }
}
