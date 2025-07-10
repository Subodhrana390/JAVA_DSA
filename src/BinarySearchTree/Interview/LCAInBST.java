package BinarySearchTree.Interview;

/**
 * This class provides a method to find the Lowest Common Ancestor (LCA)
 * of two nodes in a Binary Search Tree.
 */
public class LCAInBST {

    /**
     * Generic TreeNode class representing a node in the BST.
     *
     * @param <T> the type of value stored in the node
     */
    static class TreeNode<T extends Comparable<T>> {
        T val;
        TreeNode<T> left, right;

        TreeNode(T val) {
            this.val = val;
        }

        TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Finds the Lowest Common Ancestor (LCA) of two nodes in a BST.
     *
     * @param root the root of the BST
     * @param p    the first node
     * @param q    the second node
     * @return the LCA node
     */
    TreeNode<Integer> LCA(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        if (root == null) return null;

        if (root.val < p.val && root.val < q.val) {
            // Both nodes are in the right subtree
            return LCA(root.right, p, q);
        } else if (root.val > p.val && root.val > q.val) {
            // Both nodes are in the left subtree
            return LCA(root.left, p, q);
        } else {
            // One node is on the left and the other is on the right, or one equals root
            return root;
        }
    }

    /**
     * Example usage and testing.
     */
    public static void main(String[] args) {
        LCAInBST tree = new LCAInBST();

        /*
         * Construct the BST:
         *         20
         *       /    \
         *     10      30
         *    /  \    /  \
         *   5   15  25  35
         */

        TreeNode<Integer> root = new TreeNode<>(20,
                new TreeNode<>(10, new TreeNode<>(5), new TreeNode<>(15)),
                new TreeNode<>(30, new TreeNode<>(25), new TreeNode<>(35))
        );

        TreeNode<Integer> p = root.left.left;   // Node 5
        TreeNode<Integer> q = root.left.right;  // Node 15

        TreeNode<Integer> lca = tree.LCA(root, p, q);
        System.out.println("Lowest Common Ancestor: " + (lca != null ? lca.val : "None"));
        // Expected output: 10
    }
}
