import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompareTest {
    @Test
    public void testCreateGrid() {

        Grid grid = Compare.createGrid();
        assertEquals(3000, grid.getWidth());
        assertEquals(3000, grid.getHeight());

        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(2999, 2999);

        assertTrue(start.isWalkable());
        assertTrue(goal.isWalkable());
    }

    @Test
    public void testCompareTwo() {

        Grid grid = Compare.createGrid();

        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(2999, 2999);

        boolean comparison = Compare.compareTwo(grid, start, goal);
        assertTrue(comparison);
    }
}
