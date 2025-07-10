package Hashing.Interview;

import java.util.HashMap;

public class FindItnerayFromTicket {

    public static String getStart(HashMap<String, String> Tick) {
        HashMap<String, String> revMap = new HashMap<>();
        for (String key : Tick.keySet()) {
            revMap.put(Tick.get(key), key);
        }

        for (String key : Tick.keySet()) {
            if (!revMap.containsKey(key)) {
                return key;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Chennai", "Bengaluru");
        map.put("Mumbai", "Delhi");
        map.put("Goa", "Chennai");
        map.put("Delhi", "Goa");

        String start = getStart(map);

        while (map.containsKey(start)) {
            System.out.print(start + " -> ");
            start = map.get(start);
        }
        System.out.print(start);
    }
}
