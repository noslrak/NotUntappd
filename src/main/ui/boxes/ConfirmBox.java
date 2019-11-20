package ui.boxes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.scene.*;
import javafx.geometry.*;

public class ConfirmBox {
    private static boolean answer;
    private static Button yesButton = new Button("Yes");
    private static Button noButton = new Button("No");
    private static Stage window = new Stage();

    // EFFECTS: displays a ConfirmBox with given title and message
    public static boolean display(String title, String message) {
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        yesButton.setOnAction(e -> yes());
        noButton.setOnAction(e -> no());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

    private static void yes() {
        answer = false;
        window.close();
    }

    private static void no() {
        answer = false;
        window.close();
    }
}
