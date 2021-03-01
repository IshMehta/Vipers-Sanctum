package test;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import view.GameScreen;
import static org.testfx.api.FxToolkit.registerPrimaryStage;
import static org.testfx.api.FxToolkit.setupApplication;

/**
 * Basic set of unit tests for the game.
 *
 * @author Vipers
 * @version 1.0
 */
public class ControllerTest extends ApplicationTest {

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

    //Rahul Test
    @Test
    public void testTransferData() {
        GameScreen game = new GameScreen("easy", "maul");
        Assertions.assertEquals(game.getWeapon(), "maul");
        Assertions.assertEquals(game.getDifficulty(), "easy");
    }

    @Test
    public void testInitializationGame() {
        clickOn("Play");
        clickOn("Easy");
        clickOn("Maul");
        clickOn("Next");
        FxAssert.verifyThat("#moneyStatus", LabeledMatchers.hasText("Money: 10"));
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: Maul"));
    }

    @Test
    public void testExitButtons() {
        clickOn("Play");
        clickOn("Easy");
        clickOn("Maul");
        clickOn("Next");
        clickOn("Up");
        clickOn("Down");
        clickOn("R");
        clickOn("L");
    }

    @Test
    public void testValidNameEntered() {
        clickOn("Play");
        write("Rahul");
        clickOn("Submit Name");
        FxAssert.verifyThat("#validity", LabeledMatchers.hasText("Valid name has been entered!"));
    }

    @Test
    public void testNullWeapon() {
        clickOn("Play");
        clickOn("Easy");
        clickOn("Next");
        FxAssert.verifyThat("#moneyStatus", LabeledMatchers.hasText("Money: 10"));
        FxAssert.verifyThat("#weaponStatus", LabeledMatchers.hasText("Weapon: null"));
    }

    @Test
    public void testNoLevelSelected() {
        clickOn("Play");
        clickOn("Maul");
        clickOn("Next");
        FxAssert.verifyThat(".label", LabeledMatchers.hasText("Configuration Screen"));
    }

    //Asha Test
    @Test
    public void testWelcomeScreenandQuit() {
        FxAssert.verifyThat(".label", LabeledMatchers.hasText("The Viper's Sanctum!"));
        FxAssert.verifyThat(".button", LabeledMatchers.hasText("Play"));
        clickOn("Quit");
    }

    @Test
    public void testNavigation() {
        clickOn("Play");
        FxAssert.verifyThat(".label", LabeledMatchers.hasText("Configuration Screen"));
        clickOn("Hard");
        clickOn("Maul");
        clickOn("Next");
        FxAssert.verifyThat(".label", LabeledMatchers.hasText("Money: 5"));
    }

    // Diptendu Test
    @Test
    public void playerNameBlankCheck() {
        clickOn("Play");
        write("  ");
        clickOn("Submit Name");
        clickOn("OK");
    }

    @Test
    public void selectDifficultyCheck() {
        clickOn("Play");
        clickOn("Easy");
        clickOn("Medium");
        clickOn("Hard");
    }


}