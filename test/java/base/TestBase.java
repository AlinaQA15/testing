package base;

import org.junit.*;

public class TestBase {

    protected static ApplicationManager appManager;

    @BeforeClass
    public static void setUp() throws InterruptedException {
        appManager = ApplicationManager.getInstance();
        appManager.getNavigationHelper().openHomePage();
    }

    @AfterClass
    public static void tearDown() {
        appManager.stop();
    }
}
