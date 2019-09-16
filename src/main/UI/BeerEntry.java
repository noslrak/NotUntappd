package UI;

public class BeerEntry {
    private String beerName;
    private String brewery;
    private float rating;
    private String comments;

    BeerEntry() {
        beerName = "";
        brewery = "";
        rating = 0;
        comments = "";
    }

    void setBeerName(String beerName) {this.beerName = beerName;}

    void setBrewery(String brewery) {this.brewery = brewery; }

    void setRating(float rating) {this.rating = rating;}

    void setComments(String comments) {this.comments = comments;}

    public String toString() {
        return "Beer: " + beerName + " " + "Brewery: " + brewery + " " + "Rating: " + rating + " " + "Comments: "
                + comments;
    }
}
