package UI;

public class BeerEntry {
    private String beerName;
    private String brewery;
    private float rating;
    private String comments;

    public BeerEntry() {
        beerName = "";
        brewery = "";
        rating = 0;
        comments = "";
    }

    public void setBeerName(String beerName) {this.beerName = beerName;}

    public void setBrewery(String brewery) {this.brewery = brewery; }

    public void setRating(float rating) {this.rating = rating;}

    public void setComments(String comments) {this.comments = comments;}

    public String toString() {
        return "Beer: " + beerName + "Brewery: " + brewery + "Rating: " + rating + " " + "Comments: " + comments;
    }

}
