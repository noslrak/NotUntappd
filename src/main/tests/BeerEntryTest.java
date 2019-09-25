package tests;

import model.BeerEntry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BeerEntryTest {
    BeerEntry operis = new BeerEntry();

    @BeforeEach
    public void runBefore() {
        operis.setBeerName("Operis");
        operis.setBrewery("Four Winds");
        operis.setBeerRating(4.2);
        operis.setBeerComments("");
    }

    @Test
    public void testGetBeerName() {
        assertEquals("Operis", operis.getBeerName());
    }

    @Test
    public void testGetBreweryName() {
        assertEquals("Four Winds", operis.getBrewery());
    }

    @Test
    public void testGetRating() {
        assertEquals(4.2, operis.getBeerRating());
    }

    @Test
    public void testGetComments() {
        assertEquals("", operis.getBeerComments());
    }
}
