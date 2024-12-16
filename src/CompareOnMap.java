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

                boolean comparison = Compare.compareTwo(grid, start, goal);
            }
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }
    }
}
