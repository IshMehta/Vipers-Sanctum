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

}

