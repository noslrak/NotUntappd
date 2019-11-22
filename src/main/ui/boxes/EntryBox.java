package ui.boxes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class EntryBox {
    GridPane grid = new GridPane();
    Label beerLabel = new Label("Beer*:");
    TextField beerInput = new TextField();
    Label breweryLabel = new Label("Brewery*:");
    TextField breweryInput = new TextField();
    Label styleLabel = new Label("Style: ");
    TextField styleInput = new TextField();
    Label ratingLabel = new Label("Rating* [0.0 - 5.0]:");
    TextField ratingInput = new TextField();
    Label commentLabel = new Label("Comments:");
    TextField commentInput = new TextField();
    Button submit = new Button("Submit");
    Label label;
    int minWidth = 250;
    int minHeight = 300;
    protected Stage window = new Stage();
    boolean bool;
}
