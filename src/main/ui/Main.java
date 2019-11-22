package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import model.exceptions.EmptyListException;
import model.exceptions.NotFoundException;
import ui.boxes.*;
import ui.gui.Menu;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Main extends Application {
    public static Stage window;
    private Menu menu;

    public static void main(String[] args) {
        launch(args);
    }

    // EFFECTS: sets up application window
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("NotUntappd");

        menu = new Menu();

        window.setOnCloseRequest(e -> {
            e.consume();
            menu.quit();
        });

        BorderPane borderPane = menu.getBorderPane();
        Scene scene = new Scene(borderPane, 550, 400);
        window.setScene(scene);
        window.show();
        System.out.println("Please select Free or Premium version on the right");
    }
}
