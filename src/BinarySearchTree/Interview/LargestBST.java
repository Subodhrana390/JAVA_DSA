package BinarySearchTree.Interview;

/**
 * Class to find the size of the largest BST (Binary Search Tree)
 * present in a given binary tree.
 */
public class LargestBST {

    /**
     * Generic tree node class representing nodes in a binary tree.
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
     * Helper class to store the maximum size of a BST found so far.
     */
    static class MaxSize {
        int size = 0;
    }

    /**
     * Helper class to hold information for each subtree during traversal:
     * - maxi: Maximum value in the subtree
     * - mini: Minimum value in the subtree
     * - isBST: Whether the current subtree is a BST
     * - size: Total number of nodes in the current subtree
     */
    static class Info {
        int maxi;
        int mini;
        boolean isBST;
        int size;

        Info(int maxi, int mini, boolean isBST, int size) {
            this.maxi = maxi;
            this.mini = mini;
            this.isBST = isBST;
            this.size = size;
        }
    }

    /**
     * Recursive function that traverses the tree in postorder and returns
     * information about each subtree.
     *
     * @param root     The current root node
     * @param maxSize  Reference to the object holding maximum BST size found
     * @return Info object containing properties of the subtree
     */
    Info solve(TreeNode<Integer> root, MaxSize maxSize) {
        // Base case: null node is considered a BST with size 0
        if (root == null) {
            return new Info(Integer.MIN_VALUE, Integer.MAX_VALUE, true, 0);
        }

        // Recurse on left and right subtrees
        Info left = solve(root.left, maxSize);
        Info right = solve(root.right, maxSize);

        // Prepare current node's info
        Info currNode = new Info(
                Math.max(root.val, right.maxi),  // Max value in current subtree
                Math.min(root.val, left.mini),   // Min value in current subtree
                false,                           // Assume not a BST initially
                left.size + right.size + 1       // Size of current subtree
        );

        // Check if the current subtree is a valid BST
        if (left.isBST && right.isBST &&
                root.val > left.maxi && root.val < right.mini) {
            currNode.isBST = true;
            // Update the maximum BST size found so far
            maxSize.size = Math.max(maxSize.size, currNode.size);
        }

        return currNode;
    }

    /**
     * Main function to return the size of the largest BST in a binary tree.
     *
     * @param root Root node of the binary tree
     * @return Size of the largest BST subtree
     */
    int largestBST(TreeNode<Integer> root) {
        MaxSize maxSize = new MaxSize();
        solve(root, maxSize);
        return maxSize.size;
    }

    /**
     * Main method for demonstration.
     * Builds a sample tree and prints the size of the largest BST.
     */
    public static void main(String[] args) {
        LargestBST bst = new LargestBST();

        /*
         Sample Binary Tree:
                 50
                /  \
              30    60
             /  \   / \
            5   20 45  70
                        / \
                       65 80

         Largest BST is:
                 60
                /  \
              45    70
                    / \
                  65  80
         */

        TreeNode<Integer> root = new TreeNode<>(50,
                new TreeNode<>(30,
                        new TreeNode<>(5),
                        new TreeNode<>(20)),
                new TreeNode<>(60,
                        new TreeNode<>(45),
                        new TreeNode<>(70,
                                new TreeNode<>(65),
                                new TreeNode<>(80))));

        int result = bst.largestBST(root);
        System.out.println("Size of the largest BST subtree is: " + result);
    }
}
