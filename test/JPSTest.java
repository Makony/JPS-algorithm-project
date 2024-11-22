import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class JPSTest {
    @Test
    void testFindPathSimple() {
        Grid grid = new Grid(3, 3);

        grid.setWalkable(0, 0, true);
        grid.setWalkable(0, 1, true);
        grid.setWalkable(0, 2, true);
        grid.setWalkable(1, 2, true);
        grid.setWalkable(2, 2, true);
        grid.setWalkable(1, 0, true);
        grid.setWalkable(2, 0, true);
        grid.setWalkable(1, 1, true);
        grid.setWalkable(2, 1, false);

        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(2, 2);

        JPS jps = new JPS(grid);
        List<Node> path = jps.findPath(start, goal);

        // Check the start and goal are included in the path
        assertEquals(start, path.get(0));
        assertEquals(goal, path.get(path.size() - 1));
    }

    @Test
    void testFindPathWithObstacles() {
        Grid grid = new Grid(3, 3);

        grid.setWalkable(0, 0, true);
        grid.setWalkable(0, 1, true);
        grid.setWalkable(0, 2, true);
        grid.setWalkable(1, 2, true);
        grid.setWalkable(2, 2, true);
        grid.setWalkable(1, 0, false);
        grid.setWalkable(2, 0, false);
        grid.setWalkable(1, 1, false);
        grid.setWalkable(2, 1, false);

        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(2, 2);

        JPS jps = new JPS(grid);
        List<Node> path = jps.findPath(start, goal);

        // Ensure a path was found around the obstacles
        assertTrue(path.size() > 0);
        assertEquals(start, path.get(0));
        assertEquals(goal, path.get(path.size() - 1));
    }

    @Test
    void testNoPath() {
        Grid grid = new Grid(5, 5);

        // Block a row completely
        grid.setWalkable(2, 0, false);
        grid.setWalkable(2, 1, false);
        grid.setWalkable(2, 2, false);
        grid.setWalkable(2, 3, false);
        grid.setWalkable(2, 4, false);

        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(4, 4);

        JPS jps = new JPS(grid);
        List<Node> path = jps.findPath(start, goal);

        // Ensure no path was found
        assertTrue(path.isEmpty());
    }
}
