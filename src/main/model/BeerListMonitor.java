package model;

import java.util.Observable;
import java.util.Observer;

public class BeerListMonitor implements Observer {
    private int checkIns = 0;

    @Override
    public void update(Observable o, Object arg) {
        BeerList beerList = (BeerList) o;
        BeerEntry beerEntry = beerList.getLast();
        System.out.println(beerEntry + " was added");
        checkIns++;
        System.out.println("You have recorded " + checkIns + " beer(s) so far");
    }
}
