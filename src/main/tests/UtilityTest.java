package tests;

import model.BeerEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static model.Utility.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Random;

class UtilityTest {
    private ArrayList<BeerEntry> beerList;
    private ArrayList<BeerEntry> foundList;
    private BeerEntry operis = new BeerEntry("Operis", "Four Winds", 4.2, "");
    private BeerEntry noa = new BeerEntry("Noa", "Omnipollo", 4.4, "Test");
    private BeerEntry magic = new BeerEntry("Magic Lambic", "Cantillion", 4.75, "");

    @BeforeEach
    void runBefore() {
        beerList = new ArrayList<>();
        beerList.add(operis);
        beerList.add(noa);
        beerList.add(magic);
        foundList = new ArrayList<>();
    }

    //  noSort, sortByName, sortByRating

    @Test
    void testLoadPrevious() {
        Random random = new Random();
        int n = random.nextInt();

        assertTrue(loadPrevious(1));
        assertFalse(loadPrevious(3));
        assertFalse(loadPrevious(n));
    }

    @Test
    void testFindBeerName() {
        BeerEntry test = new BeerEntry("Operis", "Test", 0, "");
        beerList.add(test);
        foundList.add(operis);
        foundList.add(test);

        assertEquals(emptyList(), findBeerName("Pliny the Elder", beerList));
        assertEquals(foundList, findBeerName("Operis", beerList));
    }

    @Test
    void testSearchBrewery() {
        foundList.add(magic);

        assertEquals(emptyList(), searchBrewery("Russian River Brewing", beerList));
        assertEquals(foundList, searchBrewery("Cantillion", beerList));
    }

    @Test
    void testSearchRating() {
        foundList.add(magic);
        foundList.add(noa);

        assertEquals(foundList, searchRating(4.3, beerList));
        assertEquals(emptyList(), searchRating(4.8, beerList));
    }

    @Test
    void testPrintOperation() {
        assertEquals("[1] Add a new beer entry", printOperation("1"));
        assertEquals("[2] Search beer list", printOperation("2"));
        assertEquals("[3] View all beers", printOperation("3"));
        assertEquals("[4] Quit", printOperation("4"));
        assertEquals("", printOperation("7"));
    }

    @Test
    void testPrintOperationSearch() {
        assertEquals("[1] Search by beer name", printOperationSearch("1"));
        assertEquals("[2] Search by brewery", printOperationSearch("2"));
        assertEquals("[3] Search by rating", printOperationSearch("3"));
        assertEquals("[4] Return", printOperationSearch("4"));
        assertEquals("", printOperationSearch("8"));
    }

    @Test
    void testPrintOperationView() {
        assertEquals("[1] View default", printOperationView("1"));
        assertEquals("[2] View sorted by name", printOperationView("2"));
        assertEquals("[3] View sorted by rating", printOperationView("3"));
        assertEquals("[4] Return", printOperationView("4"));
        assertEquals("", printOperationView("0"));
    }

    @Test
    void testPrintList() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        printList(beerList);
        assertEquals("Beer: Operis Brewery: Four Winds Rating: 4.20 Comments: \nBeer: Noa Brewery: "
                + "Omnipollo Rating: 4.40 Comments: Test\nBeer: Magic Lambic Brewery: Cantillion "
                + "Rating: 4.75 Comments:", os.toString().trim());
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }
}
