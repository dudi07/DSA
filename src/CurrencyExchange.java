import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CurrencyExchange {
    static class Edge {
        int from, to;
        double weight;
        Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    private int n; 
    
    private List<Edge> exchanges;
    public CurrencyExchange(List<Edge> exchanges, int n) {
        this.exchanges = exchanges;
        this.n = n;
    }

    public void addRate(int u, int v, double rate) {
        exchanges.add(new Edge(u, v, -Math.log(rate))); // store -log(rate)
    }


    public List<Integer>detectArbitrageCycle(){
        int totalNodes = n+1;
        int superSource = n;    // last node as super source

        double[] dist = new double[totalNodes];
        int[] parent = new int[totalNodes];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        Arrays.fill(parent, -1);

        dist[superSource] = 0.0;
        int x=-1;
        for (int i = 0; i < n; i++) {
            exchanges.add(new Edge(superSource, i, 0.0));
        }
        for(int i = 0 ; i < totalNodes -1; i++){
            x = -1;

            for(Edge e : exchanges){
                if(dist[e.from] + e.weight < dist[e.to]){
                    dist[e.to] = dist[e.from] + e.weight;
                    parent[e.to] = e.from;
                    x = e.to;
                }
            }
        }
        if(x == -1){
            return null; // no negative cycle
        }


        int y = x;
        for (int i = 0; i < totalNodes; i++) {
            y = parent[y]; // ensure we are inside the cycle
        }

        List<Integer> cycle = new ArrayList<>();
        for (int cur = y;; cur = parent[cur]) {
            cycle.add(cur);
            if (cur == y && cycle.size() > 1) break;
        }
        Collections.reverse(cycle);

        // Remove the super source if it accidentally appears
        cycle.removeIf(node -> node == superSource);

        return cycle;
    }
}
