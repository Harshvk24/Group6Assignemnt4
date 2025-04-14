package hashtable;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashNodeTest {

    @Test
    public void testNodeCreation() {
        HashNode<String, Integer> node = new HashNode<>("testKey", 42);
        assertEquals("testKey", node.key);
        assertEquals(42, node.value);
        assertNull(node.next);
    }

    @Test
    public void testNextPointerAssignment() {
        HashNode<String, String> node1 = new HashNode<>("A", "Alpha");
        HashNode<String, String> node2 = new HashNode<>("B", "Beta");
        node1.next = node2;

        assertNotNull(node1.next);
        assertEquals("B", node1.next.key);
        assertEquals("Beta", node1.next.value);
    }

    @Test
    public void testValueMutation() {
        HashNode<String, Double> node = new HashNode<>("pi", 3.14);
        node.value = 3.14159;
        assertEquals(3.14159, node.value);
    }
}