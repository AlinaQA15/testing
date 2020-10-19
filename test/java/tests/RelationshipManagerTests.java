package tests;

import base.AuthBase;
import models.ObjectConfiguration;
import models.Relationship;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;

public class RelationshipManagerTests extends AuthBase {
    public static String objectAPIname = "account (Account)";
    public static Relationship tableColumnsViaAlookupColumn = new Relationship("Child Object",
            "Parent Field", "Is Cascade Deletion in SF", "Deletion Restricted in SF");
    public static Relationship tableColumnsViaAlookupColumnInSystemMode = new Relationship("Child Object",
            "Parent Field", "SOQL Query Type", "AVG children for 1 parent record",
            "Use Cascade Deletion", "Is Cascade Deletion in SF", "Deletion Restricted in SF");
    public static Relationship tableColumnsViaAlookupMapping = new Relationship("Child Object",
            "Parent Field", "SOQL Query Type", "AVG children for 1 parent record",
            "Use Cascade Deletion", "Is Cascade Deletion in SF", "Deletion Restricted in SF");
    public static ObjectConfiguration objectConfiguration = new ObjectConfiguration("account (Account)", "SF SOAP");

    @BeforeClass
    public static void setObjectConfiguration() throws InterruptedException {
        appManager.getNavigationHelper().openObjectConfigurationPageFromURL();
        appManager.getObjectConfigurationHelper().clickCreateNewMapping();
        appManager.getObjectConfigurationHelper().selectTypeAndObjectAPINameOnTheFirstModalWindow(objectConfiguration.getType(), objectConfiguration.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickNextOnTheFirstModalWindow();
        appManager.getObjectConfigurationHelper().selectAllCheckboxes();
        appManager.getObjectConfigurationHelper().clickValidateAndSave();
        try {
            appManager.getObjectConfigurationHelper().tryToAutoFix();
            Assert.assertTrue(appManager.getMessageHelper().successfullAutoFixMessage());
            appManager.getObjectConfigurationHelper().clickValidateAndSaveAfterAutoFix();
        } catch (NoSuchElementException | ElementClickInterceptedException e) {
        }
    }

    /*
    @Test
    public void saveRelationship() throws InterruptedException {
        appManager.getNavigationHelper().openHomePage();
        appManager.getNavigationHelper().openRelationshipManagerPageFromHomePage();
        appManager.getRelationshipManagerHelper().selectMappedSalesforceObject(objectAPIname);
        appManager.getRelationshipManagerHelper().selectAllChildObjects();
        appManager.getRelationshipManagerHelper().clickUnarchivingOrder();
        SortedMap<Integer, String> childrenInUnarchivingModalWindow1 = appManager.getRelationshipManagerHelper().getChildrenOrderInModalWindow();
        appManager.getRelationshipManagerHelper().clickCancelInModalWindow();
        appManager.getRelationshipManagerHelper().clickArchivingOrder();
        appManager.getRelationshipManagerHelper().changeChildrenOrderInModalWindow();
        SortedMap<Integer, String> childrenInArchivingModalWindowAfterChanging = appManager.getRelationshipManagerHelper().getChildrenOrderInModalWindow();
        appManager.getRelationshipManagerHelper().clickApplyInModalWindow();
        appManager.getRelationshipManagerHelper().saveRelationships();
        Assert.assertTrue(appManager.getMessageHelper().relationshipSavedMessage());
        appManager.getNavigationHelper().openRelationshipManagerPageFromURL();
        appManager.getRelationshipManagerHelper().selectMappedSalesforceObject(objectAPIname);
        Assert.assertTrue(appManager.getAssertHelper().isAllChildObjectsSelected());
        appManager.getRelationshipManagerHelper().clickUnarchivingOrder();
        SortedMap<Integer, String> childrenInUnarchivingModalWindow2 = appManager.getRelationshipManagerHelper().getChildrenOrderInModalWindow();
        appManager.getRelationshipManagerHelper().clickCancelInModalWindow();
        appManager.getRelationshipManagerHelper().clickArchivingOrder();
        SortedMap<Integer, String> childrenInArchivingModalWindowAfterSaving = appManager.getRelationshipManagerHelper().getChildrenOrderInModalWindow();
        Assert.assertEquals(childrenInArchivingModalWindowAfterChanging, childrenInArchivingModalWindowAfterSaving);
        Assert.assertEquals(childrenInUnarchivingModalWindow1, childrenInUnarchivingModalWindow2);
    }

     */

    @Test
    public void selectAllWithoutSaving() throws InterruptedException {
        appManager.getNavigationHelper().openHomePage();
        appManager.getNavigationHelper().openRelationshipManagerPageFromHomePage();
        appManager.getRelationshipManagerHelper().selectMappedSalesforceObject(objectAPIname);
        appManager.getRelationshipManagerHelper().selectAllChildObjects();
        appManager.getNavigationHelper().openRelationshipManagerPageFromURL();
        appManager.getRelationshipManagerHelper().selectMappedSalesforceObject(objectAPIname);
        Assert.assertFalse(appManager.getAssertHelper().isAllChildObjectsSelected());
    }

    /*
    @Test
    public void changeArchivingOrder_cancel() throws InterruptedException {
        appManager.getNavigationHelper().openRelationshipManagerPageFromURL();
        appManager.getRelationshipManagerHelper().selectMappedSalesforceObject(objectAPIname);
        appManager.getRelationshipManagerHelper().selectAllChildObjects();
        appManager.getRelationshipManagerHelper().clickArchivingOrder();
        appManager.getRelationshipManagerHelper().changeChildrenOrderInModalWindow();
        SortedMap<Integer, String> childrenInModalWindowAfterChanging = appManager.getRelationshipManagerHelper().getChildrenOrderInModalWindow();
        appManager.getRelationshipManagerHelper().clickCancelInModalWindow();
        appManager.getRelationshipManagerHelper().saveRelationships();
        Assert.assertTrue(appManager.getMessageHelper().relationshipSavedMessage());
        appManager.getNavigationHelper().openRelationshipManagerPageFromURL();
        appManager.getRelationshipManagerHelper().selectMappedSalesforceObject(objectAPIname);
        Assert.assertTrue(appManager.getAssertHelper().isAllChildObjectsSelected());
        appManager.getRelationshipManagerHelper().clickArchivingOrder();
        SortedMap<Integer, String> childrenInModalWindowAfterSaving = appManager.getRelationshipManagerHelper().getChildrenOrderInModalWindow();
        Assert.assertNotEquals(childrenInModalWindowAfterChanging, childrenInModalWindowAfterSaving);
    }

    @Test
    public void changeUnarchivingOrder() throws InterruptedException {
        appManager.getNavigationHelper().openRelationshipManagerPageFromURL();
        appManager.getRelationshipManagerHelper().selectMappedSalesforceObject(objectAPIname);
        appManager.getRelationshipManagerHelper().selectAllChildObjects();
        appManager.getRelationshipManagerHelper().clickArchivingOrder();
        SortedMap<Integer, String> childrenInArchivingModalWindow1 = appManager.getRelationshipManagerHelper().getChildrenOrderInModalWindow();
        appManager.getRelationshipManagerHelper().clickCancelInModalWindow();
        appManager.getRelationshipManagerHelper().clickUnarchivingOrder();
        appManager.getRelationshipManagerHelper().changeChildrenOrderInModalWindow();
        SortedMap<Integer, String> childrenInModalWindowAfterChanging = appManager.getRelationshipManagerHelper().getChildrenOrderInModalWindow();
        appManager.getRelationshipManagerHelper().clickApplyInModalWindow();
        appManager.getRelationshipManagerHelper().saveRelationships();
        Assert.assertTrue(appManager.getMessageHelper().relationshipSavedMessage());
        appManager.getNavigationHelper().openRelationshipManagerPageFromURL();
        appManager.getRelationshipManagerHelper().selectMappedSalesforceObject(objectAPIname);
        Assert.assertTrue(appManager.getAssertHelper().isAllChildObjectsSelected());
        appManager.getRelationshipManagerHelper().clickArchivingOrder();
        SortedMap<Integer, String> childrenInArchivingModalWindow2 = appManager.getRelationshipManagerHelper().getChildrenOrderInModalWindow();
        appManager.getRelationshipManagerHelper().clickCancelInModalWindow();
        appManager.getRelationshipManagerHelper().clickUnarchivingOrder();
        SortedMap<Integer, String> childrenInModalWindowAfterSaving = appManager.getRelationshipManagerHelper().getChildrenOrderInModalWindow();
        Assert.assertEquals(childrenInModalWindowAfterChanging, childrenInModalWindowAfterSaving);
        Assert.assertEquals(childrenInArchivingModalWindow1, childrenInArchivingModalWindow2);
    }

    @Test
    public void changeUnarchivingOrder_cancel() throws InterruptedException {
        appManager.getNavigationHelper().openRelationshipManagerPageFromURL();
        appManager.getRelationshipManagerHelper().selectMappedSalesforceObject(objectAPIname);
        appManager.getRelationshipManagerHelper().selectAllChildObjects();
        appManager.getRelationshipManagerHelper().clickUnarchivingOrder();
        appManager.getRelationshipManagerHelper().changeChildrenOrderInModalWindow();
        SortedMap<Integer, String> childrenInModalWindowAfterChanging = appManager.getRelationshipManagerHelper().getChildrenOrderInModalWindow();
        appManager.getRelationshipManagerHelper().clickCancelInModalWindow();
        appManager.getRelationshipManagerHelper().saveRelationships();
        Assert.assertTrue(appManager.getMessageHelper().relationshipSavedMessage());
        appManager.getNavigationHelper().openRelationshipManagerPageFromURL();
        appManager.getRelationshipManagerHelper().selectMappedSalesforceObject(objectAPIname);
        Assert.assertTrue(appManager.getAssertHelper().isAllChildObjectsSelected());
        appManager.getRelationshipManagerHelper().clickUnarchivingOrder();
        SortedMap<Integer, String> childrenInModalWindowAfterSaving = appManager.getRelationshipManagerHelper().getChildrenOrderInModalWindow();
        Assert.assertNotEquals(childrenInModalWindowAfterChanging, childrenInModalWindowAfterSaving);
    }

    @Test
    public void enableSystemMode() throws InterruptedException {
        appManager.getNavigationHelper().openRelationshipManagerPageFromURL();
        appManager.getRelationshipManagerHelper().selectMappedSalesforceObject(objectAPIname);
        appManager.getRelationshipManagerHelper().enableSystemMode();
        appManager.getRelationshipManagerHelper().confirmEnablingSystemMode();
        Assert.assertTrue(appManager.getAssertHelper().isSystemModeEnabled());
        Pair<String, String> pair = appManager.getRelationshipManagerHelper().changeSOQLQueryTypeAndAVGchildrenForOneParentRecord();
        appManager.getRelationshipManagerHelper().saveRelationships();
        appManager.getNavigationHelper().openRelationshipManagerPageFromURL();
        appManager.getRelationshipManagerHelper().selectMappedSalesforceObject(objectAPIname);
        appManager.getRelationshipManagerHelper().enableSystemMode();
        appManager.getRelationshipManagerHelper().confirmEnablingSystemMode();
        Assert.assertTrue(appManager.getAssertHelper().areSOQLQueryTypeAndAVGchildrenForOneParentRecordSaved(pair));
    }

    @Test
    public void enableSystemMode_cancell() throws InterruptedException {
        appManager.getNavigationHelper().openRelationshipManagerPageFromURL();
        appManager.getRelationshipManagerHelper().selectMappedSalesforceObject(objectAPIname);
        appManager.getRelationshipManagerHelper().enableSystemMode();
        appManager.getRelationshipManagerHelper().cancellEnablingSystemMode();
        Assert.assertFalse(appManager.getAssertHelper().isSystemModeEnabled());
    }

    @Test
    public void loadAndSelectAllPseudoChildObjects() throws InterruptedException {
        appManager.getNavigationHelper().openRelationshipManagerPageFromURL();
        appManager.getRelationshipManagerHelper().selectMappedSalesforceObject(objectAPIname);
        appManager.getRelationshipManagerHelper().clickLoadAllRelationships();
        appManager.getRelationshipManagerHelper().selectAllPseudoChildObjects();
        appManager.getRelationshipManagerHelper().saveRelationships();
        appManager.getNavigationHelper().openRelationshipManagerPageFromURL();
        appManager.getRelationshipManagerHelper().selectMappedSalesforceObject(objectAPIname);
        appManager.getRelationshipManagerHelper().clickLoadAllRelationships();
        Assert.assertTrue(appManager.getAssertHelper().isAllPseudoChildObjectsSelected());
    }

    @AfterClass
    public static void deleteObjectConfiguration() throws InterruptedException {
        appManager.getNavigationHelper().openObjectConfigurationPageFromURL();
        appManager.getObjectConfigurationHelper().deleteObjectConfiguration(objectConfiguration.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickOk();
    }

     */

}
