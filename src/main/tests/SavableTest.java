package tests;

import model.BeerEntry;
import model.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

class SavableTest {
    private Utility utility = new Utility();
    private ArrayList<BeerEntry> beerList;
    private ArrayList<BeerEntry> testList;
    private BeerEntry operis = new BeerEntry("Operis", "Four Winds", 4.2, "");
    private BeerEntry noa = new BeerEntry("Noa", "Omnipollo", 4.4, "Test");
    private BeerEntry magic = new BeerEntry("Magic Lambic", "Cantillion", 4.75, "");

    @BeforeEach
    void runBefore() {
        beerList = new ArrayList<>();
        beerList.add(operis);
        beerList.add(noa);
        beerList.add(magic);
        testList = new ArrayList<>();
    }

    @Test
    void testSave() throws IOException, ClassNotFoundException {
        utility.saveFile(beerList, "testSave");

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("testSave" + ".txt"));
        testList = (ArrayList<BeerEntry>) in.readObject();
        in.close();

        assertEquals(beerList, testList);
    }
}
