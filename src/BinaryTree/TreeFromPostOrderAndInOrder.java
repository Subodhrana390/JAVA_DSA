package BinaryTree;

import java.util.HashMap;
import java.util.Map;

/**
 * Constructs a Binary Tree from given Inorder and Postorder traversal arrays.
 */
public class TreeFromPostOrderAndInOrder {

    /**
     * Node class representing each node of the binary tree.
     */
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    /**
     * Helper class to track the current index in the postorder array.
     */
    static class Index {
        int idx;

        Index(int idx) {
            this.idx = idx;
        }
    }

    /**
     * Recursive function to build the binary tree from postorder and inorder traversals.
     *
     * @param post      The postorder traversal array.
     * @param postIndex Object tracking current index in postorder array.
     * @param inStart   Start index in the inorder array.
     * @param inEnd     End index in the inorder array.
     * @param inMap     Map of inorder values to their indices for quick lookup.
     * @return          The root of the constructed subtree.
     */
    static Node solve(int[] post, Index postIndex, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (postIndex.idx < 0 || inStart > inEnd) {
            return null;
        }

        // Root is the current postorder element
        int element = post[postIndex.idx--];
        Node root = new Node(element);

        // Get index of this root in inorder array
        int position = inMap.get(element);

        // IMPORTANT: Build right subtree before left subtree
        root.right = solve(post, postIndex, position + 1, inEnd, inMap);
        root.left = solve(post, postIndex, inStart, position - 1, inMap);

        return root;
    }

    /**
     * Builds the binary tree from inorder and postorder traversals.
     *
     * @param inorder   The inorder traversal array.
     * @param postorder The postorder traversal array.
     * @param n         The number of nodes.
     * @return          The root node of the constructed binary tree.
     */
    static Node buildTree(int[] inorder, int[] postorder, int n) {
        Index postIndex = new Index(n - 1);
        Map<Integer, Integer> inMap = new HashMap<>();

        // Map each value in inorder to its index
        for (int i = 0; i < n; i++) {
            inMap.put(inorder[i], i);
        }

        return solve(postorder, postIndex, 0, n - 1, inMap);
    }

    /**
     * Prints the preorder traversal of the binary tree.
     *
     * @param root The root of the binary tree.
     */
    static void printPreorder(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    /**
     * Main method to test the tree construction from inorder and postorder.
     */
    public static void main(String[] args) {
        int[] inorder =  {40, 20, 50, 10, 60, 30};
        int[] postorder = {40, 50, 20, 60, 30, 10};

        Node root = buildTree(inorder, postorder, inorder.length);

        System.out.print("Preorder traversal of the constructed tree: ");
        printPreorder(root);
    }
}
