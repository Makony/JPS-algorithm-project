import java.util.List;
import java.util.Scanner;

/**
 * Class for the user interface implementation
 * It takes users inputs and outputs a grid representation with the path
 */
public class Main {

    public static void main (String args[]){

        Scanner scanner = new Scanner(System.in);

        // Get grid size
        System.out.println("Enter grid size (rows *space* columns): ");
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        Grid grid = new Grid(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid.setWalkable(i, j, true);
            }
        }

        // Set obstacles
        System.out.println("Enter number of obstacles: ");
        int obstacles = scanner.nextInt();

        for (int i = 0; i < obstacles; i++) {
            System.out.println("Enter obstacle position (row *space* col): ");
            int obsRow = scanner.nextInt();
            int obsCol = scanner.nextInt();
            grid.setWalkable(obsRow, obsCol, false);
        }

        //Get start and goal coordinates
        System.out.println("Enter start point (row *space* col): ");
        int startRow = scanner.nextInt();
        int startCol = scanner.nextInt();

        System.out.println("Enter goal point (row *space* col): ");
        int goalRow = scanner.nextInt();
        int goalCol = scanner.nextInt();

        // Initializing start and goal nodes
        Node start = grid.getNode(startRow, startCol);
        Node goal = grid.getNode(goalRow, goalCol);

        // Running the JPS algorithm
        JPS jps = new JPS(grid);
        List<Node> path = jps.findPath(start, goal);

        // Printing the grid with the path
        printPath(grid, path, start, goal);

        if (!path.isEmpty()) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.println("(" + node.getX() + ", " + node.getY() + ")");
            }
        } else {
            System.out.println("No path found.");
        }

        scanner.close();

}
    /**
     * Method to display the grid with start, goal nodes and obstacles
     *
     * @param grid  grid
     * @param path  path
     * @param start start node
     * @param goal  goal node
     */
    public static void printPath(Grid grid, List<Node> path, Node start, Node goal) {
        int height = grid.getHeight();
        int width = grid.getWidth();
        char[][] displayGrid = new char[width][height];

        // grid display
        for (int r = 0; r < width; r++) {
            for (int c = 0; c < height; c++) {
                if (!grid.getNode(r, c).isWalkable()) {
                    displayGrid[r][c] = '#';
                } else {
                    displayGrid[r][c] = '.';
                }
            }
        }

        // Mark jump points on the grid
        for (Node node : path) {
            displayGrid[node.getX()][node.getY()] = '*';
        }

        // Mark start and goal positions
        displayGrid[start.getX()][start.getY()] = 'S';
        displayGrid[goal.getX()][goal.getY()] = 'G';

        // Print the grid
        for (int r = 0; r < width; r++) {
            for (int c = 0; c < height; c++) {
                System.out.print(displayGrid[r][c] + " ");
            }
            System.out.println();
        }
    }
}
