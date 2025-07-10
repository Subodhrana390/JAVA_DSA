package Hashing;

import java.util.HashMap;
import java.util.HashSet;

public class Hashing {

    public static void HashedMap() {
        HashMap<String, Integer> countryInfo = new HashMap<>();
        countryInfo.put("India", 100);
        countryInfo.put("USA", 200);
        countryInfo.put("China", 300);
//        System.out.println(countryInfo);

        countryInfo.put("India", 1000);
//        System.out.println(countryInfo);

        if (countryInfo.containsKey("India")) {
            System.out.println("India is present");
        } else {
            System.out.println("India is not present");
        }

        System.out.println(countryInfo.get("China"));

//        for (Map.Entry<String, Integer> entry : countryInfo.entrySet()) {
//            System.out.println(entry.getKey() + " => " + entry.getValue());
//        }
//
//        Set<String> keys = countryInfo.keySet();
//        for (String key : keys) {
//            System.out.println(key + "=>" + countryInfo.get(key));
//        }

        countryInfo.remove("India");
        System.out.println(countryInfo);
    }

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1);
//        System.out.println(set);
//
//        if (set.contains(1))
//            System.out.println("Element is present");
//        else
//            System.out.println("Element is not present");
//
//        set.remove(2);
//        System.out.println(set.size());
//        System.out.println(set);

//        iterator

//        Iterator it = set.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }
        HashedMap();

    }


}
