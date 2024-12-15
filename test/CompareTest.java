import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class CompareTest {
    @Test
    public void testComparisonOfJPSAndAStar() {
        Grid grid = new Grid(3000, 3000);

        for (int x = 0; x < 3000; x++) {
            for (int y = 0; y < 3000; y++) {

                // Add obstacles
                if ((x + y) % 3 == 0 && !(x == 0 && y == 0) && !(x == 2999 && y == 2999)) {
                    grid.setWalkable(x, y, false);
                }
            }
        }

        // Create a guaranteed path from the start to the goal
        for (int i = 0; i < 3000; i++) {
            grid.setWalkable(i, i, true);
            if (i < 2999){
                grid.setWalkable(i + 1, i, true);
                grid.setWalkable(i, i + 1, true);
            }
        }

        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(2999, 2999);

        JPS jps = new JPS(grid);
        long startTimeJPS = System.nanoTime();
        List<Node> pathJPS = jps.findPath(start, goal);
        long endTimeJPS = System.nanoTime();

        AStar aStar = new AStar(grid);
        long startTimeAStar = System.nanoTime();
        List<Node> pathAStar = aStar.findPath(start, goal);
        long endTimeAStar = System.nanoTime();

        double jpsTime = (endTimeJPS - startTimeJPS) / 1e6;
        double aStarTime = (endTimeAStar - startTimeAStar) / 1e6;
        assertTrue(jpsTime < aStarTime);
    }
}
