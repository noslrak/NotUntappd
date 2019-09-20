package model;

import java.util.Comparator;

class NameCompare implements Comparator<BeerEntry> {
    public int compare(BeerEntry b1, BeerEntry b2) {
        return b1.getBeerName().compareTo(b2.getBeerName());
    }
}
