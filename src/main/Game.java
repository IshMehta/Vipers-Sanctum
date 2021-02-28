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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;



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

    public Stage showConfigScreen(Stage primaryStage) {
        //Primary Border Pane that will be set to the scene
        BorderPane borderPane = new BorderPane();
        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        borderPane.setBackground(background);
        //Creates margins
        Rectangle spacingFooter = new Rectangle();
        spacingFooter.setHeight(10);
        spacingFooter.setWidth(10);
        spacingFooter.setFill(Color.WHITE);
        Rectangle spacingRightFooter = new Rectangle();
        spacingRightFooter.setHeight(10);
        spacingRightFooter.setWidth(10);
        spacingRightFooter.setFill(Color.WHITE);
        Rectangle spacingLeft = new Rectangle();
        spacingLeft.setHeight(25);
        spacingLeft.setWidth(25);
        spacingLeft.setFill(Color.WHITE);
        Rectangle spacingTop = new Rectangle();
        spacingTop.setHeight(25);
        spacingTop.setWidth(25);
        spacingTop.setFill(Color.WHITE);
        // Header Specifications
        Label header = new Label("Configuration Screen");
        Font headerFont = Font.font("Times New Roman", FontWeight.BOLD, 30);
        header.setFont(headerFont);
        header.setTextFill(Color.BLACK);
        //Add header to Border Pane
        VBox headerPane = new VBox(5);
        headerPane.setAlignment(Pos.CENTER);
        headerPane.getChildren().addAll(header, spacingTop);
        borderPane.setTop(headerPane);
        BorderPane.setAlignment(header, Pos.CENTER);
        //Sets up Body Vbox Pane
        VBox bodyPane = new VBox(20);
        //Creates Enter Name Section
        HBox enterNamePane = new HBox(20);
        TextField enterName = new TextField("Enter Name of Player");
        Button submitName = new Button("Submit Name");
        submitName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (enterName.getText() == null || enterName.getText().isBlank() || enterName.getText().isEmpty()) {
                    Alert invalidName = new Alert(Alert.AlertType.WARNING);
                    invalidName.setContentText("Name cannot be empty or only whitespaces");
                    invalidName.setHeaderText("Enter Name");
                    invalidName.setTitle("Invalid Name");
                    invalidName.show();
                } else {
                    Label validName = new Label("Valid name has been entered!");
                    validName.setTextFill(Color.BLACK);
                    Font validFont = new Font("Times New Roman", 15);
                    validName.setFont(validFont);
                    enterNamePane.getChildren().add(validName);
                    String playerName = enterName.getText();
                }
            }
        });
        enterNamePane.getChildren().addAll(enterName, submitName);
        //Creates choose difficulty section
        HBox chooseDifficultyPane = new HBox(20);
        Label chooseDifficulty = new Label("Choose Difficulty Level: ");
        Font chooseFont = Font.font("Times New Roman", 15);
        chooseDifficulty.setFont(chooseFont);
        chooseDifficulty.setTextFill(Color.BLACK);
        RadioButton easy = new RadioButton("Easy");
        RadioButton medium = new RadioButton("Medium");
        RadioButton hard = new RadioButton("Hard");
        ToggleGroup alignDifficulty = new ToggleGroup();
        easy.setToggleGroup(alignDifficulty);
        medium.setToggleGroup(alignDifficulty);
        hard.setToggleGroup(alignDifficulty);
        RadioButton[] radioButtonsDifficulty = {easy, medium, hard};
        //TODO handle when a difficulty level is selected
        chooseDifficultyPane.getChildren().addAll(chooseDifficulty, easy, medium, hard);
        // Creates choose Weapon Section
        HBox chooseWeaponPane = new HBox(20);
        Label chooseWeapon = new Label("Choose Your Weapon: ");
        chooseWeapon.setFont(chooseFont);
        chooseWeapon.setTextFill(Color.BLACK);
        RadioButton knife = new RadioButton("Knife");
        RadioButton maul = new RadioButton("Maul");
        RadioButton sword = new RadioButton("Sword");
        RadioButton bow = new RadioButton("Bow");
        ToggleGroup alignWeapon = new ToggleGroup();
        knife.setToggleGroup(alignWeapon);
        maul.setToggleGroup(alignWeapon);
        sword.setToggleGroup(alignWeapon);
        bow.setToggleGroup(alignWeapon);
        RadioButton[] radioButtonsWeapons = {easy, medium, hard};
        //TODO handle when a weapon is selected
        chooseWeaponPane.getChildren().addAll(chooseWeapon, knife, maul, sword, bow);
        //Adds all body components to bodyPane
        bodyPane.getChildren().addAll(enterNamePane, chooseDifficultyPane, chooseWeaponPane);
        borderPane.setCenter(bodyPane);
        // Creates the button that allows to go to initial game screen
        Button nextScreen = new Button("Next");
        //TODO create an action event handler to go to next page.
        //Adds nextScreen button to footer.
        HBox left = new HBox(5);
        left.getChildren().addAll(spacingLeft);
        borderPane.setLeft(left);
        HBox footerHbox = new HBox(5);
        footerHbox.setAlignment(Pos.CENTER_RIGHT);
        footerHbox.getChildren().addAll(nextScreen, spacingRightFooter);
        VBox footer = new VBox(5);
        footer.setAlignment(Pos.CENTER_RIGHT);
        footer.getChildren().addAll(footerHbox, spacingFooter);
        borderPane.setBottom(footer);
        //Sets the Scene and displays stage
        Scene scene = new Scene(borderPane, 1000, 500);
        primaryStage.setTitle("Config Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}