import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    @Test
    void testNodeInitialization() {
        Node node = new Node(5, 10);
        assertEquals(5, node.getX());
        assertEquals(10, node.getY());
    }

    @Test
    void testIsWalkable(){
        Node node = new Node(2,2);
        assertTrue(node.isWalkable());
    }

    @Test
    void testSetWalkable() {
        Node node = new Node(0, 0);
        node.setWalkable(false);
        assertFalse(node.isWalkable());
    }

    @Test
    void testSetParent() {
        Node parent = new Node(1, 1);
        Node child = new Node(2, 2);
        child.setParent(parent);
        assertEquals(parent, child.getParent());
    }

    @Test
    void testEqualsAndHashCode() {
        Node node1 = new Node(3, 3);
        Node node2 = new Node(3, 3);
        Node node3 = new Node(4, 4);

        assertEquals(node1, node2);
        assertNotEquals(node1, node3);
        assertEquals(node1.hashCode(), node2.hashCode());
        assertNotEquals(node1.hashCode(), node3.hashCode());
    }
}

