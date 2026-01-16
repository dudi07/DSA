import java.util.ArrayList;
import java.util.PriorityQueue;

public class MinSpanningTree {
    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
        int[][] edges =  {{0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}};

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<ArrayList<Integer>>());
        }

        for (int i = 0; i < 6; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            ArrayList<Integer> tmp1 = new ArrayList<>();
            ArrayList<Integer> tmp2 = new ArrayList<>();
            tmp1.add(v);
            tmp1.add(w);

            tmp2.add(u);
            tmp2.add(w);

            adj.get(u).add(tmp1);
            adj.get(v).add(tmp2);
        }

        MinSpanningTree obj = new MinSpanningTree();
        int sum = obj.primMST(V, adj);
        System.out.println("The sum of all the edge weights: " + sum);
    }
    static class Pair {
        int node;
        int distance;
        public Pair(int distance, int node) {
            this.node = node;
            this.distance = distance;
        }
    }
    int primMST(int V,ArrayList<ArrayList<ArrayList<Integer>>> adj){
        int[] vis = new int[V];

        PriorityQueue<Pair> pq =
            new PriorityQueue<Pair>((x, y) -> x.distance - y.distance);
        
        pq.add(new Pair(0, 0));
        int sum = 0;

        while (pq.size() > 0) {
            Pair p = pq.poll();

            int node = p.node;
            int distance = p.distance;

            if(vis[node] == 1) continue;

            vis[node] = 1;
            sum += distance;


            for(int i =0 ; i < adj.get(node).size(); i++){
                int edgeNode = adj.get(node).get(i).get(0);
                int edgeDist = adj.get(node).get(i).get(1);
                if(vis[edgeNode] == 0){
                    pq.add(new Pair(edgeDist, edgeNode));
                }
            }
            
        }
        return sum;
    }

}
