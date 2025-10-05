import java.util.List;
import java.util.ArrayList;

public class CycleDetection {
    public static void main(String[] args) {
        // Test Case 1: Large Cycle
        int V1 = 10;
        List<Integer>[] adj1 = new ArrayList[V1];
        for (int i = 0; i < V1; i++) adj1[i] = new ArrayList<>();
        for (int i = 0; i < V1; i++) {
            adj1[i].add((i + 1) % V1);
            adj1[(i + 1) % V1].add(i);
        }
        CycleDetection cd = new CycleDetection();
        System.out.println("Test 1 (Large Cycle): " + cd.isCycle(V1, adj1));

        // Test Case 2: Tree (No Cycle)
        int V2 = 15;
        List<Integer>[] adj2 = new ArrayList[V2];
        for (int i = 0; i < V2; i++) adj2[i] = new ArrayList<>();
        for (int i = 1; i < V2; i++) {
            adj2[i].add((i - 1) / 2);
            adj2[(i - 1) / 2].add(i);
        }
        System.out.println("Test 2 (Tree, No Cycle): " + cd.isCycle(V2, adj2));

        // Test Case 3: Multiple Components, One with Cycle
        int V3 = 8;
        List<Integer>[] adj3 = new ArrayList[V3];
        for (int i = 0; i < V3; i++) adj3[i] = new ArrayList<>();
        // Component 1: 0-1-2-0 (cycle)
        adj3[0].add(1); adj3[1].add(0);
        adj3[1].add(2); adj3[2].add(1);
        adj3[2].add(0); adj3[0].add(2);
        // Component 2: 3-4-5-6-7 (no cycle)
        adj3[3].add(4); adj3[4].add(3);
        adj3[4].add(5); adj3[5].add(4);
        adj3[5].add(6); adj3[6].add(5);
        adj3[6].add(7); adj3[7].add(6);
        System.out.println("Test 3 (Multi-component, One Cycle): " + cd.isCycle(V3, adj3));

        // Test Case 4: Dense Graph with Many Cycles
        int V4 = 6;
        List<Integer>[] adj4 = new ArrayList[V4];
        for (int i = 0; i < V4; i++) adj4[i] = new ArrayList<>();
        for (int i = 0; i < V4; i++) {
            for (int j = i + 1; j < V4; j++) {
                adj4[i].add(j);
                adj4[j].add(i);
            }
        }
        System.out.println("Test 4 (Dense, Many Cycles): " + cd.isCycle(V4, adj4));
    }
    private boolean dfs(int src, int parent, boolean[]visited, List<Integer>[] adj) {
        visited[src] = true;

        for(int nextNode: adj[src]){
            if(!visited[nextNode]){
                if(dfs(nextNode, src, visited, adj)){
                    return true;
                }
            }else if(nextNode != parent){
                return true;
            }
        }
        return false;
    }
    public boolean isCycle(int V, List<Integer>[] adj) {
        boolean[]visited = new boolean[V];
        return dfs(0,-1,visited,adj);
    }
}
