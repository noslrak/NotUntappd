package tests;

import model.BeerEntry;
import model.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static java.util.Collections.emptyList;
import static model.Utility.*;
import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {
    private ArrayList<BeerEntry> beerList;
    private ArrayList<BeerEntry> testList;
    private BeerEntry operis = new BeerEntry("Operis", "Four Winds", 4.2, "");
    private BeerEntry noa = new BeerEntry("Noa", "Omnipollo", 4.4, "Test");
    private BeerEntry magic = new BeerEntry("Magic Lambic", "Cantillion", 4.75, "");
    private Random random = new Random();
    private int rand;
    private Utility utility = new Utility();

    @BeforeEach
    void runBefore() {
        beerList = new ArrayList<>();
        beerList.add(operis);
        beerList.add(noa);
        beerList.add(magic);
        testList = new ArrayList<>();
        rand = random.nextInt();
    }

    //  noSort, sortByName, sortByRating

    @Test
    void testLoadPrevious() {
        assertTrue(loadPrevious(1));
        assertFalse(loadPrevious(3));
        assertFalse(loadPrevious(rand));
    }

    @Test
    void testFindBeerName() {
        BeerEntry test = new BeerEntry("Operis", "Test", 0, "");
        beerList.add(test);
        testList.add(operis);
        testList.add(test);

        assertEquals(emptyList(), findBeerName("Pliny the Elder", beerList));
        assertEquals(testList, findBeerName("Operis", beerList));
    }

    @Test
    void testSearchBrewery() {
        testList.add(magic);

        assertEquals(emptyList(), searchBrewery("Russian River Brewing", beerList));
        assertEquals(testList, searchBrewery("Cantillion", beerList));
    }

    @Test
    void testSearchRating() {
        testList.add(magic);
        testList.add(noa);

        assertEquals(testList, searchRating(4.3, beerList));
        assertEquals(emptyList(), searchRating(4.8, beerList));
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

    @Test
    void testNoSortList() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        noSort(beerList);
        assertEquals("Default view: \n"
                + "Beer: Operis Brewery: Four Winds Rating: 4.20 Comments: \nBeer: Noa Brewery: "
                + "Omnipollo Rating: 4.40 Comments: Test\nBeer: Magic Lambic Brewery: Cantillion "
                + "Rating: 4.75 Comments:", os.toString().trim());
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    @Test
    void testSortByName() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        sortByName(beerList);
        assertEquals("Sorted by name: \n"
                + "Beer: Magic Lambic Brewery: Cantillion Rating: 4.75 Comments: \n"
                + "Beer: Noa Brewery: Omnipollo Rating: 4.40 Comments: Test\n"
                + "Beer: Operis Brewery: Four Winds Rating: 4.20 Comments:", os.toString().trim());
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    @Test
    void testSortByRating() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        sortByRating(beerList);
        assertEquals("Sorted by rating: \n"
                + "Beer: Magic Lambic Brewery: Cantillion Rating: 4.75 Comments: \n"
                + "Beer: Noa Brewery: Omnipollo Rating: 4.40 Comments: Test\n"
                + "Beer: Operis Brewery: Four Winds Rating: 4.20 Comments:", os.toString().trim());
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    @Test
    void testLoad() throws IOException, ClassNotFoundException {
        FileOutputStream fileOut = new FileOutputStream(new File("testLoad.txt"));
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(beerList);
        out.close();
        fileOut.close();

        testList = utility.loadFile("testLoad");
        String beerString = beerList.toString();
        String testString = testList.toString();
        assertEquals(beerString, testString);
    }

    @Test
    void testSave() throws IOException, ClassNotFoundException {
        utility.saveFile(beerList, "testSave");
        String beerString = beerList.toString();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("testSave" + ".txt"));
        testList = (ArrayList<BeerEntry>) in.readObject();
        in.close();
        String testString = testList.toString();
        assertEquals(beerString, testString);
    }
}
