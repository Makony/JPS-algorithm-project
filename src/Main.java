import java.util.List;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(10, 10);

        grid.setWalkable(3, 3, true);
        grid.setWalkable(3, 4, true);
        grid.setWalkable(3, 5, true);

        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(9, 9);

        JPS jps = new JPS(grid);
        List<Node> path = jps.findPath(start, goal);

        // Print the path
        if (!path.isEmpty()) {
            for (Node node : path) {
                System.out.println("(" + node.getX() + ", " + node.getY() + ")");
            }
        } else {
            System.out.println("No path found.");
        }
    }
}

