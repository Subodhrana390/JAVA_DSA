package BinaryTree.Interview;

/**
 * A utility class to find the k-th ancestor of a given node in a binary tree.
 * <p>
 * Example: For a tree with root 1, and node 9, the 2nd ancestor is 2.
 * </p>
 */
public final class KthAncestor {

    /**
     * Represents a node in a binary tree.
     */
    public static class Node {
        int data;
        Node left, right;

        /**
         * Constructs a new Node with the given data.
         *
         * @param data the integer value of the node
         */
        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * A mutable wrapper for the value of k, needed for recursion tracking.
     */
    private static class KCounter {
        int value;

        /**
         * Constructs a KCounter with the initial value of k.
         *
         * @param value the initial k value
         */
        public KCounter(int value) {
            this.value = value;
        }
    }

    /**
     * Finds the k-th ancestor of the target node in the given binary tree.
     *
     * @param root      the root node of the binary tree
     * @param k         the k-th ancestor level to find
     * @param targetVal the target node's data value
     * @return the data of the k-th ancestor node, or -1 if not found
     * @throws IllegalArgumentException if root is null or k is negative
     */
    public static int findKthAncestor(Node root, int k, int targetVal) {
        if (root == null) {
            throw new IllegalArgumentException("Root node cannot be null.");
        }
        if (k < 0) {
            throw new IllegalArgumentException("k must be a non-negative integer.");
        }

        KCounter kCounter = new KCounter(k);
        Node ancestor = findAncestorHelper(root, kCounter, targetVal);

        // If no ancestor found or the returned node is the target node itself
        if (ancestor == null || ancestor.data == targetVal) {
            return -1;
        }

        return ancestor.data;
    }

    /**
     * A private recursive helper to find the k-th ancestor.
     *
     * @param current    the current node in the traversal
     * @param kCounter   mutable counter tracking remaining ancestor levels
     * @param targetVal  the data of the target node
     * @return the node that is the k-th ancestor, or null if not found
     */
    private static Node findAncestorHelper(Node current, KCounter kCounter, int targetVal) {
        if (current == null) {
            return null;
        }

        if (current.data == targetVal) {
            return current;
        }

        Node leftResult = findAncestorHelper(current.left, kCounter, targetVal);
        Node rightResult = findAncestorHelper(current.right, kCounter, targetVal);

        if (leftResult != null || rightResult != null) {
            kCounter.value--;

            // If k becomes 0, we've reached the ancestor
            if (kCounter.value == 0) {
                // Lock to prevent further updates
                kCounter.value = Integer.MAX_VALUE;
                return current;
            }

            // Propagate the non-null result up the recursion stack
            return leftResult != null ? leftResult : rightResult;
        }

        return null;
    }

    /**
     * Sample usage and test case.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Construct sample binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);

        int k = 2;
        int target = 9;

        int ancestor = findKthAncestor(root, k, target);
        System.out.printf("The %d-th ancestor of node %d is: %d%n", k, target, ancestor);
    }
}
