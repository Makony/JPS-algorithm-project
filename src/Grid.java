import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int width;
    private int height;
    private Node[][] grid;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Node[width][height];

        // Initialize the grid with nodes
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new Node(x, y);
            }
        }
    }

    /**
     * Get a node by its coordinates.
     *
     * @param x x-coordinate of the node.
     * @param y y-coordinate of the node.
     * @return Node at the given coordinates.
     */
    public Node getNode(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[x][y];
        }
        return null;  // return null if the coordinates are out of bounds
    }

    /**
     * Set the walkable status of a node at (x, y).
     *
     * @param x x-coordinate of the node.
     * @param y y-coordinate of the node.
     * @param walkable true if the node is walkable, false if it's blocked.
     */
    public void setWalkable(int x, int y, boolean walkable) {
        Node node = getNode(x, y);
        if (node != null) {
            node.setWalkable(walkable);
        }
    }

    /**
     * Get a list of walkable neighbors of a node.
     * Is used to identify neighbors
     * in the grid that are walkable and lead to potential jump points.
     *
     * @param node The node for which neighbors are requested.
     * @return List of walkable neighbors.
     */
    public List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int x = node.getX();
        int y = node.getY();

        // Directions: N, S, E, W, NE, NW, SE, SW (8 directions)
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},   // Cardinal directions
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}   // Diagonal directions
        };

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            Node neighbor = getNode(nx, ny);
            if (neighbor != null && neighbor.isWalkable()) {
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }

    /**
     * Get a jump point starting from the current node in a given direction.
     * It finds the next "jump point" in the search direction
     *
     * @param node The node where the search starts.
     * @param dx Direction along the X axis (-1, 0, 1).
     * @param dy Direction along the Y axis (-1, 0, 1).
     * @return The next jump point, or null if no jump point is found.
     */
    public Node jump(Node node, int dx, int dy) {
        int x = node.getX();
        int y = node.getY();

        // Move in the given direction
        while (true) {
            x += dx;
            y += dy;

            // Out of bounds check
            if (x < 0 || x >= width || y < 0 || y >= height) {
                return null;
            }

            Node current = getNode(x, y);
            if (!current.isWalkable()) {
                return null;  // Blocked node
            }

            // Check for forced neighbors (change of direction or jump point)
            if (dx != 0) {
                // Check for forced vertical neighbors (above or below the node)
                if (isForcedNeighbor(x, y - 1) || isForcedNeighbor(x, y + 1)) {
                    return current;
                }
            }

            if (dy != 0) {
                // Check for forced horizontal neighbors (left or right of the node)
                if (isForcedNeighbor(x - 1, y) || isForcedNeighbor(x + 1, y)) {
                    return current;
                }
            }

            // Continue the jump if no forced neighbors are found
        }
    }

    // Helper method to check if a node is a forced neighbor (blocked in the adjacent direction)
    private boolean isForcedNeighbor(int x, int y) {
        return getNode(x, y) != null && !getNode(x, y).isWalkable();
    }

}

