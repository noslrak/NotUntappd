package model;

import model.exceptions.DuplicateEntryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class BeerListMonitorTest {
    private PremiumBeerList beerList;
    private PremiumBeerList testList;
    private PremiumBeerEntry operis = new PremiumBeerEntry("Operis", "Four Winds", "Sour",4.2, "");
    private PremiumBeerEntry noa = new PremiumBeerEntry("Noa", "Omnipollo", "Stout",4.4, "Test");

    @BeforeEach
    void runBefore() throws DuplicateEntryException {
        beerList = new PremiumBeerList();
        testList = new PremiumBeerList();
        testList.addBeerEntry(operis);
    }

    @Test
    void testBeerListMonitorSingle() throws DuplicateEntryException {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        beerList.addBeerEntry(operis);
        assertEquals("Beer: Operis Brewery: Four Winds Style: Sour Rating: 4.20 Comments:  was added\r\n"
            + "You have recorded 1 beer(s) so far", os.toString().trim());
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    @Test
    void testBeerListMonitorDouble() throws DuplicateEntryException {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        testList.addBeerEntry(noa);
        assertEquals("Beer: Noa Brewery: Omnipollo Style: Stout Rating: 4.40 Comments: Test was added\r\n"
                + "You have recorded 2 beer(s) so far", os.toString().trim());
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }
}
