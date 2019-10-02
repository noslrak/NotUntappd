package tests;

import model.BeerEntry;
import model.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class TestLoadable {
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
    void testLoad() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(new File("test.txt"));
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(beerList);
        out.close();
        fileOut.close();

        testList = utility.loadFile("test");
        assertThat(testList, is(beerList));
    }
}
