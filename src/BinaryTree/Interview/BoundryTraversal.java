package BinaryTree.Interview;

import java.util.Vector;

/**
 * A class that provides functionality to perform a boundary traversal on a binary tree.
 * The boundary traversal includes:
 * 1. The left boundary (excluding leaves),
 * 2. All the leaf nodes,
 * 3. The right boundary (excluding leaves, printed in bottom-up order).
 */
public class BoundryTraversal {

    /**
     * Represents a node of the binary tree.
     */
    static class Node {
        int data;
        Node left, right;
    }

    /**
     * Traverses the left boundary of the tree (excluding leaf nodes) and adds them to the result.
     *
     * @param root The current node in the left boundary traversal.
     * @param ans  The vector to store the result of the traversal.
     */
    static void traverseLeft(Node root, Vector<Integer> ans) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        ans.addElement(root.data);
        if (root.left != null)
            traverseLeft(root.left, ans);
        else
            traverseLeft(root.right, ans);
    }

    /**
     * Traverses all leaf nodes of the tree and adds them to the result.
     *
     * @param root The current node in the traversal.
     * @param ans  The vector to store the leaf nodes.
     */
    static void traverseLeaf(Node root, Vector<Integer> ans) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            ans.addElement(root.data);
            return;
        }

        traverseLeaf(root.left, ans);
        traverseLeaf(root.right, ans);
    }

    /**
     * Traverses the right boundary of the tree (excluding leaf nodes) and adds them in bottom-up order.
     *
     * @param root The current node in the right boundary traversal.
     * @param ans  The vector to store the result of the traversal.
     */
    static void traverseRight(Node root, Vector<Integer> ans) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        if (root.right != null)
            traverseRight(root.right, ans);
        else
            traverseRight(root.left, ans);

        ans.addElement(root.data); // Add after recursion to get bottom-up order
    }

    /**
     * Performs the boundary traversal of a binary tree.
     *
     * @param root The root of the binary tree.
     * @return A vector containing the boundary traversal of the binary tree.
     */
    static Vector<Integer> boundary(Node root) {
        Vector<Integer> ans = new Vector<>();
        if (root == null) {
            return ans;
        }

        ans.addElement(root.data); // Root node is always part of the boundary

        // Add left boundary (excluding leaves)
        traverseLeft(root.left, ans);

        // Add all leaf nodes
        traverseLeaf(root.left, ans);
        traverseLeaf(root.right, ans);

        // Add right boundary (excluding leaves) in reverse order
        traverseRight(root.right, ans);

        return ans;
    }

    /**
     * Main method for testing boundary traversal. You can create a sample tree here and call the `boundary` method.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Example: Build a tree and call boundary()
        Node root = new Node();
        root.data = 1;
        root.left = new Node();
        root.left.data = 2;
        root.right = new Node();
        root.right.data = 3;
        Vector<Integer> result = boundary(root);
        System.out.println(result);
    }
}
