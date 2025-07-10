package collectionDemo;

import java.util.Stack;

/**
 * Demonstrates basic operations on a Stack using Java's built-in Stack class.
 * 
 * This example covers:
 * - Pushing elements onto the stack
 * - Popping elements from the stack
 * - Using a loop to empty and print the stack
 */
public class StackDemo {

    /**
     * Main method to execute the stack operations.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a stack of integers
        Stack<Integer> s = new Stack<>();

        // Push two elements onto the stack
        s.push(2);
        s.push(2);

        // Pop the top element (removes one '2')
        s.pop();

        // Pop and print elements until the stack is empty
        while (!s.isEmpty()) {
            System.out.println("Popped element: " + s.pop());
        }
    }
}
