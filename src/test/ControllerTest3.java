import components.Player;
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
import view.GameScreen;


import static org.testfx.api.FxToolkit.registerPrimaryStage;
import static org.testfx.api.FxToolkit.setupApplication;

/**
 * Set of unit tests to test M4 implementations.
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
        clickOn("Confirm the Kill");
        clickOn("Down");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        clickOn("Retreat");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isInvisible());
    }

    //Ish Test

    @Test
    public void testDamage() {
        clickOn("Play");
        clickOn("Easy");
        clickOn("Knife");
        clickOn("Next");
        clickOn("R");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        FxAssert.verifyThat("#monsterLabel", LabeledMatchers.hasText("You are fighting a Goblin" + "\nHP: 5"));
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 50"));
        clickOn("Attack");
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 47"));
        clickOn("Down");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        FxAssert.verifyThat("#monsterLabel", LabeledMatchers.hasText("You are fighting a Goblin Commander" + "\nHP: 10"));
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 47"));
        clickOn("Attack");
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 41"));
        FxAssert.verifyThat("#monsterLabel", LabeledMatchers.hasText("You are fighting a Goblin Commander" + "\nHP: 5"));
        clickOn("Attack");
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 35"));
        clickOn("R");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 35"));
        FxAssert.verifyThat("#monsterLabel", LabeledMatchers.hasText("You are fighting a Goblin" + "\nHP: 5"));
        clickOn("Attack");
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 32"));
        clickOn("Down");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 32"));
        FxAssert.verifyThat("#monsterLabel", LabeledMatchers.hasText("You are fighting a Goblin Commander" + "\nHP: 10"));
        clickOn("Attack");
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 26"));
        FxAssert.verifyThat("#monsterLabel", LabeledMatchers.hasText("You are fighting a Goblin Commander" + "\nHP: 5"));
        clickOn("Attack");
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 20"));
        clickOn("R");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 20"));
        FxAssert.verifyThat("#monsterLabel", LabeledMatchers.hasText("You are fighting a Goblin" + "\nHP: 5"));
        clickOn("Attack");
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 17"));
        clickOn("Up");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 17"));
        FxAssert.verifyThat("#monsterLabel", LabeledMatchers.hasText("You are fighting a Goblin Commander" + "\nHP: 10"));
        clickOn("Attack");
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 11"));
        FxAssert.verifyThat("#monsterLabel", LabeledMatchers.hasText("You are fighting a Goblin Commander" + "\nHP: 5"));
        clickOn("Attack");
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 5"));
        clickOn("R");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 5"));
        FxAssert.verifyThat("#monsterLabel", LabeledMatchers.hasText("You are fighting a Goblin" + "\nHP: 5"));
        clickOn("Attack");
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 2"));
        clickOn("Up");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        FxAssert.verifyThat("#playerLabel", LabeledMatchers.hasText("HP: 2"));
        FxAssert.verifyThat("#monsterLabel", LabeledMatchers.hasText("You are fighting the Viper" + "\nHP: 20"));
    }

    @Test
    public void testIfUnlockedDoor(){
        clickOn("Play");
        clickOn("Easy");
        clickOn("Knife");
        clickOn("Next");
        clickOn("R");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        FxAssert.verifyThat("Up", NodeMatchers.isInvisible());
        FxAssert.verifyThat("Down", NodeMatchers.isInvisible());
        FxAssert.verifyThat("R", NodeMatchers.isInvisible());
        FxAssert.verifyThat("L", NodeMatchers.isInvisible());
        clickOn("Attack");
        FxAssert.verifyThat("Up", NodeMatchers.isVisible());
        FxAssert.verifyThat("Down", NodeMatchers.isVisible());
        FxAssert.verifyThat("R", NodeMatchers.isVisible());
        FxAssert.verifyThat("L", NodeMatchers.isVisible());
    }


}

