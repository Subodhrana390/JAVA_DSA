package UnKnown;

public class string {
    public static void main(String[] args) {
        // String fullName = "Subodh" + "Rana";
        // System.out.println(fullName);
        // String second="SubodhRana";
        //
        // System.out.println(fullName.compareTo(second));

        // if( new String("Tony") == new String("Tony")){
        // System.out.println(" same");
        // }
        // else
        // {
        // System.out.println("not same");
        // }

        StringBuilder sb = new StringBuilder("Subodh Rana");
        System.out.println(sb);

        sb.setCharAt(0, 'p');
        sb.delete(0, 1);
        System.out.println(sb);

    }
}
