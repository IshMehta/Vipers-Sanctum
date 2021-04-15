import components.Player;
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
 * Set of unit tests to test M4 implementations.
 *
 * @author Vipers
 * @version 1.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControllerTest4 extends ApplicationTest {

    private Player testPlayer;

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
        testPlayer = controller.getPlayer();
    }

    //tests go here

    //Rahul Test
    @Test
    @Order(1)
    public void testAttackPotion() {
        clickOn("Play");
        clickOn("Easy");
        clickOn("Knife");
        clickOn("Next");
        clickOn("R");
        clickOn("Attack");
        clickOn("Down");
        clickOn("Inventory");
        clickOn("#useAttack");
        clickOn("Return to game");
        clickOn("Attack");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isInvisible());
    }

    @Test
    @Order(2)
    public void testHealthPotion() {
        clickOn("Play");
        clickOn("Easy");
        clickOn("Knife");
        clickOn("Next");
        clickOn("Inventory");
        clickOn("#useHealth");
        clickOn("Return to game");
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 60"));
    }

    //Su Test
    @Test
    @Order(3)
    public void testInventoryExists() {
        clickOn("Play");
        clickOn("Medium");
        clickOn("Maul");
        clickOn("Next");
        clickOn("R");
        clickOn("Attack");
        clickOn("Down");
        clickOn("Inventory");
        clickOn("Return to game");
    }

    @Test
    @Order(4)
    public void testPotionsExist() {
        clickOn("Play");
        clickOn("Medium");
        clickOn("Maul");
        clickOn("Next");
        clickOn("R");
        clickOn("Attack");
        clickOn("Down");
        clickOn("Inventory");
        clickOn("#useHealth");
    }

}
