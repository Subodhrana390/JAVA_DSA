package BinarySearchTree.Interview;

/**
 * Class to merge two Binary Search Trees (BSTs) into a balanced BST
 * with height as minimal as possible.
 */
public class TwoMergeBSTInHeight {

    /**
     * Definition for a binary tree node.
     */
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * Wrapper class to allow passing TreeNode references by reference.
     */
    static class TreeNodeWrapper {
        TreeNode node;
    }

    /**
     * Converts a BST to a sorted doubly linked list (DLL) in-place using in-order traversal.
     * The right pointer acts as next, and left pointer acts as previous.
     *
     * @param root        Root node of the BST
     * @param headWrapper A wrapper to hold the head of the DLL
     */
    void convertToSortedDLL(TreeNode root, TreeNodeWrapper headWrapper) {
        if (root == null) return;

        // Reverse in-order traversal (Right, Node, Left)
        convertToSortedDLL(root.right, headWrapper);

        // Link current node to the head of already processed list
        root.right = headWrapper.node;
        if (headWrapper.node != null) {
            headWrapper.node.left = root;
        }

        // Move head to current node
        headWrapper.node = root;

        // Continue traversal
        convertToSortedDLL(root.left, headWrapper);
    }

    /**
     * Merges two sorted doubly linked lists (DLLs) into a single sorted DLL.
     *
     * @param head1 Head of the first sorted DLL
     * @param head2 Head of the second sorted DLL
     * @return Head of the merged sorted DLL
     */
    TreeNode mergeLinkedList(TreeNode head1, TreeNode head2) {
        TreeNode dummy = new TreeNode(0); // Temporary dummy node
        TreeNode tail = dummy;

        // Merge by comparing values
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                tail.right = head1;
                head1.left = tail;
                head1 = head1.right;
            } else {
                tail.right = head2;
                head2.left = tail;
                head2 = head2.right;
            }
            tail = tail.right;
        }

        // Attach remaining nodes
        if (head1 != null) {
            tail.right = head1;
            head1.left = tail;
        }
        if (head2 != null) {
            tail.right = head2;
            head2.left = tail;
        }

        // Return the merged DLL, skipping dummy
        TreeNode head = dummy.right;
        if (head != null) head.left = null;
        return head;
    }

    /**
     * Counts the number of nodes in a doubly linked list.
     *
     * @param head Head of the DLL
     * @return Number of nodes
     */
    int countNodes(TreeNode head) {
        int count = 0;
        TreeNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.right;
        }
        return count;
    }

    /**
     * Converts a sorted doubly linked list to a balanced BST.
     *
     * @param headWrapper A wrapper containing the head of the DLL
     * @param n           Number of nodes in the DLL
     * @return Root of the balanced BST
     */
    TreeNode sortedLLToBST(TreeNodeWrapper headWrapper, int n) {
        if (n <= 0) return null;

        // Recursively construct the left subtree
        TreeNode left = sortedLLToBST(headWrapper, n / 2);

        // Current node becomes root
        TreeNode root = headWrapper.node;
        root.left = left;

        // Move to the next node in DLL
        headWrapper.node = headWrapper.node.right;

        // Recursively construct the right subtree
        root.right = sortedLLToBST(headWrapper, n - n / 2 - 1);

        return root;
    }

    /**
     * Prints the in-order traversal of a BST.
     *
     * @param root Root of the BST
     */
    void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    /**
     * Driver method demonstrating the merging of two BSTs into a balanced BST.
     */
    public static void main(String[] args) {
        TwoMergeBSTInHeight obj = new TwoMergeBSTInHeight();

        // Example BST 1
        TreeNode root1 = new TreeNode(10);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(15);

        // Example BST 2
        TreeNode root2 = new TreeNode(20);
        root2.left = new TreeNode(18);
        root2.right = new TreeNode(25);

        // Convert both BSTs to sorted DLLs
        TreeNodeWrapper head1 = new TreeNodeWrapper();
        TreeNodeWrapper head2 = new TreeNodeWrapper();
        obj.convertToSortedDLL(root1, head1);
        obj.convertToSortedDLL(root2, head2);

        // Merge the two DLLs into a single sorted DLL
        TreeNode mergedHead = obj.mergeLinkedList(head1.node, head2.node);

        // Count total nodes in merged DLL
        int n = obj.countNodes(mergedHead);

        // Convert the sorted DLL to a balanced BST
        TreeNodeWrapper headWrapper = new TreeNodeWrapper();
        headWrapper.node = mergedHead;
        TreeNode newRoot = obj.sortedLLToBST(headWrapper, n);

        // Print in-order traversal of the final balanced BST
        System.out.println("Inorder traversal of merged balanced BST:");
        obj.printInorder(newRoot);
    }
}
