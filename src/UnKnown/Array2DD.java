package UnKnown;
import java.util.Scanner;

public class Array2DD
{
    public static void main(String[] args){
        int[][] mat = new int[4][5];
        Scanner sc = new Scanner(System.in);
        for (int i=0;i<4;i++){
            for (int j=0;j<5;j++){
                mat[i][j]= sc.nextInt();
            }
        }

        for (int i=0;i<4;i++){
            for (int j=0;j<5;j++){
              System.out.print(mat[i][j]);
            }
        }

        sc.close();
    }
}
