package ui.boxes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;

public class SingleIntInputBox {
    private static double input;
    private static TextField fileNameInput = new TextField();
    private static Stage window = new Stage();

    // EFFECTS: displays a generic Int input box with given title and message
    public double display(String title, String message) {
        // Blocks events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        Button closeButton = new Button("Submit");
        closeButton.setOnAction(e -> checkDouble());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(fileNameInput,label,closeButton);
        layout.setAlignment(Pos.CENTER);

        // Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return input;
    }

    private static void checkDouble() {
        double number;
        try {
            number = Double.parseDouble(fileNameInput.getText());
            if (number < 0.0 || number > 5.0) {
                AlertBox.display("Invalid Rating", "Please input a valid rating");
            } else {
                input = number;
            }
        } catch (Exception e) {
            AlertBox.display("Invalid Rating", "Please input a valid rating");
        }
        window.close();
    }
}