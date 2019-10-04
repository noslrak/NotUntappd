package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public final class Utility implements Loadable, Savable {

    public static boolean loadPrevious(int operation) {
        boolean b = true;

        if (operation != 1) {
            b = false;
        }
        return b;
    }

    // REQUIRES: operation to be a numerical string of 1, 2, 3, or 4
    // EFFECTS: prints out operation choice from processOperations
    public static String printOperation(int operation) {
        String message = "";

        switch (operation) {
            case 1:
                message = "[1] Add a new beer entry";
                break;
            case 2:
                message = "[2] Search beer list";
                break;
            case 3:
                message = "[3] View all beers";
                break;
            case 4:
                message = "[4] Quit";
                break;
            default: // do nothing
                break;
        }
        return message;
    }

    // REQUIRES: operation to be a numerical string of 1, 2, 3, or 4
    // EFFECTS: prints out operation choice from searchBeerList
    public static String printOperationSearch(int operation) {
        String message = "";
        switch (operation) {
            case 1:
                message = "[1] Search by beer name";
                break;
            case 2:
                message = "[2] Search by brewery";
                break;
            case 3:
                message = "[3] Search by rating";
                break;
            case 4:
                message = "[4] Return";
                break;
            default: // do nothing
                break;
        }
        return message;
    }

    // REQUIRES: operation to be a numerical string of 1, 2, 3, or 4
    // EFFECTS: prints out the selected operation from viewBeerList
    public static String printOperationView(int operation) {
        String message = "";

        switch (operation) {
            case 1:
                message = "[1] View default";
                break;
            case 2:
                message = "[2] View sorted by name";
                break;
            case 3:
                message = "[3] View sorted by rating";
                break;
            case 4:
                message = "[4] Return";
                break;
            default: // do nothing
                break;
        }
        return message;
    }

    // EFFECTS: prints out a given list of beerEntry
    public static void printList(ArrayList<BeerEntry> list) {
        for (BeerEntry beerEntry : list) {
            System.out.println(beerEntry);
        }
    }

    // REQUIRES: beerList is not empty
    // MODIFIES: this
    // EFFECTS: attempts to find name of given beer in beerList
    public static ArrayList<BeerEntry> findBeerName(String name, ArrayList<BeerEntry> beerList) {
        ArrayList<BeerEntry> foundList = new ArrayList<>();

        for (BeerEntry beerEntry : beerList) {
            if (beerEntry.getBeerName().equals(name)) {
                foundList.add(beerEntry);
            }
        }
        foundList.sort(Comparator.comparing(BeerEntry::getBrewery));
        return foundList;
    }

    // REQUIRES: beerList is not empty
    // MODIFIES: this
    // EFFECTS: takes in a name of a brewery and a beerList, filters out beers from the brewery
    public static ArrayList<BeerEntry> searchBrewery(String brewery, ArrayList<BeerEntry> beerList) {
        ArrayList<BeerEntry> foundList = new ArrayList<>();

        for (BeerEntry beerEntry : beerList) {
            if (beerEntry.getBrewery().equals(brewery)) {
                foundList.add(beerEntry);
            }
        }
        foundList.sort(Comparator.comparing(BeerEntry::getBeerName));
        return foundList;
    }

    // REQUIRES: beerList is not empty
    // MODIFIES: this
    // EFFECTS: takes in a rating x and a beerList and produces a new list of beers with rating >= x
    public static ArrayList<BeerEntry> searchRating(double rating, ArrayList<BeerEntry> beerList) {
        ArrayList<BeerEntry> ratingList = new ArrayList<>();

        for (BeerEntry beerEntry : beerList) {
            if (beerEntry.getBeerRating() >= rating) {
                ratingList.add(beerEntry);
            }
        }
        ratingList.sort(Comparator.comparing(BeerEntry::getBeerName));
        return ratingList;
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList in default view
    public static void noSort(ArrayList<BeerEntry> beerList) {
        System.out.println("Default view: ");
        printList(beerList);
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList sorted by name
    public static void sortByName(ArrayList<BeerEntry> beerList) {
        beerList.sort(Comparator.comparing(BeerEntry::getBeerName));
        System.out.println("Sorted by name: ");
        printList(beerList);
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList sorted by rating, then by name is ratings are equal
    public static void sortByRating(ArrayList<BeerEntry> beerList) {
        beerList.sort(Comparator.comparing(BeerEntry::getBeerRating).reversed()
                .thenComparing(BeerEntry::getBeerName));
        System.out.println("Sorted by rating: ");
        printList(beerList);
    }

    // Load and save adapted from: https://stackoverflow.com/questions/16145682/deserialize-multiple-java-objects and
    //                             https://www.mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
    @Override
    public void saveFile(ArrayList<BeerEntry> beerList, String name) {
        try {
            FileOutputStream fileOut = new FileOutputStream(new File(name + ".txt"));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(beerList);
            out.close();
            fileOut.close();
        } catch (IOException ex) {
            System.out.println("File unable to be saved");
        }
    }

    @Override
    public ArrayList<BeerEntry> loadFile(String name) {
        ArrayList<BeerEntry> beerList = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(name + ".txt"));
            beerList = (ArrayList<BeerEntry>) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return beerList;
    }
}
