import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public static ArrayList<ArrayList<Integer>> pairSum(int[] arr, int n) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == 1) {
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(i);
                    pair.add(j);
                    result.add(pair);
                }
            }
        }

        return result;
    }

    public static void reverse(int[] arr) {

        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }

    }

    public static void reverseArray() {
        int[] arr = { 6, 7, 9, 11, 2, 5 };
        reverse(arr);
        for (int it : arr) {
            System.out.print(it + " ");
        }
    }

    public static List<Integer> findDuplicate(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        for (int num : arr) {
            if (!seen.add(num)) {
                duplicates.add(num);
            }
        }

        return new ArrayList<>(duplicates);
    }

    public static List<Integer> arrayIntersection(int[] arr1, int[] arr2) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        for (int num : arr1) {
            seen.add(num);
        }

        for (int num : arr2) {
            if (seen.contains(num)) {
                duplicates.add(num);
            }
        }

        return new ArrayList<>(duplicates);
    }

    public static int findUnique(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // Count frequencies of each number
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Find the number that appears only once
        for (int key : map.keySet()) {
            if (map.get(key) == 1) {
                return key;
            }
        }

        // Return -1 if no unique element is found
        return -1;
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        int[] arr = { 1, 1, 0, 0, 0, 0, 1, 0 };
        int[] arr1 = { 1, 2, 2, 1, 1, 3 };
        int n = arr.length;
        List<Integer> ans = findDuplicate(arr1);
        System.out.println(ans);

        reverseArray();
        // ArrayList<ArrayList<Integer>> sum = pairSum(arr, n);

        // for (ArrayList<Integer> pair : sum) {
        // System.out.print(pair + " ");
        // }
    }

}
