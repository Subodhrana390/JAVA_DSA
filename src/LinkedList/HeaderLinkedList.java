package LinkedList;

public class HeaderLinkedList {

    private Node header;

    // Constructor initializes dummy header node
    public HeaderLinkedList() {
        header = new Node(-1);  // Dummy node with placeholder value
    }

    public static void main(String[] args) {
        HeaderLinkedList list = new HeaderLinkedList();
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        list.printList();  // Output: 10 20 30

        list.deleteByValue(20);
        list.printList();  // Output: 10 30
    }

    // Insert new node at the end
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        Node curr = header;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
    }

    // Delete first node with the given value
    public boolean deleteByValue(int value) {
        Node curr = header;
        while (curr.next != null && curr.next.data != value) {
            curr = curr.next;
        }
        if (curr.next != null) {
            curr.next = curr.next.next;
            return true;
        }
        return false;
    }

    // Print the list from start to end
    public void printList() {
        Node curr = header.next;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    // Node class
    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
