package DynamicProgramming;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

class Pair {
    int day, cost;

    Pair(int day, int cost) {
        this.day = day;
        this.cost = cost;
    }
}


public class NinjaTrip {

    // ----------- Pure Recursive Function -----------
    int solveRecursive(int n, Vector<Integer> days, Vector<Integer> cost, int index) {
        if (index >= n) return 0;

        int option1 = cost.get(0) + solveRecursive(n, days, cost, index + 1);

        int i = index;
        while (i < n && days.get(i) < days.get(index) + 7) i++;
        int option2 = cost.get(1) + solveRecursive(n, days, cost, i);

        i = index;
        while (i < n && days.get(i) < days.get(index) + 30) i++;
        int option3 = cost.get(2) + solveRecursive(n, days, cost, i);

        return Math.min(option1, Math.min(option2, option3));
    }

    int minimumCoinsRecursive(int n, Vector<Integer> days, Vector<Integer> cost) {
        return solveRecursive(n, days, cost, 0);
    }

    // ----------- Memoized Recursive Function -----------
    int solveMemo(int n, Vector<Integer> days, Vector<Integer> cost, int index, Vector<Integer> dp) {
        if (index >= n) return 0;

        if (dp.get(index) != -1)
            return dp.get(index);

        int option1 = cost.get(0) + solveMemo(n, days, cost, index + 1, dp);

        int i = index;
        while (i < n && days.get(i) < days.get(index) + 7) i++;
        int option2 = cost.get(1) + solveMemo(n, days, cost, i, dp);

        i = index;
        while (i < n && days.get(i) < days.get(index) + 30) i++;
        int option3 = cost.get(2) + solveMemo(n, days, cost, i, dp);

        int minCost = Math.min(option1, Math.min(option2, option3));
        dp.set(index, minCost);
        return minCost;
    }

    int minimumCoinsMemo(int n, Vector<Integer> days, Vector<Integer> cost) {
        Vector<Integer> dp = new Vector<>();
        for (int i = 0; i < n; i++) dp.add(-1);
        return solveMemo(n, days, cost, 0, dp);
    }

    // ----------- Tabulation (Bottom-Up DP) -----------
    int minimumCoinsTabulation(int n, Vector<Integer> days, Vector<Integer> cost) {
        Vector<Integer> dp = new Vector<>(n + 1);
        for (int i = 0; i <= n; i++) dp.add(0);  // Fill with 0 initially

        for (int k = n - 1; k >= 0; k--) {
            int option1 = cost.get(0) + dp.get(k + 1);

            int i = k;
            while (i < n && days.get(i) < days.get(k) + 7) i++;
            int option2 = cost.get(1) + dp.get(i);

            i = k;
            while (i < n && days.get(i) < days.get(k) + 30) i++;
            int option3 = cost.get(2) + dp.get(i);

            dp.set(k, Math.min(option1, Math.min(option2, option3)));
        }

        return dp.get(0);
    }

    int solveOptimized(int n, Vector<Integer> days, Vector<Integer> cost) {
        int[] dp = new int[n + 1];  // dp[i] = min cost from i to end

        for (int k = n - 1; k >= 0; k--) {
            // Option 1: 1-day pass
            int option1 = cost.get(0) + dp[k + 1];

            // Option 2: 7-day pass
            int i = k;
            while (i < n && days.get(i) < days.get(k) + 7) i++;
            int option2 = cost.get(1) + dp[i];

            // Option 3: 30-day pass
            i = k;
            while (i < n && days.get(i) < days.get(k) + 30) i++;
            int option3 = cost.get(2) + dp[i];

            dp[k] = Math.min(option1, Math.min(option2, option3));
        }

        return dp[0];
    }


    int solveUsingQueue(Vector<Integer> days, Vector<Integer> cost) {
        Queue<Pair> weekQueue = new LinkedList<>();
        Queue<Pair> monthQueue = new LinkedList<>();
        int totalCost = 0;

        for (int day : days) {
            // Remove expired 7-day passes
            while (!weekQueue.isEmpty() && weekQueue.peek().day + 7 <= day)
                weekQueue.poll();

            // Remove expired 30-day passes
            while (!monthQueue.isEmpty() && monthQueue.peek().day + 30 <= day)
                monthQueue.poll();

            // Cost options
            int cost1 = totalCost + cost.get(0);  // Buy 1-day pass
            int cost7 = weekQueue.isEmpty() ? Integer.MAX_VALUE : weekQueue.peek().cost;
            int cost30 = monthQueue.isEmpty() ? Integer.MAX_VALUE : monthQueue.peek().cost;

            totalCost = Math.min(cost1, Math.min(cost7, cost30));

            // Push new total cost into queues
            weekQueue.offer(new Pair(day, totalCost + cost.get(1)));
            monthQueue.offer(new Pair(day, totalCost + cost.get(2)));
        }

        return totalCost;
    }

    // ----------- Main Function -----------
    public static void main(String[] args) {
        NinjaTrip trip = new NinjaTrip();
        Vector<Integer> days = new Vector<>();
        Vector<Integer> cost = new Vector<>();

        // Sample input
        int[] inputDays = {1, 4, 6, 7, 8, 20};
        int[] inputCost = {2, 7, 15};

        for (int d : inputDays) days.add(d);
        for (int c : inputCost) cost.add(c);

        int n = days.size();

        System.out.println("Using Pure Recursion: " + trip.minimumCoinsRecursive(n, days, cost));
        System.out.println("Using Memoization:    " + trip.minimumCoinsMemo(n, days, cost));
        System.out.println("Using Tabulation:     " + trip.minimumCoinsTabulation(n, days, cost));
        System.out.println("Using Optimized Tab:   " + trip.solveOptimized(n, days, cost));
        System.out.println("Using Queue:           " + trip.solveUsingQueue(days, cost));

    }
}
