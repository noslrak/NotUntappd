package ui.boxes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import model.FreeBeerEntry;
import model.FreeBeerList;
import model.exceptions.DuplicateEntryException;
import model.exceptions.MaxSizeException;

public class FreeEntryBox extends EntryBox {
    private FreeBeerList list = new FreeBeerList();

    // EFFECTS: display an EntryBox for FreeBeerEntry
    public FreeBeerList display(String title, String message, FreeBeerList list) {
        label = new Label(message);
        this.list = list;

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(minWidth);
        window.setMinHeight(minHeight);

        initializeGrid();
        setConstraints();
        submit.setOnAction(e -> checkAndSubmit());

        grid.getChildren().addAll(beerLabel, beerInput, breweryLabel, breweryInput,
                ratingLabel, ratingInput, commentLabel, commentInput, label, submit);

        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.show();
        return list;
    }

    private void initializeGrid() {
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
    }

    private void setConstraints() {
        GridPane.setConstraints(beerLabel, 0, 0);
        GridPane.setConstraints(beerInput, 1, 0);
        GridPane.setConstraints(breweryLabel, 0, 1);
        GridPane.setConstraints(breweryInput, 1, 1);
        GridPane.setConstraints(ratingLabel, 0, 2);
        GridPane.setConstraints(ratingInput, 1, 2);
        GridPane.setConstraints(commentLabel, 0, 3);
        GridPane.setConstraints(commentInput, 1, 3);
        GridPane.setConstraints(submit, 1, 4);
        GridPane.setConstraints(label, 0, 6, 2, 2);
    }

    private void checkAndSubmit() {
        double rating = 0.0;

        try {
            rating = Double.parseDouble(ratingInput.getText());
            valid = true;
        } catch (Exception e) {
            AlertBox.display("Invalid Rating", "Please input a valid rating");
        }
        if (beerInput.getText().equals("") | breweryInput.getText().equals("")) {
            AlertBox.display("Missing Information", "Please enter the missing information");
        } else if (rating < 0.0 || rating > 5.0) {
            AlertBox.display("Invalid Rating", "Please input a valid rating");
        } else if (valid) {
            FreeBeerEntry entry = new FreeBeerEntry(beerInput.getText(), breweryInput.getText(), rating,
                    commentInput.getText());
            attemptAdd(entry);
        }
    }

    private void attemptAdd(FreeBeerEntry entry) {
        try {
            list.addBeerEntry(entry);
            window.close();
        } catch (MaxSizeException e) {
            AlertBox.display("Max Entries Reached", "Please use Premium version for unlimited entries");
        } catch (DuplicateEntryException e) {
            AlertBox.display("Duplicate Entry", "This entry has already been recorded");
        }
    }
}
