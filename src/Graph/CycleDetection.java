package Graph;

import java.util.*;

public class CycleDetection {


    // Using BFS
    boolean isCyclicBFS(int src, HashMap<Integer, Boolean> visited, HashMap<Integer, ArrayList<Integer>> adj) {
        HashMap<Integer, Integer> parent = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();

        q.add(src);
        visited.put(src, true);
        parent.put(src, -1);

        while (!q.isEmpty()) {
            int u = q.poll();

            for (Integer neighbor : adj.getOrDefault(u, new ArrayList<>())) {
                if (visited.getOrDefault(neighbor, false)) {
                    if (parent.get(u) != neighbor) {
                        return true; // cycle found
                    }
                } else {
                    visited.put(neighbor, true);
                    parent.put(neighbor, u);
                    q.add(neighbor);
                }
            }
        }
        return false;
    }

    boolean isCyclicDFS(int node, int parent, HashMap<Integer, Boolean> visited, HashMap<Integer, ArrayList<Integer>> adj) {
        visited.put(node, true);
        for (Integer neighbor : adj.getOrDefault(node, new ArrayList<>())) {
            if (visited.getOrDefault(neighbor, false)) {
                boolean cycleDetected = isCyclicDFS(neighbor, node, visited, adj);
                if (cycleDetected)
                    return true;
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }

    String cycleDetection(Vector<Vector<Integer>> edges, int n, int m) {
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (Vector<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);

            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        HashMap<Integer, Boolean> visited = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (!visited.getOrDefault(i, false)) {
                if (isCyclicDFS(i, -1, visited, adj)) {
//                if (isCyclicBFS(i, visited, adj)) {
                    return "Yes";
                }
            }
        }
        return "No";
    }

    public static void main(String[] args) {
        CycleDetection cd = new CycleDetection();

        Vector<Vector<Integer>> edges = new Vector<>();
        edges.add(new Vector<>(Arrays.asList(0, 1)));
        edges.add(new Vector<>(Arrays.asList(1, 2)));
        edges.add(new Vector<>(Arrays.asList(2, 0))); // This forms a cycle

        System.out.println(cd.cycleDetection(edges, 3, 3));  // Output: Yes
    }
}
