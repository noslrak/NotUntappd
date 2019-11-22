package model;

import model.exceptions.DuplicateEntryException;
import model.exceptions.EmptyListException;
import model.exceptions.MaxSizeException;
import model.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

class FreeBeerListTest {
    private FreeBeerList beerList;
    private FreeBeerList testList;
    private FreeBeerEntry operis = new FreeBeerEntry("Operis", "Four Winds", 4.2, "");
    private FreeBeerEntry noa = new FreeBeerEntry("Noa", "Omnipollo", 4.4, "Test");
    private FreeBeerEntry magic = new FreeBeerEntry("Magic Lambic", "Cantillion", 4.75, "");
    private Random random = new Random();
    private int rand;

    @BeforeEach
    void runBefore() throws MaxSizeException, DuplicateEntryException {
        beerList = new FreeBeerList();
        beerList.addBeerEntry(operis);
        beerList.addBeerEntry(noa);
        beerList.addBeerEntry(magic);
        testList = new FreeBeerList();
        rand = random.nextInt();
    }

    @Test
    void testDuplicateEntry() {
        assertThrows(DuplicateEntryException.class, () -> beerList.addBeerEntry(operis));
    }

    @Test
    void testMaxSizeExceptionFail() throws MaxSizeException, DuplicateEntryException {
        for (int i = 0; i < FreeBeerList.maxEntry; i++) {
            FreeBeerEntry entry = new FreeBeerEntry(Integer.toString(i), "Test", 4.3,"" );
            testList.addBeerEntry(entry);
        }
        assertThrows(MaxSizeException.class, () -> testList.addBeerEntry(magic));
    }

    @Test
    void testMaxSizeExceptionPass() {
        try {
            testList.addBeerEntry(magic);
        } catch (MaxSizeException | DuplicateEntryException e) {
            fail();
        }
    }

    @Test
    void testIsEmpty() {
        assertFalse(beerList.isEmpty());
        assertTrue(testList.isEmpty());
    }

    @Test
    void testFindBeerName() throws MaxSizeException, DuplicateEntryException {
        FreeBeerEntry test = new FreeBeerEntry("Operis", "Test", 0, "");
        beerList.addBeerEntry(test);
        testList.addBeerEntry(operis);
        testList.addBeerEntry(test);

        assertEquals(emptyList(), beerList.findBeerName("Pliny the Elder"));
        assertEquals(testList.getList(), beerList.findBeerName("Operis"));
    }

    @Test
    void testSearchBrewery() throws MaxSizeException, DuplicateEntryException {
        testList.addBeerEntry(magic);

        assertEquals(emptyList(), beerList.searchBrewery("Russian River Brewing"));
        assertEquals(testList.getList(), beerList.searchBrewery("Cantillion"));
    }

    @Test
    void testSearchRating() throws MaxSizeException, DuplicateEntryException {
        testList.addBeerEntry(magic);
        testList.addBeerEntry(noa);

        assertEquals(testList.getList(), beerList.searchRating(4.3));
        assertEquals(emptyList(), beerList.searchRating(4.8));
    }

    @Test
    void testPrintList() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        beerList.printList();
        assertEquals("Beer: Operis Brewery: Four Winds Rating: 4.20 Comments: \r\nBeer: Noa Brewery: "
                + "Omnipollo Rating: 4.40 Comments: Test\r\nBeer: Magic Lambic Brewery: Cantillion "
                + "Rating: 4.75 Comments:", os.toString().trim());
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
                + "Beer: Operis Brewery: Four Winds Rating: 4.20 Comments: \r\nBeer: Noa Brewery: "
                + "Omnipollo Rating: 4.40 Comments: Test\r\nBeer: Magic Lambic Brewery: Cantillion "
                + "Rating: 4.75 Comments:", os.toString().trim());
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
                + "Beer: Magic Lambic Brewery: Cantillion Rating: 4.75 Comments: \r\n"
                + "Beer: Noa Brewery: Omnipollo Rating: 4.40 Comments: Test\r\n"
                + "Beer: Operis Brewery: Four Winds Rating: 4.20 Comments:", os.toString().trim());
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
                + "Beer: Magic Lambic Brewery: Cantillion Rating: 4.75 Comments: \r\n"
                + "Beer: Noa Brewery: Omnipollo Rating: 4.40 Comments: Test\r\n"
                + "Beer: Operis Brewery: Four Winds Rating: 4.20 Comments:", os.toString().trim());
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    @Test
    void testLoad() throws IOException, ClassNotFoundException {
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
    @SuppressWarnings("unchecked")
    void testSave() throws IOException, ClassNotFoundException {
        ArrayList<FreeBeerEntry> loadList;

        beerList.saveFile("testSave");
        String beerString = beerList.getList().toString();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("testSave" + ".txt"));
        loadList = (ArrayList<FreeBeerEntry>) in.readObject();
        in.close();
        String loadString = loadList.toString();
        assertEquals(beerString, loadString);
    }

    @Test
    void testGetLast() {
        assertEquals(magic, beerList.getLast());
    }

    @Test
    void testRemoveByName() {
        try {
            beerList.removeBeerEntry("Noa", "Omnipollo");
            testList.addBeerEntry(operis);
            testList.addBeerEntry(magic);
            String beerString = beerList.getList().toString();
            String testString = testList.getList().toString();
            assertEquals(beerString, testString);
        } catch (EmptyListException | NotFoundException | MaxSizeException | DuplicateEntryException e) {
            fail();
        }
    }

    @Test
    void testRemoveByNameWrongEverything() throws MaxSizeException, DuplicateEntryException {
        assertThrows(NotFoundException.class, () -> beerList.removeBeerEntry("Jock Jams", "Twin Sails Brewing"));
        testList.addBeerEntry(operis);
        testList.addBeerEntry(noa);
        testList.addBeerEntry(magic);
        String beerString = beerList.getList().toString();
        String testString = testList.getList().toString();
        assertEquals(beerString, testString);
    }

    @Test
    void testRemoveByNameWrongName() throws MaxSizeException, DuplicateEntryException {
        assertThrows(NotFoundException.class, () -> beerList.removeBeerEntry("Jock Jams", "Omnipollo"));
        testList.addBeerEntry(operis);
        testList.addBeerEntry(noa);
        testList.addBeerEntry(magic);
        String beerString = beerList.getList().toString();
        String testString = testList.getList().toString();
        assertEquals(beerString, testString);
    }

    @Test
    void testRemoveByNameWrongBrewery() throws MaxSizeException, DuplicateEntryException {
        assertThrows(NotFoundException.class, () -> beerList.removeBeerEntry("Noa", "Twin Sails Brewing"));
        testList.addBeerEntry(operis);
        testList.addBeerEntry(noa);
        testList.addBeerEntry(magic);
        String beerString = beerList.getList().toString();
        String testString = testList.getList().toString();
        assertEquals(beerString, testString);
    }

    @Test
    void testContains() {
        assertTrue(beerList.contains(magic));
        assertFalse(testList.contains(magic));
    }

    @Test
    void testEmptyListException() {
        assertThrows(EmptyListException.class, () -> testList.removeBeerEntry("Operis", "Four Winds"));
    }

    @Test
    void testEqualsSymmetric() {
        FreeBeerEntry one = new FreeBeerEntry("Operis", "Four Winds", 4.2, "");
        assertTrue(one.equals(operis) && operis.equals(one));
        assertEquals(one.hashCode(), operis.hashCode());
        assertFalse(operis.equals(noa) && noa.equals(operis));
        assertNotEquals(operis.hashCode(), noa.hashCode());
        assertFalse(equals(null));
    }
}
