package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public final class Utility {

    public static boolean loadPrevious(int operation) {
        return operation == 1;
    }

    // REQUIRES: operation to be a numerical string of 1, 2, 3, or 4
    // EFFECTS: prints out operation choice from processOperations
    public static String printOperation(int operation) {
        String message = "";

        switch (operation) {
            case 1:
                message = "[1] Add a new beer entry";
                break;
            case 2:
                message = "[2] Search beer list";
                break;
            case 3:
                message = "[3] View all beers";
                break;
            case 4:
                message = "[4] Quit";
                break;
            default: // do nothing
                break;
        }
        return message;
    }

    // REQUIRES: operation to be a numerical string of 1, 2, 3, or 4
    // EFFECTS: prints out operation choice from searchBeerList
    public static String printOperationSearch(int operation) {
        String message = "";
        switch (operation) {
            case 1:
                message = "[1] Search by beer name";
                break;
            case 2:
                message = "[2] Search by brewery";
                break;
            case 3:
                message = "[3] Search by rating";
                break;
            case 4:
                message = "[4] Return";
                break;
            default: // do nothing
                break;
        }
        return message;
    }

    // REQUIRES: operation to be a numerical string of 1, 2, 3, or 4
    // EFFECTS: prints out the selected operation from viewBeerList
    public static String printOperationView(int operation) {
        String message = "";

        switch (operation) {
            case 1:
                message = "[1] View default";
                break;
            case 2:
                message = "[2] View sorted by name";
                break;
            case 3:
                message = "[3] View sorted by rating";
                break;
            case 4:
                message = "[4] Return";
                break;
            default: // do nothing
                break;
        }
        return message;
    }
}
