package greedyAlgorithm;

public class SurviveOnIsland {

    int minimumDays(int S, int N, int M) {
        // 1. If you need more food per day than you can buy, impossible
        if (M > N) return -1;

        // 2. Total food required to survive S days
        int totalFoodRequired = S * M;

        // 3. Number of Sundays in S days (shop is closed)
        int sundays = S / 7;

        // 4. Maximum buying days
        int buyingDays = S - sundays;

        // 5. Maximum food that can be bought
        int maxFoodPossible = buyingDays * N;

        // 6. If not enough food can be bought
        if (totalFoodRequired > maxFoodPossible) return -1;

        // 7. Otherwise, calculate minimum number of days needed to buy enough food
        int minDays = (int) Math.ceil((double) totalFoodRequired / N);

        return minDays;
    }

    public static void main(String[] args) {
        SurviveOnIsland island = new SurviveOnIsland();

        // Example inputs
        int S = 10, N = 16, M = 2;
        int result = island.minimumDays(S, N, M);

        System.out.println("Minimum number of days: " + result);
    }
}
