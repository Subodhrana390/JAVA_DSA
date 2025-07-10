package Hashing;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class STL {

    public int maxFrequencyElements(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();

        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0;
        for (int freq : count.values()) {
            maxFreq = Math.max(maxFreq, freq);
        }

        int sum = 0;
        for (int freq : count.values()) {
            if (freq == maxFreq) {
                sum += freq;
            }
        }

        return sum;
    }


    public static void main(String[] args) {

        // Use LinkedHashMap to preserve insertion order
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        map.put("Love", 1);
        map.put("Mike", 2);

        System.out.println("Value for key 'Love': " + map.get("Love")); // 1
        System.out.println("Value for key 's': " + map.get("s")); // null (key doesn't exist)
        System.out.println("Size of map: " + map.size()); // 2
        System.out.println("Keys: " + map.keySet()); // [Love, Mike]

        // Iterating using keySet
        for (String key : map.keySet()) {
            System.out.println("Key: " + key + ", Value: " + map.get(key));
        }

        // Iterating using entrySet
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Entry: " + entry);
        }
    }
}
