package collectionDemo;

import java.util.*;
import java.util.stream.Collectors;

public class AbstractSequentialListDemo {
    public static void main(String[] args) {
        AbstractSequentialList<Integer> a = new LinkedList<>();
        a.add(5);
        a.add(6);
        a.add(7);
        a.set(2, 8); // Change 7 to 8 at index 2

        System.out.print("Original list: ");
        for (int num : a) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Convert to a new list via stream (optional here)
        List<Integer> list = a.stream().collect(Collectors.toList());

        // Sort the list
        // Collections.sort(list);
        List<Integer> sortedList = a.stream().sorted().collect(Collectors.toList());

        System.out.print("Sorted list: ");
        for (int num : sortedList) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Additional info
        System.out.println("Size: " + list.size());
        System.out.println("Array: " + Arrays.toString(list.toArray()));
    }
}
