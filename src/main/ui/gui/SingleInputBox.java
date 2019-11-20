package ui.gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;

public class SingleInputBox {
    private static String input;
    private static Stage window = new Stage();
    private static TextField fileNameInput = new TextField();

    public static String display(String title, String message) {
        // Blocks events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        Button closeButton = new Button("Submit");
        closeButton.setOnAction(e -> returnInput());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(fileNameInput, label,closeButton);
        layout.setAlignment(Pos.CENTER);

        // Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return input;
    }

    private static void returnInput() {
        input = fileNameInput.getText();
        window.close();
    }
}