package BinaryTree;

/**
 * AVL Tree implementation in Java.
 * Supports insertion, deletion, and pre-order traversal.
 * Balances the tree after each insertion or deletion to maintain O(log n) height.
 */
public class AVLTree {

    /**
     * Node class representing each node in the AVL Tree.
     */
    static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            height = 1; // New node is initially added at leaf
        }
    }

    /**
     * Get the height of a node.
     */
    static int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    /**
     * Get the balance factor of a node.
     */
    static int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    /**
     * Perform a right rotation on the subtree rooted with y.
     */
    static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    /**
     * Perform a left rotation on the subtree rooted with x.
     */
    static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    /**
     * Insert a key into the AVL tree rooted at node.
     */
    static Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node; // Duplicate keys not allowed

        // Update height
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get balance factor
        int balance = getBalance(node);

        // Balance the node if needed

        // Left Left Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * Delete a node from the AVL tree.
     */
    static Node deleteNode(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else {
            // Node with one or no child
            if (root.left == null || root.right == null) {
                Node temp = (root.left != null) ? root.left : root.right;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                // Node with two children: Get inorder successor
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        // Update height
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Get balance
        int balance = getBalance(root);

        // Rebalance if needed

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    /**
     * Get the node with the smallest key in a subtree.
     */
    static Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    /**
     * Print the tree in preorder traversal.
     */
    static void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /**
     * Driver method to test AVL Tree operations.
     */
    public static void main(String[] args) {
        Node root = null;

        // Inserting nodes
        root = insert(root, 10);
        root = insert(root, 20);
        root = insert(root, 30);
        root = insert(root, 40);
        root = insert(root, 50);
        root = insert(root, 25);

        /*
         * Constructed AVL Tree:
         *        30
         *       /  \
         *     20    40
         *    /  \     \
         *  10   25     50
         */

        System.out.println("Preorder traversal of AVL Tree:");
        preOrder(root);
        System.out.println();

        // Deletion
        root = deleteNode(root, 40);
        root = deleteNode(root, 20);

        System.out.println("Preorder traversal after deletion:");
        preOrder(root);
        System.out.println();
    }
}
