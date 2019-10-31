package model;

import java.util.ArrayList;
import java.util.List;

public class ListOfStyles {
    private ArrayList<PremiumBeerEntry> beerList;

    public ListOfStyles() {
        beerList = new ArrayList<>();
    }

    public void addBeerEntry(PremiumBeerEntry beerEntry) {
        if (!beerList.contains(beerEntry)) {
            beerList.add(beerEntry);
            beerEntry.addStyle(this);
        }
    }
}
