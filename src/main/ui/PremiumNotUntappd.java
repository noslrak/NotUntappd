package ui;

import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static model.Utility.*;

public class PremiumNotUntappd extends NotUntappd {
    private PremiumBeerList beerList;

    PremiumNotUntappd() throws IOException {
        scanner = new Scanner(System.in);
        int operation;
        String fileName;

        System.out.println("Please select an option: [1] Load previous NotUntappd or any other Integer to start new "
                + "NotUntappd");
        operation = scanner.nextInt();
        scanner.nextLine();
        if (loadPrevious(operation)) {
            System.out.println("Please enter the name of previous NotUntappd");
            fileName = scanner.nextLine();
            System.out.println("Loading " + fileName);
            beerList.loadFile(fileName);
        } else {
            System.out.println("Starting new instance of NotUntappd");
            beerList = new PremiumBeerList();
        }
        processOperations();
    }

    @Override
    // EFFECTS: creates a new BeerEntry and adds new BeerEntry to beerList
    protected void newBeerEntry() {
        System.out.println("Please enter a beer name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter the name of the brewery: ");
        String brewery = scanner.nextLine();
        System.out.println("Please enter a beer style");
        String style = scanner.nextLine();
        System.out.println("Please enter a rating [0.00 - 5.00] for this beer: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Please enter any comments or leave blank: ");
        String comments = scanner.nextLine();
        beerList.addBeerEntry(new PremiumBeerEntry(name, brewery, style, rating, comments));
    }

    public void enterFileName() throws IOException {
        String fileName;

        System.out.println("Please enter a file name");
        fileName = scanner.nextLine();
        beerList.saveFile(fileName);
        System.out.println("File saved as: " + fileName);
    }

    @Override
    // EFFECTS: receives an operation choice and directs to operation
    protected void searchBeerList() {
        int operation;

        while (true) {
            System.out.println("Please select an option: [1] Search by beer name [2] Search by brewery "
                    + "[3] Search by rating [4] Search by beer style [5] Return");
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

    @Override
    // REQUIRES: operation to be an int [1,4]
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
            case 4:
                searchByStyle();
                break;
            default: // do nothing
                break;
        }
    }

    private void searchByStyle() {
        ArrayList<PremiumBeerEntry> styleList;

        System.out.println("Please enter a beer style: ");
        String style = scanner.nextLine();
        styleList = beerList.searchStyle(style);
        System.out.println("List of " + style + "s: ");
        printList(styleList);
    }

    // EFFECTS: retrieve entry of beer name(s), if multiple beers of same name retrieved sort by brewery name
    public void searchBeerName() {
        ArrayList<PremiumBeerEntry> nameList;

        System.out.println("Please enter a beer name: ");
        String name = scanner.nextLine();
        nameList = beerList.findBeerName(name);
        printList(nameList);
    }

    private void printList(ArrayList<PremiumBeerEntry> list) {
        for (PremiumBeerEntry beerEntry : list) {
            System.out.println(beerEntry);
        }
    }

    // EFFECTS: allows searching for a brewery name and produces a list of beers from the brewery
    public void findBrewery() {
        ArrayList<PremiumBeerEntry> foundList;

        System.out.println("Please enter a brewery name: ");
        String brewery = scanner.nextLine();
        foundList = beerList.searchBrewery(brewery);
        System.out.println("Beers from this brewery: ");
        printList(foundList);
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: generates a new list of beers sorted by rating, then by name if rating is tied
    public void filterByRating() {
        ArrayList<PremiumBeerEntry> ratingList;

        System.out.println("Please enter a minimum rating [0.00 - 5.00]: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();
        ratingList = beerList.searchRating(rating);
        System.out.println("Beers above " + rating + ": ");
        printList(ratingList);
    }

    // REQUIRES: a non-empty BeerList
    // EFFECTS: receives an operation choice and directs to operation
    public void viewBeerList() throws EmptyListException {
        int operation;

        while (true) {
            if (beerList.isEmpty()) {
                throw new EmptyListException();
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
                beerList.noSort();
                break;
            case 2:
                beerList.sortByName();
                break;
            case 3:
                beerList.sortByRating();
                break;
            default: // do nothing
                break;
        }
    }
}
