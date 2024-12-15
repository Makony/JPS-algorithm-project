import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.List;

public class ScenFileReaderTest {
    private List<Scenario> scenarios;
    @Test
    public void testScenFileReader() throws IOException {

        String testScenFilePath = "maps/Berlin_0_1024.map.scen";

        scenarios = ScenFileReader.loadScenarios(testScenFilePath);

        assertNotNull(scenarios);
        assertFalse(scenarios.isEmpty());
    }
}
