import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class AStarTest {

    @Test
    void testFindPathSimple() {
        Grid grid = new Grid(5, 5);

        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(3, 0);

        AStar astar = new AStar(grid);
        List<Node> path = astar.findPath(start, goal);

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

        AStar astar = new AStar(grid);
        List<Node> path = astar.findPath(start, goal);

        assertTrue(path.size() >= 2);
        assertEquals(start, path.get(0));
        assertEquals(goal, path.get(path.size() - 1));
    }

    @Test
    void testNoPath() {
        Grid grid = new Grid(5, 5);

        grid.setWalkable(2, 0, false);
        grid.setWalkable(2, 1, false);
        grid.setWalkable(2, 2, false);
        grid.setWalkable(2, 3, false);
        grid.setWalkable(2, 4, false);

        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(4, 4);

        AStar astar = new AStar(grid);
        List<Node> path = astar.findPath(start, goal);

        assertTrue(path.isEmpty());
    }

    @Test
    void testDiagonalMovement() {
        Grid grid = new Grid(5, 5);

        grid.setWalkable(1, 0, false);
        grid.setWalkable(0, 1, false);

        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(2, 2);

        AStar astar = new AStar(grid);
        List<Node> path = astar.findPath(start, goal);

        assertEquals(start, path.get(0));
        assertEquals(goal, path.get(path.size() - 1));

        assertTrue(path.contains(grid.getNode(1, 1)));
    }

    @Test
    void testGoalIsStart() {
        Grid grid = new Grid(5, 5);


        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(0, 0);

        AStar astar = new AStar(grid);
        List<Node> path = astar.findPath(start, goal);

        assertEquals(1, path.size());
        assertEquals(start, path.get(0));
    }
}
