package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeScreenController implements Initializable {
    private Stage mainWindow;
    private int testMode;

    public void goToConfig(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/configurationScreen.fxml"));
        Parent configurationScreenParent = loader.load();
        Scene configurationScreenScene = new Scene(configurationScreenParent);

        ConfigurationScreenController controller = loader.getController();
        controller.initData(testMode);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(configurationScreenScene);
        window.show();
    }

    public void quitGame(ActionEvent actionEvent) {
        mainWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        mainWindow.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(int i) {
        this.testMode = i;
    }
}
