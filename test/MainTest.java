import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.List;

class MainTest {

    @Test
    void testInitializeGrid() {
        String input = "3 3\n2\n0 1\n1 2\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Main main = new Main();
        Grid grid = main.initializeGrid(3, 3, scanner);

        assertFalse(grid.getNode(0, 1).isWalkable());
        assertFalse(grid.getNode(1, 2).isWalkable());
        assertTrue(grid.getNode(0, 0).isWalkable());
    }

    @Test
    void testPrintPath() {
        Grid grid = new Grid(3, 3);
        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(2, 2);
        List<Node> path = List.of(grid.getNode(1, 1), goal);

        Main main = new Main();
        main.printPath(grid, path, start, goal);
    }
}
