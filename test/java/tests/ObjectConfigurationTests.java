package tests;

import base.AuthBase;
import models.ObjectConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;

public class ObjectConfigurationTests extends AuthBase {
    public static ObjectConfiguration objectConfigurationHC =
            new ObjectConfiguration("accounthistory (Account History)", "HC");
    public static ObjectConfiguration objectConfigurationSFSOAP =
            new ObjectConfiguration("accountfeed (Account Feed)", "SF SOAP");

    @Test
    public void createNewMapping() throws InterruptedException {
        appManager.getNavigationHelper().openObjectConfigurationPageFromURL();
        appManager.getObjectConfigurationHelper().clickCreateNewMapping();
        appManager.getObjectConfigurationHelper().selectTypeAndObjectAPINameOnTheFirstModalWindow(objectConfigurationHC.getType(),
                objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickNextOnTheFirstModalWindow();
        appManager.getObjectConfigurationHelper().selectAllCheckboxes();
        appManager.getObjectConfigurationHelper().clickValidateAndSave();
        try {
            appManager.getObjectConfigurationHelper().tryToAutoFix();
            Assert.assertTrue(appManager.getMessageHelper().successfullAutoFixMessage());
            appManager.getObjectConfigurationHelper().clickValidateAndSaveAfterAutoFix();
        } catch (NoSuchElementException | ElementClickInterceptedException e) {
        }
        Assert.assertTrue(appManager.getMessageHelper().objectConfigurationSavedMessage());
        appManager.getObjectConfigurationHelper().deleteObjectConfiguration(objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickOk();
    }

    /*
    @Test
    public void createNewMapping_removeSelectAll() throws InterruptedException {
        appManager.getNavigationHelper().openObjectConfigurationPageFromURL();
        appManager.getObjectConfigurationHelper().clickCreateNewMapping();
        appManager.getObjectConfigurationHelper().selectTypeAndObjectAPINameOnTheFirstModalWindow(objectConfigurationHC.getType(),
                objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickNextOnTheFirstModalWindow();
        appManager.getObjectConfigurationHelper().removeSelectAllCheckboxes();
        appManager.getObjectConfigurationHelper().clickValidateAndSave();
        try {
            appManager.getObjectConfigurationHelper().tryToAutoFix();
            Assert.assertTrue(appManager.getMessageHelper().successfullAutoFixMessage());
            appManager.getObjectConfigurationHelper().clickValidateAndSaveAfterAutoFix();
        } catch (NoSuchElementException | ElementClickInterceptedException e) {
        }
        Assert.assertTrue(appManager.getMessageHelper().objectConfigurationSavedMessage());
        appManager.getObjectConfigurationHelper().deleteObjectConfiguration(objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickOk();
    }


    @Test
    public void createNewMapping_readOnlyObject() throws InterruptedException {
        appManager.getNavigationHelper().openObjectConfigurationPageFromURL();
        appManager.getObjectConfigurationHelper().clickCreateNewMapping();
        appManager.getObjectConfigurationHelper().selectTypeAndObjectAPINameOnTheFirstModalWindow(objectConfigurationHC.getType(),
                objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickReadOnly();
        appManager.getObjectConfigurationHelper().clickNextOnTheFirstModalWindow();
        appManager.getObjectConfigurationHelper().removeSelectAllCheckboxes();
        appManager.getObjectConfigurationHelper().clickValidateAndSave();
        try {
            appManager.getObjectConfigurationHelper().tryToAutoFix();
            Assert.assertTrue(appManager.getMessageHelper().successfullAutoFixMessage());
            appManager.getObjectConfigurationHelper().clickValidateAndSaveAfterAutoFix();
        } catch (NoSuchElementException | ElementClickInterceptedException e) {
        }
        Assert.assertTrue(appManager.getMessageHelper().objectConfigurationSavedMessage());
        Assert.assertTrue(appManager.getAssertHelper().isObjectConfigurationReadOnly(objectConfigurationHC.getObjectAPIname()));
        appManager.getObjectConfigurationHelper().deleteObjectConfiguration(objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickOk();
    }


    @Test
    public void createNewMapping_cancelOnTheFirstModalWindow() throws InterruptedException {
        appManager.getNavigationHelper().openObjectConfigurationPageFromURL();
        appManager.getObjectConfigurationHelper().clickCreateNewMapping();
        int c1 = appManager.getObjectConfigurationHelper().getCountOfObjectConfigurations();
        appManager.getObjectConfigurationHelper().selectTypeAndObjectAPINameOnTheFirstModalWindow(objectConfigurationSFSOAP.getType(),
                objectConfigurationSFSOAP.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickCancelOnTheFirstModalWindow();
        int c2 = appManager.getObjectConfigurationHelper().getCountOfObjectConfigurations();
        Assert.assertEquals(c1, c2);
    }

    @Test
    public void createNewMapping_cancelOnTheSecondModalWindow() throws InterruptedException {
        appManager.getNavigationHelper().openObjectConfigurationPageFromURL();
        appManager.getObjectConfigurationHelper().clickCreateNewMapping();
        int c1 = appManager.getObjectConfigurationHelper().getCountOfObjectConfigurations();
        appManager.getObjectConfigurationHelper().selectTypeAndObjectAPINameOnTheFirstModalWindow(objectConfigurationSFSOAP.getType(),
                objectConfigurationSFSOAP.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickNextOnTheFirstModalWindow();
        appManager.getObjectConfigurationHelper().selectAllAndCancelOnTheSecondModalWindow();
        int c2 = appManager.getObjectConfigurationHelper().getCountOfObjectConfigurations();
        Assert.assertEquals(c1, c2);
    }

    @Test
    public void createNewMapping_goToPreviousPageOnTheSecondModalWindow() throws InterruptedException {
        appManager.getNavigationHelper().openObjectConfigurationPageFromURL();
        appManager.getObjectConfigurationHelper().clickCreateNewMapping();
        appManager.getObjectConfigurationHelper().selectTypeAndObjectAPINameOnTheFirstModalWindow(objectConfigurationSFSOAP.getType(),
                objectConfigurationSFSOAP.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickNextOnTheFirstModalWindow();
        appManager.getObjectConfigurationHelper().clickPreviousOnTheSecondModalWindow();
        appManager.getObjectConfigurationHelper().selectTypeAndObjectAPINameOnTheFirstModalWindow(objectConfigurationHC.getType(),
                objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickNextOnTheFirstModalWindow();
        appManager.getObjectConfigurationHelper().selectAllCheckboxes();
        appManager.getObjectConfigurationHelper().clickValidateAndSave();
        try {
            appManager.getObjectConfigurationHelper().tryToAutoFix();
            Assert.assertTrue(appManager.getMessageHelper().successfullAutoFixMessage());
            appManager.getObjectConfigurationHelper().clickValidateAndSaveAfterAutoFix();
        } catch (NoSuchElementException | ElementClickInterceptedException e) {
        }
        Assert.assertTrue(appManager.getMessageHelper().objectConfigurationSavedMessage());
        Assert.assertTrue(appManager.getAssertHelper().isMappingInTheList(objectConfigurationHC.getObjectAPIname()));
        appManager.getObjectConfigurationHelper().deleteObjectConfiguration(objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickOk();
        Thread.sleep(4000);
        Assert.assertTrue(appManager.getMessageHelper().successfulObjectConfigurationDeleteMessage());
    }


    @Test
    public void deleteMapping_cancel() throws InterruptedException {
        appManager.getObjectConfigurationHelper().createSimpleObjectConfiguration(objectConfigurationHC.getType(),
                objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().deleteObjectConfiguration(objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickCancel();
        Assert.assertTrue(appManager.getAssertHelper().isMappingInTheList(objectConfigurationHC.getObjectAPIname()));
    }

    @Test
    public void editMapping() throws InterruptedException {
        appManager.getObjectConfigurationHelper().createSimpleObjectConfiguration(objectConfigurationHC.getType(),
                objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().editObjectConfiguration(objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().selectNewType(objectConfigurationSFSOAP.getType());
        appManager.getObjectConfigurationHelper().clickNextOnTheFirstModalWindow();
        appManager.getObjectConfigurationHelper().selectAllCheckboxes();
        appManager.getObjectConfigurationHelper().clickValidateAndSave();
        try {
            appManager.getObjectConfigurationHelper().tryToAutoFix();
            Assert.assertTrue(appManager.getMessageHelper().successfullAutoFixMessage());
            appManager.getObjectConfigurationHelper().clickValidateAndSaveAfterAutoFix();
        } catch (NoSuchElementException | ElementClickInterceptedException e) {
        }
        Assert.assertTrue(appManager.getMessageHelper().objectConfigurationSavedMessage());
        Assert.assertTrue(appManager.getAssertHelper().isTypeChanged(objectConfigurationHC.getObjectAPIname(),
                objectConfigurationHC.getType()));
        appManager.getObjectConfigurationHelper().deleteObjectConfiguration(objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickOk();
    }

    @Test
    public void editMapping_cancelOnTheFirstModalWindow() throws InterruptedException {
        appManager.getObjectConfigurationHelper().createSimpleObjectConfiguration(objectConfigurationHC.getType(),
                objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().editObjectConfiguration(objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().selectNewType(objectConfigurationSFSOAP.getType());
        appManager.getObjectConfigurationHelper().clickCancelOnTheFirstModalWindow();
        Assert.assertFalse(appManager.getAssertHelper().isTypeChanged(objectConfigurationHC.getObjectAPIname(),
                objectConfigurationHC.getType()));
        appManager.getObjectConfigurationHelper().deleteObjectConfiguration(objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickOk();
    }

    @Test
    public void editMapping_cancelOnTheSecondModalWindow() throws InterruptedException {
        appManager.getObjectConfigurationHelper().createSimpleObjectConfiguration(objectConfigurationHC.getType(),
                objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().editObjectConfiguration(objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().selectNewType(objectConfigurationSFSOAP.getType());
        appManager.getObjectConfigurationHelper().clickNextOnTheFirstModalWindow();
        appManager.getObjectConfigurationHelper().selectAllAndCancelOnTheSecondModalWindow();
        Assert.assertFalse(appManager.getAssertHelper().isTypeChanged(objectConfigurationHC.getObjectAPIname(),
                objectConfigurationHC.getType()));
        appManager.getObjectConfigurationHelper().deleteObjectConfiguration(objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickOk();
    }

    @Test
    public void editMapping_cancelOnTheThirdModalWindowBeforeDBvalidation() throws InterruptedException {
        appManager.getObjectConfigurationHelper().createSimpleObjectConfiguration(objectConfigurationHC.getType(),
                objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().editObjectConfiguration(objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().selectNewType(objectConfigurationSFSOAP.getType());
        appManager.getObjectConfigurationHelper().clickNextOnTheFirstModalWindow();
        appManager.getObjectConfigurationHelper().selectAllCheckboxes();
        appManager.getObjectConfigurationHelper().clickValidateAndSave();
        appManager.getObjectConfigurationHelper().cancelOnTheThirdModalWindow();
        Assert.assertFalse(appManager.getAssertHelper().isTypeChanged(objectConfigurationHC.getObjectAPIname(),
                objectConfigurationHC.getType()));
        appManager.getObjectConfigurationHelper().deleteObjectConfiguration(objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickOk();
    }

    @Test
    public void editMapping_cancelOnTheThirdModalWindowAfterDBvalidation() throws InterruptedException {
        appManager.getObjectConfigurationHelper().createSimpleObjectConfiguration(objectConfigurationHC.getType(),
                objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().editObjectConfiguration(objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().selectNewType(objectConfigurationSFSOAP.getType());
        appManager.getObjectConfigurationHelper().clickNextOnTheFirstModalWindow();
        appManager.getObjectConfigurationHelper().selectAllCheckboxes();
        appManager.getObjectConfigurationHelper().clickValidateAndSave();
        appManager.getObjectConfigurationHelper().cancelOnTheThirdModalWindow();
        Assert.assertFalse(appManager.getAssertHelper().isTypeChanged(objectConfigurationHC.getObjectAPIname(),
                objectConfigurationHC.getType()));
        appManager.getObjectConfigurationHelper().deleteObjectConfiguration(objectConfigurationHC.getObjectAPIname());
        appManager.getObjectConfigurationHelper().clickOk();
    }

 */

}
