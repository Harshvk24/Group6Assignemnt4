package hashtable;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {

    @Test
    public void testPutAndGet() {
        HashTable<String, String> table = new HashTable<>(8);
        table.put("id1", "CV1");
        table.put("id2", "CV2");

        assertEquals("CV1", table.get("id1"));
        assertEquals("CV2", table.get("id2"));
    }

    @Test
    public void testOverwriteValue() {
        HashTable<String, String> table = new HashTable<>(4);
        table.put("key", "original");
        table.put("key", "updated");

        assertEquals("updated", table.get("key"));
        assertEquals(1, table.size(), "Overwriting shouldn't increase size");
    }

    @Test
    public void testContainsKey() {
        HashTable<String, Integer> table = new HashTable<>(4);
        table.put("test", 100);
        assertTrue(table.contains("test"));
        assertFalse(table.contains("missing"));
    }

    @Test
    public void testSizeTracking() {
        HashTable<Integer, String> table = new HashTable<>(10);
        for (int i = 0; i < 5; i++) {
            table.put(i, "Val" + i);
        }
        assertEquals(5, table.size());
    }

    @Test
    public void testLoadFactorCalculation() {
        HashTable<String, String> table = new HashTable<>(10);
        table.put("a", "1");
        table.put("b", "2");
        assertEquals(0.2, table.loadFactor(), 0.0001);
    }

    @Test
    public void testGetNonExistentKey() {
        HashTable<String, String> table = new HashTable<>(8);
        assertNull(table.get("unknown"));
    }

    @Test
    public void testBucketChainingAndCollisions() {
        HashTable<Integer, String> table = new HashTable<>(1); // force collisions
        table.put(1, "one");
        table.put(2, "two");

        HashNode<Integer, String> bucket = table.getBucket(0);
        int nodeCount = 0;
        while (bucket != null) {
            nodeCount++;
            bucket = bucket.next;
        }
        assertEquals(2, nodeCount, "Both items should be chained in same bucket");
    }

    @Test
    public void testGetBucketOutOfBounds() {
        HashTable<String, String> table = new HashTable<>(4);
        assertNull(table.getBucket(-1));
        assertNull(table.getBucket(100));
    }
}