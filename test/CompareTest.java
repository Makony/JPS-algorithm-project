import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class CompareTest {
    @Test
    public void testCompare() {

        Grid grid = Compare.createGrid();
        assertEquals(3000, grid.getWidth());
        assertEquals(3000, grid.getHeight());

        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(2999, 2999);

        assertTrue(Compare.compareTwo(grid, start, goal));

        JPS jps = new JPS(grid);
        List<Node> jpsPath = jps.findPath(start, goal);
        AStar aStar = new AStar(grid);
        List<Node> aStarPath = aStar.findPath(start, goal);

        assertEquals(jpsPath.size(), aStarPath.size());
    }

}
