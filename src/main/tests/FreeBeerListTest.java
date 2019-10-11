package tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static java.util.Collections.emptyList;
import static model.Utility.*;
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
    void runBefore() {
        beerList = new FreeBeerList();
        beerList.addBeerEntry(operis);
        beerList.addBeerEntry(noa);
        beerList.addBeerEntry(magic);
        testList = new FreeBeerList();
        rand = random.nextInt();
    }

    @Test
    void testIsEmpty() {
        assertFalse(beerList.isEmpty());
        assertTrue(testList.isEmpty());
    }

    @Test
    void testFindBeerName() {
        FreeBeerEntry test = new FreeBeerEntry("Operis", "Test", 0, "");
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
    void testPrintList() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        beerList.printList();
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

        beerList.noSort();
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

        beerList.sortByName();
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

        beerList.sortByRating();
        assertEquals("Sorted by rating: \n"
                + "Beer: Magic Lambic Brewery: Cantillion Rating: 4.75 Comments: \n"
                + "Beer: Noa Brewery: Omnipollo Rating: 4.40 Comments: Test\n"
                + "Beer: Operis Brewery: Four Winds Rating: 4.20 Comments:", os.toString().trim());
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

    /*@Test
    void testLoadFileNotFoundException() throws IOException {
        assertThrows(FileNotFoundException.class, beerList.loadFile("test"));
    }
*/
    /*@Test
    void testSave() throws IOException, ClassNotFoundException {
        beerList.saveFile("testSave");
        String beerString = beerList.getList().toString();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("testSave" + ".txt"));
        testList = (FreeBeerList) in.readObject();
        in.close();
        String testString = testList.getList().toString();
        assertEquals(beerString, testString);
    }*/
}
