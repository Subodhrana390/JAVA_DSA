package BinaryTree;

/**
 * This class provides functionality to flatten a binary tree to a linked list
 * in-place using an iterative approach (modified Morris Traversal).
 */
public class FlattenaBinaryTreeToLL {

    /**
     * Class representing a node of the binary tree.
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
     * Flattens the binary tree into a linked list in-place using a variation of
     * Morris traversal. The final tree follows preorder traversal with only right children.
     *
     * @param root Root of the binary tree
     */
    static void flatten(Node root) {
        Node curr = root;

        while (curr != null) {
            if (curr.left != null) {
                // Find the rightmost node of the left subtree
                Node pred = curr.left;
                while (pred.right != null) {
                    pred = pred.right;
                }

                // Connect the right subtree of current node to the right of its predecessor
                pred.right = curr.right;

                // Move left subtree to the right and nullify left pointer
                curr.right = curr.left;
                curr.left = null;
            }

            // Move to the next right node
            curr = curr.right;
        }
    }

    /**
     * Utility method to print the flattened tree (linked list).
     *
     * @param root Root of the flattened binary tree
     */
    static void printFlattenedTree(Node root) {
        while (root != null) {
            System.out.print(root.data + " ");
            root = root.right;
        }
    }

    /**
     * Driver code to test the flattening of a binary tree.
     */
    public static void main(String[] args) {
        /*
         * Example tree:
         *       1
         *      / \
         *     2   5
         *    / \   \
         *   3   4   6
         *
         * After flattening:
         * 1 - 2 - 3 - 4 - 5 - 6
         */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.right = new Node(6);

        System.out.print("Original Tree flattened to Linked List (preorder): ");
        flatten(root);
        printFlattenedTree(root);  // Output: 1 2 3 4 5 6
    }
}
