import java.util.ArrayList;
import java.util.List;

/**
 * Class for the grid implementation
 */
public class Grid {
    private final int width;
    private final int height;
    private final Node[][] grid;

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

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public Node getNode(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[x][y];
        }
        return null;
    }

    public void setWalkable(int x, int y, boolean walkable) {
        Node node = getNode(x, y);
        if (node != null) {
            node.setWalkable(walkable);
        }
    }

    public boolean isWalkable(int x, int y) {
        Node node = getNode(x, y);
        return node != null && node.isWalkable();
    }

    /**
     * Method to identify valid neighbors of the current node
     * @param node current node
     * @return a list of valid neighbors
     */
    public List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},   // Cardinal directions
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}  // Diagonal directions
        };

        for (int[] dir : directions) {
            Node neighbor = getNode(node.getX() + dir[0], node.getY() + dir[1]);
            //Check if the neighbor is valid
            if (neighbor != null && neighbor.isWalkable()) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }
}
