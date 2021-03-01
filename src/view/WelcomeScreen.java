package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class WelcomeScreen {
    private int width;
    private int height;
    private Button startButton;
    private Button quitButton;

    private WelcomeScreen() {}
    public WelcomeScreen(int width, int height){
        this.width = width;
        this.height = height;
        startButton = new Button("Play");
        quitButton = new Button("Quit");
    }

    public Scene getScene() {
        Label label = new Label("The Viper's Sanctum!");
        label.getStyleClass().add("statusText");
        VBox buttons = new VBox(startButton, quitButton);
        buttons.getStyleClass().add("buttons");
        VBox vbox = new VBox(label, buttons);
        vbox.getStyleClass().add("vbox");
        Scene scene = new Scene(vbox, width, height);
        scene.getStylesheets().add("file:resources/css/WelcomeScreen.css");
        return scene;
    }

    public Button getStartButton() {
        return startButton;
    }


    public Button getQuitButton() {
        return quitButton;
    }
}