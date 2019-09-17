package ui;

public class BeerEntry {
    private String beerName;
    private String brewery;
    private float rating;
    private String comments;

    // Constructor
    BeerEntry() {
        beerName = "";
        brewery = "";
        rating = 0;
        comments = "";
    }

    // Setters
    void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    void setRating(float rating) {
        this.rating = rating;
    }

    void setComments(String comments) {
        this.comments = comments;
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
