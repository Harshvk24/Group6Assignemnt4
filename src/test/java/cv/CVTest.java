package cv;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CVTest {

    @Test
    void testHashConsistency() {
        List<String> skills = Arrays.asList("Java", "SQL", "Docker");

        CV cv1 = new CV(
                "John Doe",
                "john@example.com",
                "0891234567",
                "Dublin Street 12",
                "5 years",
                skills,
                "Harvard",
                "Software Engineer"
        );

        CV cv2 = new CV(
                "John Doe",
                "john@example.com",
                "0891234567",
                "Dublin Street 12",
                "5 years",
                skills,
                "Harvard",
                "Software Engineer"
        );

        assertEquals(cv1.getHash(), cv2.getHash(), "Hashes should match for identical CVs");
    }
}
