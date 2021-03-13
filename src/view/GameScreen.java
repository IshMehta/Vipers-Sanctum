package view;

import javafx.scene.shape.Rectangle;
import controller.Controller;
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

public class GameScreen {

//    private int width;
//    private int height;
//
//    private GameScreen() {}
//    public GameScreen(int width, int height) {
//        this.width = width;
//        this.height = height;
//    }
    private final String difficulty;
    private String weapon;
    private Button buttonUp = new Button("Up");;
    private Button buttonDown = new Button("Down");;
    private Button buttonLeft = new Button("L");;
    private Button buttonRight = new Button("R");;
    private int room;

    public GameScreen(String selectedDifficulty, String selectedWeapons, int roomIndex) {
        difficulty = selectedDifficulty;
        weapon = selectedWeapons;
        room = roomIndex;
    }

    public Scene getScene() {

        BorderPane roomL = new BorderPane();
        Scene RoomScene = new Scene(roomL, 1280, 720);


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
        if (difficulty.equals("Easy")) {
            startingMoney = 10;
        }
        else if (difficulty.equals("Medium")){
            startingMoney = 8;
        } else {
            startingMoney = 5;
        }
        Label currentRoom = new Label("Room: " + room);
        Label moneyStatus = new Label("Money: " + startingMoney);
        moneyStatus.setId("moneyStatus");
        Label weaponStatus = new Label("Weapon: " + weapon);
        weaponStatus.setId("weaponStatus");
        weaponStatus.setFont(Font.font("Cambria", 32));
        moneyStatus.setFont(Font.font("Cambria", 32));
        currentRoom.setFont(Font.font("Cambria", 32));
        moneyStatus.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.7),
                new CornerRadii(0.0), new Insets(0.0))));
        weaponStatus.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.7),
                new CornerRadii(0.0), new Insets(0.0))));
        currentRoom.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.7),
                new CornerRadii(0.0), new Insets(0.0))));
        //middle view in screen (background and money, basically the main screen that the player looks at)
        StackPane MainView = new StackPane();
        MainView.setAlignment(moneyStatus, Pos.TOP_RIGHT);
        MainView.setAlignment(weaponStatus, Pos.TOP_LEFT);
        MainView.setAlignment(currentRoom, Pos.TOP_CENTER);
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
        MainView.getChildren().addAll(moneyStatus, weaponStatus, currentRoom);
        if (room != 9) {
            upSide.getChildren().add(buttonUp);
            downSide.getChildren().add(buttonDown);
            rightSide.getChildren().add(buttonRight);
            leftSide.getChildren().add(buttonLeft);
        } else {
            rightSide.getChildren().add(buttonRight);
            Rectangle fillerUp = new Rectangle();
            Rectangle fillerDown = new Rectangle();
            Rectangle fillerLeft = new Rectangle();
            fillerUp.setWidth(128);
            fillerUp.setHeight(72);
            fillerDown.setWidth(128);
            fillerDown.setHeight(72);
            fillerLeft.setHeight(128);
            fillerLeft.setWidth(72);
            fillerUp.setFill(Color.BLACK);
            fillerDown.setFill(Color.BLACK);
            fillerLeft.setFill(Color.BLACK);
            downSide.getChildren().add(fillerDown);
            upSide.getChildren().add(fillerUp);
            leftSide.getChildren().add(fillerLeft);
        }
        upSide.setAlignment(Pos.CENTER);
        downSide.setAlignment(Pos.CENTER);
        leftSide.setAlignment(Pos.CENTER);
        rightSide.setAlignment(Pos.CENTER);
        roomL.setTop(upSide);
        roomL.setBottom(downSide);
        roomL.setRight(rightSide);
        roomL.setLeft(leftSide);
        roomL.setCenter(MainView);


        return RoomScene;



    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getWeapon() {
        return weapon;
    }

    public Button getButtonUp() {
        return buttonUp;
    }

    public Button getButtonDown() {
        return buttonDown;
    }

    public Button getButtonLeft() {
        return buttonLeft;
    }

    public Button getButtonRight() {
        return buttonRight;
    }
}
