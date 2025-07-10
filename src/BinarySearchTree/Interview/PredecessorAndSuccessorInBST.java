package BinarySearchTree.Interview;

/**
 * This class finds the in-order predecessor and successor of a given key in a Binary Search Tree.
 */
public class PredecessorAndSuccessorInBST {

    /**
     * TreeNode definition for BST.
     */
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Generic Pair class to hold two values.
     */
    static class Pair<T, K> {
        T first;
        K second;

        public Pair(T first, K second) {
            this.first = first;
            this.second = second;
        }
    }

    /**
     * Finds the in-order predecessor and successor of a given key in a BST.
     *
     * @param root the root of the BST
     * @param key  the key whose predecessor and successor is to be found
     * @return a Pair where first = predecessor, second = successor
     */
    public Pair<Integer, Integer> predecessorSuccessor(TreeNode root, int key) {
        TreeNode temp = root;
        Integer predecessor = null;
        Integer successor = null;

        // Step 1: Find the node with the given key
        while (temp != null && temp.val != key) {
            if (key < temp.val) {
                successor = temp.val;  // potential successor
                temp = temp.left;
            } else {
                predecessor = temp.val;  // potential predecessor
                temp = temp.right;
            }
        }

        if (temp == null) {
            // Key not found in the BST
            return new Pair<>(predecessor, successor);
        }

        // Step 2: Find in-order predecessor (max value in left subtree)
        TreeNode leftSubtree = temp.left;
        while (leftSubtree != null) {
            predecessor = leftSubtree.val;
            leftSubtree = leftSubtree.right;
        }

        // Step 3: Find in-order successor (min value in right subtree)
        TreeNode rightSubtree = temp.right;
        while (rightSubtree != null) {
            successor = rightSubtree.val;
            rightSubtree = rightSubtree.left;
        }

        return new Pair<>(predecessor, successor);
    }

    /**
     * Example usage of predecessorSuccessor().
     */
    public static void main(String[] args) {
        PredecessorAndSuccessorInBST ps = new PredecessorAndSuccessorInBST();

        /*
         * Construct the following BST:
         *         20
         *       /    \
         *     10      30
         *    /  \    /  \
         *   5   15  25   35
         */
        TreeNode root = new TreeNode(20,
                new TreeNode(10, new TreeNode(5), new TreeNode(15)),
                new TreeNode(30, new TreeNode(25), new TreeNode(35)));

        int key = 20;
        Pair<Integer, Integer> result = ps.predecessorSuccessor(root, key);
        System.out.println("Predecessor: " + result.first + ", Successor: " + result.second);
        // Expected: Predecessor = 15, Successor = 25
    }
}
