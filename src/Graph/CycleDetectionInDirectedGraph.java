package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class CycleDetectionInDirectedGraph {

    static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    boolean checkCycleDFS(int node, HashMap<Integer, Boolean> visited, HashMap<Integer, Boolean> dfsVisited, HashMap<Integer, ArrayList<Integer>> adj) {
        visited.put(node, true);
        dfsVisited.put(node, true);

        for (Integer neighbour : adj.getOrDefault(node, new ArrayList<>())) {
            if (!visited.getOrDefault(neighbour, false)) {
                if (checkCycleDFS(neighbour, visited, dfsVisited, adj)) {
                    return true;
                }
            } else if (dfsVisited.getOrDefault(neighbour, false)) {
                return true;
            }
        }

        dfsVisited.put(node, false);
        return false;
    }

    boolean detectCycle(int n, Vector<Pair<Integer, Integer>> edges) {
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (int i = 0; i < edges.size(); i++) {
            int u = edges.get(i).getKey();
            int v = edges.get(i).getValue();
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }

        HashMap<Integer, Boolean> visited = new HashMap<>();
        HashMap<Integer, Boolean> dfsVisited = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            if (!visited.getOrDefault(i, false)) {
                if (checkCycleDFS(i, visited, dfsVisited, adj)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CycleDetectionInDirectedGraph graph = new CycleDetectionInDirectedGraph();
        Vector<Pair<Integer, Integer>> edges = new Vector<>();

        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(2, 3));
        edges.add(new Pair<>(3, 4));
        edges.add(new Pair<>(4, 2)); // Cycle here

        boolean hasCycle = graph.detectCycle(4, edges);
        System.out.println("Cycle detected? " + hasCycle); // Should print true
    }
}
