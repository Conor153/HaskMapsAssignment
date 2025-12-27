package main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class HashTableDivideAndConquerTester {
    HashTableDivideAndConquer unihashTable;
    @BeforeEach
    public void setUp() {
        unihashTable = new HashTableDivideAndConquer(3);

    }
    //Test the put and get method to ensure that the correct values are
    //inserted and retrieved
    @Test
    public void testInsert() {
        unihashTable.insert("ATU Letterkenny");
        assertTrue(unihashTable.search("ATU Letterkenny"));
        assertFalse(unihashTable.search("ATU Sligo"));
    }

    @Test
    public void testDelete() {
        unihashTable.insert("ATU Letterkenny");
        assertTrue(unihashTable.search("ATU Letterkenny"));
        unihashTable.delete("ATU Letterkenny");
        assertFalse(unihashTable.search("ATU Letterkenny"));
    }

    @Test
    public void testTableResizes() {
        unihashTable.insert("ATU Letterkenny");
        unihashTable.insert("ATU Sligo");
        unihashTable.insert("ATU Galway");
        unihashTable.insert("ATU Killybeggs");
        unihashTable.insert("ATU Dundalk");
        unihashTable.insert("UCD");
        assertTrue(unihashTable.search("ATU Letterkenny"));
        assertTrue(unihashTable.search("ATU Sligo"));
        assertTrue(unihashTable.search("ATU Galway"));
        assertTrue(unihashTable.search("ATU Killybeggs"));
        assertTrue(unihashTable.search("ATU Dundalk"));
        assertTrue(unihashTable.search("UCD"));
    }
}
