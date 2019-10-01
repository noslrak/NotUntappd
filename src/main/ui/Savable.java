package ui;

import model.BeerEntry;

import java.io.IOException;
import java.util.ArrayList;

public interface Savable {
    void save(ArrayList<BeerEntry> beerList);
}
