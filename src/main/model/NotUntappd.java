package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class NotUntappd {
    // Initial functionality taken from B04 LoggingCalculator
    private ArrayList<BeerEntry> beerList;
    private Scanner scanner;

    public NotUntappd() {
        beerList = new ArrayList<>();
        scanner = new Scanner(System.in);
        processOperations();
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
                break;
            }
        }
    }

    // REQUIRES: operation to be a numerical string of 1, 2, 3, or 4
    // EFFECTS: prints out operation choice from processOperations
    public String printOperation(String operation) {
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
        BeerEntry beerEntry = new BeerEntry();

        System.out.println("Please enter a beer name: ");
        String name = scanner.nextLine();
        beerEntry.setBeerName(name);
        System.out.println("Please enter the name of the brewery: ");
        String brewery = scanner.nextLine();
        beerEntry.setBrewery(brewery);
        System.out.println("Please enter a rating [0.00 - 5.00] for this beer: ");
        double rating = scanner.nextDouble();
        beerEntry.setBeerRating(rating);
        scanner.nextLine();
        System.out.println("Please enter any comments or leave blank: ");
        String comments = scanner.nextLine();
        beerEntry.setBeerComments(comments);
        beerList.add(beerEntry);
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
    public String printOperationSearch(String operation) {
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

    // EFFECTS: retrieve entry of given beer name(s), if multiple beers of same name retrieved sort by brewery name
    private void searchBeerName() {
        ArrayList<BeerEntry> nameList = new ArrayList<>();

        System.out.println("Please enter a beer name: ");
        String name = scanner.nextLine();
        for (BeerEntry beerEntry : beerList) {
            if (beerEntry.getBeerName().equals(name)) {
                nameList.add(beerEntry);
            }
        }
        nameList.sort(Comparator.comparing(BeerEntry::getBrewery));
        for (BeerEntry beerEntry : nameList) {
            System.out.println(beerEntry);
        }
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: produce a list of beers in alphabetical order by a given brewery
    private void findBrewery() {
        ArrayList<BeerEntry> foundList = new ArrayList<>();

        System.out.println("Please enter a brewery name: ");
        String brewery = scanner.nextLine();
        for (BeerEntry beerEntry : beerList) {
            if (beerEntry.getBrewery().equals(brewery)) {
                foundList.add(beerEntry);
            }
        }
        foundList.sort(Comparator.comparing(BeerEntry::getBeerName));
        System.out.println("Beers from this brewery: ");
        for (BeerEntry beerEntry : foundList) {
            System.out.println(beerEntry);
        }

    }

    // REQUIRES: beerList is not empty
    // EFFECTS: generates a new list of beers sorted by rating, then by name if rating is tied
    private void filterByRating() {
        ArrayList<BeerEntry> ratingList = new ArrayList<>();

        System.out.println("Please enter a minimum rating [0.00 - 5.00]: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        for (BeerEntry beerEntry : beerList) {
            if (beerEntry.getBeerRating() >= rating) {
                ratingList.add(beerEntry);
            }
        }
        ratingList.sort(Comparator.comparing(BeerEntry::getBeerRating).reversed());
        System.out.println("Beers above " + rating + ": ");
        for (BeerEntry beerEntry : ratingList) {
            System.out.println(beerEntry);
        }
    }

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
    public String printOperationView(String operation) {
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
        System.out.println("Default view: ");
        for (BeerEntry beerEntry : beerList) {
            System.out.println(beerEntry);
        }
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList sorted by name
    private void sortByName() {
        beerList.sort(Comparator.comparing(BeerEntry::getBeerName));
        System.out.println("Sorted by name: ");
        for (BeerEntry beerEntry : beerList) {
            System.out.println(beerEntry);
        }
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: prints out beerList sorted by rating, then by name is ratings are equal
    private void sortByRating() {
        beerList.sort(Comparator.comparing(BeerEntry::getBeerRating).reversed().thenComparing(BeerEntry::getBeerName));
        System.out.println("Sorted by rating");
        for (BeerEntry beerEntry : beerList) {
            System.out.println(beerEntry);
        }
    }
}
