package helpers;

import base.ApplicationManager;
import base.HelperBase;
import org.openqa.selenium.*;

import java.util.ArrayList;

public class NavigationHelper extends HelperBase {

    private String baseUrl;

    public NavigationHelper(ApplicationManager applicationManager, String baseUrl) {
        super(applicationManager);
        this.baseUrl = baseUrl;
    }

    public void openHomePage() {
        driver.get(baseUrl);
    }

    public void open(String url) {
        try {
            driver.get(url);
        } catch (UnhandledAlertException f) {
            try {
                Alert alert = driver.switchTo().alert();
                alert.accept();
            } catch (NoAlertPresentException e) {
                e.printStackTrace();
            }
        }
    }

    public void openObjectConfigurationPageFromHomePage() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id("tile-object-configuration")).click();
    }

    public void openJobsPageFromHomePage() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id("tile-jobs")).click();
    }

    public void openJobConfigsPageFromHomePage() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id("tile-job-configs")).click();
    }

    public void openJobConfigsPageFromEditJobConfigPage() throws InterruptedException {
        driver.findElement(By.id("job-configs")).click();
        Thread.sleep(5000);
    }

    public void openRelationshipManagerPageFromHomePage() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id("tile-relationship-manager")).click();
        Thread.sleep(3000);
    }

    public void openAppSettingsFromHomePage() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id("tile-app-settings")).click();
        Thread.sleep(3000);
    }

    public void openJobConfigsPageFromURL() throws InterruptedException {
        driver.get(applicationManager.baseUrl + "job-configs");
        Thread.sleep(5000);
    }

    public void openCreateJobConfigPageFromURL() throws InterruptedException {
        driver.get(applicationManager.baseUrl + "job-configs/new");
        Thread.sleep(5000);
    }

    public void openJobsPageFromURL() throws InterruptedException {
        driver.get(applicationManager.baseUrl + "jobs");
        Thread.sleep(3000);
    }

    public void openObjectConfigurationPageFromURL() throws InterruptedException {
        driver.get(applicationManager.baseUrl + "object-configuration");
        Thread.sleep(5000);
    }

    public void openRelationshipManagerPageFromURL() throws InterruptedException {
        driver.get(applicationManager.baseUrl + "relationship-manager");
        Thread.sleep(5000);
    }

    public void openAppSettingsFromURL() throws InterruptedException {
        driver.get(applicationManager.baseUrl + "app-settings");
        Thread.sleep(5000);
    }

    public void openNewTab() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        Thread.sleep(3000);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void returnToTheFirstTab() throws InterruptedException {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(2000);
    }

}