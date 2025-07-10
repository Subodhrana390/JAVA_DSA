package BinarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The {@code BST} class represents a simple Binary Search Tree (BST) with
 * insertion, search, traversal, min/max value, and deletion functionality.
 */
public class BST {

    /**
     * Represents a node in the Binary Search Tree.
     */
    static class Node {
        int data;
        Node left, right;

        /**
         * Constructs a new {@code Node} with the specified data.
         *
         * @param data the value to store in the node
         */
        public Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    /**
     * Inserts a new value into the BST.
     *
     * @param root the root node of the BST
     * @param data the value to insert
     * @return the root node of the updated BST
     */
    Node InsertInNode(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }
        if (data < root.data) {
            root.left = InsertInNode(root.left, data);
        } else if (data > root.data) {
            root.right = InsertInNode(root.right, data);
        }
        return root; // Duplicate values are ignored
    }

    /**
     * Performs a level-order traversal (Breadth-First Search) of the BST.
     * Prints each node's value in level-order.
     *
     * @param root the root node of the BST
     */
    void LevelOrderTraversal(Node root) {
        if (root == null) return;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node curr = q.poll();
            System.out.print(curr.data + " ");

            if (curr.left != null) q.offer(curr.left);
            if (curr.right != null) q.offer(curr.right);
        }
    }

    /**
     * Performs an in-order traversal (Left-Root-Right) of the BST.
     * Prints values in sorted order.
     *
     * @param root the root node of the BST
     */
    void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    /**
     * Searches for a given value in the BST.
     *
     * @param root the root node of the BST
     * @param data the value to search for
     * @return {@code true} if the value exists in the BST, otherwise {@code false}
     */
    boolean search(Node root, int data) {
        if (root == null) return false;
        if (data == root.data) return true;
        if (data < root.data) return search(root.left, data);
        return search(root.right, data);
    }

    /**
     * Returns the node with the minimum value in the BST.
     *
     * @param root the root node
     * @return the node with the smallest value
     */
    Node minValue(Node root) {
        if (root == null) return null;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    /**
     * Returns the node with the maximum value in the BST.
     *
     * @param root the root node
     * @return the node with the largest value
     */
    Node maxValue(Node root) {
        if (root == null) return null;
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    /**
     * Deletes a node with the specified key from the BST.
     *
     * @param root the root node
     * @param key  the value to delete
     * @return the root of the updated BST
     */
    Node deleteNode(Node root, int key) {
        if (root == null) return null;

        if (key < root.data) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.data) {
            root.right = deleteNode(root.right, key);
        } else {
            // Node found
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Two children: replace with in-order successor (smallest in right subtree)
            Node minNode = minValue(root.right);
            root.data = minNode.data;
            root.right = deleteNode(root.right, minNode.data);
        }
        return root;
    }

    /**
     * Main method to demonstrate BST operations.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        BST tree = new BST();
        Node root = null;
        int[] values = {50, 30, 70, 20, 40, 60, 80};

        // Insert values
        for (int value : values) {
            root = tree.InsertInNode(root, value);
        }

        // Level-order traversal
        System.out.print("Level-order traversal: ");
        tree.LevelOrderTraversal(root);

        // In-order traversal
        System.out.print("\nIn-order traversal (sorted): ");
        tree.inOrder(root);

        // Min and Max values
        System.out.println("\nMin value: " + tree.minValue(root).data);
        System.out.println("Max value: " + tree.maxValue(root).data);

        // Search for a value
        int searchKey = 20;
        System.out.println("Search " + searchKey + ": " + tree.search(root, searchKey));

        // Delete a value and re-traverse
        int deleteKey = 30;
        System.out.println("Deleting node: " + deleteKey);
        root = tree.deleteNode(root, deleteKey);
        System.out.print("Level-order after deletion: ");
        tree.LevelOrderTraversal(root);
    }
}
