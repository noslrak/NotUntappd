package model;

import model.exceptions.EmptyListException;
import model.exceptions.NotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class PremiumBeerList extends BeerList {
    private ArrayList<PremiumBeerEntry> beerList;

    public PremiumBeerList() {
        super();
        beerList = new ArrayList<>();
    }

    public void addBeerEntry(PremiumBeerEntry beerEntry) {
        beerList.add(beerEntry);
        setChanged();
        notifyObservers();
    }

    public boolean isEmpty() {
        return beerList.isEmpty();
    }

    public PremiumBeerEntry getLast() {
        return beerList.get(beerList.size() - 1);
    }

    // EFFECTS: prints out a given list of beerEntry
    public void printList(ArrayList<PremiumBeerEntry> list) {
        for (PremiumBeerEntry beerEntry : list) {
            System.out.println(beerEntry);
        }
    }

    // EFFECTS: prints out a given list of beerEntry
    void printList() {
        for (PremiumBeerEntry beerEntry : beerList) {
            System.out.println(beerEntry);
        }
    }

    ArrayList<PremiumBeerEntry> getList() {
        return beerList;
    }

    // REQUIRES: beerList is not empty
    // MODIFIES: this
    // EFFECTS: attempts to find name of given beer in beerList
    public ArrayList<PremiumBeerEntry> findBeerName(String name) {
        ArrayList<PremiumBeerEntry> foundList = new ArrayList<>();

        for (PremiumBeerEntry beerEntry : beerList) {
            if (beerEntry.getBeerName().equals(name)) {
                foundList.add(beerEntry);
            }
        }
        foundList.sort(Comparator.comparing(PremiumBeerEntry::getBrewery));
        return foundList;
    }

    // REQUIRES: beerList is not empty
    // MODIFIES: this
    // EFFECTS: takes in a name of a brewery and a beerList, filters out beers from the brewery
    public ArrayList<PremiumBeerEntry> searchBrewery(String brewery) {
        ArrayList<PremiumBeerEntry> foundList = new ArrayList<>();

        for (PremiumBeerEntry beerEntry : beerList) {
            if (beerEntry.getBrewery().equals(brewery)) {
                foundList.add(beerEntry);
            }
        }
        foundList.sort(Comparator.comparing(PremiumBeerEntry::getBeerName));
        return foundList;
    }

    // REQUIRES: beerList is not empty
    // MODIFIES: this
    // EFFECTS: takes in a rating x and a beerList and produces a new list of beers with rating >= x
    public ArrayList<PremiumBeerEntry> searchRating(double rating) {
        ArrayList<PremiumBeerEntry> ratingList = new ArrayList<>();

        for (PremiumBeerEntry beerEntry : beerList) {
            if (beerEntry.getBeerRating() >= rating) {
                ratingList.add(beerEntry);
            }
        }
        ratingList.sort(Comparator.comparing(PremiumBeerEntry::getBeerName));
        return ratingList;
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList in default view
    public void noSort() {
        System.out.println("Default view: ");
        printList(beerList);
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList sorted by name
    public void sortByName() {
        beerList.sort(Comparator.comparing(PremiumBeerEntry::getBeerName));
        System.out.println("Sorted by name: ");
        printList(beerList);
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList sorted by rating, then by name is ratings are equal
    public void sortByRating() {
        beerList.sort(Comparator.comparing(PremiumBeerEntry::getBeerRating).reversed()
                .thenComparing(PremiumBeerEntry::getBeerName));
        System.out.println("Sorted by rating: ");
        printList(beerList);
    }

    // REQUIRES: beerList is not empty
    // MODIFIES: this
    // EFFECTS: takes in a beer style and a beerList and produces a new list of beers with the same style
    public ArrayList<PremiumBeerEntry> searchStyle(String style) {
        ArrayList<PremiumBeerEntry> styleList = new ArrayList<>();

        for (PremiumBeerEntry beerEntry : beerList) {
            if (beerEntry.getBeerStyle().equals(style)) {
                styleList.add(beerEntry);
            }
        }
        styleList.sort(Comparator.comparing(BeerEntry::getBeerName));
        return styleList;
    }

    public void removeBeerEntry(String name, String brewery) throws EmptyListException, NotFoundException {
        boolean found = false;
        if (beerList.isEmpty()) {
            throw new EmptyListException();
        } else {
            for (PremiumBeerEntry beerEntry : beerList) {
                if (beerEntry.getBeerName().equals(name) && beerEntry.getBrewery().equals(brewery)) {
                    beerList.remove(beerEntry);
                    found = true;
                }
            }
        }
        if (!found) {
            throw new NotFoundException();
        }
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
    @SuppressWarnings("unchecked")
    public void loadFile(String name) throws IOException, ClassNotFoundException {
        ArrayList<PremiumBeerEntry> loadList;
        ObjectInputStream in;

        in = new ObjectInputStream(new FileInputStream(name + ".txt"));
        loadList = (ArrayList<PremiumBeerEntry>) in.readObject();

        beerList = loadList;
        in.close();
    }
}
