import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Dungeon Crawler adventure game.
 *
 * @author Vipers
 *
 */

public class Game extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Dungeon Crawler");
        VBox welcomeScreenL = new VBox();
        VBox configurationScreenL = new VBox();
        BorderPane roomL = new BorderPane();
        Scene welcomeScene = new Scene(welcomeScreenL, 1280, 720);
        Scene configurationScene = new Scene(configurationScreenL, 1280, 720);
        Scene RoomScene = new Scene(roomL, 1280, 720);

        //Buttons for first two pages to link to next
        Button buttonStart = new Button("Start");
        buttonStart.setOnAction(e -> primaryStage.setScene(configurationScene));
        Button buttonEnterDungeon = new Button("Enter the Dungeon");
        buttonEnterDungeon.setOnAction(e -> primaryStage.setScene(RoomScene));
        //adds buttonss
        welcomeScreenL.getChildren().add(buttonStart);
        configurationScreenL.getChildren().add(buttonEnterDungeon);

        //Room Scene Set Up
        Button buttonUp = new Button("Up");
        Button buttonDown = new Button("Down");
        Button buttonRight = new Button("Right");
        Button buttonLeft = new Button("Left");
        int startingMoney = 0;
        Label moneyStatus = new Label("Money: " + startingMoney);
        moneyStatus.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.7),
                new CornerRadii(5.0), new Insets(-5.0))));
        //middle view in screen
        StackPane MainView = new StackPane();
        MainView.setAlignment(moneyStatus, Pos.TOP_RIGHT);
        Image background = new Image("file:src/dungeonTest.jpg");
        BackgroundImage dungeonBackground = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1000,
                        700,
                        false,
                        false,
                        true,
                        true));
        MainView.setBackground(new Background(dungeonBackground));
        MainView.getChildren().add(moneyStatus);
        roomL.setAlignment(buttonUp, Pos.CENTER);
        roomL.setAlignment(buttonDown, Pos.CENTER);
        roomL.setAlignment(buttonLeft, Pos.CENTER);
        roomL.setAlignment(buttonRight, Pos.CENTER);
        roomL.setTop(buttonUp);
        roomL.setBottom(buttonDown);
        roomL.setRight(buttonRight);
        roomL.setLeft(buttonLeft);
        roomL.setCenter(MainView);
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}