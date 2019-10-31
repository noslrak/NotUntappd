package ui;

import model.BeerEntry;
import model.FreeBeerEntry;
import model.Utility;
import model.exceptions.EmptyListException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static model.Utility.*;

abstract class NotUntappd {
    // Initial functionality taken from B04 LoggingCalculator
    Scanner scanner;

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
                try {
                    viewBeerList();
                } catch (EmptyListException e) {
                    System.out.println("No beers have been entered");
                }
                break;
            default: // do nothing
                break;
        }
    }

    protected abstract void viewBeerList() throws EmptyListException, EmptyListException;

    protected abstract void newBeerEntry();

    protected void toSave() throws IOException {
        int operation;

        System.out.println("Would you like to save? [1] Yes [2] No");
        operation = scanner.nextInt();
        scanner.nextLine();
        if (operation == 1) {
            enterFileName();
        }
    }

    protected abstract void enterFileName() throws IOException;

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

    protected abstract void filterByRating();

    protected abstract void findBrewery();

    protected abstract void searchBeerName();
}
