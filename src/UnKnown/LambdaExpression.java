package UnKnown;

import java.util.*;

@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}

class Number implements Comparable<Number> {

    int v;

    public Number(int v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return String.valueOf(v);
    }

    @Override
    public int compareTo(Number o) {
        return v - o.v;
    }


}

class Pair implements Comparable<Pair> {
    String f; // First name
    String l;  // Last name

    // Constructor
    public Pair(String f, String l) {
        this.f = f;
        this.l = l;
    }

    // toString() method
    // for displaying the Pair
    @Override
    public String toString() {
        return "(" + f + ", " + l + ")";
    }

    @Override
    public int compareTo(Pair p) {
        if (this.f.compareTo(p.f) != 0) {
            return this.f.compareTo(p.f);
        }
        return this.l.compareTo(p.l);
    }
}

class Student {
    int rollno;
    String name;

    // Constructor
    Student(int rollno, String name) {
        this.rollno = rollno;
        this.name = name;
    }

    // Method to print Student
    // details in main()
    @Override
    public String toString() {
        return rollno + ": " + name;
    }
}

// Helper class implementing Comparator interface
class SortbyRoll implements Comparator<Student> {
    // Compare by roll number in ascending order
    public int compare(Student a, Student b) {
        return a.rollno - b.rollno;
    }
}


public class LambdaExpression {
    public static void main(String[] args) {
//        Calculator calculator = (a, b) -> a + b;
//        System.out.println("Sum: " + calculator.add(5, 3));
//
//        List<String> names = Arrays.asList("ram", "john", "alice");
//        names.forEach(name -> System.out.println(name));
//
//        Number[] n = {new Number(4), new Number(1),
//                new Number(7), new Number(2)};
//
//        System.out.println("Before Sorting: "
//                + Arrays.toString(n));
//
//        Arrays.sort(n);
//        System.out.println("After Sorting: "
//                + Arrays.toString(n));

        Pair[] p = {
                new Pair("raj", "kashup"),
                new Pair("rahul", "singh"),
                new Pair("reshmi", "dubey"),
        };


        System.out.println("Before Sorting:");
        for (Pair p1 : p) {
            System.out.println(p1);
        }

        // Sort the array of pairs
        Arrays.sort(p);

        System.out.println("\nAfter Sorting:");
        for (Pair p1 : p) {
            System.out.println(p1);
        }

        List<Student> students = new ArrayList<>();

        // Add Elements in List
        students.add(new Student(111, "Mayank"));
        students.add(new Student(131, "Anshul"));
        students.add(new Student(121, "Solanki"));
        students.add(new Student(101, "Aggarwal"));

        // Sort students by roll number
        // using SortbyRoll comparator
        Collections.sort(students, new SortbyRoll());

        System.out.println("Sorted by Roll Number ");

        // Iterating over entries to print them
        for (int i = 0; i < students.size(); i++)
            System.out.println(students.get(i));

    }
}
