package model;

import java.util.Observable;

abstract class BeerList extends Observable implements Loadable, Savable {
    BeerList() {
        addObserver(new BeerListMonitor());
    }

    abstract BeerEntry getLast();
}
