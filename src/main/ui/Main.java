package ui;

import java.io.IOException;
import java.util.Scanner;

import static network.ReadWebPage.welcomeMessage;

public class Main {
    private static String password = "idontremember";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int operation;
        String code;

        welcomeMessage();
        System.out.println("Please select an option: [1] Premium notUntappd or any other Integer for Free notUntappd");
        operation = scanner.nextInt();
        scanner.nextLine();
        if (operation != 1) {
            new FreeNotUntappd();
        } else {
            System.out.println("Please enter verification code");
            code = scanner.nextLine();
            if (code.equals(password)) {
                new PremiumNotUntappd();
            } else {
                System.out.println("Incorrect verification code");
            }
        }
    }
}
