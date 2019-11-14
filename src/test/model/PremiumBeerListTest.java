package model;

import model.exceptions.EmptyListException;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

class PremiumBeerListTest {
    private PremiumBeerList beerList;
    private PremiumBeerList testList;
    private PremiumBeerEntry operis = new PremiumBeerEntry("Operis", "Four Winds", "Sour",4.2, "");
    private PremiumBeerEntry noa = new PremiumBeerEntry("Noa", "Omnipollo", "Stout",4.4, "Test");
    private PremiumBeerEntry magic = new PremiumBeerEntry("Magic Lambic", "Cantillion", "Lambic",  4.75, "");

    @BeforeEach
    void runBefore() {
        beerList = new PremiumBeerList();
        beerList.addBeerEntry(operis);
        beerList.addBeerEntry(noa);
        beerList.addBeerEntry(magic);
        testList = new PremiumBeerList();
    }

    @Test
    void testIsEmpty() {
        assertFalse(beerList.isEmpty());
        assertTrue(testList.isEmpty());
    }

    @Test
    void testFindBeerName() {
        PremiumBeerEntry test = new PremiumBeerEntry("Operis", "Test", "Sour",0, "");
        beerList.addBeerEntry(test);
        testList.addBeerEntry(operis);
        testList.addBeerEntry(test);

        assertEquals(emptyList(), beerList.findBeerName("Pliny the Elder"));
        assertEquals(testList.getList(), beerList.findBeerName("Operis"));
    }

    @Test
    void testSearchBrewery() {
        testList.addBeerEntry(magic);

        assertEquals(emptyList(), beerList.searchBrewery("Russian River Brewing"));
        assertEquals(testList.getList(), beerList.searchBrewery("Cantillion"));
    }

    @Test
    void testSearchRating() {
        testList.addBeerEntry(magic);
        testList.addBeerEntry(noa);

        assertEquals(testList.getList(), beerList.searchRating(4.3));
        assertEquals(emptyList(), beerList.searchRating(4.8));
    }

    @Test
    void testSearchStyle() {
        testList.addBeerEntry(magic);

        assertEquals(testList.getList(), beerList.searchStyle("Lambic"));
        assertEquals(emptyList(), beerList.searchStyle("IPA"));
    }

    @Test
    void testPrintList() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        beerList.printList();
        assertEquals("Beer: Operis Brewery: Four Winds Style: Sour Rating: 4.20 Comments: \r\n"
                + "Beer: Noa Brewery: Omnipollo Style: Stout Rating: 4.40 Comments: Test\r\nBeer: Magic Lambic Brewery: "
                + "Cantillion Style: Lambic Rating: 4.75 Comments:", os.toString().trim());
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    @Test
    void testNoSortList() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        beerList.noSort();
        assertEquals("Default view: \r\n"
                + "Beer: Operis Brewery: Four Winds Style: Sour Rating: 4.20 Comments: \r\nBeer: Noa Brewery: "
                + "Omnipollo Style: Stout Rating: 4.40 Comments: Test\r\nBeer: Magic Lambic Brewery: Cantillion "
                + "Style: Lambic Rating: 4.75 Comments:", os.toString().trim());
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    @Test
    void testSortByName() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        beerList.sortByName();
        assertEquals("Sorted by name: \r\n"
                + "Beer: Magic Lambic Brewery: Cantillion Style: Lambic Rating: 4.75 Comments: \r\n"
                + "Beer: Noa Brewery: Omnipollo Style: Stout Rating: 4.40 Comments: Test\r\n"
                + "Beer: Operis Brewery: Four Winds Style: Sour Rating: 4.20 Comments:", os.toString().trim());
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    @Test
    void testSortByRating() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        beerList.sortByRating();
        assertEquals("Sorted by rating: \r\n"
                + "Beer: Magic Lambic Brewery: Cantillion Style: Lambic Rating: 4.75 Comments: \r\n"
                + "Beer: Noa Brewery: Omnipollo Style: Stout Rating: 4.40 Comments: Test\r\n"
                + "Beer: Operis Brewery: Four Winds Style: Sour Rating: 4.20 Comments:", os.toString().trim());
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    @Test
    void testLoad() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(new File("testLoad.txt"));
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(beerList.getList());
        out.close();
        fileOut.close();

        testList.loadFile("testLoad");
        String beerString = beerList.getList().toString();
        String testString = testList.getList().toString();
        assertEquals(beerString, testString);
    }

    @Test
    void testSave() throws IOException, ClassNotFoundException {
        ArrayList<PremiumBeerEntry> loadList;

        beerList.saveFile("testSave");
        String beerString = beerList.getList().toString();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("testSave" + ".txt"));
        loadList = (ArrayList<PremiumBeerEntry>) in.readObject();
        in.close();
        String loadString = loadList.toString();
        assertEquals(beerString, loadString);
    }

    @Test
    void testEmptyListException() {
        assertThrows(EmptyListException.class, () -> testList.removeBeerEntry("Operis", "Four Winds"));
    }

    @Test
    void testRemoveByName() {
        try {
            beerList.removeBeerEntry("Noa", "Omnipollo");
        } catch (EmptyListException e) {
            fail();
        }
    }

    @Test
    void testGetLast() {
        assertEquals(magic, beerList.getLast());
    }
}

