package ui;

import model.BeerEntry;
import model.FreeBeerEntry;
import model.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static model.Utility.*;

abstract class NotUntappd {
    // Initial functionality taken from B04 LoggingCalculator
    ArrayList<BeerEntry> beerList;
    Scanner scanner;
    Utility utility = new Utility();

    // EFFECTS: receives an operation choice and directs to operation
    void processOperations() throws IOException {
        int operation;

        while (true) {
            System.out.println("Please select an option: [1] Add a new beer entry [2] Search beer list "
                    + "[3] View all beers [4] Quit");
            operation = scanner.nextInt();
            scanner.nextLine();
            System.out.println("You have selected: " + printOperation(operation));
            if (operation == 4) {
                toSave();
                System.out.println("Quitting");
                break;
            } else {
                findProcessOperation(operation);
            }
        }
    }

    // REQUIRES: operation to be an int [1,3]
    // EFFECTS: directs operation to proper operation choice
    private void findProcessOperation(int operation) {
        switch (operation) {
            case 1:
                newBeerEntry();
                break;
            case 2:
                searchBeerList();
                break;
            case 3:
                viewBeerList();
                break;
            default: // do nothing
                break;
        }
    }

    private void toSave() throws IOException {
        int operation;

        System.out.println("Would you like to save? [1] Yes [2] No");
        operation = scanner.nextInt();
        scanner.nextLine();
        if (operation == 1) {
            enterFileName();
        }
    }

    private void enterFileName() throws IOException {
        String fileName;

        System.out.println("Please enter a file name");
        fileName = scanner.nextLine();
        utility.saveFile(beerList, fileName);
        System.out.println("File saved as: " + fileName);
    }

    // EFFECTS: creates a new BeerEntry and adds new BeerEntry to beerList
    protected void newBeerEntry() {
        System.out.println("Please enter a beer name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter the name of the brewery: ");
        String brewery = scanner.nextLine();
        System.out.println("Please enter a rating [0.00 - 5.00] for this beer: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Please enter any comments or leave blank: ");
        String comments = scanner.nextLine();
        beerList.add(new FreeBeerEntry(name, brewery, rating, comments));
    }

    // EFFECTS: receives an operation choice and directs to operation
    protected void searchBeerList() {
        int operation;

        while (true) {
            System.out.println("Please select an option: [1] Search by beer name [2] Search by brewery "
                    + "[3] Search by rating [4] Return");
            operation = scanner.nextInt();
            System.out.println("You have selected: " + printOperationSearch(operation));
            if (operation == 4) {
                System.out.println("Returning");
                break;
            } else {
                findSearchOperation(operation);
            }
        }
    }

    // REQUIRES: operation to be an int [1,3]
    // EFFECTS: directs operation to proper operation choice
    protected void findSearchOperation(int operation) {
        switch (operation) {
            case 1:
                searchBeerName();
                break;
            case 2:
                findBrewery();
                break;
            case 3:
                filterByRating();
                break;
            default: // do nothing
                break;
        }
    }

    // EFFECTS: retrieve entry of beer name(s), if multiple beers of same name retrieved sort by brewery name
    void searchBeerName() {
        ArrayList<BeerEntry> nameList;

        System.out.println("Please enter a beer name: ");
        String name = scanner.nextLine();
        nameList = findBeerName(name, beerList);
        printList(nameList);
    }

    // EFFECTS: allows searching for a brewery name and produces a list of beers from the brewery
    void findBrewery() {
        ArrayList<BeerEntry> foundList;

        System.out.println("Please enter a brewery name: ");
        String brewery = scanner.nextLine();
        foundList = searchBrewery(brewery, beerList);
        System.out.println("Beers from this brewery: ");
        printList(foundList);
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: generates a new list of beers sorted by rating, then by name if rating is tied
    void filterByRating() {
        ArrayList<BeerEntry> ratingList;

        System.out.println("Please enter a minimum rating [0.00 - 5.00]: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        ratingList = searchRating(rating, beerList);
        System.out.println("Beers above " + rating + ": ");
        printList(ratingList);
    }

    // REQUIRES: a non-empty BeerList
    // EFFECTS: receives an operation choice and directs to operation
    private void viewBeerList() {
        int operation;

        while (true) {
            if (beerList.isEmpty()) {
                System.out.println("No beers have been entered");
                break;
            } else {
                System.out.println("Please select an option: [1] View by default [2] View by name "
                        + "[3] View by rating [4] Return");
                operation = scanner.nextInt();
                System.out.println("You have selected: " + printOperationView(operation));
                if (operation == 4) {
                    System.out.println("Returning");
                    break;
                } else {
                    findViewOperation(operation);
                }
            }
        }
    }

    // REQUIRES: operation to be an int [1,3]
    // EFFECTS: directs operation to proper operation choice
    private void findViewOperation(int operation) {
        switch (operation) {
            case 1:
                noSort(beerList);
                break;
            case 2:
                sortByName(beerList);
                break;
            case 3:
                sortByRating(beerList);
                break;
            default: // do nothing
                break;
        }
    }

}
