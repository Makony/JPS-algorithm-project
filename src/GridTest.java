import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class GridTest {
    @Test
    void testGridInitialization() {
        Grid grid = new Grid(5, 5);
        assertNotNull(grid.getNode(0, 0));
        assertNull(grid.getNode(-1, -1)); // Out of bounds
    }

    @Test
    void testSetWalkable() {
        Grid grid = new Grid(5, 5);
        grid.setWalkable(2, 2, false);
        assertFalse(grid.getNode(2, 2).isWalkable());
    }

    @Test
    void testGetNeighbors() {
        Grid grid = new Grid(3, 3);

        // Set some nodes to non-walkable
        grid.setWalkable(1, 0, false);
        grid.setWalkable(1, 2, false);

        Node center = grid.getNode(1, 1);
        List<Node> neighbors = grid.getNeighbors(center);

        assertEquals(6, neighbors.size()); // 8 possible neighbors minus 2 blocked
        assertFalse(neighbors.contains(grid.getNode(1, 0))); // Non-walkable
        assertFalse(neighbors.contains(grid.getNode(1, 2))); // Non-walkable
    }

    @Test
    void testJump() {
        Grid grid = new Grid(5, 5);

        // Create a blocked path and test jumping logic
        grid.setWalkable(1, 1, false);

        Node start = grid.getNode(0, 0);
        Node jumpPoint = grid.jump(start, 1, 1); // Diagonal jump

        assertNull(jumpPoint); // No valid jump point in this case
    }
}

