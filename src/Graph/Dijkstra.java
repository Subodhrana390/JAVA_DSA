package Graph;

import java.util.*;

public class Dijkstra {

    public Vector<Integer> dijk(Vector<Vector<Integer>> vec, int vertices, int edges, int src) {
        // Step 1: Build the adjacency list
        HashMap<Integer, List<Map.Entry<Integer, Integer>>> adj = new HashMap<>();
        for (Vector<Integer> edge : vec) {
            int u = edge.get(0);
            int v = edge.get(1);
            int w = edge.get(2);
            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(new AbstractMap.SimpleEntry<>(v, w));
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(new AbstractMap.SimpleEntry<>(u, w));
        }

        // Step 2: Distance vector
        Vector<Integer> dist = new Vector<>(Collections.nCopies(vertices, Integer.MAX_VALUE));
        dist.set(src, 0);

        // Step 3: TreeSet for efficient min element retrieval
        TreeSet<int[]> set = new TreeSet<>((a, b) -> {
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]); // sort by distance
            return Integer.compare(a[0], b[0]); // tie-break by node
        });

        set.add(new int[]{src, 0});

        // Step 4: Dijkstra’s Algorithm using TreeSet
        while (!set.isEmpty()) {
            int[] curr = set.pollFirst(); // Remove the node with smallest distance
            int u = curr[0];
            int d = curr[1];

            for (Map.Entry<Integer, Integer> neighbor : adj.getOrDefault(u, new ArrayList<>())) {
                int v = neighbor.getKey();
                int w = neighbor.getValue();

                if (dist.get(v) > dist.get(u) + w) {
                    // Remove the old entry if present
                    set.removeIf(e -> e[0] == v);

                    dist.set(v, dist.get(u) + w);
                    set.add(new int[]{v, dist.get(v)});
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Dijkstra dj = new Dijkstra();

        // Define edges: {u, v, w}
        Vector<Vector<Integer>> vec = new Vector<>();
        vec.add(new Vector<>(Arrays.asList(0, 1, 4)));
        vec.add(new Vector<>(Arrays.asList(0, 2, 1)));
        vec.add(new Vector<>(Arrays.asList(2, 1, 2)));
        vec.add(new Vector<>(Arrays.asList(1, 3, 1)));
        vec.add(new Vector<>(Arrays.asList(2, 3, 5)));

        int vertices = 4;
        int edges = vec.size();
        int src = 0;

        Vector<Integer> dist = dj.dijk(vec, vertices, edges, src);
        System.out.println("Shortest distances from node " + src + ":");
        for (int i = 0; i < dist.size(); i++) {
            System.out.println("Node " + i + ": " + dist.get(i));
        }
    }
}
