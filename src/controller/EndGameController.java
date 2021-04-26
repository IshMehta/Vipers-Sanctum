package controller;

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
    @FXML
    private Label endGameTitle;
    private Stage mainWindow;
    private boolean win;

    public void initData(boolean win) {
        this.win = win;
        if (this.win) {
            endGameTitle.setText("YOU WON!");
        } else {
            endGameTitle.setText("YOU LOST!");
        }
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
