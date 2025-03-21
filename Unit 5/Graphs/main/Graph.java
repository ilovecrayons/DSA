package Graphs.main;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Class definition for a generic (Directed) Graph.
 * The Graph contains a collection of Nodes, each Node contains
 * a collection of references (edges) to their neighboring Nodes.
 * @param <T> - reference type of Nodes contained in the Graph.
 * The type T is expected to implement the Comparable interface, 
 * such that Nodes can be compared to each other.<br>
 * E.g.:<pre>Graph&lt;String&gt; g = new Graph&ltString&gt();</pre>
 * @see Node
 */
public class Graph<T extends Comparable<T>> {

    /**
     * Private Map keying each Node in the Graph by the hashCode of its data
     * E.g: Given <pre>Node<String> n = new Node<String>("abc");</pre> added to the graph,
     * the _nodes map contains a Map.Entry with
     * <pre>key="abc".hashCode()<br>value=n<pre>
     * @see java.lang.Object#hashCode()
     */
    private Map<Integer, Node<T>> _nodes;
    
    /**
     * Constructs a new Graph as an empty container fit for Nodes of the type T.
     * @see Graph
     * @see Node
     */
    public Graph() {
        _nodes = new TreeMap<Integer, Node<T>>();
    }
    
    /**
     * Gets the size of this Graph. The size of the Graph is equal to the number
     * of Nodes it contains.
     * @return number of Nodes in this Graph.
     */
    public int size() {
        return _nodes.size();
    }
    
