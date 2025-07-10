package Hashing.Interview;

import java.util.HashMap;

public class MajorityElement {

    public static void main(String[] args) {
        HashMap<Integer, Integer> countFreq = new HashMap<>();
        int[] arr = {1, 3, 2, 5, 1, 3, 1, 5, 1};

        for (int i : arr) {
            countFreq.put(i, countFreq.getOrDefault(i, 0) + 1);
        }

        for (int i : countFreq.keySet()) {
            if (countFreq.get(i) > arr.length / 3) {
                System.out.println(i);
            }
        }

    }
}
