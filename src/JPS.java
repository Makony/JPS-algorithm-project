import java.util.*;

public class JPS {
    private final Grid grid;

    public JPS(Grid grid) {
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

            for (Node successor : identifySuccessors(current, goal)) {
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

    private List<Node> identifySuccessors(Node current, Node goal) {
        List<Node> successors = new ArrayList<>();

        Node parent = current.getParent();
        int dx = 0, dy = 0;

        if (parent != null) {
            dx = Integer.compare(current.getX(), parent.getX());
            dy = Integer.compare(current.getY(), parent.getY());
        }

        List<int[]> validDirections = pruneDirections(dx, dy);

        for (int[] dir : validDirections) {
            Node jumpPoint = grid.jump(current, goal, dir[0], dir[1]);
            if (jumpPoint != null) {
                successors.add(jumpPoint);
            }
        }

        return successors;
    }

    private List<int[]> pruneDirections(int dx, int dy) {
        List<int[]> directions = new ArrayList<>();

        if (dx == 0 && dy == 0) {
            directions.add(new int[] {1, 0});
            directions.add(new int[] {-1, 0});
            directions.add(new int[] {0, 1});
            directions.add(new int[] {0, -1});
            directions.add(new int[] {1, 1});
            directions.add(new int[] {-1, 1});
            directions.add(new int[] {1, -1});
            directions.add(new int[] {-1, -1});
        } else {
            directions.add(new int[] {dx, dy});

            if (dx != 0 && dy == 0) {
                directions.add(new int[] {dx, 1});
                directions.add(new int[] {dx, -1});
            } else if (dx == 0 && dy != 0) {
                directions.add(new int[] {1, dy});
                directions.add(new int[] {-1, dy});
            } else {
                directions.add(new int[] {dx, 0});
                directions.add(new int[] {0, dy});
            }
        }

        return directions;
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
