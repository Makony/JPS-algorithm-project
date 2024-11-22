import java.util.*;

public class JPS {
    private Grid grid;

    public JPS(Grid grid) {
        this.grid = grid;
    }

    /**
     * Finds a path from the start node to the goal node.
     *
     * @param start The start node.
     * @param goal The goal node.
     * @return A list of nodes representing the path, or an empty list if no path exists.
     */
    public List<Node> findPath(Node start, Node goal) {
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(Node::getF));
        Set<Node> closedList = new HashSet<>();
        start.setG(0);
        start.setF(heuristic(start, goal));
        openList.add(start);

        while (!openList.isEmpty()) {
            Node current = openList.poll();

            // If the goal is reached, reconstruct the path
            if (current.equals(goal)) {
                return reconstructPath(goal);
            }

            closedList.add(current);

            // Process neighbors using JPS logic
            for (Node neighbor : identifySuccessors(current, goal)) {
                if (closedList.contains(neighbor)) {
                    continue;
                }

                double tentativeG = current.getG() + distance(current, neighbor);
                if (!openList.contains(neighbor) || tentativeG < neighbor.getG()) {
                    neighbor.setG(tentativeG);
                    neighbor.setF(tentativeG + heuristic(neighbor, goal));
                    neighbor.setParent(current);

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
        }

        // No path found
        return new ArrayList<>();
    }

    /**
     *
     * @param current
     * @param goal
     * @return
     */
    private List<Node> identifySuccessors(Node current, Node goal) {
        List<Node> successors = new ArrayList<>();

        int[][] directions = {
                {1, 0}, {0, 1}, {-1, 0}, {0, -1},   // Cardinal directions
                {1, 1}, {-1, 1}, {-1, -1}, {1, -1}   // Diagonal directions
        };

        // Iterate through each direction
        for (int[] dir : directions) {
            int dx = dir[0];
            int dy = dir[1];

            // Use jump to find the next valid jump point in this direction
            Node jumpPoint = grid.jump(current, dx, dy);

            // If a valid jump point is found, add it to the list of successors
            if (jumpPoint != null) {
                successors.add(jumpPoint);
            }
        }

        return successors;
    }



    /**
     * Calculates the heuristic distance between two nodes (Manhattan distance).
     */
    private double heuristic(Node a, Node b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    /**
     * Calculates the distance between two neighboring nodes (diagonal or straight-line).
     */
    private double distance(Node a, Node b) {
        int dx = Math.abs(a.getX() - b.getX());
        int dy = Math.abs(a.getY() - b.getY());
        return dx + dy == 2 ? Math.sqrt(2) : 1;
    }

    /**
     * Reconstructs the path from the goal node back to the start node.
     */
    private List<Node> reconstructPath(Node goal) {
        List<Node> path = new ArrayList<>();
        for (Node node = goal; node != null; node = node.getParent()) {
            path.add(node);
        }
        Collections.reverse(path);
        return path;
    }
}

