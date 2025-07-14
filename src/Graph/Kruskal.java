package Graph;

import java.util.*;

public class Kruskal {

    // Edge class to store (u, v, weight)
    static class Edge implements Comparable<Edge> {
        int u, v, wt;

        Edge(int u, int v, int wt) {
            this.u = u;
            this.v = v;
            this.wt = wt;
        }

        // Sorting by weight
        public int compareTo(Edge other) {
            return this.wt - other.wt;
        }
    }

    // Disjoint Set Union (Union-Find)
    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]); // path compression
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return false;

            // union by rank
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }

    public static int kruskalMST(int n, List<Edge> edges) {
        Collections.sort(edges); // sort by weight
        DSU dsu = new DSU(n);
        int mstWeight = 0;

        for (Edge edge : edges) {
            if (dsu.union(edge.u, edge.v)) {
                mstWeight += edge.wt;
                System.out.println("Included edge: " + edge.u + " - " + edge.v + " weight: " + edge.wt);
            }
        }

        return mstWeight;
    }

    public static void main(String[] args) {
        int n = 4; // number of nodes

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        int totalWeight = kruskalMST(n, edges);
        System.out.println("Total weight of MST: " + totalWeight);
    }
}