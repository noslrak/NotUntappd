package ui.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import model.FreeBeerEntry;

public class FreeEntryBox {
    private static GridPane grid = new GridPane();
    private static Label beerLabel = new Label("Beer*:");
    private static TextField beerInput = new TextField();
    private static Label breweryLabel = new Label("Brewery*:");
    private static TextField breweryInput = new TextField();
    private static Label ratingLabel = new Label("Rating [0.0 - 5.0]");
    private static TextField ratingInput = new TextField();
    private static Label commentLabel = new Label("Comments:");
    private static TextField commentInput = new TextField();
    private static Button submit = new Button("Submit");
    private static Label label;
    private static FreeBeerEntry entry;
    private static Stage window = new Stage();

    public static FreeBeerEntry display(String title, String message) {
        label = new Label(message);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        initializeGrid();
        setConstraints();
        submit.setOnAction(e -> checkAndSubmit());

        grid.getChildren().addAll(beerLabel, beerInput, breweryLabel, breweryInput,
                ratingLabel, ratingInput, commentLabel, commentInput, label, submit);

        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.show();
        return entry;
    }

    private static void initializeGrid() {
        grid.setPadding(new Insets(10, 10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
    }

    private static void setConstraints() {
        GridPane.setConstraints(beerLabel,0,0);
        GridPane.setConstraints(beerInput, 1,0);
        GridPane.setConstraints(breweryLabel,0,1);
        GridPane.setConstraints(breweryInput, 1,1);
        GridPane.setConstraints(ratingLabel,0,2);
        GridPane.setConstraints(ratingInput,1,2);
        GridPane.setConstraints(commentLabel,0,3);
        GridPane.setConstraints(commentInput,1,3);
        GridPane.setConstraints(submit,1,4);
        GridPane.setConstraints(label,0,6);
    }

    private static void checkAndSubmit() {
        double rating = 0.0;
        try {
            rating = Double.parseDouble(ratingInput.getText());
        } catch (Exception e) {
            AlertBox.display("Invalid Rating", "Please input a valid rating");
        }
        if (beerInput.getText().equals("") | breweryInput.getText().equals("")) {
            AlertBox.display("Missing Information", "Please enter the missing information");
        } else if (rating < 0.0 || rating > 5.0) {
            AlertBox.display("Invalid Rating", "Please input a valid rating");
        } else {
            entry = new FreeBeerEntry(beerInput.getText(), breweryInput.getText(), rating,
                    commentInput.getText());
        }
        window.close();
    }
}
