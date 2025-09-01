package greedyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class ShopInCandyStore {

    public ArrayList<Integer> minMaxCandy(int[] prices, int k) {
        Arrays.sort(prices); // sort prices in ascending order
        int n = prices.length;

        // Minimum cost calculation
        int i = 0, j = n - 1;
        int minCost = 0;
        while (i <= j) {
            minCost += prices[i];
            i++;
            j -= k; // take k most expensive for free
        }

        // Maximum cost calculation
        i = 0;
        j = n - 1;
        int maxCost = 0;
        while (i <= j) {
            maxCost += prices[j];
            j--;
            i += k; // take k cheapest for free
        }

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(minCost);
        ans.add(maxCost);
        return ans;
    }

    public static void main(String[] args) {
        ShopInCandyStore obj = new ShopInCandyStore();

        int[] prices = {3, 2, 1, 4};
        int k = 2;

        ArrayList<Integer> result = obj.minMaxCandy(prices, k);
        System.out.println("Minimum amount to buy all candies: " + result.get(0));
        System.out.println("Maximum amount to buy all candies: " + result.get(1));
    }
}
