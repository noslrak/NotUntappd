package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Objects;

public abstract class BeerEntry implements Serializable {
    protected static final long serialVersionUID = 1L;
    String beerName;
    String breweryName;
    double beerRating;
    String beerComments;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BeerEntry beerEntry = (BeerEntry) o;
        return beerName.equals(beerEntry.beerName) && breweryName.equals(beerEntry.breweryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beerName, breweryName);
    }
}
