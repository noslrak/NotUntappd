package ui;

import model.BeerEntry;

import java.io.IOException;
import java.util.ArrayList;

public interface Savable {
    void saveFile(ArrayList<BeerEntry> beerList, String name);
}
