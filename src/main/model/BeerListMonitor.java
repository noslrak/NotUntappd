package model;

import java.util.Observable;
import java.util.Observer;

public class BeerListMonitor implements Observer {
    // MODIFIES: this
    // EFFECTS: prints out to console with Observer is updated and keeps track of check-in number
    @Override
    public void update(Observable o, Object arg) {
        BeerList beerList = (BeerList) o;
        BeerEntry beerEntry = beerList.getLast();
        System.out.println(beerEntry + " was added");
        System.out.println("You have recorded " + beerList.getSize() + " beer(s) so far");
    }
}
