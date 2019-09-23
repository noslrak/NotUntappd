import model.BeerEntry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BeerEntryTest {
    public ArrayList<BeerEntry> beerList;
    BeerEntry blank = new BeerEntry("", "", 0, "");
    BeerEntry operis = new BeerEntry("Operis", "Four Winds", 3.4, "Test");
    BeerEntry pliny = new BeerEntry("Pliny the Elder", "Russian River Brewing", 4.2, "");
    BeerEntry noa = new BeerEntry("Noa", "Omnipollo", 4.6, "Test again");
    BeerEntry consecration = new BeerEntry("Consecration", "Russian River Brewing", 4.5, "");
    BeerEntry spon = new BeerEntry("Spon III", "Jester King", 4.7, "");

    @BeforeEach
    public void runBefore() {
        beerList = new ArrayList<>();
        beerList.add(operis);
        beerList.add(pliny);
        beerList.add(noa);
        beerList.add(consecration);
        beerList.add(spon);
    }

    @Test
    public void testGetBeerName() {
        assertEquals("Operis", operis.getBeerName());
        assertEquals("", blank.getBeerName());
    }

    @Test
    public void testGetBreweryName() {
        assertEquals("Four Winds", operis.getBrewery());
    }

    @Test
    public void testGetRating() {
        assertEquals(4.2, pliny.getBeerRating());
    }

    @Test
    public void testGetComments() {
        assertEquals("", spon.getBeerComments());
    }
}
