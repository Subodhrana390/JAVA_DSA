package UnKnown;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class Test {

    public static void main(String[] args) {
//        List<Integer> arr = new ArrayList<>();
        Map<String, Integer> marks = new HashMap<>();

        marks.put("rahul", 100);
        marks.put("simmy", 200);

        for (Map.Entry<String, Integer> entry : marks.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("--------");
        }

        Optional<Integer> Ronit = Optional.ofNullable(marks.get("prohibit"));

        if (Ronit.isPresent()) {
            System.out.println(marks.get("prohibit"));
        } else {
            System.out.println("I am not present");
        }


        int[] arr = {1, 2, 4};
        for (int j : arr) {
            System.out.println(j);
        }
        //Working List,Maps, ArrayList , ImmutableList, Set,streams, interface
        // Learn it
        //Null Pointer Exception
    }

    //Main
//    public static void main(String[] args) {
//        List<String> arr = new ArrayList<>();
//        int[] arr1 = {1, 2, 4};
//
//        arr.add("a");
//        arr.add("b");
//
//        for (int i = 0; i < arr.size(); i++) {
//            System.out.println(arr.get(i));
//        }
//
//    }
}