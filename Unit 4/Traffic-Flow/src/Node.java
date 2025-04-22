import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Node {
    public String name;
    public Set<Node> neighbors;
    
    public Node (String name) {
        this.name = name;
        this.neighbors = new HashSet<Node>();
    }
}
