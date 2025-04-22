import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Graph {
    public Set<Node> nodes;
    public Graph() {
        this.nodes = new HashSet<Node>();
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void addEdge(Node from, Node to) {
        if (this.nodes.contains(from) && this.nodes.contains(to)) {
            from.neighbors.add(to);
            to.neighbors.add(from); // Assuming undirected graph
        } else {
            throw new IllegalArgumentException("Both nodes must be part of the graph.");
        }
    }

    public Set<Node> getNeighbors(Node node) {
        if (this.nodes.contains(node)) {
            return node.neighbors;
        } else {
            throw new IllegalArgumentException("Node not found in the graph.");
        }
    }

    public boolean containsNode(Node node) {
        return this.nodes.contains(node);
    }

    public Set<Node> getNodes() {
        return this.nodes;
    }



}
