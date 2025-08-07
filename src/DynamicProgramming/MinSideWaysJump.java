package DynamicProgramming;

import java.util.Arrays;

public class MinSideWaysJump {

    // ------------------ Method 1: Recursion ------------------
    public static int minJumpsRec(int currLane, int pos, int[] obstacles) {
        int n = obstacles.length - 1;
        if (pos == n) return 0; // reached end

        if (obstacles[pos + 1] != currLane) {
            // move forward
            return minJumpsRec(currLane, pos + 1, obstacles);
        } else {
            // need to side jump
            int ans = Integer.MAX_VALUE;
            for (int lane = 1; lane <= 3; lane++) {
                if (lane != currLane && obstacles[pos] != lane) {
                    ans = Math.min(ans, 1 + minJumpsRec(lane, pos, obstacles));
                }
            }
            return ans;
        }
    }

    // ------------------ Method 2: Memoization ------------------
    public static int minJumpsMemo(int currLane, int pos, int[] obstacles, int[][] dp) {
        int n = obstacles.length - 1;
        if (pos == n) return 0;
        if (dp[currLane][pos] != -1) return dp[currLane][pos];

        if (obstacles[pos + 1] != currLane) {
            dp[currLane][pos] = minJumpsMemo(currLane, pos + 1, obstacles, dp);
        } else {
            int ans = Integer.MAX_VALUE;
            for (int lane = 1; lane <= 3; lane++) {
                if (lane != currLane && obstacles[pos] != lane) {
                    ans = Math.min(ans, 1 + minJumpsMemo(lane, pos, obstacles, dp));
                }
            }
            dp[currLane][pos] = ans;
        }

        return dp[currLane][pos];
    }

    // ------------------ Method 3: Tabulation (FIXED) ------------------
    public static int minJumpsTab(int[] obstacles) {
        int n = obstacles.length - 1;
        int[][] dp = new int[4][n + 1];

        // Initialize all positions with max value
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Base cases (starting point is position 0)
        dp[1][0] = 1;  // Starting in lane 1 requires 1 jump to reach lane 2
        dp[2][0] = 0;  // Starting in lane 2 requires 0 jumps
        dp[3][0] = 1;  // Starting in lane 3 requires 1 jump to reach lane 2

        for (int pos = 1; pos <= n; pos++) {
            // First check if we can continue in the same lane
            for (int lane = 1; lane <= 3; lane++) {
                if (obstacles[pos] != lane) {
                    if (dp[lane][pos - 1] != Integer.MAX_VALUE) {
                        dp[lane][pos] = dp[lane][pos - 1];
                    }
                }
            }

            // Consider side jumps from other lanes
            for (int lane = 1; lane <= 3; lane++) {
                if (obstacles[pos] != lane) {
                    for (int k = 1; k <= 3; k++) {
                        if (lane != k && obstacles[pos] != k && dp[k][pos] != Integer.MAX_VALUE) {
                            dp[lane][pos] = Math.min(dp[lane][pos], dp[k][pos] + 1);
                        }
                    }
                }
            }
        }

        return Math.min(dp[1][n], Math.min(dp[2][n], dp[3][n]));
    }

    // ------------------ Method 4: Space Optimization (FIXED) ------------------
    public static int minJumpsSpaceOpt(int[] obstacles) {
        int n = obstacles.length - 1;
        int[] prev = new int[4];
        Arrays.fill(prev, Integer.MAX_VALUE);

        // Starting point
        prev[1] = 1;
        prev[2] = 0;
        prev[3] = 1;

        for (int pos = 1; pos <= n; pos++) {
            int[] curr = new int[4];
            Arrays.fill(curr, Integer.MAX_VALUE);

            // First check if we can continue in same lane
            for (int lane = 1; lane <= 3; lane++) {
                if (obstacles[pos] != lane && prev[lane] != Integer.MAX_VALUE) {
                    curr[lane] = prev[lane];
                }
            }

            // Consider side jumps from other lanes
            for (int lane = 1; lane <= 3; lane++) {
                if (obstacles[pos] != lane) {
                    for (int k = 1; k <= 3; k++) {
                        if (lane != k && obstacles[pos] != k && curr[k] != Integer.MAX_VALUE) {
                            curr[lane] = Math.min(curr[lane], curr[k] + 1);
                        }
                    }
                }
            }

            prev = curr;
        }

        return Math.min(prev[1], Math.min(prev[2], prev[3]));
    }

    public static void main(String[] args) {
        int[] obstacles = {0, 1, 2, 3, 0}; // Example test case

        System.out.println("Recursion: " + minJumpsRec(2, 0, obstacles));

        int[][] dp = new int[4][obstacles.length];
        for (int[] row : dp) Arrays.fill(row, -1);
        System.out.println("Memoization: " + minJumpsMemo(2, 0, obstacles, dp));

        System.out.println("Tabulation: " + minJumpsTab(obstacles));

        System.out.println("Space Optimized: " + minJumpsSpaceOpt(obstacles));
    }
}
