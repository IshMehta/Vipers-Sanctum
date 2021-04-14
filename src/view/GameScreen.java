package view;

import components.Monster;
import components.Player;
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
    private final Button buttonUp = new Button("Up");
    private final Button buttonDown = new Button("Down");
    private final Button buttonLeft = new Button("L");
    private final Button buttonRight = new Button("R");
    private final Button buttonAttack = new Button("Attack");
    private final Button buttonRetreat = new Button("Retreat");
    private final Button buttonRestart = new Button("Restart");
    private final Button buttonConfirmKill = new Button("Confirm the Kill");
    private final Button buttonAccessInventory = new Button("Inventory");
    private final Button backToGame = new Button("Return to game");
    private VBox leftSide = new VBox();
    private VBox rightSide = new VBox();
    private VBox upSide = new VBox();
    private VBox downSide = new VBox();
    private int room;
    private Monster currMonster;
    private Label monsterLabel = new Label();
    private Label playerLabel = new Label();
    private Label weaponStatus;
    private Label moneyStatus;
    private int attackindex = 0;
    private boolean monsterDefeated;
    private Player player;
    private VBox inventoryScreen = new VBox();
    private VBox inventoryFiller = new VBox();
    private BorderPane roomL;
    private StackPane mainView;
    private boolean isAttackOn;
    private boolean isLuckyOn;

    public GameScreen(String selectedDifficulty, String selectedWeapons, int roomIndex,
                      Player playerIn, boolean monsterDefeatedIn) {
        difficulty = selectedDifficulty;
        weapon = selectedWeapons;
        room = roomIndex;
        currMonster = new Monster(room);
        player = playerIn;
        buttonRestart.setVisible(false);
        monsterDefeated = monsterDefeatedIn;
        isAttackOn = false;
        isLuckyOn = false;
    }

    public Scene getScene() {

        roomL = new BorderPane();
        Scene roomScene = new Scene(roomL, 1280, 720);

        //Room Scene Set Up
        //Borders of the border pane will be Vbox's that house buttons

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
        moneyStatus = new Label("Money: " + startingMoney);
        moneyStatus.setId("moneyStatus");
        weaponStatus = new Label("Weapon: " + weapon);
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
        mainView = new StackPane();
        mainView.setAlignment(moneyStatus, Pos.TOP_RIGHT);
        mainView.setAlignment(weaponStatus, Pos.TOP_LEFT);
        mainView.setAlignment(currentRoom, Pos.TOP_CENTER);
        mainView.setAlignment(monsterLabel, Pos.CENTER_RIGHT);
        mainView.setAlignment(playerLabel, Pos.CENTER_LEFT);
        mainView.setAlignment(buttonAttack, Pos.BOTTOM_RIGHT);
        mainView.setAlignment(buttonRetreat, Pos.BOTTOM_LEFT);
        mainView.setAlignment(buttonConfirmKill, Pos.CENTER);
        mainView.setAlignment(buttonAccessInventory, Pos.BOTTOM_CENTER);
        buttonAccessInventory.setOnAction(e -> setInventory());
        StackPane.setMargin(monsterLabel, new Insets(20, 20, 20, 20));
        StackPane.setMargin(playerLabel, new Insets(20, 20, 20, 20));
        Image background = new Image("dungeonTest.jpg");
        BackgroundImage dungeonBackground = new BackgroundImage(background,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1200, 450, false, false, true, false));
        mainView.setBackground(new Background(dungeonBackground));
        mainView.getChildren().addAll(moneyStatus, weaponStatus, currentRoom, monsterLabel,
                playerLabel, buttonAttack, buttonRetreat, buttonConfirmKill, buttonAccessInventory);
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

    private void setInventory() {
        roomL.setLeft(null);
        roomL.setTop(null);
        roomL.setRight(null);
        roomL.setCenter(null);
        roomL.setBottom(null);
        inventoryFiller.setPrefWidth(800);
        inventoryFiller.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0),
                CornerRadii.EMPTY, Insets.EMPTY)));
        inventoryScreen.setPrefWidth(480);
        backToGame.setOnAction(e -> setGameScreen());
        Label inventoryLabel = new Label("Inventory");
        inventoryLabel.setFont(Font.font("Cambria", 48));
        inventoryLabel.setBackground(new Background(
                new BackgroundFill(Color.rgb(255, 255, 255, 0.7),
                new CornerRadii(0.0), new Insets(0.0))));
        inventoryLabel.setPadding(new Insets(10, 100, 10, 100));
        inventoryFiller.setSpacing(100);
        inventoryScreen.setSpacing(20);
        inventoryFiller.getChildren().addAll(inventoryLabel, backToGame);
        checkInventory();
        inventoryFiller.setAlignment(Pos.CENTER);
        inventoryScreen.setAlignment(Pos.CENTER);
        roomL.setRight(inventoryScreen);
        roomL.setLeft(inventoryFiller);
    }

    private void checkInventory() {
        inventoryScreen.getChildren().clear();
        if (player.getInventoryCount().get(0) != 0) {
            Button selectKnife = new Button(Integer.toString(player.getInventoryCount().get(0)));
            selectKnife.setOnAction(e -> {
                weapon = "Knife";
                player.setSelectedWeapon("Knife");
                weaponStatus.setText("Weapon: " + weapon);
            });
            selectKnife.setFont(Font.font("Cambria", 32));
            selectKnife.setPrefWidth(128);
            selectKnife.setPrefHeight(72);
            Image img = new Image("knifeWeapon.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(60);
            view.setPreserveRatio(true);
            selectKnife.setGraphic(view);
            inventoryScreen.getChildren().add(selectKnife);
        }
        if (player.getInventoryCount().get(1) != 0) {
            Button selectMaul = new Button(Integer.toString(player.getInventoryCount().get(1)));
            selectMaul.setOnAction(e -> {
                weapon = "Maul";
                player.setSelectedWeapon("Maul");
                weaponStatus.setText("Weapon: " + weapon);
            });
            selectMaul.setFont(Font.font("Cambria", 32));
            selectMaul.setPrefWidth(128);
            selectMaul.setPrefHeight(72);
            Image img = new Image("maulWeapon.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(60);
            view.setPreserveRatio(true);
            selectMaul.setGraphic(view);
            inventoryScreen.getChildren().add(selectMaul);
        }
        if (player.getInventoryCount().get(2) != 0) {
            Button selectSword = new Button(Integer.toString(player.getInventoryCount().get(2)));
            selectSword.setOnAction(e -> {
                weapon = "Sword";
                player.setSelectedWeapon("Sword");
                weaponStatus.setText("Weapon: " + weapon);
            });
            selectSword.setFont(Font.font("Cambria", 32));
            selectSword.setPrefWidth(128);
            selectSword.setPrefHeight(72);
            Image img = new Image("swordWeapon.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(60);
            view.setPreserveRatio(true);
            selectSword.setGraphic(view);
            inventoryScreen.getChildren().add(selectSword);
        }
        if (player.getInventoryCount().get(3) != 0) {
            Button selectBow = new Button(Integer.toString(player.getInventoryCount().get(3)));
            selectBow.setOnAction(e -> {
                weapon = "Bow";
                player.setSelectedWeapon("Bow");
                weaponStatus.setText("Weapon: " + weapon);
            });
            selectBow.setFont(Font.font("Cambria", 32));
            selectBow.setPrefWidth(128);
            selectBow.setPrefHeight(72);
            Image img = new Image("bowWeapon.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(60);
            view.setPreserveRatio(true);
            selectBow.setGraphic(view);
            inventoryScreen.getChildren().add(selectBow);
        }
        if (player.getInventoryCount().get(4) != 0) {
            Button useAttack = new Button(Integer.toString(player.getInventoryCount().get(4)));
            useAttack.setOnAction(e -> {
                player.removeElement("Attack");
                isAttackOn = true;
                checkInventory();
            });
            useAttack.setFont(Font.font("Cambria", 32));
            useAttack.setPrefWidth(128);
            useAttack.setPrefHeight(72);
            Image img = new Image("attackPotion.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(60);
            view.setPreserveRatio(true);
            useAttack.setGraphic(view);
            inventoryScreen.getChildren().add(useAttack);
        }
        if (player.getInventoryCount().get(5) != 0) {
            Button useHealth = new Button(Integer.toString(player.getInventoryCount().get(5)));
            useHealth.setOnAction(e -> {
                player.removeElement("Health");
                player.setPlayerHP(player.getPlayerHP() + 10);
                playerLabel.setText("HP: " + player.getPlayerHP());
                checkInventory();
            });
            useHealth.setFont(Font.font("Cambria", 32));
            useHealth.setPrefWidth(128);
            useHealth.setPrefHeight(72);
            Image img = new Image("healthPotion.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(60);
            view.setPreserveRatio(true);
            useHealth.setGraphic(view);
            inventoryScreen.getChildren().add(useHealth);
        }
        if (player.getInventoryCount().get(6) != 0) {
            Button useLucky = new Button(Integer.toString(player.getInventoryCount().get(6)));
            useLucky.setOnAction(e -> {
                player.removeElement("Lucky");
                isLuckyOn = true;
                checkInventory();
            });
            useLucky.setFont(Font.font("Cambria", 32));
            useLucky.setPrefWidth(128);
            useLucky.setPrefHeight(72);
            Image img = new Image("luckyPotion.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(60);
            view.setPreserveRatio(true);
            useLucky.setGraphic(view);
            inventoryScreen.getChildren().add(useLucky);
        }
    }

    private void setGameScreen() {
        roomL.setTop(upSide);
        roomL.setBottom(downSide);
        roomL.setRight(rightSide);
        roomL.setLeft(leftSide);
        roomL.setCenter(mainView);
        inventoryScreen.getChildren().clear();
        inventoryFiller.getChildren().clear();
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
        buttonAccessInventory.setFont(Font.font("Cambria", 20));
        buttonAccessInventory.setPrefWidth(120);
        buttonAccessInventory.setPrefHeight(50);
        buttonConfirmKill.setFont(Font.font("Cambria", 20));
        buttonConfirmKill.setPrefWidth(300);
        buttonConfirmKill.setPrefHeight(72);
        buttonRestart.setFont(Font.font("Cambria", 40));
        buttonRestart.setPrefWidth(300);
        buttonRestart.setPrefHeight(150);
        backToGame.setFont(Font.font("Cambria", 40));
        backToGame.setPrefWidth(350);
        backToGame.setPrefHeight(50);
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
            attackdmg = 5 * player.getInventoryCount().get(0);
            if (isAttackOn) {
                attackdmg += 5;
            }
            currMonster.setMonsterHP(currMonster.getMonsterHP() - attackdmg);
            break;
        case "Maul":
            attackdmg = 10 * player.getInventoryCount().get(0);
            if (isAttackOn) {
                attackdmg += 5;
            }
            if (attackindex % 2 == 0) {
                currMonster.setMonsterHP(currMonster.getMonsterHP() - attackdmg);
            } else {
                System.out.println("Weapon is on cooldown");
            }
            break;
        case "Sword":
            attackdmg = 15 * player.getInventoryCount().get(0);
            if (isAttackOn) {
                attackdmg += 5;
            }
            if (attackindex % 2 == 0) {
                if (randomGen < .7 || isLuckyOn) {
                    currMonster.setMonsterHP(currMonster.getMonsterHP() - attackdmg);
                } else {
                    System.out.println("The sword missed");
                }
            } else {
                System.out.println("Weapon is on cooldown");
            }
            break;
        case "Bow":
            attackdmg = 10 * player.getInventoryCount().get(0);
            if (isAttackOn) {
                attackdmg += 5;
            }
            if (randomGen < .7 || isLuckyOn) {
                currMonster.setMonsterHP(currMonster.getMonsterHP() - attackdmg);
            } else {
                System.out.println("The bow missed");
            }
            break;
        default:
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
        default:
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
        playerLabel.setId("playerLabel");
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
        monsterLabel.setId("monsterLabel");
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
        mainView.setAlignment(monsterLabel, Pos.CENTER_RIGHT);
        mainView.setAlignment(playerLabel, Pos.CENTER_LEFT);
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
            switch (currMonster.getMonsterName()) {
            case "Goblin":
                int random = (int) (Math.random() * 6);
                if (random == 0) {
                    player.addElement("Attack");
                } else if (random == 1) {
                    player.addElement("Health");
                } else if (random == 2) {
                    player.addElement("Lucky");
                }
                break;
            case "Goblin Commander":
                random = (int) (Math.random() * 10);
                if (random == 0) {
                    player.addElement("Knife");
                } else if (random == 1) {
                    player.addElement("Maul");
                } else if (random == 2) {
                    player.addElement("Sword");
                } else if (random == 3) {
                    player.addElement("Bow");
                }
                break;
            default:
                break;
            }
            monsterLabel.setText("The " + currMonster.getMonsterName() + " has fainted.\nHP: 0");
            buttonAttack.setDisable(true);
            buttonAttack.setVisible(false);
            monsterDefeated = true;
            setAnimations(false, false, false, true);
        }
        if (player.getPlayerHP() <= 0) {
            playerLabel.setText("You have died.");
            buttonRestart.setVisible(true);
            buttonRestart.setDisable(false);
            setAnimations(false, true, true, false);
            roomL.setCenter(buttonRestart);
            Label gameOver = new Label("Game Over!");
            gameOver.setTextAlignment(TextAlignment.CENTER);
            gameOver.setFont(Font.font("Cambria", 80));
            gameOver.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.7),
                    new CornerRadii(0.0), new Insets(0.0))));
            roomL.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 1),
                    new CornerRadii(0.0), new Insets(0.0))));
            StackPane backgroundFiller = new StackPane();
            backgroundFiller.getChildren().addAll(gameOver);
            backgroundFiller.setAlignment(Pos.CENTER);
            roomL.setTop(backgroundFiller);
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
