package ui;

import model.BeerEntry;
import model.PremiumBeerEntry;
import model.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static model.Utility.*;

public class PremiumNotUntappd extends NotUntappd {
    private ArrayList<BeerEntry> beerList;

    PremiumNotUntappd() throws IOException, ClassNotFoundException {
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
            beerList = utility.loadFile(fileName);
        } else {
            System.out.println("Starting new instance of NotUntappd");
            beerList = new ArrayList<>();
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
        beerList.add(new PremiumBeerEntry(name, brewery, style, rating, comments));
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
            /*case 4:
                searchByStyle();
                break;*/
            default: // do nothing
                break;
        }
    }

    /*private void searchByStyle() {
        ArrayList<BeerEntry> styleList;

        System.out.println("Please enter a beer style: ");
        String style = scanner.nextLine();
        styleList = searchStyle(style, beerList);
        System.out.println("List of " + style + "s: ");
        printList(styleList);
    }*/
}
