import controller.Controller;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import static org.testfx.api.FxToolkit.registerPrimaryStage;
import static org.testfx.api.FxToolkit.setupApplication;

/**
 * Set of unit tests to test M3 implementations.
 *
 * @author Vipers
 * @version 1.0
 */
public class ControllerTest2 extends ApplicationTest {
    @BeforeAll
    public static void setupSpec() throws Exception {
        registerPrimaryStage();
    }

    @BeforeEach
    public void setUp() throws Exception {
        setupApplication(Controller.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }
    //Asha test carryover to make sure file is set up correctly (can delete later)
    @Test
    public void testWelcomeScreenandQuit() {
        FxAssert.verifyThat(".label", LabeledMatchers.hasText("The Viper's Sanctum!"));
        FxAssert.verifyThat(".button", LabeledMatchers.hasText("Play"));
        clickOn("Quit");
    }
}
