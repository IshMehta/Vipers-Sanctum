package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.ConfigurationScreen;
import view.GameScreen;
import view.WelcomeScreen;


public class Controller extends Application {
    private Stage mainWindow;
    private final int width = 500;
    private final int height = 1000;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainWindow = primaryStage;
        mainWindow.setTitle ("Welcome to the Sanctum!");
        initWelcomeScreen();
    }

    private void initWelcomeScreen() {
        WelcomeScreen welcomeScreen = new WelcomeScreen(width, height);
        Button startButton = welcomeScreen.getStartButton();
        startButton.setOnAction(e -> goToConfigScreen());
        Button quitButton = welcomeScreen.getQuitButton();
        quitButton.setOnAction(e -> mainWindow.close());

        Scene scene = welcomeScreen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();

    }

    private void goToConfigScreen() {
        ConfigurationScreen configScreen = new ConfigurationScreen();
        Button nextScreenButton = configScreen.getNextScreenButton();
        nextScreenButton.setOnAction(e -> goToGameScreen(configScreen.getDifficulty(), configScreen.getWeapon()));
        Scene scene = configScreen.showConfigScreen();
        mainWindow.setScene(scene);
        mainWindow.show();


    }

    private void goToGameScreen(String selectedDifficulty, String selectedWeapons) {
        GameScreen game = new GameScreen(selectedDifficulty, selectedWeapons);
        Scene scene = game.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();


    }



    public static void main(String[] args) {
        launch(args);
    }
}

