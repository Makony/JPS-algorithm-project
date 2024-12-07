import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {

            String mapFilePath = "C:/Users/user/Desktop/JPS/maps/Berlin_0_1024.map";
            Grid grid = MapFileReader.loadMap(mapFilePath);

            String scenFilePath = "C:/Users/user/Desktop/JPS/maps/Berlin_0_1024.map.scen";
            List<Scenario> scenarios = ScenFileReader.loadScenarios(scenFilePath);

            for (Scenario scenario : scenarios) {
                Node start = grid.getNode(scenario.startX, scenario.startY);
                Node goal = grid.getNode(scenario.goalX, scenario.goalY);

                System.out.printf("Scenario: Start (%d, %d), Goal (%d, %d)%n",
                        scenario.startX, scenario.startY, scenario.goalX, scenario.goalY);

                // Run JPS
                JPS jps = new JPS(grid);
                long startJps = System.nanoTime();
                List<Node> jpsPath = jps.findPath(start, goal);
                long endJps = System.nanoTime();

                // Run A*
                AStar astar = new AStar(grid);
                long startAStar = System.nanoTime();
                List<Node> aStarPath = astar.findPath(start, goal);
                long endAStar = System.nanoTime();

                // Display results
                System.out.println("Results:");
                if (!jpsPath.isEmpty()) {
                    System.out.println("JPS Path found. Time: " + (endJps - startJps) / 1e6 + " ms");
                } else {
                    System.out.println("JPS Path not found.");
                }

                if (!aStarPath.isEmpty()) {
                    System.out.println("A* Path found. Time: " + (endAStar - startAStar) / 1e6 + " ms");
                } else {
                    System.out.println("A* Path not found.");
                }

                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }
    }



        /*Grid grid = new Grid(4, 4);


        grid.setWalkable(0,1,false);
        grid.setWalkable(1,2,false);
        grid.setWalkable(2,3,false);
        grid.setWalkable(3,2,false);




        Node start = grid.getNode(0, 0);
        Node goal = grid.getNode(3, 1);

        JPS jps = new JPS(grid);
        List<Node> path = jps.findPath(start, goal);

        printPath(grid, path, start, goal );

        if (!path.isEmpty()) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.println("(" + node.getX() + ", " + node.getY() + ")");
            }
        } else {
            System.out.println("No path found.");
        }

        /*Scanner scanner = new Scanner(System.in);

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

        scanner.close();*/

    }

    /**
     * Method to display the grid with start, goal nodes and obstacles
     * @param grid grid
     * @param path path
     * @param start start node
     * @param goal goal node
     */
   /* public static void printPath(Grid grid, List<Node> path, Node start, Node goal) {
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
    }*/

