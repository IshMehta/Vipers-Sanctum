package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonAppT extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/welcomeScreen.fxml"));
        Parent welcomeScreenParent = loader.load();
        Scene welcomeScreenScene = new Scene(welcomeScreenParent);

        //access the controller and call a method
        WelcomeScreenController controller = loader.getController();
        controller.initData(1);

        //This line gets the Stage information
        primaryStage.setScene(welcomeScreenScene);
        primaryStage.show();
    }
}
