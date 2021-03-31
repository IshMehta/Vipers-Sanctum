package view;

import components.Monster;
import components.Player;
import controller.Controller;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
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
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class GameScreen {

    private final String difficulty;
    private String weapon;
    private Button buttonUp = new Button("Up");
    private Button buttonDown = new Button("Down");
    private Button buttonLeft = new Button("L");
    private Button buttonRight = new Button("R");
    private Button buttonAttack = new Button("Attack");
    private Button buttonRetreat = new Button("Retreat");
    private Button buttonRestart = new Button("Restart");
    private Button buttonConfirmKill = new Button("Confirm the Kill");
    private int room;
    private Monster currMonster;
    private Label monsterLabel = new Label();
    private Label playerLabel = new Label();
    private int attackindex = 0;
    private Player player;
    private BorderPane roomL;
    private boolean monsterDefeated;

    public GameScreen(String selectedDifficulty, String selectedWeapons, int roomIndex, Player playerIn, boolean MonsterDefeated) {
        difficulty = selectedDifficulty;
        weapon = selectedWeapons;
        room = roomIndex;
        currMonster = new Monster(room);
        player = playerIn;
        buttonRestart.setVisible(false);
        monsterDefeated = MonsterDefeated;
    }

    public Scene getScene() {

        roomL = new BorderPane();
        Scene roomScene = new Scene(roomL, 1280, 720);

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
        buttonStyling();

        int startingMoney = 0;
        if (difficulty.equals("Easy")) {
            startingMoney = 10;
        } else if (difficulty.equals("Medium")) {
            startingMoney = 8;
        } else {
            startingMoney = 5;
        }
        Label currentRoom = new Label("Room: " + room);
        currentRoom.setId("roomStatus");
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

        //player setting
        setPlayer();
        //monster setting
        setMonster();
        //animations setting
        setAnimations(true, true, false, false);

        //attack functions
        buttonAttack.setOnAction(e -> attackMonster());

        //middle view in screen (background and money,
        // basically the main screen that the player looks at)
        StackPane mainView = new StackPane();
        mainView.setAlignment(moneyStatus, Pos.TOP_RIGHT);
        mainView.setAlignment(weaponStatus, Pos.TOP_LEFT);
        mainView.setAlignment(currentRoom, Pos.TOP_CENTER);
        mainView.setAlignment(monsterLabel, Pos.CENTER_RIGHT);
        mainView.setAlignment(playerLabel, Pos.CENTER_LEFT);
        mainView.setAlignment(buttonAttack, Pos.BOTTOM_RIGHT);
        mainView.setAlignment(buttonRetreat, Pos. BOTTOM_LEFT);
        mainView.setAlignment(buttonConfirmKill, Pos.CENTER);
        StackPane.setMargin(monsterLabel, new Insets(20, 20, 20, 20));
        StackPane.setMargin(playerLabel, new Insets(20, 20, 20, 20));
        Image background = new Image("dungeonTest.jpg");
        BackgroundImage dungeonBackground = new BackgroundImage(background,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1200, 450, false, false, true, false));
        mainView.setBackground(new Background(dungeonBackground));
        mainView.getChildren().addAll(moneyStatus, weaponStatus, currentRoom, monsterLabel, playerLabel, buttonAttack, buttonRetreat, buttonConfirmKill);
        buttonChecker();
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
        roomL.setCenter(mainView);

        return roomScene;

    }

    private void buttonStyling() {
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
        buttonAttack.setFont(Font.font("Cambria", 20));
        buttonAttack.setPrefWidth(128);
        buttonAttack.setPrefHeight(72);
        buttonRetreat.setFont(Font.font("Cambria", 20));
        buttonRetreat.setPrefWidth(128);
        buttonRetreat.setPrefHeight(72);
        buttonConfirmKill.setFont(Font.font("Cambria", 20));
        buttonConfirmKill.setPrefWidth(300);
        buttonConfirmKill.setPrefHeight(72);
    }

    private void buttonChecker() {
        if (room <= 1) {
            buttonAttack.setDisable(true);
            buttonAttack.setVisible(false);
            buttonRetreat.setVisible(false);
            buttonConfirmKill.setVisible(false);
        } else {
            if (!monsterDefeated) {
                buttonAttack.setVisible(true);
                buttonAttack.setDisable(false);
                buttonUp.setVisible(false);
                buttonDown.setVisible(false);
                buttonRight.setVisible(false);
                buttonLeft.setVisible(false);
                buttonRetreat.setVisible(true);
                buttonConfirmKill.setVisible(false);
            } else {
                buttonAttack.setVisible(false);
                buttonAttack.setDisable(true);
                buttonUp.setVisible(true);
                buttonDown.setVisible(true);
                buttonRight.setVisible(true);
                buttonLeft.setVisible(true);
                buttonRetreat.setVisible(false);
                buttonConfirmKill.setVisible(true);
            }
        }
        if (room == 9) {
            if (!monsterDefeated) {
                buttonAttack.setVisible(true);
                buttonAttack.setDisable(false);
                buttonUp.setVisible(false);
                buttonDown.setVisible(false);
                buttonRight.setVisible(false);
                buttonLeft.setVisible(false);
                buttonRetreat.setVisible(true);
            } else {
                buttonAttack.setVisible(false);
                buttonAttack.setDisable(true);
                buttonUp.setVisible(false);
                buttonDown.setVisible(false);
                buttonRight.setVisible(true);
                buttonLeft.setVisible(false);
                buttonRetreat.setVisible(false);
            }
        }
    }

    private void attackMonster() {
        int attackdmg;
        double randomGen = Math.random();
        switch (weapon) {
        case "Knife":
            attackdmg = 5;
            currMonster.setMonsterHP(currMonster.getMonsterHP() - attackdmg);
            break;
        case "Maul":
            attackdmg = 10;
            if (attackindex % 2 == 0) {
                currMonster.setMonsterHP(currMonster.getMonsterHP() - attackdmg);
            } else {
                //log message
            }
            break;
        case "Sword":
            attackdmg = 15;
            if (attackindex % 2 == 0) {
                if (randomGen < .7) {
                    currMonster.setMonsterHP(currMonster.getMonsterHP() - attackdmg);
                } else {
                    //log message
                }
            } else {
                //log message
            }
            break;
        case "Bow":
            attackdmg = 10;
            if (randomGen < .7) {
                currMonster.setMonsterHP(currMonster.getMonsterHP() - attackdmg);
            } else {
                //log message
            }
            break;
        }
        switch (currMonster.getMonsterName()) {
        case "Goblin":
            player.setPlayerHP(player.getPlayerHP() - 3);
            break;
        case "Goblin Commander":
            player.setPlayerHP(player.getPlayerHP() - 6);
            break;
        case "Viper":
            player.setPlayerHP(player.getPlayerHP() - 10);
            break;
        }
        attackindex++;
        checkHP();
        setPlayer();
        setMonster();
        buttonChecker();
    }

    private void setPlayer() {
        Image playerImage = new Image("dungeonexplorer.png");
        ImageView playerI = new ImageView(playerImage);
        playerI.setFitHeight(250);
        playerI.setPreserveRatio(true);
        playerLabel.setText("HP: " + player.getPlayerHP());
        playerLabel.setGraphic(playerI);
        playerLabel.setContentDisplay(ContentDisplay.BOTTOM);
        playerLabel.setTextAlignment(TextAlignment.CENTER);
        playerLabel.setFont(Font.font("Cambria", 20));
        playerLabel.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.5),
                new CornerRadii(0.0), new Insets(0.0))));
    }

    private void setMonster() {
        Image goblinI = new Image("goblin.png");
        ImageView goblin = new ImageView(goblinI);
        goblin.setFitHeight(200);
        goblin.setPreserveRatio(true);
        Image goblinCommanderI = new Image("goblinCommander.png");
        ImageView goblinCommander = new ImageView(goblinCommanderI);
        goblinCommander.setFitHeight(200);
        goblinCommander.setPreserveRatio(true);
        Image viperI = new Image("viper.png");
        ImageView viper = new ImageView(viperI);
        viper.setFitHeight(300);
        viper.setPreserveRatio(true);
        switch (currMonster.getMonsterName()) {
        case "Goblin":
            monsterLabel.setText("You are fighting a " + currMonster.getMonsterName() + "\nHP: "
                    + currMonster.getMonsterHP());
            monsterLabel.setGraphic(goblin);
            break;
        case "Goblin Commander":
            monsterLabel.setText("You are fighting a " + currMonster.getMonsterName() + "\nHP: "
                    + currMonster.getMonsterHP());
            monsterLabel.setGraphic(goblinCommander);
            break;
        case "Viper":
            monsterLabel.setText("You are fighting the " + currMonster.getMonsterName() + "\nHP: "
                    + currMonster.getMonsterHP());
            monsterLabel.setGraphic(viper);
            break;
        default:
            monsterLabel.setText("");
            monsterLabel.setGraphic(null);
            break;
        }
        monsterLabel.setContentDisplay(ContentDisplay.BOTTOM);
        monsterLabel.setTextAlignment(TextAlignment.CENTER);
        monsterLabel.setFont(Font.font("Cambria", 20));
        monsterLabel.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.5),
                new CornerRadii(0.0), new Insets(0.0))));
        if (monsterDefeated) {
            monsterLabel.setVisible(false);
        }
    }

    private void setAnimations(boolean trans1, boolean trans2, boolean trans3, boolean trans4) {
        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(playerLabel);
        translate1.setDuration(Duration.millis(1000));
        translate1.setCycleCount(TranslateTransition.INDEFINITE);
        translate1.setByY(-50);
        translate1.setAutoReverse(true);
        if (trans1) {
            translate1.play();
        } else {
            translate1.stop();
        }
        TranslateTransition translate2 = new TranslateTransition();
        translate2.setNode(monsterLabel);
        translate2.setDuration(Duration.millis(1000));
        translate2.setCycleCount(TranslateTransition.INDEFINITE);
        translate2.setByY(-50);
        translate2.setAutoReverse(true);
        if (trans2) {
            translate2.play();
        } else {
            translate2.stop();
        }
        RotateTransition translate3 = new RotateTransition();
        translate3.setNode(playerLabel);
        translate3.setDuration(Duration.millis(1000));
        translate3.setCycleCount(1);
        translate3.setAxis(Rotate.Z_AXIS);
        translate3.setByAngle(180);
        if (trans3) {
            translate3.play();
        } else {
            translate3.stop();
        }
        RotateTransition translate4 = new RotateTransition();
        translate4.setNode(monsterLabel);
        translate4.setDuration(Duration.millis(1000));
        translate4.setCycleCount(1);
        translate4.setAxis(Rotate.Z_AXIS);
        translate4.setByAngle(180);
        if (trans4) {
            translate4.play();
        } else {
            translate4.stop();
        }
    }

    private void checkHP() {
        if (currMonster.getMonsterHP() <= 0) {
            monsterLabel.setText("The " + currMonster.getMonsterName() + " has fainted.\nHP: 0");
            buttonAttack.setDisable(true);
            buttonAttack.setVisible(false);
            monsterDefeated = true;
            setAnimations(true, false, false, true);
        }
        if (player.getPlayerHP() <= 0) {
            playerLabel.setText("You have died.");
            buttonRestart.setVisible(true);
            buttonRestart.setDisable(false);
            setAnimations(false, true, true, false);
            roomL.setCenter(buttonRestart);
            Label gameOver = new Label("Game Over");
            roomL.setTop(gameOver);
            roomL.setLeft(null);
            roomL.setRight(null);
            roomL.setBottom(null);
        }
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

    public Button getButtonRetreat() {
        return buttonRetreat;
    }

    public Button getButtonRestart() {
        return buttonRestart;
    }

    public Button getButtonConfirmKill() {
        return buttonConfirmKill;
    }
}
