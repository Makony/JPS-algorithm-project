import java.util.ArrayList;
import java.util.List;

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

    public Node jump(Node current, Node goal, int dx, int dy) {
        int x = current.getX();
        int y = current.getY();


        x += dx;
        y += dy;

        // Check if out of bounds or obstacle
        if (!isWalkable(x, y)) {
            //System.out.printf("Hit obstacle or out of bounds at: (%d, %d)%n", x, y);
            return null;
        }

        Node node = getNode(x, y);
        if (node == null) {
            //System.out.printf("Node retrieval failed at: (%d, %d)%n", x, y);
            return null;
        }

        // Check if we've reached the goal
        if (node.equals(goal)) {
            //System.out.printf("Reached goal at: (%d, %d)%n", x, y);
            return node;
        }

        // Check for forced neighbors
        if (dx != 0 && dy != 0) { // Diagonal movement
            if ((isWalkable(x - dx, y) && !isWalkable(x - dx, y - dy)) ||
                    (isWalkable(x, y - dy) && !isWalkable(x - dx, y - dy))) {
                //System.out.printf("Found forced neighbor at: (%d, %d) [Diagonal]%n", x, y);
                return node;
            }
        } else if (dx != 0) { // Horizontal movement
            if ((isWalkable(x, y + 1) && !isWalkable(x - dx, y + 1)) ||
                    (isWalkable(x, y - 1) && !isWalkable(x - dx, y - 1))) {
                //System.out.printf("Found forced neighbor at: (%d, %d) [Horizontal]%n", x, y);
                return node;
            }
        } else if (dy != 0) { // Vertical movement
            if ((isWalkable(x + 1, y) && !isWalkable(x + 1, y - dy)) ||
                    (isWalkable(x - 1, y) && !isWalkable(x - 1, y - dy))) {
                //System.out.printf("Found forced neighbor at: (%d, %d) [Vertical]%n", x, y);
                return node;
            }
        }

        // Diagonal case: Check recursively in horizontal and vertical directions
        if (dx != 0 && dy != 0) {
            Node jumpHorizontal = jump(getNode(x, y), goal, dx, 0);
            Node jumpVertical = jump(getNode(x, y), goal, 0, dy);
            if (jumpHorizontal != null || jumpVertical != null) {
                return node;
            }
        }


        //System.out.printf("Continuing to jump from (%d, %d) in direction dx=%d, dy=%d%n", x, y, dx, dy);


        return jump(node, goal, dx, dy);
    }



    public List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[][] directions = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},   // Cardinal directions
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}  // Diagonal directions
        };

        for (int[] dir : directions) {
            Node neighbor = getNode(node.getX() + dir[0], node.getY() + dir[1]);
            if (neighbor != null && neighbor.isWalkable()) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }
}
