package LinkedList;


public class CircularDoubledLinkedList {

    static class Node {
        int data;
        Node prev, next;

        public Node(int data) {
            this.data = data;
            prev = next = null;
        }
    }


    // Insert at given position (0-based)
    public static Node Insert(Node head, int pos, int data) {
        Node newNode = new Node(data);

        // Case 1: Empty list
        if (head == null) {
            newNode.next = newNode.prev = newNode;
            return newNode;
        }

        // Case 2: Insert at head
        if (pos == 0) {
            Node tail = head.prev;

            newNode.next = head;
            newNode.prev = tail;

            tail.next = newNode;
            head.prev = newNode;

            return newNode; // New head
        }

        // Traverse to position - 1
        Node curr = head;
        int count = 0;
        while (count < pos - 1 && curr.next != head) {
            curr = curr.next;
            count++;
        }

        Node nextNode = curr.next;

        newNode.next = nextNode;
        newNode.prev = curr;

        curr.next = newNode;
        nextNode.prev = newNode;

        return head;
    }

    // Delete node by value
    public static Node deleteNode(Node head, int val) {
        if (head == null) return null;

        Node curr = head;

        // Search the node with value
        do {
            if (curr.data == val) {
                // Only one node
                if (curr.next == curr && curr.prev == curr) {
                    return null;
                }

                // Adjust head if needed
                if (curr == head) {
                    head = head.next;
                }

                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;

                return head;
            }
            curr = curr.next;
        } while (curr != head);

        System.out.println("Value not found");
        return head;
    }

    static void printList(Node head) {
        if (head == null) return;
        Node curr = head;
        do {
            System.out.print(curr.data + " ");
            curr = curr.next;
        } while (curr != head);
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;

        head = Insert(head, 0, 10);
        head = Insert(head, 1, 20);
        head = Insert(head, 2, 30);
        head = Insert(head, 1, 15);

        printList(head);  // Output: 10 15 20 30

        head = deleteNode(head, 15);
        printList(head);  // Output: 10 20 30

        head = deleteNode(head, 10);
        printList(head);  // Output: 20 30

        head = deleteNode(head, 30);
        printList(head);  // Output: 20

        head = deleteNode(head, 20);
        printList(head);  // Output: (empty)
    }
}
