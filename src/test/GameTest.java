import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Basic set of unit tests for the game.
 *
 * @author Vipers
 * @version 1.0
 */
public class GameTest {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Controller controller = new Controller();
        controller.start(primaryStage);
    }
    // Diptendu Maity JUnit Test
    @Test
    public void playerNameBlankCheck() {
        clickOn("Play");
        write("  ");
        clickOn("Submit Name");
        clickOn("OK");
    }
    // Diptendu Maity JUnit Test
    @Test
    public void selectDifficultyCheck() {
        clickOn("Play");
        clickOn("Easy");
        clickOn("Medium");
        clickOn("Hard");
    }

    @BeforeEach
	public void setup() {
	    //
	}

	//Asha Test
	@Test
	public void AshaTest() {
	    //
	}

	//Dip Test
	@Test
	public void DipTest() {
	    //
	}

	//Rahul Test
	@Test
	public void RahulTest() {
	    //
	}

	//Ish Test
	@Test
	public void IshTest() {
	    //
	}

	//Su Test
	@Test
	public void SuTest() {
	    //
	}

}