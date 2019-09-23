package model;

public class BeerEntry {
    private String beerName;
    private String breweryName;
    private double beerRating;
    private String beerComments;

    // Constructor
    public BeerEntry(String name, String brew, double rate, String comm) {
        this.beerName = name;
        this.breweryName = brew;
        this.beerRating = rate;
        this.beerComments = comm;
    }

    // Setters
    // MODIFIES: this
    // EFFECTS: changes beerName to inputted name
    public void setBeerName(String name) {
        this.beerName = name;
    }

    // MODIFIES: this
    // EFFECTS: changes breweryName to inputted brewery
    void setBrewery(String brewery) {
        this.breweryName = brewery;
    }

    // MODIFIES: this
    // EFFECTS: changes beerRating to inputted rating
    void setBeerRating(double rate) {
        this.beerRating = rate;
    }

    // MODIFIES: this
    // EFFECTS: changes comments to inputted comment
    void setBeerComments(String comm) {
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

    public String getBeerComments() {
        return beerComments;
    }

    public String toString() {
        return "Beer: " + beerName + " " + "Brewery: " + breweryName + " " + "Rating: " + beerRating + " "
                + "Comments: " + beerComments;
    }
}


