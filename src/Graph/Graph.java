package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Graph {

    HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();

    void addEdge(int u, int v, boolean direction) {
        adj.putIfAbsent(u, new ArrayList<>());
        adj.get(u).add(v);

        if (!direction) {
            adj.putIfAbsent(v, new ArrayList<>());
            adj.get(v).add(u);
        }
    }

    void printAdjList() {
        for (int u : adj.keySet()) {
            ArrayList<Integer> list = adj.get(u);
            System.out.println(u + " -> " + list);
        }
    }

    Vector<Vector<Integer>> printAdjacencyList(int n, int m, Vector<Vector<Integer>> edges) {
        Vector<Vector<Integer>> adj = new Vector<>();
        for (int i = 0; i < n; i++) {
            adj.add(new Vector<>());
        }

        for (int i = 0; i < m; i++) {
            int u = edges.get(i).get(0);
            int v = edges.get(i).get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        return adj;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addEdge(0, 1, false);
        g.addEdge(0, 2, false);
        g.addEdge(1, 2, false);
        g.addEdge(1, 2, true);
        g.printAdjList();
    }

}
