package BinarySearchTree.Interview;

import java.util.Vector;

/**
 * This class provides functionality to determine if there exists a pair of nodes
 * in a Binary Search Tree (BST) such that their values sum up to a given target.
 */
public class TwoSumInBinarySearchTree {

    /**
     * Generic TreeNode class representing each node of the BST.
     *
     * @param <T> A type that extends Comparable, so nodes can be ordered.
     */
    static class TreeNode<T extends Comparable<T>> {
        T val;
        TreeNode<T> left, right;

        TreeNode(T val) {
            this.val = val;
        }

        TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Performs an in-order traversal of the BST and stores the node values
     * in a vector, resulting in a sorted list of values.
     *
     * @param root The root of the BST.
     * @param v    A vector to store the values in sorted order.
     */
    void inOrder(TreeNode<Integer> root, Vector<Integer> v) {
        if (root == null) {
            return;
        }
        inOrder(root.left, v);
        v.add(root.val);
        inOrder(root.right, v);
    }

    /**
     * Determines if there exists two elements in the BST such that their sum is equal to the target.
     * This is done by first getting the in-order traversal of the BST to get sorted values, and then
     * applying the two-pointer technique.
     *
     * @param root   The root of the BST.
     * @param target The target sum to check.
     * @return True if such a pair exists, otherwise false.
     */
    boolean TwoSumInBST(TreeNode<Integer> root, int target) {
        Vector<Integer> v = new Vector<>();
        inOrder(root, v);
        int i = 0;
        int j = v.size() - 1;

        // Two-pointer approach to find if there exists a pair summing to target
        while (i < j) {
            int sum = v.get(i) + v.get(j);
            if (sum == target) {
                return true;
            } else if (sum > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TwoSumInBinarySearchTree obj = new TwoSumInBinarySearchTree();

        // Constructing the following BST:
        //        5
        //       / \
        //      3   8
        //     / \   \
        //    2   4   10
        TreeNode<Integer> root = new TreeNode<>(5,
                new TreeNode<>(3,
                        new TreeNode<>(2),
                        new TreeNode<>(4)),
                new TreeNode<>(8,
                        null,
                        new TreeNode<>(10)));

        int target = 14; // 4 + 10 = 14
        System.out.println("Two sum exists: " + obj.TwoSumInBST(root, target)); // Should print true
    }
}
