package tests;

import model.BeerEntry;
import model.NotUntappd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class NotUntappdTest {
    public ArrayList<BeerEntry> beerList;
    String one = "1";
    String two = "2";

    @BeforeEach
    public void runBefore() {
        beerList = new ArrayList<>();
    }

    /*@Test
    public void testPrintOperationSearch() {
        assertEquals("[1] Search by beer name", one.printOperationSearch());
    }

    @Test
    public void testPrintOperationView() {

        assertEquals("[2] View sorted by name", two.printOperationView());
    }*/
}
