package controller;

import components.Dungeon;
import components.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigurationScreenController implements Initializable {
    @FXML private TextField nameTextField;
    @FXML private RadioButton easyButton;
    @FXML private RadioButton mediumButton;
    @FXML private RadioButton hardButton;
    @FXML private RadioButton knifeButton;
    @FXML private RadioButton maulButton;
    @FXML private RadioButton swordButton;
    @FXML private RadioButton bowButton;
    private ToggleGroup chooseWeapon;
    private ToggleGroup chooseDifficulty;

    private boolean nameSelected;
    private Player player;
    private Dungeon dungeon;
    private int testMode;

    public void goToGame(ActionEvent actionEvent) throws IOException {
        if (!nameSelected || chooseWeapon.getSelectedToggle() == null || chooseDifficulty.getSelectedToggle() == null) {
            Alert nameNotSelected = new Alert(Alert.AlertType.WARNING);
            nameNotSelected.setContentText("One of the fields has not been selected");
            nameNotSelected.setHeaderText("Invalid configuration has not been selected!");
            nameNotSelected.setTitle("Invalid Configuration");
            nameNotSelected.show();
        } else {
            player.addElement(((RadioButton)chooseWeapon.getSelectedToggle()).getText());
            player.setSelectedWeapon(((RadioButton)chooseWeapon.getSelectedToggle()).getText());
            player.setPlayerDifficulty(((RadioButton)chooseDifficulty.getSelectedToggle()).getText());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../resources/gameScreen.fxml"));
            Parent gameScreenParent = loader.load();
            Scene gameScreenScene = new Scene(gameScreenParent);

            if (testMode == 1) {
                dungeon.unRandomizeRooms();
            }
            //access the controller and call a method
            GameScreenController controller = loader.getController();
            controller.initData(player, dungeon, null);

            //This line gets the Stage information
            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(gameScreenScene);
            window.show();
        }
    }

    public void checkName(ActionEvent actionEvent) {
        if (nameTextField.getText() == null || nameTextField.getText().isBlank()) {
            nameSelected = false;
            Alert invalidName = new Alert(Alert.AlertType.WARNING);
            invalidName.setContentText("Name must not consist of only whitespaces and not be blank");
            invalidName.setHeaderText("Player must have a name!");
            invalidName.setTitle("Invalid Name");
            invalidName.show();
        } else {
            nameSelected = true;
            player.setPlayerName(nameTextField.getText());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseDifficulty = new ToggleGroup();
        easyButton.setToggleGroup(chooseDifficulty);
        mediumButton.setToggleGroup(chooseDifficulty);
        hardButton.setToggleGroup(chooseDifficulty);

        chooseWeapon = new ToggleGroup();
        knifeButton.setToggleGroup(chooseWeapon);
        maulButton.setToggleGroup(chooseWeapon);
        swordButton.setToggleGroup(chooseWeapon);
        bowButton.setToggleGroup(chooseWeapon);

        player = new Player(100);
        player.setPlayerX(0);
        player.setPlayerY(2);
        dungeon = new Dungeon();
        nameSelected = false;
    }

    public void initData(int testMode) {
        this.testMode = testMode;
    }
}
