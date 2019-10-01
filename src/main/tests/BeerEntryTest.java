package tests;

import model.BeerEntry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BeerEntryTest {
    private BeerEntry operis = new BeerEntry("Operis", "Four Winds", 4.2, "");
    private BeerEntry blank = new BeerEntry("", "", 0, "");

    @BeforeEach
    void runBefore() {
    }

    @Test
    void testSetBeerName() {
        blank.setBeerName("Noa");
        assertEquals("Noa", blank.getBeerName());
    }

    @Test
    void testGetBeerName() {
        assertEquals("Operis", operis.getBeerName());
    }

    @Test
    void testGetBreweryName() {
        assertEquals("Four Winds", operis.getBrewery());
    }

    @Test
    void testGetRating() {
        assertEquals(4.2, operis.getBeerRating());
    }

    @Test
    void testGetComments() {
        assertEquals("", operis.getBeerComments());
    }
}