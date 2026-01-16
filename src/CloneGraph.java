public class CloneGraph {
    public static class Node {
        int val;
        Node[] neighbors;
    }

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        return node;

    }

}
