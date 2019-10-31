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
        switch (operation) {
            case 1:
                return "[1] Add a new beer entry";
            case 2:
                return "[2] Search beer list";
            case 3:
                return "[3] View all beers";
            case 4:
                return "[4] Quit";
            default: // do nothing
                return "";
        }
    }

    // REQUIRES: operation to be a numerical string of 1, 2, 3, or 4
    // EFFECTS: prints out operation choice from searchBeerList
    public static String printOperationSearch(int operation) {
        switch (operation) {
            case 1:
                return "[1] Search by beer name";
            case 2:
                return "[2] Search by brewery";
            case 3:
                return "[3] Search by rating";
            case 4:
                return "[4] Return";
            default: // do nothing
                return "";
        }
    }

    // REQUIRES: operation to be a numerical string of 1, 2, 3, or 4
    // EFFECTS: prints out the selected operation from viewBeerList
    public static String printOperationView(int operation) {
        switch (operation) {
            case 1:
                return "[1] View default";
            case 2:
                return "[2] View sorted by name";
            case 3:
                return  "[3] View sorted by rating";
            case 4:
                return "[4] Return";
            default: // do nothing
                return "";
        }
    }
}
