package BinaryTree.Interview;

/**
 * Computes the sum of the longest root-to-leaf path in a binary tree.
 */
public class SumOfLongestBloodline {

    // Wrapper class to hold mutable maxLen and maxSum values
    static class Result {
        int maxSum = Integer.MIN_VALUE;
        int maxLen = 0;
    }

    /**
     * Recursive function to find the sum of the longest root-to-leaf path.
     *
     * @param root   Current node.
     * @param sum    Sum of the current path.
     * @param len    Length of the current path.
     * @param result Object to store the maximum sum and corresponding length.
     */
    static void solve(Node root, int sum, int len, Result result) {
        if (root == null) return;

        sum += root.data;
        len++;

        // If it's a leaf node
        if (root.left == null && root.right == null) {
            if (len > result.maxLen) {
                result.maxLen = len;
                result.maxSum = sum;
            } else if (len == result.maxLen) {
                result.maxSum = Math.max(result.maxSum, sum);
            }
            return;
        }

        solve(root.left, sum, len, result);
        solve(root.right, sum, len, result);
    }

    /**
     * Main function to compute the sum of the longest root-to-leaf path.
     *
     * @param root The root of the binary tree.
     * @return Sum of the longest path from root to a leaf.
     */
    static int sumOfLongRootToLeaf(Node root) {
        Result result = new Result();
        solve(root, 0, 0, result);
        return result.maxSum;
    }

    public static void main(String[] args) {
        // Sample binary tree:
        //         1
        //       /   \
        //      2     3
        //     / \     \
        //    4   5     6

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        int result = sumOfLongRootToLeaf(root);
        System.out.println("Sum of the longest root-to-leaf path: " + result);
    }

    /**
     * A class representing a node in a binary tree.
     */
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }
}
