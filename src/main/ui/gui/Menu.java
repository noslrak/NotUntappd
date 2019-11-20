package ui.gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import model.FreeBeerEntry;
import model.FreeBeerList;
import model.PremiumBeerEntry;
import model.PremiumBeerList;
import model.exceptions.EmptyListException;
import model.exceptions.NotFoundException;
import ui.Main;
import ui.boxes.*;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Menu {
    private BorderPane borderPane = new BorderPane();
    private PremiumBeerList premiumBeerList;
    private FreeBeerList freeBeerList;
    private final int buttonWidth = 150;
    private boolean premium = false;
    private Region spacer = new Region();
    private Button quitButton = new Button("Exit");
    private TextArea textArea = new TextArea();
    private Button loadButton = new Button("Load");
    private Button saveButton = new Button("Save");
    private Button clearButton = new Button("Clear");
    private Button returnButton = new Button("Return");

    public Menu() {
        VBox.setVgrow(spacer, Priority.ALWAYS);
        initializeButtons();
        initializeOutput();
        initializeBorderPane();
    }

    public void quit() {
        boolean bool = ConfirmBox.display("", "Would you like to save before quitting?");
        if (bool) {
            initializeSave();
            Main.window.close();
        } else {
            Main.window.close();
        }
    }

    private void initializeOutput() {
        textArea = new TextArea();
        textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new TextAreaPrintStream(textArea));
        PrintStream standardOut = System.out;

        System.setOut(printStream);
        System.setOut(printStream);
    }

    private void initializeBorderPane() {
        borderPane.setCenter(textArea);
        borderPane.setRight(firstRightMenu());
    }

    private void initializeButtons() {
        loadButton.setOnAction(e -> initializeLoad());
        saveButton.setOnAction(e -> initializeSave());
        clearButton.setOnAction(e -> clearArea());
        quitButton.setOnAction(e -> quit());
        returnButton.setOnAction(e -> borderPane.setRight(thirdRightMenu()));
        loadButton.setMinWidth(buttonWidth);
        saveButton.setMinWidth(buttonWidth);
        clearButton.setMinWidth(buttonWidth);
        quitButton.setMinWidth(buttonWidth);
        returnButton.setMinWidth(buttonWidth);
    }

    private void initializeLoad() {
        String fileName;
        fileName = SingleInputBox.display("Load Previous NotUntappd", "Please enter file name");
        try {
            if (premium) {
                premiumBeerList = new PremiumBeerList();
                premiumBeerList.loadFile(fileName);
            } else {
                freeBeerList = new FreeBeerList();
                freeBeerList.loadFile(fileName);
            }
        } catch (IOException | ClassNotFoundException e) {
            AlertBox.display("File Not Found", "Please enter a valid file name or start a new NotUntappd");
        }
        System.out.println(fileName + " successfully loaded");
        borderPane.setRight(thirdRightMenu());
    }

    private void initializeSave() {
        String fileName;
        fileName = SingleInputBox.display("Save Current NotUntappd", "Please enter file name");
        if (premiumBeerList != null && freeBeerList != null) {
            try {
                if (premium) {
                    premiumBeerList.saveFile(fileName);
                } else {
                    freeBeerList.saveFile(fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            AlertBox.display("No Instance Found", "Please create a new NotUntappd");
        }
    }

    private void clearArea() {
        textArea.setText("");
    }

    private VBox firstRightMenu() {
        VBox vbox = new VBox();

        vbox.setPrefWidth(buttonWidth);
        Button freeButton = new Button("Free");
        Button premiumButton = new Button("Premium");
        freeButton.setOnAction(e -> {
            System.out.println("Welcome to the free version of NotUntappd");
            premium = false;
            borderPane.setRight(secondRightMenu());
        });
        premiumButton.setOnAction(e -> confirmPremiumAccess());
        freeButton.setMinWidth(buttonWidth);
        premiumButton.setMinWidth(buttonWidth);
        vbox.getChildren().addAll(freeButton, premiumButton, spacer,quitButton);
        return vbox;
    }

    private void confirmPremiumAccess() {
        boolean confirmed = LogInBox.display("Log-In", "Please enter credentials");
        if (confirmed) {
            System.out.println("Welcome to the premium version of NotUntappd");
            premium = true;
            borderPane.setRight(secondRightMenu());
        } else {
            AlertBox.display("Access Denied", "Incorrect credentials");
        }
    }

    private VBox secondRightMenu() {
        VBox vbox = new VBox();

        vbox.setPrefWidth(buttonWidth);
        Button newButton = new Button("New");
        newButton.setOnAction(e -> instantiateNewNotUntappd());
        newButton.setMinWidth(buttonWidth);
        vbox.getChildren().addAll(clearButton, loadButton,saveButton,newButton,spacer,quitButton);
        return vbox;
    }

    private void instantiateNewNotUntappd() {
        if (premium) {
            premiumBeerList = new PremiumBeerList();
        } else {
            freeBeerList = new FreeBeerList();
        }
        borderPane.setRight(thirdRightMenu());
    }

    private VBox thirdRightMenu() {
        VBox vbox = new VBox();

        vbox.setPrefWidth(buttonWidth);
        Button addButton = new Button("New Entry");
        addButton.setMinWidth(buttonWidth);
        addButton.setOnAction(e -> addNewEntry());
        Button searchButton = new Button("Search Entries");
        searchButton.setMinWidth(buttonWidth);
        searchButton.setOnAction(e -> borderPane.setRight(searchMenu()));
        Button viewButton = new Button("View Current Entries");
        Button removeButton = new Button("Remove an Entry");
        removeButton.setMinWidth(buttonWidth);
        removeButton.setOnAction(e -> removeEntry());
        viewButton.setMinWidth(buttonWidth);
        viewButton.setOnAction(e -> borderPane.setRight(viewMenu()));
        vbox.getChildren().addAll(clearButton,loadButton,saveButton,addButton,searchButton,viewButton, removeButton,
                spacer, quitButton);
        return vbox;
    }

    private void removeEntry() {
        ArrayList<String> nameAndBrewery = RemoveBox.display("Remove an Entry", "Please fill in the above details"
                + "\n * = Mandatory fields");
        String beerName = nameAndBrewery.get(0);
        String breweryName = nameAndBrewery.get(1);
        try {
            if (premium) {
                premiumBeerList.removeBeerEntry(beerName, breweryName);
            } else {
                freeBeerList.removeBeerEntry(beerName, breweryName);
            }
        } catch (EmptyListException e) {
            AlertBox.display("No Entries Found", "Please add entries before attempting to remove");
        } catch (NotFoundException e) {
            AlertBox.display("No Entry Found", "No such entry found, unable to remove");
        }
    }

    private void addNewEntry() {
        if (premium) {
            addPremiumEntry();
        } else {
            addFreeEntry();
        }
    }

    private void addPremiumEntry() {
        PremiumEntryBox premiumEntryBox = new PremiumEntryBox();
        premiumBeerList = premiumEntryBox.display("Create New Entry", "Please fill in the above details"
                + "\n * = Mandatory fields", premiumBeerList);
    }

    private void addFreeEntry() {
        FreeEntryBox freeEntryBox = new FreeEntryBox();
        freeBeerList = freeEntryBox.display("Create New Entry", "Please fill in the above details"
                + "\n * = Mandatory fields", freeBeerList);
    }

    private VBox searchMenu() {
        System.out.println("Please select an option to search/filter by");
        Button searchBeer = new Button("Search by Beer Name");
        searchBeer.setMinWidth(buttonWidth);
        searchBeer.setOnAction(e -> searchBeer());
        Button searchBrewery = new Button("Search by Brewery");
        searchBrewery.setMinWidth(buttonWidth);
        searchBrewery.setOnAction(e -> searchBrewery());
        Button searchStyle = new Button("Filter by Style");
        searchStyle.setMinWidth(buttonWidth);
        searchStyle.setOnAction(e -> searchStyle());
        Button filterRating = new Button("Filter by Rating");
        filterRating.setMinWidth(buttonWidth);
        filterRating.setOnAction(e -> filterRating());
        return setSearchMenuButtons(searchBeer, searchBrewery, searchStyle, filterRating);
    }

    private VBox setSearchMenuButtons(Button beer, Button brewery, Button style, Button rating) {
        VBox vbox = new VBox();

        if (premium) {
            vbox.getChildren().addAll(clearButton,loadButton,saveButton, beer, brewery, style, rating, spacer,
                    returnButton,quitButton);
        } else {
            vbox.getChildren().addAll(clearButton,loadButton,saveButton, beer, brewery, rating, spacer,
                    returnButton,quitButton);
        }
        return vbox;
    }

    private void searchBeer() {
        String string = SingleInputBox.display("Search by Beer Name", "Please enter a beer name");
        if (premium) {
            ArrayList<PremiumBeerEntry> found = premiumBeerList.findBeerName(string);
            if (found.isEmpty()) {
                System.out.println("No entries by this name found");
            } else {
                premiumBeerList.printList(found);
            }
        } else {
            ArrayList<FreeBeerEntry> found = freeBeerList.findBeerName(string);
            if (found.isEmpty()) {
                System.out.println("No entries by this name found");
            } else {
                freeBeerList.printList(found);
            }
        }
    }

    private void searchBrewery() {
        String string = SingleInputBox.display("Search by Brewery Name", "Please enter a brewery name");
        if (premium) {
            ArrayList<PremiumBeerEntry> found = premiumBeerList.searchBrewery(string);
            if (found.isEmpty()) {
                System.out.println("No entries by this name found");
            } else {
                premiumBeerList.printList(found);
            }
        } else {
            ArrayList<FreeBeerEntry> found = freeBeerList.searchBrewery(string);
            if (found.isEmpty()) {
                System.out.println("No entries by this name found");
            } else {
                freeBeerList.printList(found);
            }
        }
    }

    private void searchStyle() {
        String string = SingleInputBox.display("Search by Brewery Name", "Please enter a brewery name");

        ArrayList<PremiumBeerEntry> found = premiumBeerList.searchStyle(string);
        if (found.isEmpty()) {
            System.out.println("No entries by this name found");
        } else {
            premiumBeerList.printList(found);
        }

    }

    private void filterRating() {
        double rating = SingleIntInputBox.display("Search by Brewery Name", "Please enter a brewery name");
        if (premium) {
            ArrayList<PremiumBeerEntry> found = premiumBeerList.searchRating(rating);
            if (found.isEmpty()) {
                System.out.println("No entries by this name found");
            } else {
                premiumBeerList.printList(found);
            }
        } else {
            ArrayList<FreeBeerEntry> found = freeBeerList.searchRating(rating);
            if (found.isEmpty()) {
                System.out.println("No entries by this name found");
            } else {
                freeBeerList.printList(found);
            }
        }
    }

    private VBox viewMenu() {
        VBox vbox = new VBox();

        System.out.println("Select a method to view recorded entries");
        Button defaultButton = new Button("View by Default");
        defaultButton.setMinWidth(buttonWidth);
        defaultButton.setOnAction(e -> defaultView());
        Button beerViewButton = new Button("View by Beer Name");
        beerViewButton.setMinWidth(buttonWidth);
        defaultButton.setOnAction(e -> viewByBeer());
        Button ratingViewButton = new Button("View by Rating");
        ratingViewButton.setMinWidth(buttonWidth);
        ratingViewButton.setOnAction(e -> ratingView());
        vbox.getChildren().addAll(clearButton, loadButton,saveButton,defaultButton,beerViewButton,ratingViewButton,
                spacer,returnButton,quitButton);
        return vbox;
    }

    private void defaultView() {
        if (premium) {
            premiumBeerList.noSort();
        } else {
            freeBeerList.noSort();
        }
    }

    private void viewByBeer() {
        if (premium) {
            premiumBeerList.sortByName();
        } else {
            freeBeerList.sortByName();
        }
    }

    private void beerView() {
        if (premium) {
            premiumBeerList.sortByName();
        } else {
            freeBeerList.sortByName();
        }
    }

    private void ratingView() {
        if (premium) {
            premiumBeerList.sortByRating();
        } else {
            freeBeerList.sortByRating();
        }
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }
}
