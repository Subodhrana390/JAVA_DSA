package backtracking;

import java.util.Collections;
import java.util.Vector;

public class RatInMazeProblem {

    boolean isSafe(int x, int y, Vector<Vector<Boolean>> visited, Vector<Vector<Integer>> arr, int n) {
        return (x >= 0 && x < n) && (y >= 0 && y < n) &&
                !visited.get(x).get(y) &&
                arr.get(x).get(y) == 1;
    }

    void solve(int x, int y, Vector<Vector<Integer>> arr, int n,
               Vector<String> ans, Vector<Vector<Boolean>> visited, StringBuilder path) {

        if (x == n - 1 && y == n - 1) {
            ans.add(path.toString());
            return;
        }

        visited.get(x).set(y, true);

        // Possible moves and directions: D, L, R, U
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, -1, 1, 0};
        char[] dir = {'D', 'L', 'R', 'U'};

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (isSafe(newX, newY, visited, arr, n)) {
                path.append(dir[i]);
                solve(newX, newY, arr, n, ans, visited, path);
                path.deleteCharAt(path.length() - 1); // backtrack
            }
        }

        visited.get(x).set(y, false); // backtrack
    }

    Vector<String> searchMaze(Vector<Vector<Integer>> arr, int n) {
        Vector<String> ans = new Vector<>();

        if (arr.getFirst().getFirst() == 0) return ans; // start is blocked

        Vector<Vector<Boolean>> visited = new Vector<>();
        for (int i = 0; i < n; i++) {
            visited.add(new Vector<>(Collections.nCopies(n, false)));
        }

        StringBuilder path = new StringBuilder();
        solve(0, 0, arr, n, ans, visited, path);

        return ans;
    }

    public static void main(String[] args) {
        RatInMazeProblem rat = new RatInMazeProblem();

        Vector<Vector<Integer>> maze = new Vector<>();
        maze.add(new Vector<>(java.util.List.of(1, 0, 0, 0)));
        maze.add(new Vector<>(java.util.List.of(1, 1, 0, 1)));
        maze.add(new Vector<>(java.util.List.of(1, 1, 0, 0)));
        maze.add(new Vector<>(java.util.List.of(0, 1, 1, 1)));

        Vector<String> result = rat.searchMaze(maze, 4);

        System.out.println("All paths:");
        for (String path : result) {
            System.out.println(path);
        }
    }
}
