package Graph;

import java.util.*;

public class MST {

    Vector<Map.Entry<Map.Entry<Integer, Integer>, Integer>> calculatePrimMST(
            int n, int m,
            Vector<Map.Entry<Map.Entry<Integer, Integer>, Integer>> g) {

        // Step 1: Build adjacency list
        HashMap<Integer, ArrayList<Map.Entry<Integer, Integer>>> adj = new HashMap<>();
        for (int i = 0; i < g.size(); i++) {
            int u = g.get(i).getKey().getKey();
            int v = g.get(i).getKey().getValue();
            int wt = g.get(i).getValue();

            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());

            adj.get(u).add(new AbstractMap.SimpleEntry<>(v, wt));
            adj.get(v).add(new AbstractMap.SimpleEntry<>(u, wt));
        }

        // Step 2: Prim's Algorithm
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        pq.add(new AbstractMap.SimpleEntry<>(1, 0)); // Start from node 1

        Vector<Map.Entry<Map.Entry<Integer, Integer>, Integer>> mst = new Vector<>();
        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);

        while (!pq.isEmpty()) {
            Map.Entry<Integer, Integer> current = pq.poll();
            int node = current.getKey();
            int weight = current.getValue();

            if (visited[node]) continue;
            visited[node] = true;

            if (parent[node] != -1) {
                mst.add(new AbstractMap.SimpleEntry<>(
                        new AbstractMap.SimpleEntry<>(parent[node], node),
                        weight));
            }

            for (Map.Entry<Integer, Integer> neighbor : adj.getOrDefault(node, new ArrayList<>())) {
                int adjNode = neighbor.getKey();
                int edgeWt = neighbor.getValue();

                if (!visited[adjNode]) {
                    pq.add(new AbstractMap.SimpleEntry<>(adjNode, edgeWt));
                    parent[adjNode] = node;
                }
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        MST obj = new MST();

        int n = 5; // number of nodes
        int m = 7; // number of edges

        // Representing edges: ((u, v), weight)
        Vector<Map.Entry<Map.Entry<Integer, Integer>, Integer>> edges = new Vector<>();
        edges.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(1, 2), 2));
        edges.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(1, 3), 3));
        edges.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(2, 3), 1));
        edges.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(2, 4), 4));
        edges.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(3, 4), 5));
        edges.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(3, 5), 6));
        edges.add(new AbstractMap.SimpleEntry<>(new AbstractMap.SimpleEntry<>(4, 5), 7));

        Vector<Map.Entry<Map.Entry<Integer, Integer>, Integer>> mst = obj.calculatePrimMST(n, m, edges);

        System.out.println("Edges in MST:");
        for (Map.Entry<Map.Entry<Integer, Integer>, Integer> edge : mst) {
            System.out.println(edge.getKey().getKey() + " - " + edge.getKey().getValue() + " : " + edge.getValue());
        }
    }
}