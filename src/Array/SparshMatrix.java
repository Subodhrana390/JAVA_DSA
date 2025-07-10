class Node {
    int row;
    int col;
    int data;
    Node next;

    public Node(int row, int col, int data) {
        this.row = row;
        this.col = col;
        this.data = data;
        this.next = null;
    }
}

public class SparshMatrix {

    // Method to create a new node and append it to the list
    public static Node createNode(Node root, int row, int col, int value) {
        Node newNode = new Node(row, col, value);
        
        // If the root is null, the new node becomes the root
        if (root == null) {
            root = newNode;
        } else {
            Node temp = root;
            // Traverse the list to find the last node
            while (temp.next != null) {
                temp = temp.next;
            }
            // Append the new node at the end
            temp.next = newNode;
        }
        
        return root; // Return the updated root
    }

    // Method to print the sparse matrix
    public static void printSparseMatrix(Node root) {
        Node temp = root;
        while (temp != null) {
            System.out.println("Row: " + temp.row + ", Col: " + temp.col + ", Data: " + temp.data);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Node root = null; // Start with an empty list
        
        // Adding some non-zero elements to the sparse matrix
        root = createNode(root, 0, 1, 5);
        root = createNode(root, 1, 2, 10);
        root = createNode(root, 2, 3, 15);
        root = createNode(root, 3, 4, 20);
        
        // Print the sparse matrix
        printSparseMatrix(root);
    }
}
