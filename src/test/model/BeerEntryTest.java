package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BeerEntryTest {
    private FreeBeerEntry operis = new FreeBeerEntry("Operis", "Four Winds", 4.2, "");
    private FreeBeerEntry blank = new FreeBeerEntry("", "", 0, "");
    private PremiumBeerEntry magic = new PremiumBeerEntry("Magic Lambic", "Cantillion", "Lambic",
            4.75, "");
    private PremiumBeerEntry premiumBlank = new PremiumBeerEntry("", "", "", 0, "");
    private PremiumBeerEntry noa = new PremiumBeerEntry("Noa", "Omnipollo", "Stout",4.4, "Test");

    @Test
    void testSetBeerName() {
        blank.setBeerName("Noa");
        assertEquals("Noa", blank.getBeerName());
    }

    @Test
    void testSetBrewery() {
        blank.setBrewery("Cantillion");
        assertEquals("Cantillion", blank.getBrewery());
    }

    @Test
    void testSetBeerRating() {
        blank.setBeerRating(4.6);
        assertEquals(4.6, blank.getBeerRating());
    }

    @Test
    void testSetBeerComments() {
        blank.setBeerComments("Test comment please ignore");
        assertEquals("Test comment please ignore", blank.getBeerComments());
    }

    @Test
    void testSetBeerStyle() {
        premiumBlank.setBeerStyle("Gose");
        assertEquals("Gose", premiumBlank.getBeerStyle());
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

    @Test
    void testGetBeerStyle() {
        assertEquals("Lambic", magic.getBeerStyle());
    }

    @Test
    void testToString() {
        assertEquals("Beer: Operis Brewery: Four Winds Rating: 4.20 Comments: ", operis.toString());
    }

    @Test
    void testPremiumToString() {
        assertEquals("Beer: Magic Lambic Brewery: Cantillion Style: Lambic Rating: 4.75 Comments: ", magic.toString());
    }

    @Test
    void testEqualsSymmetric() {
        PremiumBeerEntry one = new PremiumBeerEntry("Magic Lambic", "Cantillion", "Sour",4.2, "");
        assertTrue(one.equals(magic) && magic.equals(one));
        assertEquals(one.hashCode(), magic.hashCode());
        assertFalse(noa.equals(magic) && magic.equals(noa));
        assertNotEquals(noa.hashCode(), magic.hashCode());
        assertNotEquals(one, null);
    }
}
