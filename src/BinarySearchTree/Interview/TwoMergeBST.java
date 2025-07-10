/**
 * File: TwoMergeBST.java
 * Author: [Your Name]
 * Date: [Insert Date]
 * Course: SWE1 - Software Engineering 1
 * Description: Merges two Binary Search Trees (BSTs) into a single BST.
 *              Uses inorder traversal + merge + insertion approach.
 */

package BinarySearchTree.Interview;

import java.util.Vector;

/**
 * Class TwoMergeBST
 * Merges two BSTs by collecting their inorders, merging them,
 * and then constructing a new BST.
 */
public class TwoMergeBST {

    /**
     * Generic TreeNode class for BST.
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
     * Performs inorder traversal and collects node values.
     *
     * @param root   the root of the BST
     * @param vector the list to collect values
     */
    void inOrder(TreeNode<Integer> root, Vector<Integer> vector) {
        if (root == null) return;
        inOrder(root.left, vector);
        vector.add(root.val);
        inOrder(root.right, vector);
    }

    /**
     * Merges two sorted vectors into one.
     *
     * @param inorder1 first sorted vector
     * @param inorder2 second sorted vector
     * @return merged sorted vector
     */
    Vector<Integer> mergeArrays(Vector<Integer> inorder1, Vector<Integer> inorder2) {
        Vector<Integer> ans = new Vector<>(inorder1.size() + inorder2.size());
        int i = 0, j = 0;
        while (i < inorder1.size() && j < inorder2.size()) {
            if (inorder1.get(i) <= inorder2.get(j)) {
                ans.add(inorder1.get(i++));
            } else {
                ans.add(inorder2.get(j++));
            }
        }

        while (i < inorder1.size()) ans.add(inorder1.get(i++));
        while (j < inorder2.size()) ans.add(inorder2.get(j++));
        return ans;
    }

    /**
     * Inserts a value into the BST.
     *
     * @param root current root
     * @param data value to insert
     * @return updated root
     */
    TreeNode<Integer> InsertInNode(TreeNode<Integer> root, int data) {
        if (root == null) return new TreeNode<>(data);
        if (data < root.val) {
            root.left = InsertInNode(root.left, data);
        } else if (data > root.val) {
            root.right = InsertInNode(root.right, data);
        }
        return root;
    }

    /**
     * Merges two BSTs into one BST.
     *
     * @param root1 root of first BST
     * @param root2 root of second BST
     * @return root of merged BST
     */
    TreeNode<Integer> mergeBST(TreeNode<Integer> root1, TreeNode<Integer> root2) {
        Vector<Integer> inOrder1 = new Vector<>();
        Vector<Integer> inOrder2 = new Vector<>();

        inOrder(root1, inOrder1);
        inOrder(root2, inOrder2);

        Vector<Integer> mergedArray = mergeArrays(inOrder1, inOrder2);

        TreeNode<Integer> newRoot = null;
        for (int val : mergedArray) {
            newRoot = InsertInNode(newRoot, val); // ✅ capture result!
        }
        return newRoot;
    }

    /**
     * Utility to print inorder traversal (for testing).
     *
     * @param root root of the tree
     */
    void printInOrder(TreeNode<Integer> root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    /**
     * Main method to test merging two BSTs.
     */
    public static void main(String[] args) {
        TwoMergeBST merger = new TwoMergeBST();

        // First BST
        TreeNode<Integer> root1 = null;
        root1 = merger.InsertInNode(root1, 3);
        root1 = merger.InsertInNode(root1, 1);
        root1 = merger.InsertInNode(root1, 5);

        // Second BST
        TreeNode<Integer> root2 = null;
        root2 = merger.InsertInNode(root2, 4);
        root2 = merger.InsertInNode(root2, 2);
        root2 = merger.InsertInNode(root2, 6);

        // Merge and print
        TreeNode<Integer> mergedRoot = merger.mergeBST(root1, root2);
        System.out.print("Inorder of Merged BST: ");
        merger.printInOrder(mergedRoot);  // Expected: 1 2 3 4 5 6
    }
}
