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
import javafx.scene.text.Font;
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
        //adds buttons
        welcomeScreenL.getChildren().add(buttonStart);
        configurationScreenL.getChildren().add(buttonEnterDungeon);

        //Room Scene Set Up
        //Borders of the border pane will be Vbox's that house buttons
        VBox leftSide = new VBox();
        VBox rightSide = new VBox();
        VBox upSide = new VBox();
        VBox downSide = new VBox();
        leftSide.setStyle("-fx-background-color: #000000;");
        rightSide.setStyle("-fx-background-color: #000000;");
        upSide.setStyle("-fx-background-color: #000000;");
        downSide.setStyle("-fx-background-color: #000000;");
        //Making the buttons and styling
        Button buttonUp = new Button("Up");
        Button buttonDown = new Button("Down");
        Button buttonRight = new Button("Right");
        Button buttonLeft = new Button("Left");
        buttonUp.setFont(Font.font("Cambria", 32));
        buttonUp.setPrefWidth(128);
        buttonUp.setPrefHeight(72);
        buttonDown.setFont(Font.font("Cambria", 32));
        buttonDown.setPrefWidth(128);
        buttonDown.setPrefHeight(72);
        buttonRight.setFont(Font.font("Cambria", 32));
        buttonRight.setPrefHeight(128);
        buttonRight.setPrefWidth(72);
        buttonLeft.setFont(Font.font("Cambria", 32));
        buttonLeft.setPrefHeight(128);
        buttonLeft.setPrefWidth(72);
        int startingMoney = 0;
        Label moneyStatus = new Label("Money: " + startingMoney);
        moneyStatus.setFont(Font.font("Cambria", 32));
        moneyStatus.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.7),
                new CornerRadii(0.0), new Insets(0.0))));
        //middle view in screen (background and money, basically the main screen that the player looks at)
        StackPane MainView = new StackPane();
        MainView.setAlignment(moneyStatus, Pos.TOP_RIGHT);
        Image background = new Image("file:src/dungeonTest.jpg");
        BackgroundImage dungeonBackground = new BackgroundImage(background,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1200,
                        450,
                        false,
                        false,
                        true,
                        false));
        MainView.setBackground(new Background(dungeonBackground));
        MainView.getChildren().add(moneyStatus);
        upSide.getChildren().add(buttonUp);
        downSide.getChildren().add(buttonDown);
        rightSide.getChildren().add(buttonRight);
        leftSide.getChildren().add(buttonLeft);
        upSide.setAlignment(Pos.CENTER);
        downSide.setAlignment(Pos.CENTER);
        leftSide.setAlignment(Pos.CENTER);
        rightSide.setAlignment(Pos.CENTER);
        roomL.setTop(upSide);
        roomL.setBottom(downSide);
        roomL.setRight(rightSide);
        roomL.setLeft(leftSide);
        roomL.setCenter(MainView);
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}