import java.io.IOException;
import java.util.List;

/**
 * Class that runs JPS and A* on a 1024x1024 Berlin Map and compares their runtime
 */
public class CompareOnMap {
    public static void main(String[] args) {

        try {

            String mapFilePath = "maps/Berlin_0_1024.map";
            Grid grid = MapFileReader.loadMap(mapFilePath);

            String scenFilePath = "maps/Berlin_0_1024.map.scen";
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
}
