package LinkedList;


public class DoubledLinkedList {

    public static Node Insert(Node head, int pos, int data) {
        Node newNode = new Node(data);

        if (pos < 0) {
            System.out.println("Invalid position");
            return head;
        }
        // Insert at head
        if (pos == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            return newNode;
        }

        Node current = head;
        int count = 0;

        while (current != null && count < pos - 1) {
            current = current.next;
            count++;
        }

        if (current == null) {
            System.out.println("Position out of bounds.");
            return head;
        }

        newNode.next = current.next;
        newNode.prev = current;

        if (current.next != null) {
            current.next.prev = newNode;
        }

        current.next = newNode;

        return head;
    }

    public static Node DeleteNode(Node head, int value) {
        if (head == null) {
            System.out.println("Nothing to delete");
            return null;
        }

        // Delete head node
        if (head.data == value) {
            Node newHead = head.next;
            if (newHead != null) {
                newHead.prev = null;
            }
            return newHead;
        }

        Node curr = head;

        while (curr != null && curr.data != value) {
            curr = curr.next;
        }

        if (curr == null) {
            System.out.println("Value not found in list");
            return head;
        }

        // Node found: adjust pointers
        if (curr.prev != null) {
            curr.prev.next = curr.next;
        }

        if (curr.next != null) {
            curr.next.prev = curr.prev;
        }

        return head;
    }

    public static void main(String[] args) {

    }

    static class Node {
        int data;
        Node prev, next;

        public Node(int data) {
            this.data = data;
            this.next = this.prev = null;
        }
    }
}
