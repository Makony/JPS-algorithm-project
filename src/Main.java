import java.util.List;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    /**
     * Method to run the program with users inputs
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        // Collect inputs
        System.out.println("Enter grid size (rows *space* columns): ");
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        Grid grid = initializeGrid(width, height, scanner);

        // Get start and goal positions
        System.out.println("Enter start point (row *space* col): ");
        int startRow = scanner.nextInt();
        int startCol = scanner.nextInt();

        System.out.println("Enter goal point (row *space* col): ");
        int goalRow = scanner.nextInt();
        int goalCol = scanner.nextInt();

        Node start = grid.getNode(startRow, startCol);
        Node goal = grid.getNode(goalRow, goalCol);

        // Run JPS algorithm
        JPS jps = new JPS(grid);
        List<Node> path = jps.findPath(start, goal);

        // Print results
        printPath(grid, path, start, goal);

        scanner.close();
    }

    /**
     * Method that uses user inputs to initialize the grid
     * @param width grid width
     * @param height grid height
     * @param scanner scanner
     * @return initialized grid
     */
    public Grid initializeGrid(int width, int height, Scanner scanner) {
        Grid grid = new Grid(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid.setWalkable(i, j, true);
            }
        }

        System.out.println("Enter number of obstacles: ");
        int obstacles = scanner.nextInt();

        for (int i = 0; i < obstacles; i++) {
            System.out.println("Enter obstacle position (row *space* col): ");
            int obsRow = scanner.nextInt();
            int obsCol = scanner.nextInt();
            grid.setWalkable(obsRow, obsCol, false);
        }
        return grid;
    }

    /**
     * Method to display the grid with start, goal nodes and obstacles
     * then prints the path (only jump points)
     * @param grid  grid
     * @param path  path
     * @param start start node
     * @param goal  goal node
     */
    public void printPath(Grid grid, List<Node> path, Node start, Node goal) {
        int height = grid.getHeight();
        int width = grid.getWidth();
        char[][] displayGrid = new char[width][height];

        // Fill grid with default values
        for (int r = 0; r < width; r++) {
            for (int c = 0; c < height; c++) {
                displayGrid[r][c] = grid.getNode(r, c).isWalkable() ? '.' : '#';
            }
        }

        for (Node node : path) {
            displayGrid[node.getX()][node.getY()] = '*';
        }
        displayGrid[start.getX()][start.getY()] = 'S';
        displayGrid[goal.getX()][goal.getY()] = 'G';

        // Print the grid
        for (int r = 0; r < width; r++) {
            for (int c = 0; c < height; c++) {
                System.out.print(displayGrid[r][c] + " ");
            }
            System.out.println();
        }

        if (!path.isEmpty()) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.println("(" + node.getX() + ", " + node.getY() + ")");
            }
        } else {
            System.out.println("No path found.");
        }
    }
}
