package base;

import models.Account;
import org.junit.BeforeClass;

public class AuthBase extends TestBase {

    @BeforeClass
    public static void setUp() throws InterruptedException {
        TestBase.setUp();
        if (!appManager.getLoginHelper().isLoggedIn()) {
            appManager.getLoginHelper().login(new Account(Settings.getLogin(), Settings.getPassword()));
        }
    }

}
