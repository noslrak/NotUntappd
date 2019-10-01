package model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class BeerEntry implements Serializable {
    private static final long serialVersionUID = 1L;
    private String beerName;
    private String breweryName;
    private double beerRating;
    private String beerComments;

    // Constructor
    public BeerEntry(String name, String brewery, double rating, String comments) {
        this.beerName = name;
        this.breweryName = brewery;
        this.beerRating = rating;
        this.beerComments = comments;
    }

    // Setters
    // MODIFIES: this
    // EFFECTS: changes beerName to inputted name
    public void setBeerName(String name) {
        this.beerName = name;
    }

    // MODIFIES: this
    // EFFECTS: changes breweryName to inputted brewery
    public void setBrewery(String brewery) {
        this.breweryName = brewery;
    }

    // MODIFIES: this
    // EFFECTS: changes beerRating to inputted rating
    public void setBeerRating(double rate) {
        this.beerRating = rate;
    }

    // MODIFIES: this
    // EFFECTS: changes comments to inputted comment
    public void setBeerComments(String comm) {
        this.beerComments = comm;
    }

    // Getters
    // EFFECTS: returns the beer name
    public String getBeerName() {
        return beerName;
    }

    // EFFECTS: returns the brewery name
    public String getBrewery() {
        return breweryName;
    }

    // EFFECTS: returns the beer rating
    public double getBeerRating() {
        return beerRating;
    }

    // EFFECTS: returns the beer comment
    public String getBeerComments() {
        return beerComments;
    }

    private DecimalFormat format = new DecimalFormat("##.00");

    public String toString() {
        return "Beer: " + beerName + " " + "Brewery: " + breweryName + " " + "Rating: " + format.format(beerRating)
                + " " + "Comments: " + beerComments;
    }
}


