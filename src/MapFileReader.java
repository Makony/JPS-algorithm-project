import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MapFileReader {
    public static Grid loadMap(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        int height = lines.size() - 4;
        int width = lines.get(4).length();
        Grid grid = new Grid(width, height);

        for (int i = 0; i < height; i++) {
            String line = lines.get(i + 4);
            for (int j = 0; j < width; j++) {
                char cell = line.charAt(j);
                grid.setWalkable(i, j, cell == '.');
            }
        }
        return grid;
    }
}
