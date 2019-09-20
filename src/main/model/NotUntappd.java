package model;

import java.util.ArrayList;
import java.util.Collections;
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

    private String printOperation(String operation) {
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

    private void newBeerEntry() {
        System.out.println("Please enter a beer name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter the name of the brewery: ");
        String brewery = scanner.nextLine();
        System.out.println("Please enter a rating for the beer: ");
        float rating = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Please enter any comments or leave blank: ");
        String comments = scanner.nextLine();
        beerList.add(new BeerEntry(name, brewery, rating, comments));
    }

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

    private String printOperationSearch(String operation) {
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

    private void searchBeerName() {
        BeerEntry search;

        System.out.println("Please enter a beer name: ");
        String name = scanner.nextLine();
        for (BeerEntry beerEntry : beerList) {
            if (beerEntry.getBeerName().equals(name)) {
                search = beerEntry;
                System.out.println("Beer: " + search.getBeerName() + " " + "Brewery: " + search.getBrewery() + " "
                        + "Rating: " + search.getRating() + " " + "Comments: " + search.getComments());
            } else {
                System.out.println("Beer not found");
            }
        }
    }

    // attempts to produce a list of beers by a given brewery
    private void findBrewery() {
        ArrayList<BeerEntry> foundList = new ArrayList();

        System.out.println("Please enter a brewery name: ");
        String brewery = scanner.nextLine();
        for (BeerEntry beerEntry : beerList) {
            if (beerEntry.getBrewery().equals(brewery)) {
                foundList.add(beerEntry);
            }
        }
        System.out.println("Beers from this brewery: ");
        for (int i = 0; i < foundList.size(); i++) {
            System.out.println(foundList.get(i));
        }

    }

    // generates a new list of beers sorted by rating
    private void filterByRating() {
        ArrayList<BeerEntry> ratingList = new ArrayList();

        System.out.println("Please enter a minimum rating: ");
        float rating = scanner.nextFloat();
        scanner.nextLine();
        for (BeerEntry beerEntry : beerList) {
            if (beerEntry.getRating() >= rating) {
                ratingList.add(beerEntry);
            }
        }
        System.out.println("Beers above " + rating + ": ");
        for (int i = 0; i < ratingList.size(); i++) {
            System.out.println(ratingList.get(i));
        }
    }

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

    private String printOperationView(String operation) {
        String message = "";

        switch (operation) {
            case "1":
                message = "[1] View by default";
                break;
            case "2":
                message = "[2] View by name";
                break;
            case "3":
                message = "[4] View by rating";
                break;
            case "4":
                message = "[5] Return";
                break;
            default: // do nothing
                break;
        }
        return message;
    }


    private void noSort() {
        System.out.println("Default view: ");
        for (int i = 0; i < beerList.size(); i++) {
            System.out.println(beerList.get(i));
        }
    }

    private void sortByName() {
        Collections.sort(beerList, new NameCompare());
        System.out.println("Sorted by name: ");
        for (int i = 0; i < beerList.size(); i++) {
            System.out.println(beerList.get(i));
        }
    }

    private void sortByRating() {

    }


}
