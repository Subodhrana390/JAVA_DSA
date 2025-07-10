package BinaryTree.Interview;

import java.util.Vector;

/**
 * This class finds the count of all paths in a binary tree
 * where the sum of node values in the path equals a given value k.
 *
 * A path can start and end at any node but must be downward (parent to child).
 */
public class KSumPaths {

    public static void main(String[] args) {
        // Example to test
        Node root = new Node(1);
        root.left = new Node(3);
        root.right = new Node(-1);
        root.left.left = new Node(2);
        root.left.right = new Node(1);
        root.left.right.left = new Node(1);
        root.right.left = new Node(4);
        root.right.right = new Node(5);
        root.right.right.right = new Node(2);

        KSumPaths solver = new KSumPaths();
        int k = 5;
        System.out.println("Number of paths with sum " + k + ": " + solver.SumK(root, k));
    }

    /**
     * Returns the count of all paths in the tree with sum equals k.
     * @param root The root of the binary tree.
     * @param k The target sum.
     * @return Number of k-sum paths.
     */
    public int SumK(Node root, int k) {
        Vector<Integer> path = new Vector<>();
        int[] count = new int[1]; // Mutable container to hold count across recursive calls
        solve(root, k, count, path);
        return count[0];
    }

    /**
     * Recursive helper method that traverses the tree and counts paths summing to k.
     * @param root Current node in traversal.
     * @param k Target sum.
     * @param count Mutable count container.
     * @param path Current path from root to this node.
     */
    void solve(Node root, int k, int[] count, Vector<Integer> path) {
        if (root == null) {
            return;
        }
        // Add current node to path
        path.add(root.data);

        // Traverse left and right subtree
        solve(root.left, k, count, path);
        solve(root.right, k, count, path);

        // Check all sub-path sums ending at current node
        int sum = 0;
        for (int i = path.size() - 1; i >= 0; i--) {
            sum += path.get(i);
            if (sum == k) {
                count[0]++;
            }
        }

        // Remove current node from path before returning to parent
        path.remove(path.size() - 1);
    }

    /**
     * Class representing a node in a binary tree.
     */
    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
