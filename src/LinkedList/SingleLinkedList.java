package LinkedList;

/**
 * A simple implementation of a singly linked list in Java.
 * Supports insertion at a given position, deletion by value,
 * search operation, and list printing.
 */
public class SingleLinkedList {

    /**
     * Represents a node in the singly linked list.
     */
    static class Node {
        int data;
        Node next;

        /**
         * Constructor to create a new node.
         * @param data The integer value to store in the node.
         */
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Inserts a node with the specified data at the given position in the list.
     * @param head The head of the linked list.
     * @param pos The position at which to insert the new node (0-based index).
     * @param data The data to insert.
     * @return The (possibly new) head of the linked list.
     */
    public static Node insert(Node head, int pos, int data) {
        Node newNode = new Node(data);

        // Insert at the beginning
        if (pos == 0) {
            newNode.next = head;
            return newNode;
        }

        Node current = head;
        int count = 0;

        // Traverse to the node just before the insertion point
        while (current != null && count < pos - 1) {
            current = current.next;
            count++;
        }

        // If position is out of bounds
        if (current == null) {
            System.out.println("Position out of bounds.");
            return head;
        }

        // Insert the new node
        newNode.next = current.next;
        current.next = newNode;

        return head;
    }

    /**
     * Deletes the first node that contains the given value.
     * @param head The head of the linked list.
     * @param value The value to delete.
     * @return The (possibly new) head of the linked list.
     */
    public static Node deleteNode(Node head, int value) {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return null;
        }

        // Delete the head node if it matches
        if (head.data == value) {
            return head.next;
        }

        Node curr = head;

        // Traverse to find the node before the one to delete
        while (curr.next != null && curr.next.data != value) {
            curr = curr.next;
        }

        // If found, remove it by skipping the node
        if (curr.next != null) {
            curr.next = curr.next.next;
        } else {
            System.out.println("Value not found in the list.");
        }

        return head;
    }

    /**
     * Searches for a value in the linked list.
     * @param head The head of the linked list.
     * @param value The value to search for.
     * @return True if the value is found, otherwise false.
     */
    public static boolean search(Node head, int value) {
        Node curr = head;
        while (curr != null) {
            if (curr.data == value) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    /**
     * Prints the linked list in a readable format.
     * @param head The head of the linked list.
     */
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    /**
     * Main method to demonstrate the linked list operations.
     */
    public static void main(String[] args) {
        // Create initial list: 10 -> 20 -> 30
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);

        System.out.println("Original List:");
        printList(head);

        // Insert 15 at position 1: 10 -> 15 -> 20 -> 30
        head = insert(head, 1, 15);
        System.out.println("After Insertion at position 1:");
        printList(head);

        // Delete node with value 20: 10 -> 15 -> 30
        head = deleteNode(head, 20);
        System.out.println("After Deletion of value 20:");
        printList(head);

        // Search for a value
        System.out.println("Search 30: " + search(head, 30));
        System.out.println("Search 50: " + search(head, 50));
    }
}
