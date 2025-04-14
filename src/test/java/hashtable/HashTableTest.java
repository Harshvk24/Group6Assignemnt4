// src/test/java/hashtable/HashTableTest.java
package hashtable;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {
    @Test
    void testInsertAndGet() {
        HashTable<String, String> table = new HashTable<>();
        table.put("key1", "value1");
        assertEquals("value1", table.get("key1"));
    }

    @Test
    void testContains() {
        HashTable<String, String> table = new HashTable<>();
        table.put("exists", "yes");
        assertTrue(table.contains("exists"));
        assertFalse(table.contains("missing"));
    }
}