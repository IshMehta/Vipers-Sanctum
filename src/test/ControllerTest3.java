import controller.ControllerT;
import javafx.stage.Stage;


import org.junit.Assert;
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
public class ControllerTest3 extends ApplicationTest {



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

    //tests go here
    //Rahul Test
    @Test
    @Order(1)
    public void testMonsterFaint() {
        clickOn("Play");
        clickOn("Easy");
        clickOn("Knife");
        clickOn("Next");
        clickOn("R");
        clickOn("Attack");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isInvisible());
    }

    @Test
    @Order(2)
    public void testNumMonsters() {
        clickOn("Play");
        clickOn("Easy");
        clickOn("Knife");
        clickOn("Next");
        clickOn("R");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        clickOn("Attack");
        clickOn("Down");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        clickOn("Attack");
        clickOn("Down");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        clickOn("Attack");
        clickOn("Up");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        clickOn("Attack");
        clickOn("Up");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());

    }

    //Su Tests
    @Test
    @Order(3)
    public void testAttack() {
        clickOn("Play");
        clickOn("Easy");
        clickOn("Maul");
        clickOn("Next");
        clickOn("R");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        clickOn("Attack");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isInvisible());
    }

    @Test
    @Order(4)
    public void testRetreat()  {
        clickOn("Play");
        clickOn("Easy");
        clickOn("Knife");
        clickOn("Next");
        clickOn("R");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        clickOn("Attack");
        clickOn("Down");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        clickOn("Up");
    }

}

