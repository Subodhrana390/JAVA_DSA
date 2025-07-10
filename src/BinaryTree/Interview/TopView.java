package BinaryTree.Interview;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;
import java.util.Vector;

/**
 * A utility class to compute the top view of a binary tree.
 */
public class TopView {

    /**
     * Computes the top view of a binary tree.
     * The top view of a binary tree is the set of nodes visible when the tree is viewed from the top.
     *
     * @param root The root node of the binary tree.
     * @return A vector of integers representing the node values from left to right in the top view.
     */
    public static Vector<Integer> topView(Node root) {
        Vector<Integer> ans = new Vector<>();

        if (root == null) {
            return ans;
        }

        // TreeMap stores the first encountered node for each horizontal distance (sorted by key)
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();

        // Start BFS with horizontal level 0
        q.add(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            Node node = p.node;
            int level = p.level;

            // If this level is not yet recorded in the map, record the node's data
            if (!map.containsKey(level)) {
                map.put(level, node.data);
            }

            // Add left child with horizontal distance -1
            if (node.left != null) {
                q.add(new Pair(node.left, level - 1));
            }

            // Add right child with horizontal distance +1
            if (node.right != null) {
                q.add(new Pair(node.right, level + 1));
            }
        }

        // Add all top view nodes in horizontal order
        ans.addAll(map.values());
        return ans;
    }


    /**
     * Computes the bottom view of a binary tree.
     *
     * @param root The root node of the binary tree.
     * @return A vector of integers representing the node values from left to right in the bottom view.
     */
    public static Vector<Integer> bottomView(Node root) {
        Vector<Integer> ans = new Vector<>();

        if (root == null) {
            return ans;
        }

        // TreeMap to store nodes at each horizontal distance
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            Node node = p.node;
            int level = p.level;

            // For bottom view, overwrite the value at the horizontal distance
            map.put(level, node.data);

            if (node.left != null) {
                q.add(new Pair(node.left, level - 1));
            }

            if (node.right != null) {
                q.add(new Pair(node.right, level + 1));
            }
        }

        ans.addAll(map.values());
        return ans;
    }


    /**
     * Computes the left view of a binary tree.
     * The left view includes the first node encountered at each level when viewed from the left side.
     *
     * @param root The root node of the binary tree.
     * @return A vector containing node values in the left view, from top to bottom.
     */
    public static Vector<Integer> leftView(Node root) {
        Vector<Integer> ans = new Vector<>();
        solve(root, ans, 0);
        return ans;
    }

    /**
     * Helper recursive function to build the left view.
     *
     * @param root  The current node being visited.
     * @param ans   The vector storing the first node encountered at each level.
     * @param level The current depth level of the tree.
     */
    private static void solve(Node root, Vector<Integer> ans, int level) {
        if (root == null) {
            return;
        }

        // If this is the first time visiting this level, add the node
        if (ans.size() == level) {
            ans.add(root.data);
        }

        // Recurse to left first to ensure leftmost nodes are processed first
        solve(root.left, ans, level + 1);
        solve(root.right, ans, level + 1);
    }

    /**
     * Computes the right view of a binary tree.
     * The right view includes the last visible node at each level from the right side.
     *
     * @param root The root node of the binary tree.
     * @return A vector containing node values in the right view, from top to bottom.
     */
    public static Vector<Integer> rightView(Node root) {
        Vector<Integer> ans = new Vector<>();
        lsolve(root, ans, 0);  // Corrected from 'solve' to 'lsolve'
        return ans;
    }

    /**
     * Helper recursive function to build the right view.
     *
     * @param root  The current node being visited.
     * @param ans   The vector storing the first node encountered at each level from the right.
     * @param level The current depth level of the tree.
     */
    private static void lsolve(Node root, Vector<Integer> ans, int level) {
        if (root == null) {
            return;
        }

        // If this is the first time visiting this level, add the node
        if (ans.size() == level) {
            ans.add(root.data);
        }

        // Recurse right first to ensure rightmost nodes are processed first
        lsolve(root.right, ans, level + 1);
        lsolve(root.left, ans, level + 1);
    }


    /**
     * Main method for testing the top view implementation.
     */
    public static void main(String[] args) {
        // Sample binary tree:
        //         1
        //       /   \
        //      2     3
        //       \   / \
        //        4 5   6

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);

        Vector<Integer> result = topView(root);
        System.out.println("Top view of the binary tree: " + result);

        Vector<Integer> result1 = bottomView(root);
        System.out.println("Bottom view of the binary tree: " + result1);

        Vector<Integer> result2 = leftView(root);
        System.out.println("Left view of the binary tree: " + result2);

        Vector<Integer> result3 = rightView(root);
        System.out.println("Right view of the binary tree: " + result3);
    }

    /**
     * A helper class to represent a pair of a tree node and its horizontal distance from the root.
     */
    static class Pair {
        Node node;
        int level;

        Pair(Node node, int level) {
            this.node = node;
            this.level = level;
        }
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
;