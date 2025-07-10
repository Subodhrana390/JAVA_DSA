package Heap;

public class BuildHeap {

    int[] arr = new int[101]; // One extra space for 1-based indexing
    static int size = 0;

    void insert(int value) {
        size++;
        int index = size;
        arr[index] = value;

        while (index > 1) {
            int parent = index / 2;
            if (arr[parent] < arr[index]) {
                int temp = arr[parent];
                arr[parent] = arr[index];
                arr[index] = temp;
                index = parent;
            } else {
                return;
            }
        }
    }

    void deletion() {
        if (size == 0) {
            System.out.println("Heap is empty");
            return;
        }

        arr[1] = arr[size];
        size--;
        int index = 1;

        while (index <= size) {
            int leftIndex = 2 * index;
            int rightIndex = 2 * index + 1;
            int largest = index;

            if (leftIndex <= size && arr[leftIndex] > arr[largest]) {
                largest = leftIndex;
            }

            if (rightIndex <= size && arr[rightIndex] > arr[largest]) {
                largest = rightIndex;
            }

            if (largest != index) {
                int temp = arr[index];
                arr[index] = arr[largest];
                arr[largest] = temp;
                index = largest;
            } else {
                return;
            }
        }
    }

    static void Heapify(int[] arr, int size, int index) {
        int largest = index;
        int leftIndex = 2 * index;
        int rightIndex = 2 * index + 1;

        if (leftIndex <= size && arr[leftIndex] > arr[largest]) {
            largest = leftIndex;
        }

        if (rightIndex <= size && arr[rightIndex] > arr[largest]) {
            largest = rightIndex;
        }

        if (largest != index) {
            int temp = arr[index];
            arr[index] = arr[largest];
            arr[largest] = temp;
            Heapify(arr, size, largest);
        }
    }

    static void heapSort(int[] arr, int n) {
        int tempSize = n;

        for (int i = n; i >= 2; i--) {
            // Swap root with last element
            int temp = arr[1];
            arr[1] = arr[i];
            arr[i] = temp;

            tempSize--; // reduce heap size
            Heapify(arr, tempSize, 1);
        }
    }

    static void print(int[] arr, int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BuildHeap heap = new BuildHeap();

        int[] arr = {-1, 54, 53, 55, 52, 50}; // 1-based indexing
        int n = arr.length - 1;

        // Build heap (heapify non-leaf nodes)
        for (int i = n / 2; i >= 1; i--) {
            Heapify(arr, n, i);
        }

        System.out.print("Heapified array: ");
        print(arr, n);

        heapSort(arr, n);

        System.out.print("Sorted array: ");
        print(arr, n);
    }
}
