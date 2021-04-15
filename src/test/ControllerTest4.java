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

    @Test
    @Order(3)
    public void testWeaponTypes() {
        clickOn("Play");
        clickOn("Easy");
        clickOn("Knife");
        clickOn("Next");
        clickOn("Inventory");
        FxAssert.verifyThat("#selectBow", NodeMatchers.isVisible());
        FxAssert.verifyThat("#selectMaul", NodeMatchers.isVisible());
        FxAssert.verifyThat("#selectKnife", NodeMatchers.isVisible());
        FxAssert.verifyThat("#selectSword", NodeMatchers.isVisible());
    }

    //Su Test
    @Test
    @Order(4)
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
    @Order(5)
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

    // Dip's Test
    @Test
    @Order(5)
    public void testWeaponsExist() {
        clickOn("Play");
        clickOn("Medium");
        clickOn("Maul");
        clickOn("Next");
        clickOn("Inventory");
        clickOn("2");
        clickOn("1");
    }

    @Test
    @Order(6)
    public void testAddWeapons() {
         clickOn("R");
        clickOn("Attack");
        clickOn("Confirm the Kill");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Confirm the Kill");
        clickOn("Inventory");
        clickOn("1");
      
    //Ish Test
    @Test
    @Order(6)
    public void testInventoryStartKnife() {
        clickOn("Play");
        clickOn("Medium");
        clickOn("Knife");
        clickOn("Next");
        clickOn("Inventory");
        FxAssert.verifyThat("#selectKnife", LabeledMatchers.hasText("2"));
    }

    @Test
    @Order(7)
    public void testInventoryStartMaul() {
        clickOn("Play");
        clickOn("Medium");
        clickOn("Maul");
        clickOn("Next");
        clickOn("Inventory");
        FxAssert.verifyThat("#selectMaul", LabeledMatchers.hasText("2"));
    }

    @Test
    @Order(8)
    public void testInventoryStartSword() {
        clickOn("Play");
        clickOn("Medium");
        clickOn("Sword");
        clickOn("Next");
        clickOn("Inventory");
        FxAssert.verifyThat("#selectSword", LabeledMatchers.hasText("2"));
    }

    @Test
    @Order(9)
    public void testInventoryStartBow() {
        clickOn("Play");
        clickOn("Medium");
        clickOn("Bow");
        clickOn("Next");
        clickOn("Inventory");
        FxAssert.verifyThat("#selectBow", LabeledMatchers.hasText("2"));
    }

    @Test
    @Order(10)
    public void testSelectWeapons() {
        clickOn("Play");
        clickOn("Medium");
        clickOn("Bow");
        clickOn("Next");
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Bow"));
        clickOn("Inventory");
        FxAssert.verifyThat("#selectBow", LabeledMatchers.hasText("2"));
        FxAssert.verifyThat("#selectMaul", LabeledMatchers.hasText("1"));
        clickOn("#selectMaul");
        clickOn("Return to game");
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Maul"));
        clickOn("Inventory");
        FxAssert.verifyThat("#selectKnife", LabeledMatchers.hasText("1"));
        clickOn("#selectKnife");
        clickOn("Return to game");
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Knife"));
        clickOn("Inventory");
        FxAssert.verifyThat("#selectSword", LabeledMatchers.hasText("1"));
        clickOn("#selectSword");
        clickOn("Return to game");
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Sword"));
    }
}
