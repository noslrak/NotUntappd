package ui.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;

public class LogInBox {
    private static boolean giveAccess;
    private static String password = "cpsc210";
    private static Stage window = new Stage();
    private static TextField userInput = new TextField();
    private static PasswordField passwordInput = new PasswordField();

    public static boolean display(String title, String message) {
        // Blocks events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        userInput.setPromptText("Username");
        passwordInput.setPromptText("Password");

        Label label = new Label();
        label.setText(message);

        Button closeButton = new Button("Submit");
        closeButton.setOnAction(e -> confirmPassword(passwordInput.getText()));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(userInput,passwordInput,label,closeButton);
        layout.setAlignment(Pos.CENTER);

        // Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return giveAccess;
    }

    private static void confirmPassword(String pass) {
        giveAccess = password.equals(pass);
        window.close();
    }
}
