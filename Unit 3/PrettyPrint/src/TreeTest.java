import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

public class TreeTest {
    @Test
    public void testPreOrder(){
        IntTree tree = new IntTree();
        tree.addValue(2);
        tree.addValue(1);
        tree.addValue(4);
        tree.addValue(3);
        tree.addValue(5);
        assertEquals("[2]([1]()())([4]([3]()())([5]()()))", tree.preOrder(tree.overallRoot));
    }

    @Test
    public void testInOrder(){
        IntTree tree = new IntTree();
        tree.addValue(2);
        tree.addValue(1);
        tree.addValue(4);
        tree.addValue(3);
        tree.addValue(5);
        assertEquals("(()[1]())[2]((()[3]())[4](()[5]()))", tree.inOrder(tree.overallRoot));
    }

    @Test
    public void testPostOrder(){
        IntTree tree = new IntTree();
        tree.addValue(2);
        tree.addValue(1);
        tree.addValue(4);
        tree.addValue(3);
        tree.addValue(5);
        assertEquals("(()()[1])((()()[3])(()()[5])[4])[2]", tree.postOrder(tree.overallRoot));
    }

    @Test
    public void testAddValueEmpty() {
        IntTree tree = new IntTree();
        tree.addValue(5);
        assertNotNull(tree.overallRoot);
        assertEquals(5, tree.overallRoot.data);
    }
    @Test
    public void testAddValueLeft() {
        IntTree tree = new IntTree();
        tree.addValue(5);
        tree.addValue(3);
        assertNotNull(tree.overallRoot.left);
        assertEquals(3, tree.overallRoot.left.data);
    }

    @Test
    public void testAddValueRight() {
        IntTree tree = new IntTree();
        tree.addValue(5);
        tree.addValue(7);
        assertNotNull(tree.overallRoot.right);
        assertEquals(7, tree.overallRoot.right.data);
    }
}
