package BinaryTree;

import java.util.HashMap;
import java.util.Map;

/**
 * Constructs a Binary Tree from given Inorder and Preorder traversal arrays.
 * Provides functionality to build the tree and print its postorder traversal.
 */
public class ConstructBTFromInorderPreOrder {

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
     * Helper class to track the current index in the preorder array.
     */
    static class Index {
        int idx = 0;
    }

    /**
     * Recursive function to build a binary tree from preorder and inorder traversals.
     *
     * @param pre          The preorder traversal array.
     * @param preIndex     Object containing current index in the preorder array.
     * @param inOrderStart Start index of the current subtree in inorder array.
     * @param inOrderEnd   End index of the current subtree in inorder array.
     * @param inMap        A map storing element-to-index mapping for inorder array.
     * @return             Root node of the constructed subtree.
     */
    static Node solve(int[] pre, Index preIndex, int inOrderStart, int inOrderEnd, Map<Integer, Integer> inMap) {
        // Base case: if no subtree to build
        if (preIndex.idx >= pre.length || inOrderStart > inOrderEnd) {
            return null;
        }

        // Get current root element from preorder
        int element = pre[preIndex.idx++];
        Node root = new Node(element);

        // Get root index in inorder traversal
        int position = inMap.get(element);

        // Recursively build left and right subtrees
        root.left = solve(pre, preIndex, inOrderStart, position - 1, inMap);
        root.right = solve(pre, preIndex, position + 1, inOrderEnd, inMap);

        return root;
    }

    /**
     * Builds a binary tree from given inorder and preorder traversal arrays.
     *
     * @param inorder  The inorder traversal array.
     * @param preorder The preorder traversal array.
     * @param n        Number of elements in the tree.
     * @return         Root of the constructed binary tree.
     */
    static Node buildTree(int[] inorder, int[] preorder, int n) {
        Index preIndex = new Index();

        // Create a map of inorder elements for quick lookup
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inMap.put(inorder[i], i);
        }

        return solve(preorder, preIndex, 0, n - 1, inMap);
    }

    /**
     * Prints the postorder traversal of the binary tree.
     *
     * @param root Root of the binary tree.
     */
    static void printPostorder(Node root) {
        if (root == null) return;
        printPostorder(root.left);
        printPostorder(root.right);
        System.out.print(root.data + " ");
    }

    /**
     * Driver method to test binary tree construction from inorder and preorder arrays.
     */
    public static void main(String[] args) {
        int[] inorder =  {40, 20, 50, 10, 60, 30};
        int[] preorder = {10, 20, 40, 50, 30, 60};

        Node root = buildTree(inorder, preorder, inorder.length);

        System.out.print("Postorder traversal: ");
        printPostorder(root);
    }
}
