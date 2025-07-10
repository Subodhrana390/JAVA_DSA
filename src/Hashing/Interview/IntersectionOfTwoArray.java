package Hashing.Interview;

import java.util.HashSet;

public class IntersectionOfTwoArray {

    public static void Intersection(int[] arr1, int[] arr2) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();

        // Add elements of arr1 to set
        for (int num : arr1) {
            set.add(num);
        }

        // Check for intersection with arr2
        for (int num : arr2) {
            if (set.contains(num)) {
                result.add(num);
            }
        }

        // Print result
        for (int num : result) {
            System.out.print(num + " ");
        } 
    }

    public static void main(String[] args) {
        int[] arr1 = { 7, 3, 9 };
        int[] arr2 = { 6, 3, 9, 2, 9, 4 };
        Intersection(arr1, arr2);
    }
}
