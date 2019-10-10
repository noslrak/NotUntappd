package model;

public class PremiumBeerEntry extends BeerEntry {
    private String beerStyle;

    // Constructor
    public PremiumBeerEntry(String name, String brewery, String style, double rating, String comments) {
        this.beerName = name;
        this.breweryName = brewery;
        this.beerStyle = style;
        this.beerRating = rating;
        this.beerComments = comments;
    }

    // EFFECTS: sets beerStyle
    public void setBeerStyle(String style) {
        this.beerStyle = style;
    }

    // EFFECTS: returns beerStyle
    public String getBeerStyle() {
        return beerStyle;
    }

    @Override
    public String toString() {
        return "Beer: " + beerName + " " + "Brewery: " + breweryName + " " + "Style: " + beerStyle + "Rating: "
                + format.format(beerRating) + " " + "Comments: " + beerComments;
    }
}
