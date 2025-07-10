package Graph;

import java.util.*;

public class ShortestPathInDirectedPath {

    static class Pair<U, V> {
        public final U first;
        public final V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }

    public HashMap<Integer, ArrayList<Pair<Integer, Integer>>> adj = new HashMap<>();

    void addEdge(int u, int v, int weight) {
        adj.putIfAbsent(u, new ArrayList<>());
        adj.get(u).add(new Pair<>(v, weight));
    }

    void printAdj() {
        for (Integer u : adj.keySet()) {
            System.out.print(u + " ->");
            for (Pair<Integer, Integer> pair : adj.get(u)) {
                System.out.print(" [ " + pair.first + " " + pair.second + " ] ");
            }
            System.out.println();
        }
    }

    void topologicalSort(int node, HashMap<Integer, Boolean> visited, Stack<Integer> s) {
        visited.put(node, true);
        for (Pair<Integer, Integer> neighbour : adj.getOrDefault(node, new ArrayList<>())) {
            if (!visited.getOrDefault(neighbour.first, false)) {
                topologicalSort(neighbour.first, visited, s);
            }
        }
        s.push(node);
    }

    void shortestPath(int source, int n) {
        // Step 1: Topological sort
        Stack<Integer> s = new Stack<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!visited.getOrDefault(i, false)) {
                topologicalSort(i, visited, s);
            }
        }

        // Step 2: Initialize distances
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Step 3: Relax edges in topological order
        while (!s.isEmpty()) {
            int u = s.pop();
            if (dist[u] != Integer.MAX_VALUE) {
                for (Pair<Integer, Integer> p : adj.getOrDefault(u, new ArrayList<>())) {
                    int v = p.first;
                    int wt = p.second;
                    if (dist[v] > dist[u] + wt) {
                        dist[v] = dist[u] + wt;
                    }
                }
            }
        }

        // Step 4: Print shortest distances
        System.out.println("Shortest distances from node " + source + ":");
        for (int i = 0; i < n; i++) {
            System.out.println("Node " + i + ": " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }

    public static void main(String[] args) {
        ShortestPathInDirectedPath sp = new ShortestPathInDirectedPath();

        // Example DAG with weights
        sp.addEdge(0, 1, 5);
        sp.addEdge(0, 2, 3);
        sp.addEdge(1, 3, 6);
        sp.addEdge(1, 2, 2);
        sp.addEdge(2, 4, 4);
        sp.addEdge(2, 5, 2);
        sp.addEdge(2, 3, 7);
        sp.addEdge(3, 4, -1);
        sp.addEdge(4, 5, -2);

        sp.printAdj();
        int n = 6;
        int source = 1;
        sp.shortestPath(source, n);
    }
}
