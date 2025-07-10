package BinaryTree.Interview;

import java.util.*;

/**
 * This class performs a vertical order traversal of a binary tree.
 * In vertical order traversal, nodes are printed column-wise from left to right.
 * For each column, nodes are printed top-to-bottom, and if multiple nodes share the same position,
 * they are printed in the order they appear in the traversal (left before right).
 */
public class VerticalTraversalOfTree {

    /**
     * Represents a node in the binary tree.
     */
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    /**
     * Helper class to store a node along with its vertical (x) and level (y) positions.
     */
    static class Tuple {
        Node node;
        int x, y;

        Tuple(Node node, int x, int y) {
            this.node = node;
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Performs vertical order traversal of a binary tree.
     * 
     * @param root The root of the binary tree.
     * @return A vector of node values in vertical order (flattened from left to right).
     */
    static Vector<Integer> verticalOrder(Node root) {
        // TreeMap to keep x-coordinates in sorted order
        TreeMap<Integer, TreeMap<Integer, Vector<Integer>>> nodes = new TreeMap<>();

        // Queue to perform level order traversal while tracking (x, y) coordinates
        Queue<Tuple> q = new LinkedList<>();

        // Result vector
        Vector<Integer> ans = new Vector<>();

        // Edge case: empty tree
        if (root == null) return ans;

        // Start BFS with root at position (0, 0)
        q.offer(new Tuple(root, 0, 0));

        while (!q.isEmpty()) {
            Tuple t = q.poll();
            Node node = t.node;
            int x = t.x; // vertical coordinate
            int y = t.y; // level coordinate

            // Insert the node's value into the TreeMap structure
            nodes
                .computeIfAbsent(x, k -> new TreeMap<>())
                .computeIfAbsent(y, k -> new Vector<>())
                .add(node.data);

            // Add left and right children to the queue with updated coordinates
            if (node.left != null) q.offer(new Tuple(node.left, x - 1, y + 1));
            if (node.right != null) q.offer(new Tuple(node.right, x + 1, y + 1));
        }

        // Traverse the TreeMap to compile the result in vertical order
        for (TreeMap<Integer, Vector<Integer>> ys : nodes.values()) {
            for (Vector<Integer> vs : ys.values()) {
                ans.addAll(vs);
            }      
        }

        return ans;
    }

    /**
     * Example usage of the verticalOrder() function.
     */
    public static void main(String[] args) {
        // Construct a sample binary tree
        /*
                  1
                /   \
               2     3
              / \   / \
             4   5 6   7
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        // Perform vertical traversal and print result
        Vector<Integer> result = verticalOrder(root);
        System.out.println("Vertical Order Traversal: " + result);
    }
}
