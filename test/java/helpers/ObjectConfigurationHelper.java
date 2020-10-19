package helpers;

import base.ApplicationManager;
import base.HelperBase;
import models.ObjectConfiguration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ObjectConfigurationHelper extends HelperBase {

    public ObjectConfigurationHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void createSimpleObjectConfiguration(String type, String objectAPIname) throws InterruptedException {
        applicationManager.getNavigationHelper().openObjectConfigurationPageFromURL();
        clickCreateNewMapping();
        selectTypeAndObjectAPINameOnTheFirstModalWindow(type, objectAPIname);
        clickNextOnTheFirstModalWindow();
        selectAllCheckboxes();
        clickValidateAndSave();
        try {
            tryToAutoFix();
            clickValidateAndSaveAfterAutoFix();
        } catch (NoSuchElementException | ElementClickInterceptedException e) {
        }
    }

    public void clickCreateNewMapping() {
        driver.findElement(By.id("create-new-mapping")).click();
    }

    public void clickValidateAndSave() throws InterruptedException {
        driver.findElement(By.id("obj-config-wizard-step-two-save")).click();
        Thread.sleep(3000);
    }

    public void selectTypeAndObjectAPINameOnTheFirstModalWindow(String type, String objectAPIname) throws InterruptedException {
        driver.findElement(By.id("management-type")).click();
        new Select(driver.findElement(By.id("management-type"))).selectByVisibleText(type);
        driver.findElement(By.id("management-type")).click();
        Thread.sleep(3000);
        try {
            driver.findElement(By.id("object-name")).click();
            new Select(driver.findElement(By.id("object-name"))).selectByVisibleText(objectAPIname);
            driver.findElement(By.id("object-name")).click();
            Thread.sleep(3000);
        } catch (NoSuchElementException n) {
            applicationManager.getNavigationHelper().openObjectConfigurationPageFromURL();
            deleteObjectConfiguration(objectAPIname);
            clickOk();
            clickCreateNewMapping();
            driver.findElement(By.id("management-type")).click();
            new Select(driver.findElement(By.id("management-type"))).selectByVisibleText(type);
            driver.findElement(By.id("management-type")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("object-name")).click();
            new Select(driver.findElement(By.id("object-name"))).selectByVisibleText(objectAPIname);
            driver.findElement(By.id("object-name")).click();
            Thread.sleep(2000);
        }
    }

    public void clickCancelOnTheFirstModalWindow() throws InterruptedException {
        driver.findElement(By.id("obj-config-wizard-step-one-cancel")).click();
        Thread.sleep(2000);
    }

    public void clickNextOnTheFirstModalWindow() throws InterruptedException {
        driver.findElement(By.id("obj-config-wizard-step-one-next")).click();
        Thread.sleep(3000);
    }

    public int getCountOfObjectConfigurations() {
        List<WebElement> list = driver.findElements(By.xpath("//tr//td[1]"));
        return list.size();
    }

    public void selectAllAndCancelOnTheSecondModalWindow() throws InterruptedException {
        try {
            driver.findElement(By.id("select-all-fields")).click();
        } catch (NoSuchElementException | ElementClickInterceptedException e) {
        }
        driver.findElement(By.id("obj-config-wizard-step-two-cancel")).click();
        Thread.sleep(2000);
    }

    public void clickPreviousOnTheSecondModalWindow() throws InterruptedException {
        driver.findElement(By.id("obj-config-wizard-step-two-prev")).click();
        Thread.sleep(3000);
    }

    public void selectAllCheckboxes() throws InterruptedException {
        if (!driver.findElement(By.id("select-all-fields")).isSelected()) {
            try {
                driver.findElement(By.id("select-all-fields")).click();
            } catch (ElementClickInterceptedException e) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", driver.findElement(By.id("select-all-fields")));
            }
        }
        Thread.sleep(2000);
    }

    public void removeSelectAllCheckboxes() throws InterruptedException {
        if (driver.findElement(By.id("select-all-fields")).isSelected()) {
            try {
                driver.findElement(By.id("select-all-fields")).click();
            } catch (ElementClickInterceptedException e) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", driver.findElement(By.id("select-all-fields")));
            }
        }
        Thread.sleep(2000);
    }

    public void tryToAutoFix() throws InterruptedException {
        driver.findElement(By.id("obj-config-wizard-try-to-auto-fix")).click();
        Thread.sleep(3000);
    }

    public ObjectConfiguration getObjectConfigurationByObjectAPIName(String objectAPIname) {
        List<WebElement> list = driver.findElements(By.xpath("//tr//td[1]"));
        String type, readOnly;
        int row = 1;
        for (WebElement webElement : list) {
            String name = webElement.getText();
            if (name.contains(objectAPIname)) {
                type = webElement.findElement(By.xpath("//tr[" + row + "]//td[2]")).getText();
                readOnly = webElement.findElement(By.xpath("//tr[" + row + "]//td[3]")).getText();
                return new ObjectConfiguration(objectAPIname, type, readOnly, row);
            }
            row++;
        }
        return null;
    }


    public void deleteObjectConfiguration(String objectAPIname) throws InterruptedException {
        ObjectConfiguration objectConfiguration = getObjectConfigurationByObjectAPIName(objectAPIname);
        if (objectConfiguration.getObjectAPIname().equalsIgnoreCase(objectAPIname)) {
            driver.findElement(By.xpath("//tr[" + objectConfiguration.getRow() + "]//td//button[@data-btn-type='delete']")).click();
        }
        Thread.sleep(3000);
    }

    public void editObjectConfiguration(String objectAPIname) throws InterruptedException {
        ObjectConfiguration objectConfiguration = getObjectConfigurationByObjectAPIName(objectAPIname);
        if (objectConfiguration.getObjectAPIname().equalsIgnoreCase(objectAPIname)) {
            driver.findElement(By.xpath("//tr[" + objectConfiguration.getRow() + "]//td//button[@date-btn-type='edit']")).click();
        }
        Thread.sleep(3000);
    }


    public void clickOk() throws InterruptedException {
        driver.findElement(By.id("prompt-ok")).click();
        Thread.sleep(3000);
    }

    public void clickCancel() throws InterruptedException {
        driver.findElement(By.id("prompt-cancel")).click();
        Thread.sleep(2000);
    }

    public void selectNewType(String type1) throws InterruptedException {
        driver.findElement(By.id("management-type")).click();
        new Select(driver.findElement(By.id("management-type"))).selectByVisibleText(type1);
        driver.findElement(By.id("management-type")).click();
        Thread.sleep(3000);
    }

    public void clickValidateAndSaveAfterAutoFix() throws InterruptedException {
        driver.findElement(By.id("obj-config-wizard-step-warning-save")).click();
        Thread.sleep(3000);
    }

    public void cancelOnTheThirdModalWindow() throws InterruptedException {
        driver.findElement(By.id("obj-config-wizard-step-warning-cancel")).click();
        Thread.sleep(2000);
    }

    public void clickReadOnly() throws InterruptedException {
        try {
            driver.findElement(By.id("is-read-only")).click();
        } catch (ElementClickInterceptedException e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(By.id("is-read-only")));
        }
        Thread.sleep(2000);
    }

    public int getSizeOfListOfObjectConfigurations() {
        return driver.findElements(By.xpath("//tr//td[1]")).size();
    }
}
