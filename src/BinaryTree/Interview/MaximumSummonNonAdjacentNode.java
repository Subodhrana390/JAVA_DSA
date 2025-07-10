package BinaryTree.Interview;

/**
 * A utility class to calculate the maximum sum of non-adjacent nodes
 * in a binary tree.
 * <p>
 * Two nodes are considered adjacent if one is a parent of the other.
 */
public final class MaximumSummonNonAdjacentNode {

    /**
     * Represents a node in a binary tree.
     */
    public static class Node {
        int data;
        Node left, right;

        /**
         * Constructs a Node with the specified data.
         *
         * @param data the value to store in the node
         */
        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * A helper class to hold two values:
     * - `include`: maximum sum including the current node
     * - `exclude`: maximum sum excluding the current node
     */
    private static class SumPair {
        int include;
        int exclude;

        /**
         * Constructs a SumPair.
         *
         * @param include sum including this node
         * @param exclude sum excluding this node
         */
        public SumPair(int include, int exclude) {
            this.include = include;
            this.exclude = exclude;
        }
    }

    /**
     * Computes the maximum sum of non-adjacent nodes in the tree.
     *
     * @param root the root of the binary tree
     * @return the maximum sum
     */
    public static int getMaxSum(Node root) {
        SumPair result = computeMaxSum(root);
        return Math.max(result.include, result.exclude);
    }

    /**
     * Recursively computes the maximum sum for each subtree rooted at {@code node}.
     *
     * @param node the current tree node
     * @return a SumPair containing the maximum sums with and without the current node
     */
    private static SumPair computeMaxSum(Node node) {
        if (node == null) {
            return new SumPair(0, 0);
        }

        SumPair left = computeMaxSum(node.left);
        SumPair right = computeMaxSum(node.right);

        // If we include the current node, we can't include its children
        int include = node.data + left.exclude + right.exclude;

        // If we exclude the current node, we can choose to include or exclude its children
        int exclude = Math.max(left.include, left.exclude) + Math.max(right.include, right.exclude);

        return new SumPair(include, exclude);
    }

    /**
     * Main method to demonstrate usage.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        /*
            Construct the following binary tree:
                    10
                   /  \
                  1    2
                 / \  / \
                3  4 5   6
         */
        Node root = new Node(10);
        root.left = new Node(1);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);

        int maxSum = getMaxSum(root);
        System.out.println("Maximum sum of non-adjacent nodes: " + maxSum);
    }
}
