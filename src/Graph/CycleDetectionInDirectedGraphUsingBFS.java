package Graph;

import java.util.*;

public class CycleDetectionInDirectedGraphUsingBFS {

    boolean detectCycleInDirectedGraph(int n, Vector<Vector<Integer>> edges) {
        // Step 1: Build adjacency list
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (Vector<Integer> edge : edges) {
            int u = edge.get(0) - 1;
            int v = edge.get(1) - 1;
            adj.putIfAbsent(u, new ArrayList<>());
            adj.get(u).add(v);
        }

        // Step 2: Initialize indegree array
        Vector<Integer> indegree = new Vector<>(Collections.nCopies(n, 0));
        for (int u : adj.keySet()) {
            for (int v : adj.get(u)) {
                indegree.set(v, indegree.get(v) + 1);
            }
        }

        // Step 3: Add all nodes with indegree 0 to queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree.get(i) == 0) {
                q.add(i);
            }
        }

        // Step 4: BFS traversal
        int count = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            count++;

            for (int nbr : adj.getOrDefault(u, new ArrayList<>())) {
                indegree.set(nbr, indegree.get(nbr) - 1);
                if (indegree.get(nbr) == 0) {
                    q.add(nbr);
                }
            }
        }

        // If all nodes are not processed, there's a cycle
        return count != n;
    }

    public static void main(String[] args) {
        CycleDetectionInDirectedGraphUsingBFS graph = new CycleDetectionInDirectedGraphUsingBFS();

        Vector<Vector<Integer>> edges = new Vector<>();
        edges.add(new Vector<>(Arrays.asList(0, 1)));
        edges.add(new Vector<>(Arrays.asList(1, 2)));
        edges.add(new Vector<>(Arrays.asList(2, 0)));  // This creates a cycle

        int n = 3; // Number of nodes

        boolean hasCycle = graph.detectCycleInDirectedGraph(n, edges);
        System.out.println("Cycle detected: " + hasCycle); // Should print true
    }
}
