package collectionDemo;

import java.util.Hashtable;
import java.util.Vector;

public class MyAbstractList {
    public static void getHashTable() {
        int arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Vector<Integer> v = new Vector<>();
        Hashtable<Integer, String> h = new Hashtable<>();

        v.addElement(1);
        v.addElement(2);

        h.put(1, "geeks");
        h.put(2, "4geeks");

        System.out.println(arr[0]);
        System.out.println(v.elementAt(0));
        System.out.println(h.get(1));

    }

    public static void main(String[] args) {
        getHashTable();

        // AbstractList<String> list = new ArrayList<>();
        // list.add("Hello");
        // System.out.println("AbstractList:" + list);
        //
        // AbstractList<String> L_list = new LinkedList<String>();
        // L_list.add("Hello");
        // L_list.add("A");
        // L_list.add("B");
        // L_list.add("C");
        // L_list.add("D");
        // L_list.add("E");
        // L_list.add("F");
        // System.out.println("ArrayList:" + L_list);
        // L_list.remove(0);
        // System.out.println("ArrayList:" + L_list);
        // System.out.println(L_list.lastIndexOf("A"));
        // System.out.println(L_list.get(0));
        // System.out.println(L_list.indexOf("B"));
        // L_list.set(0, "X");
        //
        // for (String s : L_list) {
        // System.out.print(s + " ");
        // }

    }
}