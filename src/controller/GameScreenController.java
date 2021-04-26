package controller;

import components.Dungeon;
import components.Monster;
import components.Player;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameScreenController implements Initializable {

    @FXML private Button pickUpAttack;
    @FXML private Button pickUpHealth;
    @FXML private Button pickUpLucky;
    @FXML private Button pickUpKnife;
    @FXML private Button pickUpMaul;
    @FXML private Button pickUpSword;
    @FXML private Button pickUpBow;

    @FXML private Button buttonUp;
    @FXML private Button buttonLeft;
    @FXML private Button buttonRight;
    @FXML private Button buttonDown;

    @FXML private Button buttonRetreat;
    @FXML private Button buttonAttack;
    @FXML private Button buttonConfirmKill;

    @FXML private Label playerLabel;
    @FXML private Label monsterLabel;

    @FXML private Label weaponLabel;
    @FXML private Label moneyLabel;
    @FXML private Label roomNoLabel;

    private Player player;
    private int[][] roomsArray;
    private boolean[] roomsAccessed;
    private boolean[] monstersDefeated;
    private Monster monster;
    private Dungeon dungeon;
    private int attackindex;

    public void initData(Player player, Dungeon dungeon, Monster monster) {
        this.player = player;
        this.dungeon = dungeon;
        this.roomsArray = dungeon.getRoomsArray();
        this.roomsAccessed = dungeon.getRoomsAccessed();
        this.monstersDefeated = dungeon.getMonstersDefeated();
        if (roomsArray[player.getPlayerX()][player.getPlayerY()] == 0) {
            this.roomNoLabel.setText("Room: ?");
        }
        this.monster = monster;
        setTopLabels();
        setPlayer();
        setMonster();
        setButtons();
        setAnimations(true, true);
    }

    private void setButtons() {
        if (this.monster != null) {
            this.buttonLeft.setVisible(false);
            this.buttonRight.setVisible(false);
            this.buttonUp.setVisible(false);
            this.buttonDown.setVisible(false);
            this.buttonAttack.setVisible(true);
            this.buttonRetreat.setVisible(true);
        } else {
            this.buttonLeft.setVisible(true);
            this.buttonRight.setVisible(true);
            this.buttonUp.setVisible(true);
            this.buttonDown.setVisible(true);
            this.buttonAttack.setVisible(false);
            this.buttonRetreat.setVisible(false);
        }
        this.buttonConfirmKill.setVisible(false);
        this.pickUpAttack.setVisible(false);
        this.pickUpHealth.setVisible(false);
        this.pickUpLucky.setVisible(false);
        this.pickUpKnife.setVisible(false);
        this.pickUpMaul.setVisible(false);
        this.pickUpSword.setVisible(false);
        this.pickUpBow.setVisible(false);
    }

    private void setPlayer() {
        this.playerLabel.setText("HP: " + this.player.getPlayerHP());
    }

    private void setMonster() {
        if (roomsArray[player.getPlayerX()][player.getPlayerY()] <= 1) {
            this.monster = null;
            this.monsterLabel.setVisible(false);
            return;
        }
        if (this.monstersDefeated[this.roomsArray[this.player.getPlayerX()][this.player.getPlayerY()] - 1]) {
            this.monster = null;
            this.monsterLabel.setVisible(false);
            return;
        }
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
        switch(this.monster.getMonsterName()) {
            case "Goblin":
                monsterLabel.setText("You are fighting a " + this.monster.getMonsterName() + "\nHP: "
                        + this.monster.getMonsterHP());
                monsterLabel.setGraphic(goblin);
                break;
            case "Goblin Commander":
                monsterLabel.setText("You are fighting a " + this.monster.getMonsterName() + "\nHP: "
                        + this.monster.getMonsterHP());
                monsterLabel.setGraphic(goblinCommander);
                break;
            case "Viper":
                monsterLabel.setText("You are fighting the " + this.monster.getMonsterName() + "\nHP: "
                        + this.monster.getMonsterHP());
                monsterLabel.setGraphic(viper);
                break;
            default:
                monsterLabel.setText("");
                monsterLabel.setGraphic(null);
                break;
        }
    }

    private void setAnimations(boolean trans1, boolean trans2) {
        TranslateTransition translate1 = new TranslateTransition();
        translate1.setNode(this.playerLabel);
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
        translate2.setNode(this.monsterLabel);
        translate2.setDuration(Duration.millis(1000));
        translate2.setCycleCount(TranslateTransition.INDEFINITE);
        translate2.setByY(-50);
        translate2.setAutoReverse(true);
        if (trans2) {
            translate2.play();
        } else {
            translate2.stop();
        }
    }

    private void setTopLabels() {
        this.weaponLabel.setText("Weapon: " + this.player.getSelectedWeapon());
        int money = 0;
        switch (player.getPlayerDifficulty()) {
        case "Easy":
            money = 15;
            break;
        case "Medium":
            money = 10;
            break;
        case "Hard":
            money = 5;
            break;
        default:
            break;
        }
        this.moneyLabel.setText("Money: " + money);
        this.roomNoLabel.setText("Room: " + roomsArray[player.getPlayerX()][player.getPlayerY()]);
    }

    public void attackButtonPressed(ActionEvent actionEvent) {
        int attackdmg;
        double randomGen = Math.random();
        switch (this.player.getSelectedWeapon()) {
            case "Knife":
                attackdmg = 5 * player.getInventoryCount().get(0);
                if (this.player.isAttackOn()) {
                    attackdmg += 5;
                }
                this.monster.setMonsterHP(this.monster.getMonsterHP() - attackdmg);
                break;
            case "Maul":
                attackdmg = 10 * player.getInventoryCount().get(0);
                if (this.player.isAttackOn()) {
                    attackdmg += 5;
                }
                if (attackindex % 2 == 0) {
                    this.monster.setMonsterHP(this.monster.getMonsterHP() - attackdmg);
                } else {
                    System.out.println("Weapon is on cooldown");
                }
                break;
            case "Sword":
                attackdmg = 15 * player.getInventoryCount().get(0);
                if (this.player.isAttackOn()) {
                    attackdmg += 5;
                }
                if (attackindex % 2 == 0) {
                    if (randomGen < .7 || this.player.isLuckyOn()) {
                        this.monster.setMonsterHP(this.monster.getMonsterHP() - attackdmg);
                    } else {
                        System.out.println("The sword missed");
                    }
                } else {
                    System.out.println("Weapon is on cooldown");
                }
                break;
            case "Bow":
                attackdmg = 10 * player.getInventoryCount().get(0);
                if (this.player.isAttackOn()) {
                    attackdmg += 5;
                }
                if (randomGen < .7 || this.player.isLuckyOn()) {
                    this.monster.setMonsterHP(this.monster.getMonsterHP() - attackdmg);
                } else {
                    System.out.println("The bow missed");
                }
                break;
            default:
                break;
        }
        switch (this.monster.getMonsterName()) {
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
        setPlayer();
        setMonster();
        checkHP();
        //cleanup
    }

    private void checkHP() {
        if (this.monster.getMonsterHP() <= 0) {
            switch (this.monster.getMonsterName()) {
                case "Goblin":
                    int random = (int) (Math.random() * 6);
                    if (random == 0) {
                        pickUpAttack.setVisible(true);
                        pickUpAttack.setOnAction(e -> {
                            player.addElement("Attack");
                            pickUpAttack.setVisible(false);
                        });
                    } else if (random == 1) {
                        pickUpHealth.setVisible(true);
                        pickUpHealth.setOnAction(e -> {
                            player.addElement("Health");
                            pickUpHealth.setVisible(false);
                        });
                    } else if (random == 2) {
                        pickUpLucky.setVisible(true);
                        pickUpLucky.setOnAction(e -> {
                            player.addElement("Lucky");
                            pickUpLucky.setVisible(false);
                        });
                    }
                    break;
                case "Goblin Commander":
                    random = (int) (Math.random() * 10);
                    if (random == 0) {
                        pickUpKnife.setVisible(true);
                        pickUpKnife.setOnAction(e -> {
                            player.addElement("Knife");
                            pickUpKnife.setVisible(false);
                        });
                    } else if (random == 1) {
                        pickUpMaul.setVisible(true);
                        pickUpMaul.setOnAction(e -> {
                            player.addElement("Maul");
                            pickUpMaul.setVisible(false);
                        });
                    } else if (random == 2) {
                        pickUpSword.setVisible(true);
                        pickUpSword.setOnAction(e -> {
                            player.addElement("Sword");
                            pickUpSword.setVisible(false);
                        });
                    } else if (random == 3) {
                        pickUpBow.setVisible(true);
                        pickUpBow.setOnAction(e -> {
                            player.addElement("Bow");
                            pickUpBow.setVisible(false);
                        });
                    }
                    break;
                default:
                    break;
            }
            buttonAttack.setVisible(false);
            buttonRetreat.setVisible(false);
            buttonUp.setVisible(true);
            buttonDown.setVisible(true);
            buttonRight.setVisible(true);
            buttonLeft.setVisible(true);
            buttonConfirmKill.setVisible(true);
            monsterLabel.setVisible(false);
            setAnimations(false, false);
        }
        if (this.player.getPlayerHP() <= 0) {
            goToGameOver();
        }
    }

    public void retreatButtonPressed(ActionEvent actionEvent) {
    }

    public void changeRoom(ActionEvent actionEvent) throws IOException {
        String direction = ((Button) actionEvent.getSource()).getText();
        switch (direction) {
        case "Up":
            if (player.getPlayerY() == 0) {
                Alert invalidRoom = new Alert(Alert.AlertType.WARNING);
                invalidRoom.setContentText("You are at the edge of "
                        + "the dungeon and cannot travel this way");
                invalidRoom.setHeaderText("Choose a different exit");
                invalidRoom.setTitle("Invalid Exit");
                invalidRoom.show();
                return;
            }
            player.setPlayerY(player.getPlayerY()-1);
            if (roomsArray[player.getPlayerX()][player.getPlayerY()] <= 1) {
                break;
            } else if (roomsAccessed[roomsArray[player.getPlayerX()][player.getPlayerY()] - 2]) {
                roomsAccessed[roomsArray[player.getPlayerX()][player.getPlayerY()] - 1] = true;
            } else {
                Alert invalidRoom = new Alert(Alert.AlertType.WARNING);
                invalidRoom.setContentText("You are attempting to access room "
                        + roomsArray[player.getPlayerX()][player.getPlayerY()]
                        + " and you must access room "
                        + (roomsArray[player.getPlayerX()][player.getPlayerY()] - 1) + " first!");
                invalidRoom.setHeaderText("Choose a different exit");
                invalidRoom.setTitle("Invalid Exit");
                invalidRoom.show();
                player.setPlayerY(player.getPlayerY()+1);
                return;
            }
            break;
        case "Down":
            if (player.getPlayerY() == 4) {
                Alert invalidRoom = new Alert(Alert.AlertType.WARNING);
                invalidRoom.setContentText("You are at the edge of "
                        + "the dungeon and cannot travel this way");
                invalidRoom.setHeaderText("Choose a different exit");
                invalidRoom.setTitle("Invalid Exit");
                invalidRoom.show();
                return;
            }
            player.setPlayerY(player.getPlayerY()+1);
            if (roomsArray[player.getPlayerX()][player.getPlayerY()] <= 1) {
                break;
            } else if (roomsAccessed[roomsArray[player.getPlayerX()][player.getPlayerY()] - 2]) {
                roomsAccessed[roomsArray[player.getPlayerX()][player.getPlayerY()] - 1] = true;
            } else {
                Alert invalidRoom = new Alert(Alert.AlertType.WARNING);
                invalidRoom.setContentText("You are attempting to access room "
                        + roomsArray[player.getPlayerX()][player.getPlayerY()]
                        + " and you must access room "
                        + (roomsArray[player.getPlayerX()][player.getPlayerY()] - 1) + " first!");
                invalidRoom.setHeaderText("Choose a different exit");
                invalidRoom.setTitle("Invalid Exit");
                invalidRoom.show();
                player.setPlayerY(player.getPlayerY()-1);
                return;
            }
            break;
        case "R":
            if (player.getPlayerX() == 4) {
                Alert invalidRoom = new Alert(Alert.AlertType.WARNING);
                invalidRoom.setContentText("You are at the edge of "
                        + "the dungeon and cannot travel this way");
                invalidRoom.setHeaderText("Choose a different exit");
                invalidRoom.setTitle("Invalid Exit");
                invalidRoom.show();
                return;
            }
            player.setPlayerX(player.getPlayerX()+1);
            if (roomsArray[player.getPlayerX()][player.getPlayerY()] <= 1) {
                break;
            } else if (roomsAccessed[roomsArray[player.getPlayerX()][player.getPlayerY()] - 2]) {
                roomsAccessed[roomsArray[player.getPlayerX()][player.getPlayerY()] - 1] = true;
            } else {
                Alert invalidRoom = new Alert(Alert.AlertType.WARNING);
                invalidRoom.setContentText("You are attempting to access room "
                        + roomsArray[player.getPlayerX()][player.getPlayerY()]
                        + " and you must access room "
                        + (roomsArray[player.getPlayerX()][player.getPlayerY()] - 1) + " first!");
                invalidRoom.setHeaderText("Choose a different exit");
                invalidRoom.setTitle("Invalid Exit");
                invalidRoom.show();
                player.setPlayerY(player.getPlayerX()-1);
                return;
            }
            break;
        case "L":
            if (player.getPlayerX() == 0) {
                Alert invalidRoom = new Alert(Alert.AlertType.WARNING);
                invalidRoom.setContentText("You are at the edge of "
                        + "the dungeon and cannot travel this way");
                invalidRoom.setHeaderText("Choose a different exit");
                invalidRoom.setTitle("Invalid Exit");
                invalidRoom.show();
                return;
            }
            player.setPlayerX(player.getPlayerX()-1);
            if (roomsArray[player.getPlayerX()][player.getPlayerY()] <= 1) {
                break;
            } else if (roomsAccessed[roomsArray[player.getPlayerX()][player.getPlayerY()] - 2]) {
                roomsAccessed[roomsArray[player.getPlayerX()][player.getPlayerY()] - 1] = true;
            } else {
                Alert invalidRoom = new Alert(Alert.AlertType.WARNING);
                invalidRoom.setContentText("You are attempting to access room "
                        + roomsArray[player.getPlayerX()][player.getPlayerY()]
                        + " and you must access room "
                        + (roomsArray[player.getPlayerX()][player.getPlayerY()] - 1) + " first!");
                invalidRoom.setHeaderText("Choose a different exit");
                invalidRoom.setTitle("Invalid Exit");
                invalidRoom.show();
                player.setPlayerY(player.getPlayerX()+1);
                return;
            }
            break;
        default:
            break;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/gameScreen.fxml"));
        Parent gameScreenParent = loader.load();
        Scene gameScreenScene = new Scene(gameScreenParent);

        //access the controller and call a method
        GameScreenController controller = loader.getController();
        controller.initData(this.player, this.dungeon, new Monster(this.roomsArray[player.getPlayerX()][player.getPlayerY()]));

        //This line gets the Stage information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(gameScreenScene);
        window.show();
    }

    public void goToInventory(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/inventoryScreen.fxml"));
        Parent inventoryScreenParent = loader.load();
        Scene inventoryScreenScene = new Scene(inventoryScreenParent);

        //access the controller and call a method
        InventoryScreenController controller = loader.getController();
        controller.initData(this.player, this.dungeon, this.monster);

        //This line gets the Stage information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(inventoryScreenScene);
        window.show();
    }

    private void goToGameOver() {
        //go game over
    }

    public void confirmedButtonPressed(ActionEvent actionEvent) {
        this.monstersDefeated[this.roomsArray[this.player.getPlayerX()][this.player.getPlayerY()] - 1] = true;
        this.buttonConfirmKill.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
