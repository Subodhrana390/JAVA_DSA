package Graph;

import java.util.*;

public class TopologicalSort {

    void Tort(int node, HashMap<Integer, Boolean> visited, Stack<Integer> s, HashMap<Integer, ArrayList<Integer>> adj) {
        visited.put(node, true);
        for (Integer neighbour : adj.getOrDefault(node, new ArrayList<>())) {
            if (!visited.getOrDefault(neighbour, false)) {
                Tort(neighbour, visited, s, adj);
            }
        }
        s.push(node);
    }


    //UsingDFS
    Vector<Integer> TopoSort(Vector<Vector<Integer>> edges, int v, int e) {
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();

        // Prepare adjacency list
        for (Vector<Integer> edge : edges) {
            int u = edge.get(0);
            int vtx = edge.get(1);
            adj.putIfAbsent(u, new ArrayList<>());
            adj.get(u).add(vtx);
            adj.putIfAbsent(vtx, new ArrayList<>());
        }

        HashMap<Integer, Boolean> visited = new HashMap<>();
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < v; i++) {
            if (!visited.getOrDefault(i, false)) {
                Tort(i, visited, s, adj);
            }
        }

        Vector<Integer> ans = new Vector<>();
        while (!s.isEmpty()) {
            ans.add(s.pop());
        }
        return ans;
    }

    Vector<Integer> TopoSortUsingBFS(Vector<Vector<Integer>> edges, int v, int e) {
        // Step 1: Build adjacency list
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (Vector<Integer> edge : edges) {
            int u = edge.get(0);
            int vtx = edge.get(1);
            adj.putIfAbsent(u, new ArrayList<>());
            adj.get(u).add(vtx);
            adj.putIfAbsent(vtx, new ArrayList<>()); // ensure all nodes are present
        }

        // Step 2: Initialize indegree array
        Vector<Integer> indegree = new Vector<>(Collections.nCopies(v, 0));
        for (int u : adj.keySet()) {
            for (int nbr : adj.get(u)) {
                indegree.set(nbr, indegree.get(nbr) + 1);
            }
        }

        // Step 3: Add all nodes with indegree 0 to queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (indegree.get(i) == 0) {
                q.add(i);
            }
        }

        // Step 4: BFS traversal
        Vector<Integer> ans = new Vector<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            ans.add(u);
            for (int nbr : adj.getOrDefault(u, new ArrayList<>())) {
                indegree.set(nbr, indegree.get(nbr) - 1);
                if (indegree.get(nbr) == 0) {
                    q.add(nbr);
                }
            }
        }

        return ans;
    }


    public static void main(String[] args) {
        TopologicalSort sorter = new TopologicalSort();

        int v = 6, e = 6;
        Vector<Vector<Integer>> edges = new Vector<>();

        edges.add(new Vector<>(Arrays.asList(5, 2)));
        edges.add(new Vector<>(Arrays.asList(5, 0)));
        edges.add(new Vector<>(Arrays.asList(4, 0)));
        edges.add(new Vector<>(Arrays.asList(4, 1)));
        edges.add(new Vector<>(Arrays.asList(2, 3)));
        edges.add(new Vector<>(Arrays.asList(3, 1)));

        Vector<Integer> result = sorter.TopoSortUsingBFS(edges, v, e);
        System.out.println("Topological Sort: " + result);
    }
}
