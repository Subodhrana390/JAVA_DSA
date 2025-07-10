package BinaryTree.Interview;

import java.util.*;

/**
 * A utility class to compute the diagonal view of a binary tree.
 */
public class DiagonalView {

    /**
     * Computes the diagonal view of a binary tree.
     *
     * @param root The root node of the binary tree.
     * @return A list of lists, where each inner list contains nodes on the same diagonal.
     */
    public static List<List<Integer>> diagonalView(Node root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) return result;

        // TreeMap to store diagonal levels in sorted order
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();

        // Queue for level-order traversal with diagonal level
        Queue<DiagonalPair> q = new LinkedList<>();
        q.add(new DiagonalPair(root, 0));

        while (!q.isEmpty()) {
            DiagonalPair pair = q.poll();
            Node node = pair.node;
            int diag = pair.diagonal;

            // Add node to corresponding diagonal list
            map.putIfAbsent(diag, new ArrayList<>());
            map.get(diag).add(node.data);

            // Left child goes to next diagonal
            if (node.left != null) {
                q.add(new DiagonalPair(node.left, diag + 1));
            }

            // Right child stays on the same diagonal
            if (node.right != null) {
                q.add(new DiagonalPair(node.right, diag));
            }
        }

        result.addAll(map.values());
        return result;
    }

    /**
     * Main method to test the diagonal view implementation.
     */
    public static void main(String[] args) {
        // Sample tree:
        //         8
        //       /   \
        //      3     10
        //     / \      \
        //    1   6      14
        //       / \     /
        //      4   7   13

        Node root = new Node(8);
        root.left = new Node(3);
        root.right = new Node(10);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.left.right.left = new Node(4);
        root.left.right.right = new Node(7);
        root.right.right = new Node(14);
        root.right.right.left = new Node(13);

        List<List<Integer>> view = diagonalView(root);
        System.out.println("Diagonal view of the binary tree:");
        for (List<Integer> diag : view) {
            System.out.println(diag);
        }
    }

    /**
     * Helper class to associate a node with its diagonal level.
     */
    static class DiagonalPair {
        Node node;
        int diagonal;

        DiagonalPair(Node node, int diagonal) {
            this.node = node;
            this.diagonal = diagonal;
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
