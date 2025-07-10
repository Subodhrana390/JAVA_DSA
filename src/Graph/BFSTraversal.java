package Graph;

import java.util.*;

public class BFSTraversal {

    static class Pair<K, V> {
        K first;
        V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }
    }

    void prepareAdjList(HashMap<Integer, ArrayList<Integer>> adj, Vector<Pair<Integer, Integer>> edges) {
        for (Pair<Integer, Integer> edge : edges) {
            int u = edge.first;
            int v = edge.second;

            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());

            adj.get(u).add(v);
            adj.get(v).add(u);
        }
    }

    void Solve(HashMap<Integer, ArrayList<Integer>> adj, HashMap<Integer, Boolean> visited, Vector<Integer> ans, int node) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        visited.put(node, true);

        while (!q.isEmpty()) {
            int u = q.poll();
            ans.add(u);

            for (int v : adj.get(u)) {
                if (!visited.getOrDefault(v, false)) {
                    q.add(v);
                    visited.put(v, true);
                }
            }
        }
    }

    Vector<Integer> BFS(int vertex, Vector<Pair<Integer, Integer>> edges) {
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
        Vector<Integer> ans = new Vector<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();

        prepareAdjList(adj, edges);

        for (int i = 0; i < vertex; i++) {
            if (!visited.getOrDefault(i, false)) {
                Solve(adj, visited, ans, i);
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        BFSTraversal g = new BFSTraversal();

        Vector<Pair<Integer, Integer>> edges = new Vector<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(1, 3));
        edges.add(new Pair<>(2, 3));
        int vertex = 4;

        Vector<Integer> traversal = g.BFS(vertex, edges);

        System.out.println("BFS Traversal: " + traversal);
    }
}
