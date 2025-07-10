package sorting;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] array = {170, 45, 75, 90, 802, 24, 2, 66};

        System.out.println("Unsorted array: " + Arrays.toString(array));
        radixSort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));
    }

    public static void radixSort(int[] array) {
        // Find the maximum number to know the number of digits
        int max = Arrays.stream(array).max().orElse(0);

        // Apply counting sort for each digit
        // (Starting from least significant digit to most significant one)
        for (int digit = 1; max / digit > 0; digit *= 10) {
            countingSort(array, digit);
        }
    }

    // A method to do counting sort based on the digit represented by 'place'
    public static void countingSort(int[] array, int place) {
        int size = array.length;
        int[] output = new int[size]; // Output array to hold sorted values
        int[] count = new int[10]; // Count array to store frequency of digits (0-9)

        // Calculate frequency of each digit at 'place'
        for (int j : array) {
            int digit = (j / place) % 10;
            count[digit]++;
        }

        // Convert count into prefix sum array (cumulative count)
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array using the count array for ordering
        for (int i = size - 1; i >= 0; i--) { // Traverse from end to maintain stability
            int digit = (array[i] / place) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }

        // Copy the sorted values into the original array
        System.arraycopy(output, 0, array, 0, size);
    }
}