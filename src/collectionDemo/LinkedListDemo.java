package collectionDemo;

import java.util.LinkedList;

/**
 * A simple demonstration of basic operations on a LinkedList in Java.
 * This example covers:
 * - Adding elements to a LinkedList
 * - Removing elements by index
 * - Printing the LinkedList at different stages
 */
public class LinkedListDemo {

    /**
     * The main method executes basic LinkedList operations.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a LinkedList of Integers
        LinkedList<Integer> l = new LinkedList<>();

        // Add elements to the list
        l.add(2);
        l.add(3);

        // Print the list after additions
        System.out.println("List after adding elements: " + l);

        // Remove element at index 1 (removes the second element, which is 3)
        l.remove(1);

        // Print the list after removal
        System.out.println("List after removing element at index 1: " + l);
    }
}
