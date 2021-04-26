import components.Player;
import controller.DungeonApp;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;

import org.junit.jupiter.api.TestMethodOrder;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;



import static org.testfx.api.FxToolkit.registerPrimaryStage;
import static org.testfx.api.FxToolkit.setupApplication;

/**
 * Set of unit tests to test M6 implementations.
 *
 * @author Vipers
 * @version 1.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Milestone6Tests extends ApplicationTest {

    @BeforeAll
    public static void setupSpec() throws Exception {
        registerPrimaryStage();
    }

    @BeforeEach
    public void setUp() throws Exception {
        setupApplication(DungeonApp.class);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        DungeonApp dungeonApplication = new DungeonApp();
        dungeonApplication.start(primaryStage);
    }

    //tests go here
    //Rahul Test
    @Test
    @Order(1)
    public void testChallengeRooms() {
        clickOn("Play");
        clickOn("#nameTextField");
        write("GG");
        clickOn("Submit Name");
        clickOn("#easyButton");
        clickOn("#knifeButton");
        clickOn("Next");
    }
}