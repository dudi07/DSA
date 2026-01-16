import java.util.ArrayList;
import java.util.List;

public class MiinimumReversals {
    int reversals = 0;

    // Edge class to store neighbor and cost (0 = no reversal, 1 = needs reversal)
    static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public void dfs(int node, List<List<Edge>> adj, boolean[] visited) {
        visited[node] = true;

        for (Edge edge : adj.get(node)) {
            if (!visited[edge.to]) {
                reversals += edge.cost;
                dfs(edge.to, adj, visited);
            }
        }
    }

    public int minimumReversals(int[][] graph) {
        int n = graph.length;

        // Build adjacency list with edges
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // For each edge in the original graph (from -> to)
        for (int from = 0; from < graph.length; from++) {
            for (int to : graph[from]) {
                // Original edge: from -> to (cost 0, already correct direction)
                adj.get(from).add(new Edge(to, 0));

                // Reverse edge: to -> from (cost 1, needs reversal)
                adj.get(to).add(new Edge(from, 1));
            }
        }

        boolean[] visited = new boolean[n];
        reversals = 0;
        dfs(0, adj, visited);
        return reversals;
    }

    // Overloaded method for connections format: [[from, to], [from, to], ...]
    public int minimumReversals(int n, int[][] connections) {
        // Build adjacency list with edges
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // For each edge in the connections (from -> to)
        for (int[] connection : connections) {
            int from = connection[0];
            int to = connection[1];

            // Original edge: from -> to (cost 0, already correct direction)
            adj.get(from).add(new Edge(to, 0));

            // Reverse edge: to -> from (cost 1, needs reversal)
            adj.get(to).add(new Edge(from, 1));
        }

        boolean[] visited = new boolean[n];
        reversals = 0;
        dfs(0, adj, visited);
        return reversals;
    }

    public static void main(String[] args) {
        MiinimumReversals solution = new MiinimumReversals();

        // Test Case 1: Example from typical problem
        // n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
        // Expected: 3 (need to reverse edges 2->3, 4->0, and 4->5)
        System.out.println("=== Test Case 1 ===");
        System.out.println("Cities: 6");
        System.out.println("Connections: [[0,1],[1,3],[2,3],[4,0],[4,5]]");
        int[][] connections1 = { { 0, 1 }, { 1, 3 }, { 2, 3 }, { 4, 0 }, { 4, 5 } };
        int result1 = solution.minimumReversals(6, connections1);
        System.out.println("Result: " + result1);
        System.out.println("Expected: 2");
        System.out.println();

        // Reset for next test
        solution.reversals = 0;

        // Test Case 2: Simple chain
        // n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
        // Expected: 2
        System.out.println("=== Test Case 2 ===");
        System.out.println("Cities: 5");
        System.out.println("Connections: [[1,0],[1,2],[3,2],[3,4]]");
        int[][] connections2 = { { 1, 0 }, { 1, 2 }, { 3, 2 }, { 3, 4 } };
        int result2 = solution.minimumReversals(5, connections2);
        System.out.println("Result: " + result2);
        System.out.println("Expected: 2");
        System.out.println();

        // Reset for next test
        solution.reversals = 0;

        // Test Case 3: All edges already point to 0
        // n = 4, connections = [[1,0],[2,0],[3,0]]
        // Expected: 0
        System.out.println("=== Test Case 3 ===");
        System.out.println("Cities: 4");
        System.out.println("Connections: [[1,0],[2,0],[3,0]]");
        int[][] connections3 = { { 1, 0 }, { 2, 0 }, { 3, 0 } };
        int result3 = solution.minimumReversals(4, connections3);
        System.out.println("Result: " + result3);
        System.out.println("Expected: 3");
        System.out.println();

        // Reset for next test
        solution.reversals = 0;

        // Test Case 4: All edges point away from 0
        // n = 4, connections = [[0,1],[0,2],[0,3]]
        // Expected: 3
        System.out.println("=== Test Case 4 ===");
        System.out.println("Cities: 4");
        System.out.println("Connections: [[0,1],[0,2],[0,3]]");
        int[][] connections4 = { { 0, 1 }, { 0, 2 }, { 0, 3 } };
        int result4 = solution.minimumReversals(4, connections4);
        System.out.println("Result: " + result4);
        System.out.println("Expected: 0");
        System.out.println();

        // Reset for next test
        solution.reversals = 0;

        // Test Case 5: Larger tree
        // n = 7, connections = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
        // Expected: 6
        System.out.println("=== Test Case 5 ===");
        System.out.println("Cities: 7");
        System.out.println("Connections: [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]");
        int[][] connections5 = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 2, 6 } };
        int result5 = solution.minimumReversals(7, connections5);
        System.out.println("Result: " + result5);
        System.out.println("Expected: 0");
        System.out.println();
    }
}
