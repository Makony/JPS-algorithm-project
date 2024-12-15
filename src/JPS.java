import java.util.*;

/**
 * Class for the implementation of JPS logic
 */
public class JPS {
    private final Grid grid;

    public JPS(Grid grid) {
        this.grid = grid;
    }

    /**
     * Method that finds path from start node to the goal
     * @param start start node
     * @param goal goal node
     * @return path from start to the goal node, empty list if no path is found
     */
    public List<Node> findPath(Node start, Node goal) {
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(Node::getF)); //Nodes for further evaluation
        Map<Node, Double> gScores = new HashMap<>();
        Set<Node> closedList = new HashSet<>();

        //Initialize the start node
        start.setG(0);
        start.setF(heuristic(start, goal));
        openList.add(start);
        gScores.put(start, 0.0);

        while (!openList.isEmpty()) {
            Node current = openList.poll();

            //Check if we have reached the goal
            if (current.equals(goal)) {
                return reconstructPath(goal);
            }

            closedList.add(current);

            //Find the next valid node to go to
            for (Node successor : identifySuccessors(current, goal)) {
                if (closedList.contains(successor)) continue;

                //Tentative G evaluates whether the path prom the current node to the successor is better
                double tentativeG = gScores.getOrDefault(current, Double.MAX_VALUE) + distance(current, successor);

                //Check if tentative G score is lower than successor's g score
                if (tentativeG < gScores.getOrDefault(successor, Double.MAX_VALUE)) {
                    //Update
                    gScores.put(successor, tentativeG);
                    successor.setG(tentativeG);
                    successor.setF(tentativeG + heuristic(successor, goal));
                    successor.setParent(current);

                    if (!openList.contains(successor)) {
                        openList.add(successor);
                    }
                }
            }
        }

        return Collections.emptyList();
    }

    /**
     * Method to find the next node after the current node
     * @param current current node
     * @param goal goal node
     * @return list of successor nodes
     */
    private List<Node> identifySuccessors(Node current, Node goal) {
        List<Node> successors = new ArrayList<>();
        Node parent = current.getParent();

        //Identify direction based on the parent node
        int dx = 0, dy = 0;
        if (parent != null) {
            dx = Integer.compare(current.getX(), parent.getX());
            dy = Integer.compare(current.getY(), parent.getY());
        }

        //Prune and find valid jump points
        for (int[] dir : pruneDirections(dx, dy)) {
            Node jumpPoint = jump(current, goal, dir[0], dir[1]);
            if (jumpPoint != null) {
                successors.add(jumpPoint);
            }
        }

        return successors;
    }

    /**
     * Method to find jump points
     * @param current current node
     * @param goal goal node
     * @param dx direction in x-axis
     * @param dy direction in y-axis
     * @return the next valid jump point, null if there is no jump point
     */
    private Node jump(Node current, Node goal, int dx, int dy) {
        int x = current.getX() + dx;
        int y = current.getY() + dy;

        if (!grid.isWalkable(x, y)) return null;

        Node node = grid.getNode(x, y);
        if (node == null || node.equals(goal)) return node;

        // Check for forced neighbors
        if (dx != 0 && dy != 0) {
            if ((grid.isWalkable(x - dx, y) && !grid.isWalkable(x - dx, y - dy)) ||
                    (grid.isWalkable(x, y - dy) && !grid.isWalkable(x - dx, y - dy))) {
                return node;
            }
        } else if (dx != 0) {
            if ((grid.isWalkable(x, y + 1) && !grid.isWalkable(x - dx, y + 1)) ||
                    (grid.isWalkable(x, y - 1) && !grid.isWalkable(x - dx, y - 1))) {
                return node;
            }
        } else if (dy != 0) {
            if ((grid.isWalkable(x + 1, y) && !grid.isWalkable(x + 1, y - dy)) ||
                    (grid.isWalkable(x - 1, y) && !grid.isWalkable(x - 1, y - dy))) {
                return node;
            }
        }

        // Check for jump points in diagonal directions
        if (dx != 0 && dy != 0) {
            if (jump(node, goal, dx, 0) != null || jump(node, goal, 0, dy) != null) {
                return node;
            }
        }

        // Continue jumping in the current direction
        return jump(node, goal, dx, dy);
    }

    /**
     * Method for pruning
     * @param dx direction in x-axis
     * @param dy direction in y-axis
     * @return list of valid directions
     */
    private List<int[]> pruneDirections(int dx, int dy) {
        //Consider all directions if it is a start node
        if (dx == 0 && dy == 0) {
            return List.of(
                    new int[]{1, 0}, new int[]{-1, 0},
                    new int[]{0, 1}, new int[]{0, -1},
                    new int[]{1, 1}, new int[]{-1, 1},
                    new int[]{1, -1}, new int[]{-1, -1}
            );
        }

        List<int[]> directions = new ArrayList<>();
        directions.add(new int[]{dx, dy});

        if (dx != 0 && dy != 0) {
            directions.add(new int[]{dx, 0}); //horizontal
            directions.add(new int[]{0, dy}); //vertical
        } else if (dx != 0) {
            directions.add(new int[]{dx, 1}); // step up
            directions.add(new int[]{dx, -1}); //step down
        } else if (dy != 0) {
            directions.add(new int[]{1, dy}); //step right
            directions.add(new int[]{-1, dy}); //step left
        }

        return directions;
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
        return (a.getX() != b.getX() && a.getY() != b.getY()) ? Math.sqrt(2) : 1;
    }

    /**
     * Backtrack from the goal node to the start node to create the path
     * @param goal goal node
     * @return reconstructed path
     */
    private List<Node> reconstructPath(Node goal) {
        LinkedList<Node> path = new LinkedList<>();
        Node current = goal;

        while (current != null) {
            path.addFirst(current);
            current = current.getParent();
        }

        return path;
    }
}
