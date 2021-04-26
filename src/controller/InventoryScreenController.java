package controller;

import components.Dungeon;
import components.Monster;
import components.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryScreenController implements Initializable {

    @FXML private Button buttonKnife;
    @FXML private Button buttonMaul;
    @FXML private Button buttonSword;
    @FXML private Button buttonBow;
    @FXML private Button buttonHealth;
    @FXML private Button buttonAttack;
    @FXML private Button buttonLucky;
    private Player player;
    private Dungeon dungeon;
    private Monster monster;

    public void initData(Player player, Dungeon dungeon, Monster monster) {
        this.player = player;
        this.dungeon = dungeon;
        this.monster = monster;
        this.buttonKnife.setText(String.valueOf(player.getInventoryCount().get(0)));
        this.buttonMaul.setText(String.valueOf(player.getInventoryCount().get(1)));
        this.buttonSword.setText(String.valueOf(player.getInventoryCount().get(2)));
        this.buttonBow.setText(String.valueOf(player.getInventoryCount().get(3)));
        this.buttonAttack.setText(String.valueOf(player.getInventoryCount().get(4)));
        this.buttonHealth.setText(String.valueOf(player.getInventoryCount().get(5)));
        this.buttonLucky.setText(String.valueOf(player.getInventoryCount().get(6)));
    }

    public void goToGame(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/gameScreen.fxml"));
        Parent gameScreenParent = loader.load();
        Scene gameScreenScene = new Scene(gameScreenParent);

        //access the controller and call a method
        GameScreenController controller = loader.getController();
        controller.initData(this.player, this.dungeon, this.monster);

        //This line gets the Stage information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(gameScreenScene);
        window.show();
    }

    public void selectKnife(ActionEvent actionEvent) {
        if (player.getInventoryCount().get(0) > 0) {
            this.player.setSelectedWeapon("Knife");
        }
    }

    public void selectMaul(ActionEvent actionEvent) {
        if (player.getInventoryCount().get(1) > 0) {
            this.player.setSelectedWeapon("Maul");
        }
    }

    public void selectSword(ActionEvent actionEvent) {
        if (player.getInventoryCount().get(2) > 0) {
            this.player.setSelectedWeapon("Sword");
        }
    }

    public void selectBow(ActionEvent actionEvent) {
        if (player.getInventoryCount().get(3) > 0) {
            this.player.setSelectedWeapon("Bow");
        }
    }

    public void useAttack(ActionEvent actionEvent) {
        if (player.getInventoryCount().get(4) > 0) {
            this.player.removeElement("Attack");
            this.buttonAttack.setText(String.valueOf(player.getInventoryCount().get(4)));
            this.player.setAttackOn(true);
        }
    }

    public void useHealth(ActionEvent actionEvent) {
        if (player.getInventoryCount().get(5) > 0) {
            this.player.removeElement("Health");
            this.buttonHealth.setText(String.valueOf(player.getInventoryCount().get(5)));
            this.player.setPlayerHP(this.player.getPlayerHP()+10);
        }
    }

    public void useLucky(ActionEvent actionEvent) {
        if (player.getInventoryCount().get(6) > 0) {
            this.player.removeElement("Lucky");
            this.buttonLucky.setText(String.valueOf(player.getInventoryCount().get(6)));
            this.player.setLuckyOn(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
