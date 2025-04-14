package cv;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class CVTest {

    private CV createSampleCV(String uuidHint) {
        return new CV(
            "Alice " + uuidHint,
            "alice@example.com",
            "1234567890",
            "123 Main St",
            "3 years in Java",
            List.of("Java", "Spring", "SQL"),
            "BSc Computer Science",
            "Software Engineer"
        );
    }

    @Test
    public void testCVConstructionDoesNotThrow() {
        assertDoesNotThrow(() -> createSampleCV("Test"));
    }

    @Test
    public void testHashIsBase64MD5Encoded() {
        CV cv = createSampleCV("A");
        String hash = cv.getHash();
        assertNotNull(hash);
        assertTrue(hash.matches("^[A-Za-z0-9+/=]+$"), "Hash should be base64 encoded MD5 string");
    }

    @Test
    public void testHashUniquenessForDifferentCVs() {
        CV cv1 = createSampleCV("X");
        CV cv2 = createSampleCV("Y");
        assertNotEquals(cv1.getHash(), cv2.getHash(), "Different UUIDs should produce different hashes");
    }

    @Test
    public void testToStringContainsKeyFields() {
        CV cv = createSampleCV("PrintTest");
        String s = cv.toString();
        assertTrue(s.contains("Alice"), "ToString should contain name");
        assertTrue(s.contains("Software Engineer"), "ToString should contain job title");
    }
}