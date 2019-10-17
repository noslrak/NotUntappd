package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static model.Utility.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilityTest {
    private Random random = new Random();
    private int rand;

    @BeforeEach
    void runBefore() {
        rand = random.nextInt();
    }

    @Test
    void testLoadPrevious() {
        assertTrue(loadPrevious(1));
        assertFalse(loadPrevious(3));
        assertFalse(loadPrevious(rand));
    }

    @Test
    void testPrintOperation() {
        assertEquals("[1] Add a new beer entry", printOperation(1));
        assertEquals("[2] Search beer list", printOperation(2));
        assertEquals("[3] View all beers", printOperation(3));
        assertEquals("[4] Quit", printOperation(4));
        assertEquals("", printOperation(rand));
    }

    @Test
    void testPrintOperationSearch() {
        assertEquals("[1] Search by beer name", printOperationSearch(1));
        assertEquals("[2] Search by brewery", printOperationSearch(2));
        assertEquals("[3] Search by rating", printOperationSearch(3));
        assertEquals("[4] Return", printOperationSearch(4));
        assertEquals("", printOperationSearch(rand));
    }

    @Test
    void testPrintOperationView() {
        assertEquals("[1] View default", printOperationView(1));
        assertEquals("[2] View sorted by name", printOperationView(2));
        assertEquals("[3] View sorted by rating", printOperationView(3));
        assertEquals("[4] Return", printOperationView(4));
        assertEquals("", printOperationView(rand));
    }
}
