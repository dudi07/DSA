import java.util.ArrayList;
import java.util.List;

public class ConnectedComponents {
    private void dfs(int node, List<List<Integer>> adj, boolean[] visited, ArrayList<Integer> component) {
        visited[node] = true;
        component.add(node);
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, component);
            }
        }
    }
        public ArrayList<ArrayList<Integer>> getComponents(int V, int[][] edges) {
        
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u); // If undirected
        }
        

        int n = adj.size();
        boolean[] visited = new boolean[n];
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ArrayList<Integer> component = new ArrayList<>();
                dfs(i, adj, visited, component);
                components.add(component);
            }
        }
        return components;
    }
}
