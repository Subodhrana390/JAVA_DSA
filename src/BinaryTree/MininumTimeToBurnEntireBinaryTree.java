package BinaryTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * This class provides functionality to compute the minimum time required to burn an entire binary tree
 * starting from a given target node. The fire spreads to adjacent nodes (parent, left child, right child)
 * every second.
 */
public class MininumTimeToBurnEntireBinaryTree {

    /**
     * Class representing a node in the binary tree.
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
     * Creates a mapping of each node to its parent and identifies the target node from where fire starts.
     *
     * @param root          Root node of the binary tree
     * @param target        Data value of the target node
     * @param nodeToParent  HashMap to store mapping of node to its parent
     * @return              Reference to the target node
     */
    static Node createParentMapping(Node root, int target, Map<Node, Node> nodeToParent) {
        Node result = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        nodeToParent.put(root, null);  // Root has no parent

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.data == target) {
                result = current;
            }
            if (current.left != null) {
                nodeToParent.put(current.left, current);
                queue.add(current.left);
            }
            if (current.right != null) {
                nodeToParent.put(current.right, current);
                queue.add(current.right);
            }
        }

        return result;
    }

    /**
     * Performs a BFS traversal simulating the burning of the tree. In each second, fire spreads to
     * adjacent unvisited nodes (left, right, parent).
     *
     * @param root          The node from where the burning starts (target node)
     * @param nodeToParent  Map containing the parent references for each node
     * @return              Minimum time required to burn the entire tree
     */
    static int burnTree(Node root, Map<Node, Node> nodeToParent) {
        Map<Node, Boolean> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        visited.put(root, true);

        int time = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean spread = false;  // Flag to check if fire spread in this second

            for (int i = 0; i < size; i++) {
                Node current = queue.poll();

                // Check and add left child
                if (current.left != null && !visited.containsKey(current.left)) {
                    spread = true;
                    visited.put(current.left, true);
                    queue.add(current.left);
                }

                // Check and add right child
                if (current.right != null && !visited.containsKey(current.right)) {
                    spread = true;
                    visited.put(current.right, true);
                    queue.add(current.right);
                }

                // Check and add parent
                Node parent = nodeToParent.get(current);
                if (parent != null && !visited.containsKey(parent)) {
                    spread = true;
                    visited.put(parent, true);
                    queue.add(parent);
                }
            }

            // Increase time only if fire spread to new nodes
            if (spread) {
                time++;
            }
        }

        return time;
    }

    /**
     * Computes the minimum time to burn the entire binary tree from a given target node.
     *
     * @param root    Root of the binary tree
     * @param target  Data value of the target node where fire starts
     * @return        Minimum time (in seconds) to burn the entire tree
     */
    static int minTime(Node root, int target) {
        Map<Node, Node> nodeToParent = new HashMap<>();
        Node targetNode = createParentMapping(root, target, nodeToParent);
        return burnTree(targetNode, nodeToParent);
    }

    /**
     * Main method to test the burn time calculation.
     */
    public static void main(String[] args) {
        /*
         * Example Binary Tree:
         *           1
         *         /   \
         *        2     3
         *       / \   / \
         *      4   5 6   7
         *
         * Target = 5
         */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        int target = 5;
        int timeToBurn = minTime(root, target);
        System.out.println("Minimum time to burn the entire tree: " + timeToBurn);
    }
}
