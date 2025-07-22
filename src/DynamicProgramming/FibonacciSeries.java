package DynamicProgramming;

import java.util.Vector;

/**
 * Class to compute Fibonacci numbers using Dynamic Programming.
 * Includes Top-Down (Memoization), Bottom-Up (Tabulation),
 * and Space-Optimized approaches.
 */
public class FibonacciSeries {

    /**
     * Recursive helper function to compute Fibonacci using memoization (Top-Down DP).
     *
     * @param n  The Fibonacci term to compute.
     * @param dp Memoization table storing previously computed values.
     * @return The nth Fibonacci number.
     */
    int solve(int n, Vector<Integer> dp) {
        if (n <= 1) {
            return n;
        }

        if (dp.get(n) != -1) {
            return dp.get(n);
        }

        dp.set(n, solve(n - 1, dp) + solve(n - 2, dp));
        return dp.get(n);
    }

    /**
     * Computes Fibonacci using Top-Down (Memoization) approach.
     *
     * @param n The Fibonacci term to compute.
     */
    void Fibonacci(int n) {
        Vector<Integer> dp = new Vector<>(n + 1);
        for (int i = 0; i <= n; i++) {
            dp.add(-1);
        }

        int result = solve(n, dp);
        System.out.println("Top-Down: Fibonacci(" + n + ") = " + result);
    }

    /**
     * Computes Fibonacci using Bottom-Up (Tabulation) approach.
     *
     * @param n The Fibonacci term to compute.
     */
    void Fibonacci1(int n) {
        if (n == 0) {
            System.out.println("Bottom-Up: Fibonacci(0) = 0");
            return;
        }

        Vector<Integer> dp = new Vector<>(n + 1);
        for (int i = 0; i <= n; i++) {
            dp.add(0);
        }

        dp.set(0, 0);
        dp.set(1, 1);

        for (int i = 2; i <= n; i++) {
            dp.set(i, dp.get(i - 1) + dp.get(i - 2));
        }

        System.out.println("Bottom-Up: Fibonacci(" + n + ") = " + dp.get(n));
    }

    /**
     * Computes Fibonacci using Space Optimized approach.
     * Only keeps track of last two Fibonacci numbers.
     *
     * @param n The Fibonacci term to compute.
     * @return  The nth Fibonacci number.
     */
    int Fibonacci2(int n) {
        if (n <= 1) return n;

        int prev2 = 0;
        int prev1 = 1;
        int curr = 0;

        for (int i = 2; i <= n; i++) {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return curr;
    }

    /**
     * Main method to run all Fibonacci approaches.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        FibonacciSeries obj = new FibonacciSeries();
        int n = 6;

        obj.Fibonacci(n);                  // Top-Down
        obj.Fibonacci1(n);                 // Bottom-Up
        System.out.println("Space-Optimized: Fibonacci(" + n + ") = " + obj.Fibonacci2(n));
    }
}
