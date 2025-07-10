package Graph;

import java.util.*;

public class ShortestPathInUndirectedPath {

    Vector<Integer> shortestPath(Vector<ArrayList<Integer>> edges, int n, int m, int s, int t) {
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();

        // Build adjacency list
        for (int i = 0; i < edges.size(); i++) {
            int u = edges.get(i).get(0);
            int v = edges.get(i).get(1);
            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());
            adj.get(u).add(v);
            adj.get(v).add(u); // undirected
        }

        HashMap<Integer, Boolean> visited = new HashMap<>();
        HashMap<Integer, Integer> parent = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();

        // BFS
        q.add(s);
        parent.put(s, -1);
        visited.put(s, true);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (Integer v : adj.get(u)) {
                if (!visited.getOrDefault(v, false)) {
                    visited.put(v, true);
                    q.add(v);
                    parent.put(v, u);
                }
            }
        }

        // Reconstruct path from t to s
        Vector<Integer> ans = new Vector<>();
        int currentNode = t;
        ans.add(currentNode);
        while (currentNode != s) {
            if (!parent.containsKey(currentNode)) {
                // No path found
                return new Vector<>();
            }
            currentNode = parent.get(currentNode);
            ans.add(currentNode);
        }

        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        ShortestPathInUndirectedPath sp = new ShortestPathInUndirectedPath();

        Vector<ArrayList<Integer>> edges = new Vector<>();
        edges.add(new ArrayList<>(Arrays.asList(1, 2)));
        edges.add(new ArrayList<>(Arrays.asList(2, 3)));
        edges.add(new ArrayList<>(Arrays.asList(3, 4)));
        edges.add(new ArrayList<>(Arrays.asList(1, 5)));
        edges.add(new ArrayList<>(Arrays.asList(5, 4)));

        Vector<Integer> result = sp.shortestPath(edges, 5, 5, 1, 4);
        System.out.println("Shortest path from 1 to 4: " + result);
    }
}
