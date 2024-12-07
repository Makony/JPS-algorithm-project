import java.util.*;

public class AStar {
    private final Grid grid;

    public AStar(Grid grid) {
        this.grid = grid;
    }

    public List<Node> findPath(Node start, Node goal) {
        PriorityQueue<Node> openList = new PriorityQueue<>();
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
        return grid.getNeighbors(current);
    }

    private double heuristic(Node a, Node b) {
        double dx = Math.abs(a.getX() - b.getX());
        double dy = Math.abs(a.getY() - b.getY());
        return dx + dy + (Math.sqrt(2) - 2) * Math.min(dx, dy);
    }

    private double distance(Node a, Node b) {
        int dx = Math.abs(a.getX() - b.getX());
        int dy = Math.abs(a.getY() - b.getY());
        return (dx + dy == 2) ? Math.sqrt(2) : 1;
    }

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
