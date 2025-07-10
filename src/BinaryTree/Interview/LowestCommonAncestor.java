package BinaryTree.Interview;

/// This class provides a method to find the Lowest Common Ancestor (LCA)
/// of two nodes in a binary tree.
///
/// The LCA of two nodes v1 and v2 is defined as the deepest node that has both
/// v1 and v2 as descendants (where we allow a node to be a descendant of itself).
///
/// This implementation assumes that both v1 and v2 are present in the tree.
public class LowestCommonAncestor {

    public static void main(String[] args) {
        // Sample binary tree:
        //
        //         3
        //       /   \
        //      5     1
        //     / \   / \
        //    6   2 0   8
        //       / \
        //      7   4

        Node root = new Node(3);
        root.left = new Node(5);
        root.right = new Node(1);
        root.left.left = new Node(6);
        root.left.right = new Node(2);
        root.right.left = new Node(0);
        root.right.right = new Node(8);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);

        LowestCommonAncestor tree = new LowestCommonAncestor();
        Node lca = tree.lca(root, 5, 1);
        System.out.println("LCA of 5 and 1: " + lca.data);  // Output: 3

        lca = tree.lca(root, 6, 4);
        System.out.println("LCA of 6 and 4: " + lca.data);  // Output: 5
    }

    /**
     * Finds the Lowest Common Ancestor (LCA) of two nodes with values v1 and v2
     * in a binary tree rooted at the given node.
     *
     * @param root The root of the binary tree.
     * @param v1   The value of the first node.
     * @param v2   The value of the second node.
     * @return The node representing the LCA of v1 and v2, or null if not found.
     */
    public Node lca(Node root, int v1, int v2) {
        // Base case: if root is null or matches one of the target nodes
        if (root == null) return null;
        if (root.data == v1 || root.data == v2) return root;

        // Recur for left and right subtrees
        Node leftAns = lca(root.left, v1, v2);
        Node rightAns = lca(root.right, v1, v2);

        // If both sides return non-null, current node is LCA
        if (leftAns != null && rightAns != null) return root;

        // Otherwise, return the non-null side
        return (leftAns != null) ? leftAns : rightAns;
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
