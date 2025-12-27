package main;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class LRUCacheTester {

    LRUCache lruCache;
    @BeforeEach
    public void setUp() {
        lruCache = new LRUCache(3);
    }
    //Test the put and get method to ensure that the correct values are
    //inserted and retrieved
    @Test
    public void testInsert() {
        lruCache.put(1, 100);
        lruCache.put(2, 200);
        lruCache.put(3, 300);
        assertEquals(100, lruCache.get(1));
        assertEquals(300, lruCache.get(3));
        assertEquals(-1, lruCache.get(4));
    }

    @Test
    public void testCapacityInsert() {
        lruCache.put(1, 100);
        lruCache.put(2, 200);
        lruCache.put(3, 300);
        lruCache.put(4, 400);
        //Test that the capacity is not overrun
        //Test that 1 is correctly evicted from the table
        assertEquals(-1, lruCache.get(1));
        assertEquals(200, lruCache.get(2));
        assertEquals(300, lruCache.get(3));
        assertEquals(400, lruCache.get(4));
    }

}
