package UnKnown;
import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        String[] name = new String[3];
        int[] num = { 1, 2, 34, 56, 78 };
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.print("Enter name " + (i + 1) + ": ");
            name[i] = sc.nextLine();
        }

        System.out.println("\nYou entered:");
        for (int i = 0; i < 5; i++) {
            System.out.println(num[i]);
        }

        sc.close();
    }
}
