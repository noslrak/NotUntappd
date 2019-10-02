package model;

import java.util.ArrayList;
import java.util.Comparator;

public final class Utility {

    public static boolean loadPrevious(int operation) {
        boolean b = true;

        if (operation != 1) {
            b = false;
        }
        return b;
    }

    // REQUIRES: operation to be a numerical string of 1, 2, 3, or 4
    // EFFECTS: prints out operation choice from processOperations
    public static String printOperation(String operation) {
        String message = "";

        switch (operation) {
            case "1":
                message = "[1] Add a new beer entry";
                break;
            case "2":
                message = "[2] Search beer list";
                break;
            case "3":
                message = "[3] View all beers";
                break;
            case "4":
                message = "[4] Quit";
                break;
            default: // do nothing
                break;
        }
        return message;
    }

    // REQUIRES: operation to be a numerical string of 1, 2, 3, or 4
    // EFFECTS: prints out operation choice from searchBeerList
    public static String printOperationSearch(String operation) {
        String message = "";
        switch (operation) {
            case "1":
                message = "[1] Search by beer name";
                break;
            case "2":
                message = "[2] Search by brewery";
                break;
            case "3":
                message = "[3] Search by rating";
                break;
            case "4":
                message = "[4] Return";
                break;
            default: // do nothing
                break;
        }
        return message;
    }

    // REQUIRES: operation to be a numerical string of 1, 2, 3, or 4
    // EFFECTS: prints out the selected operation from viewBeerList
    public static String printOperationView(String operation) {
        String message = "";

        switch (operation) {
            case "1":
                message = "[1] View default";
                break;
            case "2":
                message = "[2] View sorted by name";
                break;
            case "3":
                message = "[4] View sorted by rating";
                break;
            case "4":
                message = "[5] Return";
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

    // EFFECTS: takes in a name of a brewery and filters out beers from the brewery
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
        if (beerList.isEmpty()) {
            System.out.println("No beers entered");
        } else {
            System.out.println("Default view: ");
            printList(beerList);
        }
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList sorted by name
    public static void sortByName(ArrayList<BeerEntry> beerList) {
        if (beerList.isEmpty()) {
            System.out.println("No beers entered");
        } else {
            beerList.sort(Comparator.comparing(BeerEntry::getBeerName));
            System.out.println("Sorted by name: ");
            printList(beerList);
        }
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList sorted by rating, then by name is ratings are equal
    public static void sortByRating(ArrayList<BeerEntry> beerList) {
        if (beerList.isEmpty()) {
            System.out.println("No beers entered");
        } else {
            beerList.sort(Comparator.comparing(BeerEntry::getBeerRating).reversed()
                    .thenComparing(BeerEntry::getBeerName));
            System.out.println("Sorted by rating");
            printList(beerList);
        }
    }
}
