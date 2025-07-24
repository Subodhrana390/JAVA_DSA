package DynamicProgramming;

public class HouseRobberyProblem {

    public static int rob(int[] houses) {
        int n = houses.length;
        if (n == 0) return 0;
        if (n == 1) return houses[0];

        int[] dp = new int[n];
        dp[0] = houses[0];
        dp[1] = Math.max(houses[0], houses[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + houses[i]);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] houses = {2, 7, 9, 3, 1};
        int maxRobbedAmount = rob(houses);
        System.out.println("Maximum amount that can be robbed: " + maxRobbedAmount);
    }
}
