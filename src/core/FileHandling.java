package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles basic file operations: creating a file, taking user input,
 * and appending text to the file.
 *
 * <p>Each operation is encapsulated in its own method to promote modularity
 * and ease of testing.
 *
 * <p>Appends user input to {@code file1.txt} instead of overwriting.
 *
 * <p>Example usage:
 * <pre>{@code
 *   java core.FileHandling
 * }</pre>
 *
 * <p>Expected output:
 * <pre>
 *   File created: file1.txt
 *   Enter text to write to the file:
 *   Hello, World!
 *   Successfully wrote to the file.
 * </pre>
 *
 * @author
 */
public class FileHandling {

    /**
     * Main method to drive the file handling process.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        File file = createFile("file1.txt");
        if (file != null) {
            String userInput = readUserInput();
            if (userInput != null && !userInput.isBlank()) {
                writeToFile(file, userInput);
            } else {
                System.out.println("No input provided. Skipping file write.");
            }
        }
    }

    /**
     * Creates a new file with the given filename if it does not already exist.
     *
     * @param filename the name of the file to create
     * @return the File object, or null if creation failed
     */
    public static File createFile(String filename) {
        File file = new File(filename);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            return file;
        } catch (IOException e) {
            System.err.println("An error occurred while creating the file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Reads a single line of user input from the console.
     *
     * @return the input string entered by the user
     */
    public static String readUserInput() {
        System.out.println("Enter text to write to the file:");
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        } catch (Exception e) {
            System.err.println("Error reading input: " + e.getMessage());
            return null;
        }
    }

    /**
     * Appends the given content to the specified file.
     *
     * @param file    the file to write to
     * @param content the content to write
     */
    public static void writeToFile(File file, String content) {
        try (FileWriter writer = new FileWriter(file, true)) { // true for append mode
            writer.write(content + System.lineSeparator());
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
