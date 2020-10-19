package helpers;

import base.ApplicationManager;
import base.HelperBase;
import models.Account;
import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void login(Account account) throws InterruptedException {
        writeToById("username", account.getLogin());
        writeToById("password", account.getPassword());
        driver.findElement(By.id("Login")).click();
        Thread.sleep(5000);
    }

    public boolean isLoggedIn() {
        return !driver.findElement(By.id("Login")).isDisplayed();
    }

    private void writeToById(String where, String what) {
        driver.findElement(By.id(where)).click();
        driver.findElement(By.id(where)).clear();
        driver.findElement(By.id(where)).sendKeys(what);
    }

    public void logout() throws InterruptedException {
        driver.get(applicationManager.baseUrl + "logout");
        Thread.sleep(3000);
    }

    public void clickLoginViaSalesforce() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='slds-button slds-button_neutral slds-button_stretch']")).click();
        Thread.sleep(3000);
    }

    public void logoutAndloginAsAnotherUser(Account account) throws InterruptedException {
        logout();
        clickLoginViaSalesforce();
        login(new Account(account.getLogin(), account.getPassword()));
        Thread.sleep(5000);
    }
}
