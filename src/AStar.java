import java.util.*;

/**
 * Class for implementation of A* algorithm
 */
public class AStar {
    private final Grid grid;

    public AStar(Grid grid) {
        this.grid = grid;
    }

    /**
     * Method that finds path from start node to the goal
     * @param start start node
     * @param goal goal node
     * @return path from start to the goal node, empty list if no path is found
     */
    public List<Node> findPath(Node start, Node goal) {
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(Node::getF));
        Set<Node> closedList = new HashSet<>();

        start.setG(0);
        start.setF(heuristic(start, goal));
        openList.add(start);

        while (!openList.isEmpty()) {
            Node current = openList.poll();

            if (current.equals(goal)) {
                return reconstructPath(goal);
            }

            closedList.add(current);

            for (Node successor : identifySuccessors(current)) {
                if (closedList.contains(successor)) continue;

                double tentativeG = current.getG() + distance(current, successor);

                if (!openList.contains(successor) || tentativeG < successor.getG()) {
                    successor.setG(tentativeG);
                    successor.setF(tentativeG + heuristic(successor, goal));
                    successor.setParent(current);

                    if (!openList.contains(successor)) {
                        openList.add(successor);
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    /**
     * Unlike JPS, A* considers all valid neighbors without additional pruning.
     */
    private List<Node> identifySuccessors(Node current) {
        List<Node> neighbors = grid.getNeighbors(current);
        return neighbors;
    }

    /**
     * Calculates the heuristics between the nodes
     * @param a first node
     * @param b second node
     * @return heuristic costs
     */
    private double heuristic(Node a, Node b) {
        double dx = Math.abs(a.getX() - b.getX());
        double dy = Math.abs(a.getY() - b.getY());
        return dx + dy + (Math.sqrt(2) - 2) * Math.min(dx, dy);
    }

    /**
     * Computes the cost to move from the current node to the next one
     * @param a current node
     * @param b successor node
     * @return computed distance
     */
    private double distance(Node a, Node b) {
        int dx = Math.abs(a.getX() - b.getX());
        int dy = Math.abs(a.getY() - b.getY());
        return (dx + dy == 2) ? Math.sqrt(2) : 1;
    }

    /**
     * Backtrack from the goal node to the start node to create the path
     * @param goal goal node
     * @return reconstructed path
     */
    private List<Node> reconstructPath(Node goal) {
        List<Node> path = new ArrayList<>();
        Node current = goal;

        while (current != null) {
            path.add(0, current);
            current = current.getParent();
        }

        return path;
    }
}
