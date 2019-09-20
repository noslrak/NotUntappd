package model;

import java.util.Comparator;

public class BeerEntry {
    private String beerName;
    private String brewery;
    private float rating;
    private String comments;

    // Constructor
    public BeerEntry(String name, String brew, float rate, String comm) {
        this.beerName = name;
        this.brewery = brew;
        this.rating = rate;
        this.comments = comm;
    }

    // Getters
    String getBeerName() {
        return beerName;
    }

    String getBrewery() {
        return brewery;
    }

    float getRating() {
        return rating;
    }

    String getComments() {
        return comments;
    }

    public String toString() {
        return "Beer: " + beerName + " " + "Brewery: " + brewery + " " + "Rating: " + rating + " " + "Comments: "
                + comments;
    }
}


