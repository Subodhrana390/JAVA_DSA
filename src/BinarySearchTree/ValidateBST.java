package BinarySearchTree;

/**
 * Utility class to validate whether a binary tree is a Binary Search Tree (BST).
 */
public class ValidateBST {

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
     * Validates if a binary tree is a BST using the min/max constraint approach.
     *
     * @param root the root node
     * @param min  the allowed minimum value
     * @param max  the allowed maximum value
     * @return {@code true} if the subtree is a valid BST, otherwise {@code false}
     */
    boolean isBST(Node root, int min, int max) {
        if (root == null) return true;

        if (root.data <= min || root.data >= max) return false;

        return isBST(root.left, min, root.data) &&
                isBST(root.right, root.data, max);
    }

    /**
     * Public method to validate the tree as a BST.
     *
     * @param root the root node of the tree
     * @return {@code true} if it is a BST, otherwise {@code false}
     */
    boolean validBST(Node root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Main method to test the BST validation.
     */
    public static void main(String[] args) {
        ValidateBST tree = new ValidateBST();

        // Constructing a valid BST
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(2);
        root.left.right = new Node(7);
        root.right.left = new Node(12);
        root.right.right = new Node(20);

        System.out.println("Is valid BST? " + tree.validBST(root));  // Output: true

        // Introduce violation to test invalid BST
        root.right.left = new Node(8);  // This violates the BST property
        System.out.println("Is valid BST after violation? " + tree.validBST(root));  // Output: false
    }
}
