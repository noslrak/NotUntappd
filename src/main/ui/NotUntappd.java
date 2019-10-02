package ui;

import model.BeerEntry;
import static model.Utility.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NotUntappd implements Loadable, Savable {
    // Initial functionality taken from B04 LoggingCalculator
    private ArrayList<BeerEntry> beerList;
    private Scanner scanner;

    NotUntappd() {
        scanner = new Scanner(System.in);
        int operation;

        System.out.println("Please select an option: [1] Load previous NotUntappd or any other Integer to start new "
                + "NotUntappd");
        operation = scanner.nextInt();
        scanner.nextLine();
        if (loadPrevious(operation)) {
            beerList = load();
        } else {
            beerList = new ArrayList<>();
        }
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
                save(beerList);
                break;
            }
        }
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

    // EFFECTS: retrieve entry of beer name(s), if multiple beers of same name retrieved sort by brewery name
    private void searchBeerName() {
        ArrayList<BeerEntry> nameList;

        System.out.println("Please enter a beer name: ");
        String name = scanner.nextLine();
        nameList = findBeerName(name, beerList);
        printList(nameList);
    }

    // EFFECTS: allows searching for a brewery name and produces a list of beers from the brewery
    private void findBrewery() {
        ArrayList<BeerEntry> foundList;

        System.out.println("Please enter a brewery name: ");
        String brewery = scanner.nextLine();
        foundList = searchBrewery(brewery, beerList);
        System.out.println("Beers from this brewery: ");
        printList(foundList);
    }

    // REQUIRES: beerList is not empty
    // EFFECTS: generates a new list of beers sorted by rating, then by name if rating is tied
    private void filterByRating() {
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
        String operation;

        while (true) {
            System.out.println("Please select an option: [1] View by default [2] View by name "
                    + "[3] View by rating [4] Return");
            operation = scanner.nextLine();
            System.out.println("You have selected: " + printOperationView(operation));
            if (operation.equals("1")) {
                noSort(beerList);
            } else if (operation.equals("2")) {
                sortByName(beerList);
            } else if (operation.equals("3")) {
                sortByRating(beerList);
            } else if (operation.equals("4")) {
                System.out.println("Returning");
                break;
            }
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
