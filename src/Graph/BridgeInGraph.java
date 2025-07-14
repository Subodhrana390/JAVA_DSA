package Graph;

import java.util.*;

public class BridgeInGraph {

    Vector<Vector<Integer>> findBridge(Vector<Vector<Integer>> edges, int vn, int e) {
        // Build adjacency list
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (int i = 0; i < edges.size(); i++) {
            int u = edges.get(i).get(0);
            int v = edges.get(i).get(1);
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        int[] time = {0};
        int[] disc = new int[vn];
        int[] low = new int[vn];
        boolean[] visited = new boolean[vn];
        Vector<Vector<Integer>> bridges = new Vector<>();

        // Initialize discovery and low arrays
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);

        // Call DFS for each component
        for (int i = 0; i < vn; i++) {
            if (!visited[i]) {
                dfs(i, -1, adj, visited, disc, low, time, bridges);
            }
        }

        return bridges;
    }

    private void dfs(int u, int parent, HashMap<Integer, ArrayList<Integer>> adj,
                     boolean[] visited, int[] disc, int[] low, int[] time,
                     Vector<Vector<Integer>> bridges) {

        visited[u] = true;
        disc[u] = low[u] = time[0]++;

        for (int v : adj.getOrDefault(u, new ArrayList<>())) {
            if (v == parent) continue; // Skip the parent

            if (!visited[v]) {
                dfs(v, u, adj, visited, disc, low, time, bridges);

                // Check if the edge (u, v) is a bridge
                if (low[v] > disc[u]) {
                    Vector<Integer> bridge = new Vector<>();
                    bridge.add(u);
                    bridge.add(v);
                    bridges.add(bridge);
                }

                low[u] = Math.min(low[u], low[v]);
            } else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static void main(String[] args) {
        BridgeInGraph bg = new BridgeInGraph();

        // Sample input: undirected graph edges
        Vector<Vector<Integer>> edges = new Vector<>();
        edges.add(new Vector<>(Arrays.asList(0, 1)));
        edges.add(new Vector<>(Arrays.asList(1, 2)));
        edges.add(new Vector<>(Arrays.asList(2, 0)));
        edges.add(new Vector<>(Arrays.asList(1, 3)));

        Vector<Vector<Integer>> bridges = bg.findBridge(edges, 4, edges.size());

        System.out.println("Bridges in the graph:");
        for (Vector<Integer> bridge : bridges) {
            System.out.println(bridge.get(0) + " - " + bridge.get(1));
        }
    }
}
