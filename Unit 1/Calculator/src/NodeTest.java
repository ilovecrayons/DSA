import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void testRawNodeCreation() {
        RawNode node = RawNode.createNode("Test");
        assertNotNull(node);
        assertEquals("Test", node.getRawContent());
    }

    @Test
    void testRawNodeAddNext() {
        RawNode node1 = RawNode.createNode("Node 1");
        RawNode node2 = RawNode.createNode("Node 2");
        node1.addNext(node2);
        assertEquals(node2, node1.getNext());
    }

    @Test
    void testRawNodeAddPrev() {
        RawNode node1 = RawNode.createNode("Node 1");
        RawNode node2 = RawNode.createNode("Node 2");
        node1.addPrev(node2);
        assertEquals(node2, node1.getPrev());
    }

    @Test
    void testNumNodeCreation() {
        NumNode node = NumNode.createNode("10.5");
        assertNotNull(node);
        assertEquals(10.5, node.getNumValue(), 0.01);
    }

    @Test
    void testOpNodeCreation() {
        OpNode node = OpNode.createNode("+");
        assertNotNull(node);
        assertEquals(OpNode.OpCode.ADDITION, node.getOpCode());
        assertEquals(OpNode.OpPrio.LOWER, node.getOpPrio());
    }
}