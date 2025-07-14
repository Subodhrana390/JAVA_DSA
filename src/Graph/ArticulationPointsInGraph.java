package Graph;

import java.util.*;

public class ArticulationPointsInGraph {

    public List<Integer> findArticulationPoints(int V, Vector<Vector<Integer>> edges) {
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        for (Vector<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] disc = new int[V];
        int[] low = new int[V];
        boolean[] visited = new boolean[V];
        boolean[] isAP = new boolean[V]; // To store articulation points
        int[] time = {0};

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, -1, adj, visited, disc, low, time, isAP);
            }
        }

        // Collect articulation points
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (isAP[i]) result.add(i);
        }

        return result;
    }

    private void dfs(int u, int parent, List<List<Integer>> adj, boolean[] visited,
                     int[] disc, int[] low, int[] time, boolean[] isAP) {

        visited[u] = true;
        disc[u] = low[u] = time[0]++;
        int children = 0;

        for (int v : adj.get(u)) {
            if (v == parent) continue;

            if (!visited[v]) {
                children++;
                dfs(v, u, adj, visited, disc, low, time, isAP);
                low[u] = Math.min(low[u], low[v]);

                // If u is not root and low[v] >= disc[u], it's an articulation point
                if (parent != -1 && low[v] >= disc[u]) {
                    isAP[u] = true;
                }
            } else {
                // Back edge
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        // If u is root and has more than one child
        if (parent == -1 && children > 1) {
            isAP[u] = true;
        }
    }

    public static void main(String[] args) {
        ArticulationPointsInGraph apg = new ArticulationPointsInGraph();

        // Sample graph: 0-1-2-3-4 with a back edge 2-0
        Vector<Vector<Integer>> edges = new Vector<>();
        edges.add(new Vector<>(Arrays.asList(0, 1)));
        edges.add(new Vector<>(Arrays.asList(1, 2)));
        edges.add(new Vector<>(Arrays.asList(2, 0)));
        edges.add(new Vector<>(Arrays.asList(1, 3)));
        edges.add(new Vector<>(Arrays.asList(3, 4)));

        List<Integer> articulationPoints = apg.findArticulationPoints(5, edges);
        System.out.println("Articulation Points:");
        for (int point : articulationPoints) {
            System.out.println(point);
        }
    }
}
