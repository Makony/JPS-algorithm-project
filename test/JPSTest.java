import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

class JPSTest {
    @Test
    void testFindPathSimple() {
        Grid grid = new Grid(5, 5);

        grid.setWalkable(0,2,false);
        grid.setWalkable(1,2,false);


        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(3, 0);

        JPS jps = new JPS(grid);
        List<Node> path = jps.findPath(start, goal);

        // Check the start and goal are included in the path
        assertEquals(start, path.get(0));
        assertEquals(goal, path.get(path.size() - 1));
    }

    @Test
    void testFindPathWithObstacles() {
        Grid grid = new Grid(5, 5);

        grid.setWalkable(0, 2, false);
        grid.setWalkable(1, 2, false);

        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(4, 2);

        JPS jps = new JPS(grid);
        List<Node> path = jps.findPath(start, goal);

        // Ensure a path was found
        assertTrue(path.size() >= 2);
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

    @Test
    void testGoalIsStart() {
        Grid grid = new Grid(5, 5);


        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(0, 0);

        JPS jps = new JPS(grid);
        List<Node> path = jps.findPath(start, goal);

        assertEquals(1, path.size());
        assertEquals(start, path.get(0));
    }

    @Test
    void testJumpFunction() {

        Grid grid = new Grid(3, 3);

        Node start = new Node(0, 0);
        Node goal = new Node(2, 2);

        JPS jps = new JPS(grid);
        List<Node> path = jps.findPath(start, goal);

        Node jumpNode = jps.jump(start, goal, 1, 1);

        assertNotNull(jumpNode);

        Node expectedJumpNode = new Node(2, 2);
        assertEquals(expectedJumpNode, jumpNode);
    }
    @Test
    void testPruneDirections() {

        Grid grid = new Grid(3, 3);
        JPS jps = new JPS(grid);

        List<int[]> directions = jps.pruneDirections(0, 0);
        assertEquals(8, directions.size());

        directions = jps.pruneDirections(1, 1);
        assertTrue(containsDirection(directions, new int[]{1, 0}));
        assertTrue(containsDirection(directions, new int[]{0, 1}));
        assertTrue(containsDirection(directions, new int[]{1, 1}));

        directions = jps.pruneDirections(1, 0);
        assertTrue(containsDirection(directions, new int[]{1, 0}));
        assertTrue(containsDirection(directions, new int[]{1, 1}));
        assertTrue(containsDirection(directions, new int[]{1, -1}));

        directions = jps.pruneDirections(0, 1);
        assertTrue(containsDirection(directions, new int[]{0, 1}));
        assertTrue(containsDirection(directions, new int[]{1, 1}));
        assertTrue(containsDirection(directions, new int[]{-1, 1}));
    }

    // Helper method to compare arrays
    private boolean containsDirection(List<int[]> directions, int[] d) {
        for (int[] dir : directions) {
            if (Arrays.equals(dir, d)) {
                return true;
            }
        }
        return false;
    }
}

