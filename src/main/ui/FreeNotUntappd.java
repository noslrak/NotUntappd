package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static model.Utility.loadPrevious;

class FreeNotUntappd extends NotUntappd {
    FreeNotUntappd() throws IOException, ClassNotFoundException {
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
}
