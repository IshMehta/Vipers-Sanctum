import org.testfx.framework.junit.ApplicationTest;

import org.testfx.matcher.base.NodeMatchers;
import controller.Controller;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.matcher.control.LabeledMatchers;
import static org.testfx.api.FxAssert.verifyThat;

public class ControllerTest extends ApplicationTest {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller();
        controller.start(primaryStage);
    }
    //Asha Test (1)
    @Test
    public void testWelcomeScreenandQuit() {
        FxAssert.verifyThat(".label", LabeledMatchers.hasText("The Viper's Sanctum!"));
        FxAssert.verifyThat(".button", LabeledMatchers.hasText("Play"));
        clickOn("Quit");
    }


    //Asha Test (2)
    @Test
    public void testNavigation() {
        clickOn("Play");
        FxAssert.verifyThat(".label", LabeledMatchers.hasText("Configuration Screen"));
        clickOn("Next");
        FxAssert.verifyThat(".label", LabeledMatchers.hasText("Money: 0"));
    }



}
