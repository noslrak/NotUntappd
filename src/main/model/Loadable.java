package model;

import java.io.IOException;
import java.util.ArrayList;

public interface Loadable {
    ArrayList<BeerEntry> loadFile(String name) throws IOException, ClassNotFoundException;
}
