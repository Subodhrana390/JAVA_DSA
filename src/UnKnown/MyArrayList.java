package UnKnown;
import java.util.ArrayList;
import java.util.Collections;

public class MyArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(3);
        System.out.println(list);
        System.out.println(list.get(2));
        list.add(1, 2);
//        list.remove(2);
        list.set(1, 4);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }
}
