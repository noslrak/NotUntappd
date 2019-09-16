package UI;

import java.util.ArrayList;
import java.util.Scanner;

public class NotUntappd {
    // Initial functionality taken from B04 LoggingCalculator
    private ArrayList<BeerEntry> beerList;
    private Scanner scanner;

    private NotUntappd() {
        beerList = new ArrayList<>();
        scanner = new Scanner(System.in);
        processOperations();
    }

    private void processOperations() {
        String operation;

        while (true) {
            System.out.println("Please select an option: [1] Add a new beer entry [2] Search beer list " +
                    "[3] View all beers [4] Quit");
            operation = scanner.nextLine();
            System.out.println("You have selected: " + printOperation(operation));
            BeerEntry beerEntry = new BeerEntry();
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
        System.out.println("Please enter any comments or leave blank: ");
        scanner.nextLine();
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

    }

    public static void main(String[] args) {
        new NotUntappd();
    }
}
