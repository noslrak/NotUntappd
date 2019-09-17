package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class NotUntappd {
    // Initial functionality taken from B04 LoggingCalculator
    private ArrayList<BeerEntry> beerList;
    private Scanner scanner;

    NotUntappd() {
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
                System.out.println("Current beer list: " + beerList);
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
        BeerEntry beerEntry = new BeerEntry();

        System.out.println("Please enter a beer name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter the name of the brewery: ");
        String brewery = scanner.nextLine();
        System.out.println("Please enter a rating for the beer: ");
        float rating = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Please enter any comments or leave blank: ");
        String comments = scanner.nextLine();
        addBeer(beerEntry, name, brewery, rating, comments);
    }

    private void addBeer(BeerEntry beerEntry, String name, String brewery, float rating, String comments) {
        beerEntry.setBeerName(name);
        beerEntry.setBrewery(brewery);
        beerEntry.setRating(rating);
        beerEntry.setComments(comments);
        beerList.add(beerEntry);
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
                sortByRating();
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
        BeerEntry search = new BeerEntry();

        while (true) {
            System.out.println("Please enter a beer name: ");
            String name = scanner.nextLine();
            for (BeerEntry beerEntry : beerList) {
                if (beerEntry.getBeerName().equals(name)) {
                    search = beerEntry;
                    System.out.println("Beer: " + search.getBeerName() + " " + "Brewery: " + search.getBrewery() + " "
                            + "Rating: " + search.getRating() + " " + "Comments: " + search.getComments());
                    break;
                }
            }
            if (search.getBeerName() == "") {
                System.out.println("Beer not found");
                break;
            }
        }
    }

    // attempts to produce a list of beers by a given brewery
    private void findBrewery() {
        ArrayList<BeerEntry> foundList = new ArrayList();

        while (true) {
            System.out.println("Please enter a brewery name");
            String brewery = scanner.nextLine();
            for (BeerEntry beerEntry : beerList) {
                if (beerEntry.getBrewery().equals(brewery)) {
                    foundList.add(beerEntry);
                }
            }
            System.out.println("Beers from this brewery: " + foundList);
            break;
        }
    }

    // generates a new list of beers sorted by rating
    private ArrayList sortByRating() {
        return null;
    }
}
