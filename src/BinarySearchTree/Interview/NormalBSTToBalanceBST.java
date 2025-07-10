package BinarySearchTree.Interview;

import java.util.Vector;

/**
 * This class provides functionality to convert a normal (unbalanced) BST
 * into a balanced Binary Search Tree using in-order traversal and reconstruction.
 */
public class NormalBSTToBalanceBST {

    /**
     * Generic TreeNode class representing each node of the BST.
     *
     * @param <T> A type that extends Comparable for ordering.
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
     * Performs in-order traversal of a BST to collect sorted node values.
     *
     * @param root The root of the BST.
     * @param v    Vector to store the values.
     */
    void inOrder(TreeNode<Integer> root, Vector<Integer> v) {
        if (root == null) return;
        inOrder(root.left, v);
        v.add(root.val);
        inOrder(root.right, v);
    }

    /**
     * Recursively builds a balanced BST from a sorted list of values.
     *
     * @param s Start index of the vector.
     * @param e End index of the vector.
     * @param v Vector containing sorted values.
     * @return Root of the balanced BST.
     */
    TreeNode<Integer> inorderToBST(int s, int e, Vector<Integer> v) {
        if (s > e) return null;

        int mid = (s + e) / 2;
        TreeNode<Integer> root = new TreeNode<>(v.get(mid));
        root.left = inorderToBST(s, mid - 1, v);
        root.right = inorderToBST(mid + 1, e, v);
        return root;
    }

    /**
     * Converts an unbalanced BST into a balanced BST.
     *
     * @param root Root of the unbalanced BST.
     * @return Root of the new balanced BST.
     */
    TreeNode<Integer> balanceBST(TreeNode<Integer> root) {
        Vector<Integer> v = new Vector<>();
        inOrder(root, v);
        return inorderToBST(0, v.size() - 1, v);
    }

    /**
     * Utility method to print the in-order traversal of a BST.
     */
    void printInOrder(TreeNode<Integer> root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    public static void main(String[] args) {
        NormalBSTToBalanceBST obj = new NormalBSTToBalanceBST();

        // Example of a skewed BST (right-heavy):
        //    10
        //      \
        //       20
        //         \
        //          30
        //            \
        //             40
        TreeNode<Integer> unbalanced = new TreeNode<>(10,
                null,
                new TreeNode<>(20,
                        null,
                        new TreeNode<>(30,
                                null,
                                new TreeNode<>(40))));

        TreeNode<Integer> balancedRoot = obj.balanceBST(unbalanced);

        System.out.print("In-order of balanced BST: ");
        obj.printInOrder(balancedRoot); // Output should be sorted and tree balanced
    }
}
