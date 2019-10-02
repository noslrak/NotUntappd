package tests;

import model.BeerEntry;
import model.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import java.util.ArrayList;

class NotUntappdTest {
    private ArrayList<BeerEntry> beerList;
    private BeerEntry operis = new BeerEntry("Operis", "Four Winds", 4.2, "");
    private BeerEntry noa = new BeerEntry("Noa", "Omnipollo", 4.4, "Test");

    @BeforeEach
    void runBefore() {
        beerList = new ArrayList<>();
        beerList.add(operis);
        beerList.add(noa);
    }

    @Test
    void testPrintOperation() {
        assertEquals("[4] Quit", Utility.printOperation("4"));
    }

    @Test
    void testPrintOperationSearch() {
        assertEquals("[1] Search by beer name", Utility.printOperationSearch("1"));
    }

    @Test
    void testPrintOperationView() {
        assertEquals("[2] View sorted by name", Utility.printOperationView("2"));
    }

    @Test
    void testPrintList() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        Utility.printList(beerList);
        assertEquals("Beer: Operis Brewery: Four Winds Rating: 4.20 Comments: \nBeer: Noa Brewery: "
                + "Omnipollo Rating: 4.40 Comments: Test", os.toString().trim());
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }
}
