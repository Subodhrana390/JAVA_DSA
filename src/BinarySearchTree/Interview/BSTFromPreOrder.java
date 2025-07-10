package BinarySearchTree.Interview;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class BSTFromPreOrder {
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

    TreeNode<Integer> solve(Vector<Integer> preOrder, int mini, int maxi, AtomicInteger index) {
        if (index.get() >= preOrder.size()) return null;

        int val = preOrder.get(index.get());
        if (val < mini || val > maxi) return null;

        TreeNode<Integer> root = new TreeNode<>(val);
        index.incrementAndGet();  // Move to the next element

        root.left = solve(preOrder, mini, val, index);
        root.right = solve(preOrder, val, maxi, index);

        return root;
    }

    TreeNode<Integer> buildBST(Vector<Integer> preOrder) {
        AtomicInteger index = new AtomicInteger(0);
        return solve(preOrder, Integer.MIN_VALUE, Integer.MAX_VALUE, index);
    }

    // Helper to print the tree (inorder traversal)
    void printInOrder(TreeNode<Integer> root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    public static void main(String[] args) {
        BSTFromPreOrder bstBuilder = new BSTFromPreOrder();
        Vector<Integer> preOrder = new Vector<>();
        preOrder.add(10);
        preOrder.add(5);
        preOrder.add(1);
        preOrder.add(7);
        preOrder.add(40);
        preOrder.add(50);

        TreeNode<Integer> root = bstBuilder.buildBST(preOrder);

        System.out.print("Inorder traversal of constructed BST: ");
        bstBuilder.printInOrder(root);  // Should print 1 5 7 10 40 50
    }
}
