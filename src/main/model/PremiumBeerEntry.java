package model;

import java.util.ArrayList;
import java.util.Objects;

public class PremiumBeerEntry extends BeerEntry {
    private String beerStyle;
    private ArrayList<ListOfStyles> styleList;

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
        return "Beer: " + beerName + " " + "Brewery: " + breweryName + " " + "Style: " + beerStyle + " Rating: "
                + format.format(beerRating) + " " + "Comments: " + beerComments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PremiumBeerEntry that = (PremiumBeerEntry) o;
        return beerStyle.equals(that.beerStyle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beerStyle);
    }

    public void addStyle(ListOfStyles styles) {
        if (styleList.contains(styles)) {
            styleList.add(styles);
            styles.addBeerEntry(this);
        }
    }
}
