
public class BinarySearch {

    public static int binarySearch(int arr[], int key) {

        int s = 0;
        int e = arr.length - 1;
        int mid = s + (e - s) / 2;
        while (s <= e) {
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
            mid = s + (e - s) / 2;
        }
        return -1;
    }

    // Recursive binary search method
    public static int binarySearch1(int arr[], int s, int e, int key) {
        if (s > e)
            return -1;

        int mid = s + (e - s) / 2;
        if (arr[mid] == key) {
            return mid;
        } else if (arr[mid] < key) {
            return binarySearch1(arr, mid + 1, e, key);
        } else {
            return binarySearch1(arr, s, mid - 1, key);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 2, 4, 6, 8, 10, 12, 14 }; // Sorted array
        int key = 10;

        int index = binarySearch1(arr, 0, arr.length - 1, key);

        if (index != -1)
            System.out.println("Element found at index: " + index);
        else
            System.out.println("Element not found");

    }
}
