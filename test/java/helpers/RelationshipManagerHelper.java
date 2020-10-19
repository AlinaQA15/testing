package helpers;

import base.ApplicationManager;
import base.HelperBase;
import javafx.util.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class RelationshipManagerHelper extends HelperBase {

    public RelationshipManagerHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void saveRelationships() throws InterruptedException {
        driver.findElement(By.id("save-relationships")).click();
        Thread.sleep(2000);
    }

    public void selectAllChildObjects() throws InterruptedException {
        List<WebElement> list = driver.findElements(By.xpath("//table[@class='slds-table slds-table_cell-buffer slds-table_bordered table__row--height-sm']//td[1]"));
        int countOfChildObjects = list.size();
        for (int i = 1; i <= countOfChildObjects; i++) {
            if (!driver.findElement(By.xpath("//tr[" + i + "]//td[1]//div[1]//label[1]//input[1]")).isSelected()) {
                driver.findElement(By.xpath("//tr[" + i + "]/td/div/label/span[2]/span[3]")).click();
            }
        }
    }

    public void loadAllRelationships() throws InterruptedException {
        driver.findElement(By.id("load-all-lm")).click();
        Thread.sleep(3000);
    }

    public void selectMappedSalesforceObject(String objectAPIname) throws InterruptedException {
        driver.findElement(By.id("manager-object")).click();
        new Select(driver.findElement(By.id("manager-object"))).selectByVisibleText(objectAPIname);
        driver.findElement(By.id("manager-object")).click();
        Thread.sleep(5000);
    }

    public void enableSystemMode() throws InterruptedException {
        driver.findElement(By.id("enable-system-mode")).click();
        Thread.sleep(2000);
    }

    public void confirmEnablingSystemMode() throws InterruptedException {
        driver.findElement(By.id("prompt-ok")).click();
        Thread.sleep(3000);
    }

    public void cancellEnablingSystemMode() {
        driver.findElement(By.id("prompt-cancel")).click();
    }

    public void clickArchivingOrder() throws InterruptedException {
        driver.findElement(By.id("archiving-order")).click();
        Thread.sleep(2000);
    }

    public void clickApplyInModalWindow() throws InterruptedException {
        driver.findElement(By.id("relationship-order-apply")).click();
        Thread.sleep(2000);
    }

    public void clickCancelInModalWindow() throws InterruptedException {
        driver.findElement(By.id("relationship-order-cancel")).click();
        Thread.sleep(2000);
    }

    public void clickUnarchivingOrder() throws InterruptedException {
        driver.findElement(By.id("unarchiving-order")).click();
        Thread.sleep(2000);
    }

    public SortedMap<Integer, String> getChildrenOrderInModalWindow() {
        TreeMap<Integer, String> sortedMap = new TreeMap<>();
        List<WebElement> list = driver.findElements(By.xpath("//div[@id='modal-content-id-1']/div/div/div/table/tbody/tr/td[2]/div"));
        int i = 1;
        int id;
        String objectAPIname;
        for (WebElement webElement : list) {
            id = Integer.parseInt(webElement.findElement(By.xpath("//tr[" + i + "]//th[1]//div[1]")).getText());
            objectAPIname = webElement.findElement(By.xpath("//tr[" + i + "]//td[2]//div[1]")).getText();
            sortedMap.put(id, objectAPIname);
            i++;
        }
        return sortedMap;
    }

    public void changeChildrenOrderInModalWindow() {
        driver.findElement(By.xpath("//div[@id='modal-content-id-1']/div/div/div/table/tbody/tr[2]/td/button[3]")).click();
    }

    public Pair<String, String> changeSOQLQueryTypeAndAVGchildrenForOneParentRecord() {
        String avgChildrenForOneParentRecord;//td[4]/div/select
        String xPathToSoqlQueryType = "//tr[1]//select[@data-select-type='soql-query-type']";
        String soqlQueryType = driver.findElement(By.xpath(xPathToSoqlQueryType)).getAttribute("value");
        if (soqlQueryType.contains("DIRECT")) {
            soqlQueryType = "VIA PARENT OBJECT";
            driver.findElement(By.xpath(xPathToSoqlQueryType)).click();
            new Select(driver.findElement(By.xpath(xPathToSoqlQueryType))).selectByVisibleText(soqlQueryType);
            driver.findElement(By.xpath(xPathToSoqlQueryType)).click();
            String xPathToAvgChildren = "//input[@data-input-type='avg-children-count-for-parent']";
            avgChildrenForOneParentRecord = driver.findElement(By.xpath(xPathToAvgChildren)).getAttribute("value") + 0;
            driver.findElement(By.xpath(xPathToAvgChildren)).click();
            driver.findElement(By.xpath(xPathToAvgChildren)).clear();
            driver.findElement(By.xpath(xPathToAvgChildren)).sendKeys(avgChildrenForOneParentRecord);
            return new Pair<>("VIA_PARENT_OBJECT", avgChildrenForOneParentRecord);
        } else {
            soqlQueryType = "DIRECT";
            driver.findElement(By.xpath(xPathToSoqlQueryType)).click();
            new Select(driver.findElement(By.xpath(xPathToSoqlQueryType))).selectByVisibleText(soqlQueryType);
            driver.findElement(By.xpath(xPathToSoqlQueryType)).click();
            avgChildrenForOneParentRecord = null;
            return new Pair<>(soqlQueryType, avgChildrenForOneParentRecord);
        }
    }

    public void clickLoadAllRelationships() throws InterruptedException {
        driver.findElement(By.xpath("//div[2]/div/div[2]/div/div[3]/div/button")).click();
        Thread.sleep(5000);
    }

    public void selectAllPseudoChildObjects() {
        List<WebElement> list = driver.findElements(By.xpath("//table[@class='slds-table slds-table_cell-buffer slds-table_bordered slds-m-bottom_medium table__row--height-sm']//td[1]"));
        String path;
        int countOfChildObjects = list.size();
        for (int i = 1; i <= countOfChildObjects; i++) {
            if (i == 1)
                path = "//div[3]/table/tbody/tr/td/div/label/span[2]/span[3]";
            else
                path = "//div[3]/table/tbody/tr[" + i + "]/td/div/label/span[2]/span[3]";
            if (!driver.findElement(By.xpath(path)).isSelected()) {
                driver.findElement(By.xpath(path)).click();
            }
        }
    }
}
