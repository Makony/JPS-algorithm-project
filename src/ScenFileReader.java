import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScenFileReader {
    public static List<Scenario> loadScenarios(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<Scenario> scenarios = new ArrayList<>();

        for (String line : lines) {
            if (line.startsWith("version")) continue;
            String[] parts = line.split("\t");
            int startX = Integer.parseInt(parts[4]);
            int startY = Integer.parseInt(parts[5]);
            int goalX = Integer.parseInt(parts[6]);
            int goalY = Integer.parseInt(parts[7]);

            scenarios.add(new Scenario(startX, startY, goalX, goalY));
        }
        return scenarios;
    }
}
