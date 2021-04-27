import controller.DungeonAppT;
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
        setupApplication(DungeonAppT.class);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        DungeonAppT dungeonApplication = new DungeonAppT();
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
        clickOn("Down");
        clickOn("Attack");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        FxAssert.verifyThat("#roomNoLabel", LabeledMatchers.hasText("Room: Challenge"));
        clickOn("Decline?");
        clickOn("R");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("Up");
        clickOn("Attack");
        FxAssert.verifyThat("#roomNoLabel", LabeledMatchers.hasText("Room: Challenge"));
        clickOn("Decline?");
        clickOn("Up");
        FxAssert.verifyThat("#roomNoLabel", LabeledMatchers.hasText("Room: 9"));
    }

    @Test
    @Order(2)
    public void checkIfEmpty() {
        clickOn("Play");
        clickOn("#nameTextField");
        write("GG");
        clickOn("Submit Name");
        clickOn("#easyButton");
        clickOn("#knifeButton");
        clickOn("Next");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isInvisible());
        FxAssert.verifyThat("#playerLabel", NodeMatchers.isInvisible());
        FxAssert.verifyThat("#roomNoLabel", LabeledMatchers.hasText("Room: Challenge"));
    }

    //Su Test
    @Test
    @Order(3)
    public void checkDeclineChallenge() {
        clickOn("Play");
        clickOn("#nameTextField");
        write("Su");
        clickOn("Submit Name");
        clickOn("#easyButton");
        clickOn("#knifeButton");
        clickOn("Next");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        FxAssert.verifyThat("#roomNoLabel", LabeledMatchers.hasText("Room: Challenge"));
        clickOn("Decline?");
        clickOn("R");
    }

    @Test
    @Order(4)
    public void testChallengeRoomExits() {
        clickOn("Play");
        clickOn("#nameTextField");
        write("Su");
        clickOn("Submit Name");
        clickOn("#easyButton");
        clickOn("#knifeButton");
        clickOn("Next");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        FxAssert.verifyThat("#roomNoLabel", LabeledMatchers.hasText("Room: Challenge"));
        clickOn("Accept?");
        clickOn("L");
    }

    //Diptendu Test
    @Test
    @Order(5)
    public void testEndGameScreen() {
        clickOn("Play");
        clickOn("#nameTextField");
        write("GG");
        clickOn("Submit Name");
        clickOn("#easyButton");
        clickOn("#knifeButton");
        clickOn("Next");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        clickOn("Decline?");
        clickOn("R");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("Up");
        clickOn("Attack");
        clickOn("Decline?");
        clickOn("Up");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        clickOn("Quit");
    }

    @Test
    @Order(6)
    public void checkEGScreen() {
        clickOn("Play");
        clickOn("#nameTextField");
        write("GG");
        clickOn("Submit Name");
        clickOn("#easyButton");
        clickOn("#knifeButton");
        clickOn("Next");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        clickOn("Decline?");
        clickOn("R");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("Up");
        clickOn("Attack");
        clickOn("Decline?");
        clickOn("Up");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        clickOn("Restart");
    }
    
    //Asha Test
    @Test
    @Order(7)
    public void containsBoss() {
        clickOn("Play");
        clickOn("#nameTextField");
        write("Asha Redhead");
        clickOn("Submit Name");
        clickOn("#easyButton");
        clickOn("#knifeButton");
        clickOn("Next");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        clickOn("Decline?");
        clickOn("R");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("Up");
        clickOn("Attack");
        clickOn("Decline?");
        clickOn("Up");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
    }

    @Test
    @Order(8)
    public void testChallengeRoomMonsters() {
        clickOn("Play");
        clickOn("#nameTextField");
        write("GG");
        clickOn("Submit Name");
        clickOn("#easyButton");
        clickOn("#knifeButton");
        clickOn("Next");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Down");
        clickOn("Attack");
        clickOn("Attack");
        clickOn("R");
        clickOn("Attack");
        FxAssert.verifyThat("#roomNoLabel", LabeledMatchers.hasText("Room: Challenge"));
        clickOn("Accept?");
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());
        FxAssert.verifyThat("#monsterLabel", NodeMatchers.isVisible());

    }

}
