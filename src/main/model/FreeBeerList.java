package model;

import model.exceptions.MaxSizeException;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class FreeBeerList extends BeerList {
    private ArrayList<FreeBeerEntry> beerList = new ArrayList<>();
    public static final int maxEntry = 50;

    public FreeBeerList() {}

    public void addBeerEntry(FreeBeerEntry beerEntry) throws MaxSizeException {
        if (beerList.size() >= maxEntry) {
            throw new MaxSizeException();
        } else {
            beerList.add(beerEntry);
        }
    }

    public boolean isEmpty() {
        return beerList.isEmpty();
    }

    public ArrayList<FreeBeerEntry> getList() {
        return beerList;
    }

    // EFFECTS: prints out a given list of beerEntry
    public void printList() {
        for (FreeBeerEntry beerEntry : beerList) {
            System.out.println(beerEntry);
        }
    }

    // REQUIRES: beerList is not empty
    // MODIFIES: this
    // EFFECTS: attempts to find name of given beer in beerList
    public ArrayList<FreeBeerEntry> findBeerName(String name) {
        ArrayList<FreeBeerEntry> foundList = new ArrayList<>();

        for (FreeBeerEntry beerEntry : beerList) {
            if (beerEntry.getBeerName().equals(name)) {
                foundList.add(beerEntry);
            }
        }
        foundList.sort(Comparator.comparing(FreeBeerEntry::getBrewery));
        return foundList;
    }

    // REQUIRES: beerList is not empty
    // MODIFIES: this
    // EFFECTS: takes in a name of a brewery and a beerList, filters out beers from the brewery
    public ArrayList<FreeBeerEntry> searchBrewery(String brewery) {
        ArrayList<FreeBeerEntry> foundList = new ArrayList<>();

        for (FreeBeerEntry beerEntry : beerList) {
            if (beerEntry.getBrewery().equals(brewery)) {
                foundList.add(beerEntry);
            }
        }
        foundList.sort(Comparator.comparing(FreeBeerEntry::getBeerName));
        return foundList;
    }

    // REQUIRES: beerList is not empty
    // MODIFIES: this
    // EFFECTS: takes in a rating x and a beerList and produces a new list of beers with rating >= x
    public ArrayList<FreeBeerEntry> searchRating(double rating) {
        ArrayList<FreeBeerEntry> ratingList = new ArrayList<>();

        for (FreeBeerEntry beerEntry : beerList) {
            if (beerEntry.getBeerRating() >= rating) {
                ratingList.add(beerEntry);
            }
        }
        ratingList.sort(Comparator.comparing(FreeBeerEntry::getBeerName));
        return ratingList;
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList in default view
    public void noSort() {
        System.out.println("Default view: ");
        printList();
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList sorted by name
    public void sortByName() {
        beerList.sort(Comparator.comparing(FreeBeerEntry::getBeerName));
        System.out.println("Sorted by name: ");
        printList();
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList sorted by rating, then by name is ratings are equal
    public void sortByRating() {
        beerList.sort(Comparator.comparing(FreeBeerEntry::getBeerRating).reversed()
                .thenComparing(FreeBeerEntry::getBeerName));
        System.out.println("Sorted by rating: ");
        printList();
    }

    @Override
    public void saveFile(String name) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(new File(name + ".txt"));
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(beerList);
        out.close();
        fileOut.close();
    }

    @Override
    public void loadFile(String name) throws IOException {
        ArrayList<FreeBeerEntry> loadList = new ArrayList<>();
        ObjectInputStream in = null;
        boolean fileFound = true;

        try {
            in = new ObjectInputStream(new FileInputStream(name + ".txt"));
            loadList = (ArrayList<FreeBeerEntry>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            fileFound = false;
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } finally {
            if (fileFound) {
                in.close();
            }
        }
        beerList = loadList;
    }
}