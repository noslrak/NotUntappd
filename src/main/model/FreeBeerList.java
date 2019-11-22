package model;

import model.exceptions.EmptyListException;
import model.exceptions.MaxSizeException;
import model.exceptions.NotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class FreeBeerList extends BeerList {
    private ArrayList<FreeBeerEntry> beerList;
    static final int maxEntry = 50;

    // Constructor
    public FreeBeerList() {
        super();
        beerList = new ArrayList<>();
    }

    // REQUIRES: beerList has less than maxEntry entries
    // MODIFIES: this
    // EFFECTS: takes in a FreeBeerEntry and adds it to beerList, notifies observer on add
    public void addBeerEntry(FreeBeerEntry beerEntry) throws MaxSizeException {
        if (beerList.size() >= maxEntry) {
            throw new MaxSizeException();
        } else {
            beerList.add(beerEntry);
            setChanged();
            notifyObservers();
        }
    }

    // EFFECTS: returns true if a given FreeBeerEntry is already in beerList
    public boolean contains(FreeBeerEntry beerEntry) {
        return beerList.contains(beerEntry);
    }

    // EFFECTS: returns the last entry of beerList
    public FreeBeerEntry getLast() {
        return beerList.get(beerList.size() - 1);
    }

    // EFFECTS: returns size of beerList
    @Override
    public int getSize() {
        return beerList.size();
    }

    // EFFECTS: returns true if beerList is empty
    boolean isEmpty() {
        return beerList.isEmpty();
    }

    // EFFECTS: returns beerList
    ArrayList<FreeBeerEntry> getList() {
        return beerList;
    }

    // EFFECTS: prints out a given list of beerEntry
    void printList() {
        for (FreeBeerEntry beerEntry : beerList) {
            System.out.println(beerEntry);
        }
    }

    // EFFECTS: prints out a given list of beerEntry
    public void printList(ArrayList<FreeBeerEntry> list) {
        for (FreeBeerEntry beerEntry : list) {
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
    // EFFECTS: takes in a name of a brewery filters out beers from the brewery
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
    // EFFECTS: takes in a rating x and produces a new list of beers with rating >= x
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
        ArrayList<FreeBeerEntry> toBeSorted = beerList;

        toBeSorted.sort(Comparator.comparing(FreeBeerEntry::getBeerName));
        System.out.println("Sorted by name: ");
        printList(toBeSorted);
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList sorted by rating, then by name is ratings are equal
    public void sortByRating() {
        ArrayList<FreeBeerEntry> toBeSorted = beerList;

        toBeSorted.sort(Comparator.comparing(FreeBeerEntry::getBeerRating).reversed()
                .thenComparing(FreeBeerEntry::getBeerName));
        System.out.println("Sorted by rating: ");
        printList(toBeSorted);
    }

    // MODIFIES: this
    // EFFECTS: saves beerList as file with given String name
    @Override
    public void saveFile(String name) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(new File(name + ".txt"));
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(beerList);
        out.close();
        fileOut.close();
    }

    // MODIFIES: this
    // EFFECTS: attempts to load a beerList from a file with given String name
    @Override
    @SuppressWarnings("unchecked")
    public void loadFile(String name) throws IOException, ClassNotFoundException {
        ArrayList<FreeBeerEntry> loadList;
        ObjectInputStream in;

        in = new ObjectInputStream(new FileInputStream(name + ".txt"));
        loadList = (ArrayList<FreeBeerEntry>) in.readObject();
        beerList = loadList;
        in.close();
    }

    public void removeBeerEntry(String name, String brewery) throws EmptyListException, NotFoundException {
        ArrayList<FreeBeerEntry> removeList = new ArrayList<>();

        boolean found = false;
        if (beerList.isEmpty()) {
            throw new EmptyListException();
        } else {
            for (FreeBeerEntry beerEntry : beerList) {
                if (beerEntry.getBeerName().equals(name) && beerEntry.getBrewery().equals(brewery)) {
                    removeList.add(beerEntry);
                    found = true;
                }
            }
        }
        beerList.removeAll(removeList);
        if (!found) {
            throw new NotFoundException();
        }
    }
}
