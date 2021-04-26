package controller;

import components.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class EndGameController {

    @FXML private Label labelTime;
    @FXML private Label labelDmg;
    @FXML private Label labelMonsters;
    @FXML private Label endGameTitle;

    private Stage mainWindow;
    private boolean win;
    private Player player;
    private long elapsedTime;

    public void initData(boolean win, Player player) {
        this.win = win;
        if (this.win) {
            endGameTitle.setText("YOU WON!");
        } else {
            endGameTitle.setText("YOU LOST!");
        }
        this.player = player;
        elapsedTime = (int) ((System.currentTimeMillis() - this.player.getStartPlayerTime()) / 1000);
        labelTime.setText(labelTime.getText() + elapsedTime + " seconds");
        labelDmg.setText(labelDmg.getText() + this.player.getTotalDmgDealt());
        labelMonsters.setText(labelMonsters.getText() + this.player.getTotalMonstersKilled());
    }

    public void goToWelcome(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/welcomeScreen.fxml"));
        Parent welcomeScreenParent = loader.load();
        Scene welcomeScreenScene = new Scene(welcomeScreenParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(welcomeScreenScene);
        window.show();
    }

    public void exitApplication(ActionEvent actionEvent) {
        mainWindow = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        mainWindow.close();
    }
}
