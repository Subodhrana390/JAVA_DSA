package Graph;

import java.util.Vector;

public class BellmanFord {

    int bellmanFord(int n, int m, int src, int dest, Vector<Vector<Integer>> edges) {
        Vector<Integer> dist = new Vector<>();

        // Initialize distances
        for (int i = 0; i <= n; i++) {
            dist.add(Integer.MAX_VALUE);
        }
        dist.set(src, 0);

        // Relax edges (n - 1) times
        for (int i = 1; i <= n - 1; i++) {
            for (int j = 0; j < m; j++) {
                int u = edges.get(j).get(0);
                int v = edges.get(j).get(1);
                int wt = edges.get(j).get(2);

                if (dist.get(u) != Integer.MAX_VALUE && dist.get(u) + wt < dist.get(v)) {
                    dist.set(v, dist.get(u) + wt);
                }
            }
        }

        // Check for negative weight cycle
        for (int j = 0; j < m; j++) {
            int u = edges.get(j).get(0);
            int v = edges.get(j).get(1);
            int wt = edges.get(j).get(2);

            if (dist.get(u) != Integer.MAX_VALUE && dist.get(u) + wt < dist.get(v)) {
                return -1;  // Negative weight cycle detected
            }
        }

        return dist.get(dest) == Integer.MAX_VALUE ? -1 : dist.get(dest);
    }

    public static void main(String[] args) {
        BellmanFord bf = new BellmanFord();
        int n = 5, m = 8;
        int src = 0, dest = 4;

        Vector<Vector<Integer>> edges = new Vector<>();
        edges.add(new Vector<>(java.util.List.of(0, 1, -1)));
        edges.add(new Vector<>(java.util.List.of(0, 2, 4)));
        edges.add(new Vector<>(java.util.List.of(1, 2, 3)));
        edges.add(new Vector<>(java.util.List.of(1, 3, 2)));
        edges.add(new Vector<>(java.util.List.of(1, 4, 2)));
        edges.add(new Vector<>(java.util.List.of(3, 2, 5)));
        edges.add(new Vector<>(java.util.List.of(3, 1, 1)));
        edges.add(new Vector<>(java.util.List.of(4, 3, -3)));

        int result = bf.bellmanFord(n, m, src, dest, edges);
        System.out.println("Shortest distance from " + src + " to " + dest + ": " + result);
    }
}
