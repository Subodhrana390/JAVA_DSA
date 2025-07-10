package sorting;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {54, 26, 93, 17, 77, 31, 44, 55, 20};

        System.out.println("Original UnSorted Element :");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();

        bubbleSort(arr, arr.length);

        System.out.println("Display Sorted Element :");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d ", arr[i]);
        }
    }

    public static void bubbleSort(int[] arr, int n) {
        // Base case
        if (n == 1) return;

        // One pass of bubble sort to move the largest to the end
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                // Swap arr[i] and arr[i+1]
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }

        // Recursive call for remaining unsorted part
        bubbleSort(arr, n - 1);
    }
}
