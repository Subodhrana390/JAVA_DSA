package BinarySearchTree.Interview;

import java.util.Vector;

/**
 * This class provides functionality to flatten a Binary Search Tree (BST)
 * into a sorted singly linked list using only right pointers.
 */
public class FlattenBSTtoSortedLL {

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
     * Performs in-order traversal of the BST and stores values in a vector.
     *
     * @param root The root of the BST.
     * @param v    A vector to collect the in-order traversal.
     */
    void inOrder(TreeNode<Integer> root, Vector<Integer> v) {
        if (root == null) return;
        inOrder(root.left, v);
        v.add(root.val);
        inOrder(root.right, v);
    }

    /**
     * Flattens the BST to a sorted linked list using right pointers.
     * Left pointers of all nodes will be set to null.
     *
     * NOTE: This function does not modify the original tree in place.
     * It just constructs a new flattened tree structure.
     *
     * @param root The root of the BST to flatten.
     * @return The head of the newly created flattened linked list.
     */
    TreeNode<Integer> flatten(TreeNode<Integer> root) {
        Vector<Integer> v = new Vector<>();
        inOrder(root, v);

        if (v.isEmpty()) return null;

        TreeNode<Integer> newRoot = new TreeNode<>(v.getFirst());
        TreeNode<Integer> curr = newRoot;

        for (int i = 1; i < v.size(); i++) {
            TreeNode<Integer> temp = new TreeNode<>(v.get(i));
            curr.left = null;
            curr.right = temp;
            curr = temp;
        }
        // Ensure the last node is properly terminated
        curr.left = null;
        curr.right = null;

        return newRoot;
    }

    /**
     * Utility method to print the flattened list.
     */
    void printFlattenedList(TreeNode<Integer> root) {
        TreeNode<Integer> curr = root;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FlattenBSTtoSortedLL obj = new FlattenBSTtoSortedLL();

        // Constructing the BST:
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

        TreeNode<Integer> flattenedHead = obj.flatten(root);

        System.out.print("Flattened BST to sorted linked list: ");
        obj.printFlattenedList(flattenedHead);
    }
}
