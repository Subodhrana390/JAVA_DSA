package sorting;

public class SelectionSort {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int size;
//        System.out.println("Enter the size of Arr :");
//        size = sc.nextInt();
//
//        int[] arr = new int[size];

        int[] arr = {54, 26, 93, 17, 77, 31, 44, 55, 20};
//        System.out.println("Enter Elements : ");
//        for (int i = 0; i < size; i++) {
//            System.out.printf("Enter Element arr[%d] : ", i + 1);
//            arr[i] = sc.nextInt();
//        }

        System.out.println("Original Unsorted Element :");
        for (int k : arr) {
            System.out.printf("%d ", k);
        }

        System.out.println();
        sortRecursive(arr, 0);

        System.out.println("Display sorted Element :");
        for (int j : arr) {
            System.out.printf("%d ", j);
        }

    }

    public static void sort(int[] arr, int n) {

        for (int i = 0; i < n - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex])
                    minIndex = j;
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void sortRecursive(int[] arr, int n) {
        // Base case: If starting index reaches array length, return
        if (n == arr.length - 1) {
            return;
        }

        // Find the index of the minimum element from n to end
        int minIndex = n;
        for (int i = n + 1; i < arr.length; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }

        // Swap the found minimum with the current position
        int temp = arr[minIndex];
        arr[minIndex] = arr[n];
        arr[n] = temp;

        // Recursive call for the rest of the array
        sortRecursive(arr, n + 1);
    }
}
