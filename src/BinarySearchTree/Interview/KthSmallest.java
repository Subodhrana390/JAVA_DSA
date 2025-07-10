package BinarySearchTree.Interview;

/**
 * This class provides a method to find the kth smallest element in a Binary Search Tree (BST).
 * It uses in-order traversal to access elements in sorted order.
 */
public class KthSmallest {

    /**
     * Definition for a binary tree node.
     */
    static class TreeNode {
        int val;
        TreeNode left, right;

        /**
         * Default constructor.
         */
        TreeNode() {}

        /**
         * Constructs a TreeNode with a specified value.
         *
         * @param val the value of the node
         */
        TreeNode(int val) {
            this.val = val;
        }

        /**
         * Constructs a TreeNode with a specified value, left child, and right child.
         *
         * @param val the value of the node
         * @param left the left child
         * @param right the right child
         */
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Helper class to keep track of the number of nodes visited during in-order traversal.
     */
    static class Index {
        int i = 0;
    }

    /**
     * Recursively traverses the BST in in-order and returns the kth smallest element.
     *
     * @param root the current node
     * @param index an object tracking the in-order index
     * @param k the kth position to find
     * @return the value of the kth smallest node or -1 if not found
     */
    private int solve(TreeNode root, Index index, int k) {
        if (root == null) {
            return -1;
        }

        // Traverse left subtree
        int left = solve(root.left, index, k);
        if (left != -1) {
            return left;
        }

        // Visit current node
        index.i++;
        if (index.i == k) {
            return root.val;
        }

        // Traverse right subtree
        return solve(root.right, index, k);
    }

    /**
     * Finds the kth smallest element in the BST.
     *
     * @param root the root of the BST
     * @param k the kth smallest element to find
     * @return the value of the kth smallest node
     */
    public int kthSmallest(TreeNode root, int k) {
        Index index = new Index();
        return solve(root, index, k);
    }

    /**
     * Main method to demonstrate the usage of kthSmallest().
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        /*
         * Example BST:
         *        5
         *       / \
         *      3   6
         *     / \
         *    2   4
         *   /
         *  1
         */
        TreeNode root = new TreeNode(5,
                new TreeNode(3,
                        new TreeNode(2,
                                new TreeNode(1), null),
                        new TreeNode(4)),
                new TreeNode(6));

        KthSmallest finder = new KthSmallest();
        int k = 3;
        System.out.println("The " + k + "rd smallest element is: " + finder.kthSmallest(root, k));
        // Expected output: 3
    }
}
