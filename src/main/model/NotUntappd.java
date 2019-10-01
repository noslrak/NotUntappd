package model;

import ui.Loadable;
import ui.Savable;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class NotUntappd implements Loadable, Savable {
    // Initial functionality taken from B04 LoggingCalculator
    private ArrayList<BeerEntry> beerList;
    private Scanner scanner;

    public NotUntappd() {
        scanner = new Scanner(System.in);
        String operation;

        System.out.println("Please select an option: [1] Start new NotUntappd [2] Load previous NotUntappd");
        operation = scanner.nextLine();
        if (startNew(operation)) {
            beerList = new ArrayList<>();
        } else {
            beerList = load();
        }
        processOperations();
    }

    private boolean startNew(String operation) {
        boolean b = true;

        switch (operation) {
            case "1":
                b = true;
                break;
            case "2":
                b = false;
                break;
            default: // do nothing
                break;
        }
        return b;
    }

    // EFFECTS: receives an operation choice and directs to operation
    private void processOperations() {
        String operation;

        while (true) {
            System.out.println("Please select an option: [1] Add a new beer entry [2] Search beer list "
                    + "[3] View all beers [4] Quit");
            operation = scanner.nextLine();
            System.out.println("You have selected: " + printOperation(operation));
            if (operation.equals("1")) {
                newBeerEntry();
            } else if (operation.equals("2")) {
                searchBeerList();
            } else if (operation.equals("3")) {
                viewBeerList();
            } else if (operation.equals("4")) {
                System.out.println("Quitting");
                save(beerList);
                break;
            }
        }
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

    // EFFECTS: creates a new BeerEntry and adds new BeerEntry to beerList
    private void newBeerEntry() {
        System.out.println("Please enter a beer name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter the name of the brewery: ");
        String brewery = scanner.nextLine();
        System.out.println("Please enter a rating [0.00 - 5.00] for this beer: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Please enter any comments or leave blank: ");
        String comments = scanner.nextLine();
        beerList.add(new BeerEntry(name, brewery, rating, comments));
    }

    // EFFECTS: receives an operation choice and directs to operation
    private void searchBeerList() {
        String operation;

        while (true) {
            System.out.println("Please select an option: [1] Search by beer name [2] Search by brewery "
                    + "[3] Search by rating [4] Return");
            operation = scanner.nextLine();
            System.out.println("You have selected: " + printOperationSearch(operation));
            if (operation.equals("1")) {
                searchBeerName();
            } else if (operation.equals("2")) {
                findBrewery();
            } else if (operation.equals("3")) {
                filterByRating();
            } else if (operation.equals("4")) {
                System.out.println("Returning");
                break;
            }
        }
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

    // EFFECTS: retrieve entry of beer name(s), if multiple beers of same name retrieved sort by brewery name
    private void searchBeerName() {
        ArrayList<BeerEntry> nameList;

        System.out.println("Please enter a beer name: ");
        String name = scanner.nextLine();
        nameList = findBeerName(name);
        printList(nameList);
    }

    // EFFECTS: attempts to find name of given beer in beerList
    private ArrayList<BeerEntry> findBeerName(String name) {
        ArrayList<BeerEntry> foundList = new ArrayList<>();

        for (BeerEntry beerEntry : beerList) {
            if (beerEntry.getBeerName().equals(name)) {
                foundList.add(beerEntry);
            }
        }
        foundList.sort(Comparator.comparing(BeerEntry::getBrewery));
        return foundList;
    }

    // EFFECTS: allows searching for a brewery name and produces a list of beers from the brewery
    private void findBrewery() {
        ArrayList<BeerEntry> foundList;

        System.out.println("Please enter a brewery name: ");
        String brewery = scanner.nextLine();
        foundList = searchBrewery(brewery);
        System.out.println("Beers from this brewery: ");
        printList(foundList);
    }

    // EFFECTS: takes in a name of a brewery and filters out beers from the brewery
    private ArrayList<BeerEntry> searchBrewery(String brewery) {
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
    // EFFECTS: generates a new list of beers sorted by rating, then by name if rating is tied
    private void filterByRating() {
        ArrayList<BeerEntry> ratingList;

        System.out.println("Please enter a minimum rating [0.00 - 5.00]: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        ratingList = searchRating(rating);
        System.out.println("Beers above " + rating + ": ");
        printList(ratingList);
    }

    private ArrayList<BeerEntry> searchRating(double rating) {
        ArrayList<BeerEntry> ratingList = new ArrayList<>();

        for (BeerEntry beerEntry : beerList) {
            if (beerEntry.getBeerRating() >= rating) {
                ratingList.add(beerEntry);
            }
        }
        ratingList.sort(Comparator.comparing(BeerEntry::getBeerName));
        return ratingList;
    }

    // REQUIRES: a non-empty BeerList
    // EFFECTS: receives an operation choice and directs to operation
    private void viewBeerList() {
        String operation;

        while (true) {
            System.out.println("Please select an option: [1] View by default [2] View by name "
                    + "[3] View by rating [4] Return");
            operation = scanner.nextLine();
            System.out.println("You have selected: " + printOperationView(operation));
            if (operation.equals("1")) {
                noSort();
            } else if (operation.equals("2")) {
                sortByName();
            } else if (operation.equals("3")) {
                sortByRating();
            } else if (operation.equals("4")) {
                System.out.println("Returning");
                break;
            }
        }
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

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList in default view
    private void noSort() {
        if (beerList.isEmpty()) {
            System.out.println("No beers entered");
        } else {
            System.out.println("Default view: ");
            printList(beerList);
        }
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList sorted by name
    private void sortByName() {
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
    private void sortByRating() {
        if (beerList.isEmpty()) {
            System.out.println("No beers entered");
        } else {
            beerList.sort(Comparator.comparing(BeerEntry::getBeerRating).reversed()
                    .thenComparing(BeerEntry::getBeerName));
            System.out.println("Sorted by rating");
            printList(beerList);
        }
    }

    // EFFECTS: prints out a given list of beerEntry
    public static void printList(ArrayList<BeerEntry> list) {
        for (BeerEntry beerEntry : list) {
            System.out.println(beerEntry);
        }
    }

    // Load and save adapted from: https://stackoverflow.com/questions/16145682/deserialize-multiple-java-objects and
    //                             https://www.mkyong.com/java/how-to-read-and-write-java-object-to-a-file/
    @Override
    public void save(ArrayList<BeerEntry> beerList) {
        try {
            FileOutputStream fileOut = new FileOutputStream(new File("myObjects.txt"));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(beerList);
            out.close();
            fileOut.close();
        } catch (IOException ex) {
            System.out.println("File unable to be saved");
        }
    }

    @Override
    public ArrayList<BeerEntry> load() {
        ArrayList<BeerEntry> beerList = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("myObjects.txt"));
            beerList = (ArrayList<BeerEntry>) in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return beerList;
    }
}
