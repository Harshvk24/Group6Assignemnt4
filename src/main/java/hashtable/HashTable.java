// src/main/java/hashtable/HashTable.java
package hashtable;

import java.util.Objects;

public class HashTable<K, V> {
    private final int capacity;
    private int size = 0;
    private HashNode<K, V>[] buckets;

    public HashTable(int capacity) {
        this.capacity = capacity;
        buckets = new HashNode[capacity];
    }

    private int getBucketIndex(K key) {
        return Math.abs(Objects.hashCode(key)) % buckets.length;
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = buckets[index];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        HashNode<K, V> head = buckets[index];
        while (head != null) {
            if (head.key.equals(key)) return head.value;
            head = head.next;
        }
        return null;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public double loadFactor() {
        return (double) size / capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    // For analysis/debugging purposes only
    public HashNode<K, V> getBucket(int index) {
        if (index < 0 || index >= buckets.length) return null;
        return buckets[index];
    }
}