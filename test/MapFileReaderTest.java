import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class MapFileReaderTest {
    private Grid grid;

    @Test
    public void testMapFileReader() throws IOException {

        String testMapFilePath = "maps/Berlin_0_1024.map";

        grid = MapFileReader.loadMap(testMapFilePath);

        assertNotNull(grid, "Grid should not be null after loading map file.");
    }

}