    /**
     * Checks if the state of all the Nodes in the Graph matches a given value.
     * @param state - the value to check against all Nodes in the Graph.
     * @return true if all the Nodes in the Graph have a state matching the
     * given value, false otherwise.
     * @see Node#getState()
     */
    public boolean checkState(int state) {
        for (Node<?> n : _nodes.values()) {
            if (state != n.getState()) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Adds a new Node to the Graph containing the <i>data</i>. The method 
     * throws if the Graph already contains a Node with data having the same
     * hashCode().
     * @param data - the data reference (of type T) contained in the new Node.
     * @throws RuntimeException if the Graph already contains a Node for the
     * given data.
     * @see java.lang.Object#hashCode()
     */
    public void addNode(T data) {
        int nodeHash = data.hashCode();
        if (_nodes.containsKey(nodeHash)) {
            throw new RuntimeException("Ambiguous graph!");
        }
        
        _nodes.put(nodeHash, new Node<T>(data));
    }
    
    /**
     * Adds a new directed Edge to the Graph, linking the Nodes containing
     * <i>from</i> and <i>to</i> data. It is expected the two Nodes exist
     * otherwise the method throws an exception.
     * @param from - Node where the Edge is starting.
     * @param to - Node where the Edge is ending.
     * @throws RuntimeException if either of the two Nodes are not present in the Graph.
     * @see Node
     * @see Graph#removeEdge(Comparable, Comparable)
     */
    public void addEdge(T from, T to) {
        Node<T> fromNode = _nodes.get(from.hashCode());
        Node<T> toNode = _nodes.get(to.hashCode());
        if (fromNode == null || toNode == null) {
            throw new RuntimeException("Node(s) not in the graph!");
        }
        
        fromNode.addEdge(toNode);
    }
    
    /**
     * Removes an existent directed Edge from the Graph, if one exists. 
     * The Edge to be removed is linking the nodes containing the <i>from</i> 
     * and <i>to</i> data references. The two Nodes must exist, otherwise the 
     * method throws an exception.
     * @param from - Node at the starting point of the Edge.
     * @param to - Node at the ending point of the Edge.
     * @throws RuntimeException if either of the two Nodes are not present in the Graph.
     * @see Node
     * @see Graph#addEdge(Comparable, Comparable)
     */
    public void removeEdge(T from, T to) {
        Node <T> fromNode = _nodes.get(from.hashCode());
        Node <T> toNode = _nodes.get(to.hashCode());
        if(fromNode == null || toNode == null) { 
            throw new RuntimeException("Node(s) not in the graph!");
        }
        fromNode.removeEdge(toNode);
    }
    
    /**
     * Removes a Node from the Graph if one exists, along with all
     * its outgoing (egress) and incoming (ingress) edges. If there
     * is no Node hosting the <i>data</i> reference the method does
     * nothing.
     * @param data - Node to be removed from the Graph.
     */
    public void removeNode(T data) {
        Node<T> node = _nodes.get(data.hashCode());
        if(node == null){
            return;
        } else {
            for(Node<T> n : _nodes.values()) {
                n.removeEdge(node);
            }
            _nodes.remove(data.hashCode());
        }
    }



    /**
     * Checks if the Graph is undirected.
     * @return true if Graph is undirected, false otherwise.
     */
    public boolean isUGraph() {
        for(Node<T> n : _nodes.values()){
            for(Node<T> m : n.getEdges().values()){
                if(!m.getEdges().containsValue(n)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks is the Graph is connected.
     * @return true if the Graph is connected, false otherwise.
     */
    public boolean isConnected() {
        for(Node<T> n : _nodes.values()){
            n.reset();
        }
        if(_nodes.isEmpty()){
            return true;
        }
        
        int count = 0;
        
        Stack<Node<T>> stack = new Stack<Node<T>>();
        Node<T> start = _nodes.values().iterator().next();
        stack.push(start);
        while(!stack.isEmpty()){
            Node<T> current = stack.pop();
            if(current.getState() != 0){
                continue;
            }
            current.setState();
            
            count++;
            for(Node<T> n : current.getEdges().values()){
                if(n.getState() == 0){
                    stack.push(n);
                }
            }
        }

        return count == _nodes.size();
    }

    /**
     * Checks if the Graph is Directed Acyclic graph.
     * @return true if Graph is Directed Acyclic, false otherwise.
     */
    public boolean isDAGraph() {
        // Reset all nodes' states
        for (Node<T> n : _nodes.values()) {
            n.reset();
        }
    
        Set<Node<T>> recursionStack = new HashSet<>();
    
        for (Node<T> node : _nodes.values()) {
            if (node.getState() == 0) { 
                if (hasCycle(node, recursionStack)) {
                    return false; 
                }
            }
        }
    
        return true; 
    }
    
    private boolean hasCycle(Node<T> node, Set<Node<T>> recursionStack) {
        node.setState();
        recursionStack.add(node);
    
        for (Node<T> neighbor : node.getEdges().values()) {
            if (neighbor.getState() == 0) { 
                if (hasCycle(neighbor, recursionStack)) {
                    return true; 
                }
            } else if (recursionStack.contains(neighbor)) {
                return true; 
            }
        }
    
        
        recursionStack.remove(node);
        
        return false; 
    }

    /**
     * Generates the adjacency matrix for this Graph.
     * @return the adjacency matrix.
     */
    public int[][] getAdjacencyMatrix() {
        int size = _nodes.size();
        int[][] adjMatrix = new int[size][size];
        Map<Node<T>, Integer> nodeIndexMap = new HashMap<>();
        int index = 0;
        for (Node<T> node : _nodes.values()) {
            nodeIndexMap.put(node, index++);
        }
        
        for(Node<T> node : _nodes.values()){
            for(Node<T> neighbor : node.getEdges().values()){
                adjMatrix[nodeIndexMap.get(node)][nodeIndexMap.get(neighbor)] = 1;
            }
        }

        return adjMatrix;
    }

    /**
     * Gives a multi-line String representation of this Graph. Each line in the returned
     * string represent a Node in the graph, followed by its outgoing (egress) Edges.
     * E.g: If the graph contains 3 nodes, A, B an C, where A and B point to each other and
     * both of them point to C, the value returned by toString() will be as follows:
     * <pre>
     * A > B C
     * B > A C
     * C > 
     * </pre>
     * <u>Note:</u> Each line is a space-separated sequence of token. A Node with no
     * outgoing (egress) edges, like C in the example above still has a line where 
     * the ' > ' token is surrounded by the space characters.
     * @return multi-line String reflecting the content and structure of this Graph.
     */
    @Override
    public String toString() {
        String output = "";
        boolean first = true;
        for(Node<?> n : _nodes.values()) {
            if (!first) {
                output += "\n";
            }
            output += n.toString();
            first = false;
        }
        
        return output;
    }
}
