package BinarySearchTree.Interview;

/**
 * This class provides a method to find the kth largest element in a Binary Search Tree (BST).
 * It uses reverse in-order traversal to access elements in descending order.
 */
public class KthLargest {

    /**
     * Definition for a binary tree node.
     */
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Helper class to track the number of nodes visited during traversal.
     */
    static class Index {
        int i = 0;
    }

    /**
     * Performs reverse in-order traversal (right → root → left) to find the kth largest element.
     *
     * @param root the current node
     * @param index the current count of visited nodes
     * @param k the kth position to find
     * @return the kth largest value or -1 if not found
     */
    private int solve(TreeNode root, Index index, int k) {
        if (root == null) {
            return -1;
        }

        // Traverse right subtree first (larger values)
        int right = solve(root.right, index, k);
        if (right != -1) {
            return right;
        }

        // Visit current node
        index.i++;
        if (index.i == k) {
            return root.val;
        }

        // Traverse left subtree
        return solve(root.left, index, k);
    }

    /**
     * Finds the kth largest element in a BST.
     *
     * @param root the root of the BST
     * @param k the kth largest position
     * @return the value of the kth largest node
     */
    public int kthLargestNode(TreeNode root, int k) {
        Index index = new Index();
        return solve(root, index, k);
    }

    /**
     * Demonstrates usage of kthLargestNode() with a sample tree.
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

        KthLargest kl = new KthLargest();
        int k = 3;
        System.out.println("The " + k + "rd largest element is: " + kl.kthLargestNode(root, k));
        // Expected output: 4
    }
}
