package Graph;

import java.util.*;

public class KosarajuAglo {

    void dfs(int node, HashMap<Integer, Boolean> visited, Stack<Integer> st, HashMap<Integer, ArrayList<Integer>> adj) {
        visited.put(node, true);
        for (Integer nbr : adj.getOrDefault(node, new ArrayList<>())) {
            if (!visited.getOrDefault(nbr, false)) {
                dfs(nbr, visited, st, adj);
            }
        }
        st.push(node);
    }

    void revdfs(int node, HashMap<Integer, Boolean> visited, HashMap<Integer, ArrayList<Integer>> adj) {
        visited.put(node, true);
        for (Integer nbr : adj.getOrDefault(node, new ArrayList<>())) {
            if (!visited.getOrDefault(nbr, false)) {
                revdfs(nbr, visited, adj);
            }
        }
    }

    int stronglyConnectedComponents(int vn, Vector<Vector<Integer>> edges) {
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (Vector<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            adj.putIfAbsent(u, new ArrayList<>());
            adj.get(u).add(v);
        }

        // Step 1: Fill stack with finishing times
        Stack<Integer> st = new Stack<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();
        for (int i = 0; i < vn; i++) {
            if (!visited.getOrDefault(i, false)) {
                dfs(i, visited, st, adj);
            }
        }

        // Step 2: Transpose the graph
        HashMap<Integer, ArrayList<Integer>> transpose = new HashMap<>();
        for (Map.Entry<Integer, ArrayList<Integer>> entry : adj.entrySet()) {
            int u = entry.getKey();
            for (int v : entry.getValue()) {
                transpose.putIfAbsent(v, new ArrayList<>());
                transpose.get(v).add(u); // reverse the edge
            }
        }

        // Step 3: DFS on transposed graph
        visited.clear();
        int count = 0;
        while (!st.isEmpty()) {
            int node = st.pop();
            if (!visited.getOrDefault(node, false)) {
                count++;
                revdfs(node, visited, transpose);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        KosarajuAglo ka = new KosarajuAglo();
        Vector<Vector<Integer>> edges = new Vector<>();
        edges.add(new Vector<>(List.of(0, 1)));
        edges.add(new Vector<>(List.of(1, 2)));
        edges.add(new Vector<>(List.of(2, 0)));
        edges.add(new Vector<>(List.of(1, 3)));
        edges.add(new Vector<>(List.of(3, 4)));

        int vn = 5;
        int sccCount = ka.stronglyConnectedComponents(vn, edges);
        System.out.println("Number of Strongly Connected Components: " + sccCount);
    }
}
