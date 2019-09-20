package model;

import java.util.Comparator;

class RatingCompare implements Comparator<BeerEntry> {
    public int compare(BeerEntry b1, BeerEntry b2) {
        if (b1.getRating() < b2.getRating()) {
            return -1;
        }
        if (b1.getRating() > b2.getRating()) {
            return 1;
        } else {
            return 0;
        }
    }
}