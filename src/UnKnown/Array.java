package UnKnown;

import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Array {
    public static void main(String[] args) {
//        String[] name = new String[3];
//        int[] num = { 1, 2, 34, 56, 78 };
//        Scanner sc = new Scanner(System.in);
//
//        for (int i = 0; i < 3; i++) {
//            System.out.print("Enter name " + (i + 1) + ": ");
//            name[i] = sc.nextLine();
//        }
//
//        System.out.println("\nYou entered:");
//        for (int i = 0; i < 5; i++) {
//            System.out.println(num[i]);
//        }
//
//        sc.close();
//        int n = 5;
//        int[] values = {20, 10, 50, 40};
//        System.out.println(values.length);
//
//        Arrays.sort(values);
//        int index = Arrays.binarySearch(values, 50);
//        System.out.println(index);
//
//        int[] arr = new int[5];
//        Arrays.fill(arr, 3);
//        System.out.println(Arrays.toString(arr));
//
//        int[] copy = Arrays.copyOf(values, values.length);
//        System.out.println(Arrays.toString(copy));
//
//        int[][] matrix = {{1, 2, 4}, {3, 4, 5}};
//        System.out.println(Arrays.deepToString(matrix));

//        int n = 10;
//        Vector<Integer> vector = new Vector<>(n + 1);
//        vector.add(1);
//        vector.add(2);
//        vector.add(3);
//        vector.add(1, 4);
//
//        System.out.println(vector);
//        System.out.println(vector.get(1));
//        vector.remove(1);
////        vector.clear();
//        System.out.println(vector.contains(3));

        Vector<Vector<Integer>> matrix = new Vector<>(3);
        for (int i = 0; i < 3; i++) {
            Vector<Integer> row = new Vector<>(Collections.nCopies(3, -1));
            matrix.add(row);
        }

        System.out.println(matrix);

        int rows = 3;
        int cols = 4;

        Vector<Vector<Integer>> m = IntStream.range(0, rows).mapToObj(i -> new Vector<>(Collections.nCopies(cols, 0))).collect(Collectors.toCollection(Vector::new));
        System.out.println(m);

        HashMap<String, String> map = new HashMap<>();
        map.put(null, "value");
        map.put("key", null);
        System.out.println(map);


    }


}
