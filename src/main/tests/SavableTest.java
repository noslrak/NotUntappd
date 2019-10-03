package tests;

import model.BeerEntry;
import model.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String beerString = beerList.toString();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("testSave" + ".txt"));
        testList = (ArrayList<BeerEntry>) in.readObject();
        in.close();
        String testString = testList.toString();
        assertEquals(beerString, testString);
    }
}
