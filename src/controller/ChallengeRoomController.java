package controller;

import components.Dungeon;
import components.Monster;
import components.Player;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ChallengeRoomController {

    @FXML private Button buttonRetreat;
    @FXML private Button buttonAttack;
    @FXML private Button buttonAcceptChallenge;
    @FXML private Label playerLabel;
    @FXML private Label monsterLabel;
    @FXML private Button buttonDeclineChallenge;
    private Player player;
    private Dungeon dungeon;
    private Monster monster;

    private Monster monster1;
    private Monster monster2;
    private Monster monster3;
    private Monster currMonster;
    private int attackindex;

    void initData(Player player, Dungeon dungeon, Monster monster) {
        this.player = player;
        this.dungeon = dungeon;
        this.monster = monster;
        this.playerLabel.setVisible(false);
        this.monsterLabel.setVisible(false);
        this.buttonAttack.setVisible(false);
        this.buttonRetreat.setVisible(false);
        setAnimations(true, true);
    }

    public void retreatButtonPressed(ActionEvent actionEvent) throws IOException {
        goToGameScreen();
    }

    public void attackButtonPressed(ActionEvent actionEvent) throws IOException {
        int attackdmg;
        double randomGen = Math.random();
        switch (this.player.getSelectedWeapon()) {
        case "Knife":
            attackdmg = 5 * player.getInventoryCount().get(0);
            if (this.player.isAttackOn()) {
                attackdmg += 5;
            }
            player.setTotalDmgDealt(player.getTotalDmgDealt() + attackdmg);
            this.currMonster.setMonsterHP(this.currMonster.getMonsterHP() - attackdmg);
            break;
        case "Maul":
            attackdmg = 10 * player.getInventoryCount().get(0);
            if (this.player.isAttackOn()) {
                attackdmg += 5;
            }
            if (attackindex % 2 == 0) {
                player.setTotalDmgDealt(player.getTotalDmgDealt() + attackdmg);
                this.currMonster.setMonsterHP(this.currMonster.getMonsterHP() - attackdmg);
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
                    player.setTotalDmgDealt(player.getTotalDmgDealt() + attackdmg);
                    this.currMonster.setMonsterHP(this.currMonster.getMonsterHP() - attackdmg);
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
                player.setTotalDmgDealt(player.getTotalDmgDealt() + attackdmg);
                this.currMonster.setMonsterHP(this.currMonster.getMonsterHP() - attackdmg);
            } else {
                System.out.println("The bow missed");
            }
            break;
        default:
            break;
        }
        switch (this.currMonster.getMonsterName()) {
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
        //cleanup
    }

    private void setPlayer() {
        this.playerLabel.setText("HP: " + this.player.getPlayerHP());
    }

    private void setMonster() throws IOException {
        Image goblinI = new Image("goblin.png");
        ImageView goblin = new ImageView(goblinI);
        goblin.setFitHeight(200);
        goblin.setPreserveRatio(true);
        if (currMonster == monster1) {
            monsterLabel.setText("You are fighting goblin 1.\nHP: "
                    + this.currMonster.getMonsterHP());
        } else if (currMonster == monster2) {
            monsterLabel.setText("You are fighting goblin 2.\nHP: "
                    + this.currMonster.getMonsterHP());
        } else {
            monsterLabel.setText("You are fighting goblin 3.\nHP: "
                    + this.currMonster.getMonsterHP());
        }
        monsterLabel.setGraphic(goblin);
        checkHP();
    }

    private void checkHP() throws IOException {
        if (this.currMonster.getMonsterHP() <= 0) {
            player.setTotalMonstersKilled(player.getTotalMonstersKilled() + 1);
            startChallenge();
        }
        if (this.player.getPlayerHP() <= 0) {
            goToGameOver();
        }
    }

    private void goToGameOver() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/endGameScreen.fxml"));
        Parent endGameScreenParent = loader.load();
        Scene endGameScreenScene = new Scene(endGameScreenParent);

        //access the controller and call a method
        EndGameController controller = loader.getController();
        controller.initData(false, this.player);

        //This line gets the Stage information
        Stage window = (Stage) buttonAttack.getScene().getWindow();
        if (window == null) {
            return;
        }
        window.setScene(endGameScreenScene);
        window.show();
    }

    public void acceptButtonPressed(ActionEvent actionEvent) throws IOException {
        buttonAcceptChallenge.setVisible(false);
        buttonDeclineChallenge.setVisible(false);
        this.playerLabel.setVisible(true);
        this.monsterLabel.setVisible(true);
        this.buttonAttack.setVisible(true);
        this.buttonRetreat.setVisible(true);
        startChallenge();
    }

    public void declineButtonPressed(ActionEvent actionEvent) throws IOException {
        goToGameScreen();
    }

    private void startChallenge() throws IOException {
        if (monster3 != null && monster3.getMonsterHP() <= 0) {
            this.player.addElement("Attack");
            this.player.addElement("Health");
            this.player.addElement("Lucky");
            goToGameScreen();
        } else if (monster2 != null && monster2.getMonsterHP() <= 0) {
            spawnMonster3();
        } else if (monster1 == null) {
            spawnMonster1();
        } else if (monster1.getMonsterHP() <= 0) {
            spawnMonster2();
        }
    }

    private void spawnMonster1() {
        monster1 = new Monster(2);
        currMonster = monster1;
        Image goblinI = new Image("goblin.png");
        ImageView goblin = new ImageView(goblinI);
        goblin.setFitHeight(200);
        goblin.setPreserveRatio(true);
        monsterLabel.setText("You are fighting goblin 1.\nHP: "
                + this.currMonster.getMonsterHP());
        monsterLabel.setGraphic(goblin);
    }

    private void spawnMonster2() throws IOException {
        monster2 = new Monster(2);
        currMonster = monster2;
        setMonster();
    }

    private void spawnMonster3() throws IOException {
        monster3 = new Monster(2);
        currMonster = monster3;
        setMonster();
    }

    public void goToGameScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/gameScreen.fxml"));
        Parent gameScreenParent = loader.load();
        Scene gameScreenScene = new Scene(gameScreenParent);

        //access the controller and call a method
        GameScreenController controller = loader.getController();
        controller.initData(this.player, this.dungeon, this.monster);

        //This line gets the Stage information
        Stage window = (Stage) buttonAttack.getScene().getWindow();
        window.setScene(gameScreenScene);
        window.show();
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
}
