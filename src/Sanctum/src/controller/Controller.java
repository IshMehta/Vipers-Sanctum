package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
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
        startButton.setOnAction(e -> mainWindow.close());
        Button loginButton = welcomeScreen.getLoginButton();
        loginButton.setOnAction(e -> mainWindow.close());

        Scene scene = welcomeScreen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
