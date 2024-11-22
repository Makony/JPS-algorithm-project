import java.util.List;

public class Main {
    public static void main(String[] args) {
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

