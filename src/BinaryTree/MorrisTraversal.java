package BinaryTree;

/**
 * This class demonstrates the Morris Inorder Traversal algorithm for Binary Trees.
 * Morris Traversal uses threaded binary trees to traverse the tree without using recursion or a stack,
 * thus achieving O(1) auxiliary space.
 */
public class MorrisTraversal {

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
     * Performs Morris Inorder Traversal of the binary tree.
     * This traversal modifies the tree structure temporarily by creating threads to the inorder predecessor.
     *
     * @param root Root of the binary tree
     */
    static void morrisInorderTraversal(Node root) {
        Node current = root;

        while (current != null) {
            if (current.left == null) {
                // No left child, print and move to right
                System.out.print(current.data + " ");
                current = current.right;
            } else {
                // Find the inorder predecessor of current
                Node predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // Make a temporary link (thread) to the current node
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Thread already exists, remove it and visit current
                    predecessor.right = null;
                    System.out.print(current.data + " ");
                    current = current.right;
                }
            }
        }
    }

    /**
     * Driver code to test Morris Inorder Traversal.
     */
    public static void main(String[] args) {
        /*
         * Construct the binary tree:
         *        1
         *       / \
         *      2   3
         *     / \
         *    4   5
         */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        System.out.print("Morris Inorder Traversal: ");
        morrisInorderTraversal(root);
    }
}
