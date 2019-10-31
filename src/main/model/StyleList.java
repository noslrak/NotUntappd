package model;

import java.util.HashMap;

public class StyleList {
    private HashMap<String, PremiumBeerEntry> map;

    public StyleList() {
        map = new HashMap<>();
    }

    public void addPremiumBeerEntry(PremiumBeerEntry beerEntry) {
        map.put(beerEntry.getBeerStyle(), beerEntry);
    }

    public PremiumBeerEntry searchStyleList(String style) {
        return map.get(style);
    }
}
