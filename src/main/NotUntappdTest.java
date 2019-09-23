import model.BeerEntry;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

public class NotUntappdTest {
    public ArrayList<BeerEntry> beerList;
    BeerEntry blank = new BeerEntry("", "", 0, "");
    BeerEntry operis = new BeerEntry("Operis", "Four Winds", 3.4, "Test");
    BeerEntry pliny = new BeerEntry("Pliny the Elder", "Russian River Brewing", 4.2, "");
    BeerEntry noa = new BeerEntry("Noa", "Omnipollo", 4.6, "Test again");
    BeerEntry consecration = new BeerEntry("Consecration", "Russian River Brewing", 4.5, "");
    BeerEntry spon = new BeerEntry("Spon III", "Jester King", 4.7, "");
    String one = "1";
    String two = "2";

    @BeforeEach
    public void runBefore() {
        beerList = new ArrayList<>();
        beerList.add(operis);
        beerList.add(pliny);
        beerList.add(noa);
        beerList.add(consecration);
        beerList.add(spon);
    }

   /* @Test
    public void testPrintOperationSearch() {
        assertEquals("[1] Search by beer name", one.printOperationSearch());
    }

    @Test
    public void testPrintOperationView() {
        assertEquals("[2] View sorted by name", two.printOperationView());
    }*/
}
