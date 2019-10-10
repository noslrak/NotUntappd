package model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class FreeBeerEntry extends BeerEntry {
    // Constructor
    public FreeBeerEntry(String name, String brewery, double rating, String comments) {
        this.beerName = name;
        this.breweryName = brewery;
        this.beerRating = rating;
        this.beerComments = comments;
    }
}


