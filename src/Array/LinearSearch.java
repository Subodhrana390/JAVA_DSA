import java.util.HashSet;

public class LinearSearch {

    public static boolean search(int[] arr, int key) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        return set.contains(key);
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        int key = 30;

        if (search(arr, key)) {
            System.out.println("Found");
        } else {
            System.out.println("Not Found");
        }
    }
}
