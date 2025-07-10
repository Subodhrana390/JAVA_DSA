package core;

import java.io.*;

/**
 * Demonstrates how to write to and read from a text file using Java IO streams.
 *
 * <p>This class writes a simple "Hello World" message to a file named {@code file1.txt}
 * and then reads the content from the file and prints it to the console.
 *
 * <p>It uses {@link BufferedWriter} for writing and {@link BufferedReader} for reading.
 * Any I/O exceptions are caught and printed to the standard error stream.
 *
 * <p>Example usage:
 * <pre>{@code
 *   java core.ReadFile
 * }</pre>
 *
 * <p>Expected output:
 * <pre>
 *   Hello World
 * </pre>
 *
 * @author Subodh Rana
 */
public class ReadFile {

    /**
     * The main entry point of the program.
     *
     * <p>This method performs the following steps:
     * <ol>
     *   <li>Creates or overwrites {@code file1.txt}</li>
     *   <li>Writes "Hello World" to the file</li>
     *   <li>Closes the writer to flush and save the data</li>
     *   <li>Reads the content of {@code file1.txt} line by line</li>
     *   <li>Prints each line to the console</li>
     * </ol>
     *
     * <p>If an {@link IOException} occurs at any point, it is caught and its stack trace is printed.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("file1.txt"));
            writer.write("Hello World");
            writer.close();

            BufferedReader reader = new BufferedReader(new FileReader("file1.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
