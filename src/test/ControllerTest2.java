import controller.Controller;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
        controller.clearRooms();
        controller.setRooms(1, 0, 2);
        controller.setRooms(2, 1, 2);
        controller.setRooms(3, 1, 3);
        controller.setRooms(4, 2, 3);
        controller.setRooms(5, 2, 4);
        controller.setRooms(6, 3, 4);
        controller.setRooms(7, 3, 3);
        controller.setRooms(8, 4, 3);
        controller.setRooms(9, 4, 2);
    }
    //Rahul Test
    @Test
    @Order(1)
    public void testEdgeBoundsInitialize() {
        clickOn("Play");
        clickOn("Easy");
        clickOn("Maul");
        clickOn("Next");
        clickOn("L");
        clickOn("OK");
    }
    @Test
    @Order(2)
    public void testRoom1Exits() {
        clickOn("Play");
        clickOn("Easy");
        clickOn("Maul");
        clickOn("Next");
        clickOn("R");
        clickOn("L");
        clickOn("Down");
        clickOn("Up");
        clickOn("Up");
        clickOn("Down");
        clickOn("L");
        clickOn("OK");
    }
}
