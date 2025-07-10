package Hashing.Interview;

import java.util.HashMap;

public class UnionOfTwoArray {

    public static void unionSet(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i : arr1) {
            map.put(i, 1);
        }

        for (int i : arr2) {
            map.put(i, 1);
        }

        System.out.println("Union of arrays:" + map.size());

    }

    public static void main(String[] args) {
        int[] arr1 = { 7, 3 };
        int[] arr2 = { 6, 3, 9, 2, 9, 4 };
        unionSet(arr1, arr2);
    }
}
