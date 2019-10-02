package ui;

import model.BeerEntry;

import java.io.IOException;
import java.util.ArrayList;

public interface Loadable {
    ArrayList<BeerEntry> loadFile(String name);
}
