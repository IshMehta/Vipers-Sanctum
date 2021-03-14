
import controller.ControllerT;
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
        setupApplication(ControllerT.class);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        ControllerT controller = new ControllerT();
        controller.start(primaryStage);

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

    //Ish Test
    @Test
    @Order(3)
    public void testSuccessfulMazeTraversal() {
        clickOn("Play");
        clickOn("Medium");
        clickOn("Sword");
        clickOn("Next");
        FxAssert.verifyThat("#moneyStatus", LabeledMatchers.hasText("Money: 8"));
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Sword"));
        FxAssert.verifyThat("#roomStatus", LabeledMatchers.hasText("Room: 1"));
        clickOn("R");
        FxAssert.verifyThat("#moneyStatus", LabeledMatchers.hasText("Money: 8"));
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Sword"));
        FxAssert.verifyThat("#roomStatus", LabeledMatchers.hasText("Room: 2"));
        clickOn("Down");
        FxAssert.verifyThat("#moneyStatus", LabeledMatchers.hasText("Money: 8"));
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Sword"));
        FxAssert.verifyThat("#roomStatus", LabeledMatchers.hasText("Room: 3"));
        clickOn("R");
        FxAssert.verifyThat("#moneyStatus", LabeledMatchers.hasText("Money: 8"));
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Sword"));
        FxAssert.verifyThat("#roomStatus", LabeledMatchers.hasText("Room: 4"));
        clickOn("Down");
        FxAssert.verifyThat("#moneyStatus", LabeledMatchers.hasText("Money: 8"));
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Sword"));
        FxAssert.verifyThat("#roomStatus", LabeledMatchers.hasText("Room: 5"));
        clickOn("R");
        FxAssert.verifyThat("#moneyStatus", LabeledMatchers.hasText("Money: 8"));
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Sword"));
        FxAssert.verifyThat("#roomStatus", LabeledMatchers.hasText("Room: 6"));
        clickOn("Up");
        FxAssert.verifyThat("#moneyStatus", LabeledMatchers.hasText("Money: 8"));
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Sword"));
        FxAssert.verifyThat("#roomStatus", LabeledMatchers.hasText("Room: 7"));
        clickOn("R");
        FxAssert.verifyThat("#moneyStatus", LabeledMatchers.hasText("Money: 8"));
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Sword"));
        FxAssert.verifyThat("#roomStatus", LabeledMatchers.hasText("Room: 8"));
        clickOn("Up");
        FxAssert.verifyThat("#moneyStatus", LabeledMatchers.hasText("Money: 8"));
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Sword"));
        FxAssert.verifyThat("#roomStatus", LabeledMatchers.hasText("Room: 9"));
    }

    @Test
    @Order(4)
    public void testAccessRoomOutOfOrder() {
        clickOn("Play");
        clickOn("Medium");
        clickOn("Sword");
        clickOn("Next");
        FxAssert.verifyThat("#moneyStatus", LabeledMatchers.hasText("Money: 8"));
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Sword"));
        FxAssert.verifyThat("#roomStatus", LabeledMatchers.hasText("Room: 1"));
        clickOn("Down");
        FxAssert.verifyThat("#moneyStatus", LabeledMatchers.hasText("Money: 8"));
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Sword"));
        FxAssert.verifyThat("#roomStatus", LabeledMatchers.hasText("Room: 0"));
        clickOn("R");
        FxAssert.verifyThat("OK", NodeMatchers.isVisible());
        clickOn("OK");
    }

    //Diptendu Test
    @Test
    @Order(5)
    public void checkUpperBound() {
        clickOn("Play");
        clickOn("Medium");
        clickOn("Sword");
        clickOn("Next");
        clickOn("Up");
        clickOn("Up");
        clickOn("Up");
        FxAssert.verifyThat("OK", NodeMatchers.isVisible());
        clickOn("OK");
    }
    @Test
    @Order(6)
    public void checkLowerBound() {
        clickOn("Play");
        clickOn("Medium");
        clickOn("Sword");
        clickOn("Next");
        clickOn("Down");
        clickOn("Down");
        clickOn("Down");
        FxAssert.verifyThat("OK", NodeMatchers.isVisible());
        clickOn("OK");
    }
    
    //Asha Tests
    @Test
    @Order(7)
    public void checkSingleErrorSuccessfulTraversal() {
        clickOn("Play");
        clickOn("Hard");
        clickOn("Maul");
        clickOn("Next");
        clickOn("R");
        clickOn("Down");
        clickOn("R");
        clickOn("Down");
        clickOn("R");
        clickOn("Up");
        clickOn("Up");
        clickOn("Up");
        clickOn("Up");
        clickOn("Up");
        clickOn("OK");
        clickOn("Down");
        clickOn("Down");
        clickOn("Down");
        clickOn("R");
        clickOn("Up");
        clickOn("R");

    }

    @Test
    @Order(8)
    public void checkWinScreen() {
        clickOn("Play");
        clickOn("Hard");
        clickOn("Maul");
        clickOn("Next");
        clickOn("R");
        clickOn("Down");
        clickOn("R");
        clickOn("Down");
        clickOn("R");
        clickOn("Up");
        clickOn("R");
        clickOn("Up");
        clickOn("R");
        FxAssert.verifyThat(".label", LabeledMatchers.hasText("You Won!"));

    }
}
