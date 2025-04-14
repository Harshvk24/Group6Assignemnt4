package app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

public class ApplicationTest {

    @Test
    public void testMainRunsWithoutException() {
        assertDoesNotThrow(() -> Application.main(new String[]{}));
    }

    @Test
    public void testCSVFileGenerated() {
        Application.main(new String[]{});
        File file = new File("experiment_metrics.csv");
        assertTrue(file.exists(), "experiment_metrics.csv should be created");
        assertTrue(file.length() > 0, "CSV file should not be empty");
    }

    @Test
    public void testMultipleRuns() {
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 3; i++) {
                Application.main(new String[]{});
            }
        });
    }
}