package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class DFSTraversal {

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

    void dfs(int node, HashMap<Integer, Boolean> visited,
             HashMap<Integer, ArrayList<Integer>> adj, Vector<Integer> component) {
        visited.put(node, true);
        component.add(node);
        for (int neighbor : adj.getOrDefault(node, new ArrayList<>())) {
            if (!visited.getOrDefault(neighbor, false)) {
                dfs(neighbor, visited, adj, component);
            }
        }
    }

    Vector<Vector<Integer>> DepthFirst(int V, Vector<Pair<Integer, Integer>> edges) {
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
        prepareAdjList(adj, edges);

        Vector<Vector<Integer>> ans = new Vector<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();

        for (int i = 0; i < V; i++) {
            if (!visited.getOrDefault(i, false)) {
                Vector<Integer> component = new Vector<>();
                dfs(i, visited, adj, component);
                ans.add(component);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        DFSTraversal dfsTraversal = new DFSTraversal();
        Vector<DFSTraversal.Pair<Integer, Integer>> edges = new Vector<>();
        edges.add(new DFSTraversal.Pair<>(0, 1));
        edges.add(new DFSTraversal.Pair<>(1, 2));
        edges.add(new DFSTraversal.Pair<>(3, 4));

        Vector<Vector<Integer>> result = dfsTraversal.DepthFirst(5, edges);

        for (Vector<Integer> component : result) {
            System.out.println(component);
        }
    }

}
