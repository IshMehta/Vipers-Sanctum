package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class WinScreen {
    public WinScreen() {

    }
    public Scene getScene() {
        BorderPane mainDisplay = new BorderPane();
        Label winText = new Label("You Won!");
        winText.setFont(Font.font("Cambria", 64));
        mainDisplay.setCenter(winText);
        Scene RoomScene = new Scene(mainDisplay, 1280, 720);
        return RoomScene;
    }
}
