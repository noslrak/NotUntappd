package model;

import java.text.DecimalFormat;

public class BeerEntry {
    private String beerName;
    private String breweryName;
    private double beerRating;
    private String beerComments;

    // Constructor
    public BeerEntry() {
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

    DecimalFormat format = new DecimalFormat("##.00");

    public String toString() {
        return "Beer: " + beerName + " " + "Brewery: " + breweryName + " " + "Rating: " + format.format(beerRating)
                + " " + "Comments: " + beerComments;
    }
}


