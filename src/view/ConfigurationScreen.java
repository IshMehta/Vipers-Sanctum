package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ConfigurationScreen {

    // Creates the button that allows to go to initial game screen
    Button nextScreen = new Button("Next");
    String weapon;
    String difficulty;
    private ToggleGroup alignWeapon= new ToggleGroup();
    private ToggleGroup alignDifficulty= new ToggleGroup();

    public Scene showConfigScreen() {
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
                    validName.setId("validity");
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
        //alignDifficulty = new ToggleGroup();
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
        //alignWeapon = new ToggleGroup();
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
        return scene;
    }

    public String getWeapon() {
        if (alignWeapon.getSelectedToggle() != null) {
            return ((RadioButton) alignWeapon.getSelectedToggle()).getText();
        }
        return null;
    }

    public String getDifficulty() {
        if (alignDifficulty.getSelectedToggle() != null) {
            return ((RadioButton) alignDifficulty.getSelectedToggle()).getText();
        }
        return null;
    }

    public Button getNextScreenButton() {
        return nextScreen;
    }

}
