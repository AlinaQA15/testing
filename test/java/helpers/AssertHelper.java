package helpers;

import base.ApplicationManager;
import base.HelperBase;
import base.Settings;
import javafx.util.Pair;
import models.AppSettings;
import models.Job;
import models.Schedule;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static tests.RelationshipManagerTests.tableColumnsViaAlookupColumnInSystemMode;

public class AssertHelper extends HelperBase {

    public AssertHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public boolean isJobStatusQueued() throws InterruptedException {
        String status = driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]")).getText();
        return status.contains("QUEUED");
    }

    public boolean isJobStatusPaused() {
        String status = driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]")).getText();
        return status.contains("PAUSED");
    }

    public boolean isJobStatusInitialization() throws InterruptedException {
        Thread.sleep(40000);
        String status = driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]")).getText();
        return status.contains("INITIALIZATION");
    }

    public boolean isJobStatusCompleted() throws InterruptedException {
        Thread.sleep(95000);
        String status = driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]")).getText();
        return status.contains("COMPLETED");
    }

    public boolean isNewTitleSaved(String insertedTitle) {
        String text = driver.findElement(By.xpath("//div[2]/div[2]/div/div/div[2]/div/div")).getText();
        return text.contains(insertedTitle);
    }

 /*
    public boolean isMappingCreated() {
        WebElement element = driver.findElement(By.xpath("//tr//td[contains(text(),'address')]"));
        return element != null;
    }
*/

    public boolean isJobConfigurationInTheList(String title) throws InterruptedException {
        List<WebElement> list = driver.findElements(By.xpath("//tr//td[1]"));
        for (WebElement webElement : list) {
            String name = webElement.getText();
            if (name.equals(title))
                return true;
        }
        return false;
    }

    public boolean currentPageURLequalTo(String URL) {
        String currentURL = driver.getCurrentUrl();
        return currentURL.equals(URL);
    }

    public boolean hasEveryJobCertainAction(String certainAction) throws InterruptedException {
        int pages = applicationManager.getJobHelper().getCountOfPages();
        String xPath;

        for (int p = 1; p <= pages; p++) {
            List<WebElement> list = driver.findElements(By.xpath("//body/div/div/div/div/div/table/tbody/tr//td[4]"));
            int countOfJobs = list.size();
            for (int i = 1; i <= countOfJobs; i++) {
                xPath = "//body/div/div/div/div/div/table/tbody/tr[" + i + "]//td[4]";
                String action = driver.findElement(By.xpath(xPath)).getText();
                if (!action.equals(certainAction))
                    return false;
            }
            if (pages == 1)
                return true;
            driver.findElement(By.xpath("//button[@data-btn-type='next-page']")).click();
            //       driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
            Thread.sleep(3000);
        }
        return true;
    }


    public boolean hasEveryJobCertainStatus(String certainStatus) throws InterruptedException {
        int pages = applicationManager.getJobHelper().getCountOfPages();
        String xPath;

        for (int p = 1; p <= pages; p++) {
            List<WebElement> list = driver.findElements(By.xpath("//body/div/div/div/div/div/table/tbody/tr//td[5]"));
            int countOfJobs = list.size();
            for (int i = 1; i <= countOfJobs; i++) {
                xPath = "//body/div/div/div/div/div/table/tbody/tr[" + i + "]//td[5]";
                String status = driver.findElement(By.xpath(xPath)).getText();
                if (!status.equals(certainStatus))
                    return false;
            }
            if (pages == 1)
                return true;

            driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
            Thread.sleep(3000);
        }
        return true;
    }


    public boolean hasEveryJobCertainVolume(String certainVolume) throws InterruptedException {
        int pages = applicationManager.getJobHelper().getCountOfPages();
        String xPath;

        for (int p = 1; p <= pages; p++) {
            List<WebElement> list = driver.findElements(By.xpath("//body/div/div/div/div/div/table/tbody/tr//td[6]"));
            int countOfJobs = list.size();
            for (int i = 1; i <= countOfJobs; i++) {
                xPath = "//body/div/div/div/div/div/table/tbody/tr[" + i + "]//td[6]";
                String volume = driver.findElement(By.xpath(xPath)).getText();
                if (!volume.equals(certainVolume))
                    return false;
            }
            if (pages == 1)
                return true;

            driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
            Thread.sleep(3000);
        }
        return true;
    }


    public boolean hasEveryJobCertainObjectAPIname(String certainObjectAPIname) throws InterruptedException {
        int pages = applicationManager.getJobHelper().getCountOfPages();
        String xPath;

        for (int p = 1; p <= pages; p++) {
            List<WebElement> list = driver.findElements(By.xpath("//body/div/div/div/div/div/table/tbody/tr//td[3]"));
            int countOfJobs = list.size();
            for (int i = 1; i <= countOfJobs; i++) {
                xPath = "//body/div/div/div/div/div/table/tbody/tr[" + i + "]//td[3]";
                String volume = driver.findElement(By.xpath(xPath)).getText();
                if (!volume.equalsIgnoreCase(certainObjectAPIname))
                    return false;
            }
            if (pages == 1)
                return true;

            driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
            Thread.sleep(3000);
        }
        return true;
    }


    public boolean hasEveryJobCertainDate() throws InterruptedException, ParseException {
        int pages = applicationManager.getJobHelper().getCountOfPages();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        String xPathFrom, xPathTo;

        for (int p = 1; p <= pages; p++) {
            List<WebElement> list = driver.findElements(By.xpath("//body/div/div/div/div/div/table/tbody/tr//td[7]"));
            int countOfJobs = list.size();
            for (int i = 1; i <= countOfJobs; i++) {
                xPathFrom = "//body/div/div/div/div/div/table/tbody/tr[" + i + "]//td[7]";
                xPathTo = "//body/div/div/div/div/div/table/tbody/tr[" + i + "]//td[8]";
                String stringDateFromInFilter = driver.findElement(By.xpath("//div[5]//div[1]//div[1]//div[1]//input[1]")).getAttribute("value");
                String stringDateToInFilter = driver.findElement(By.xpath("//div[6]//div[1]//div[1]//div[1]//input[1]")).getAttribute("value");
                String stringDateFromInList = driver.findElement(By.xpath(xPathFrom)).getText().substring(0, 10);
                String stringDateToInList = driver.findElement(By.xpath(xPathTo)).getText().substring(0, 10);
                if (!stringDateFromInFilter.equals("") & !stringDateToInFilter.equals("")) {
                    Date dateFromInFilter = dateFormat.parse(stringDateFromInFilter);
                    Date dateFromInList = dateFormat.parse(stringDateFromInList);
                    Date dateToInFilter = dateFormat.parse(stringDateToInFilter);
                    Date dateToInList = dateFormat.parse(stringDateToInList);
                    if (dateFromInFilter.compareTo(dateFromInList) > 0 | dateToInFilter.compareTo(dateToInList) < 0)
                        return false;
                }

                if (!stringDateFromInFilter.equals("") & stringDateToInFilter.equals("")) {
                    Date dateFromInFilter = dateFormat.parse(stringDateFromInFilter);
                    Date dateFromInList = dateFormat.parse(stringDateFromInList);
                    if (dateFromInFilter.compareTo(dateFromInList) > 0)
                        return false;
                }

                if (stringDateFromInFilter.equals("") & !stringDateToInFilter.equals("")) {
                    Date dateToInFilter = dateFormat.parse(stringDateToInFilter);
                    Date dateToInList = dateFormat.parse(stringDateToInList);
                    if (dateToInFilter.compareTo(dateToInList) < 0)
                        return false;
                }
            }
            if (pages == 1)
                return true;

            driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
            Thread.sleep(3000);
        }
        return true;
    }


    public boolean hasEveryJobCertainMultipleFiltersWithoutDates(String certainAction, String certainStatus, String certainVolume,
                                                                 String certainObjectAPIname) throws InterruptedException {
        int pages = applicationManager.getJobHelper().getCountOfPages();
        String xPathAction, xPathStatus, xPathVolume, xPathObjectAPIname;

        for (int p = 1; p <= pages; p++) {
            List<WebElement> list = driver.findElements(By.xpath("//body/div/div/div/div/div/table/tbody/tr//td[4]"));
            int countOfJobs = list.size();
            for (int i = 1; i <= countOfJobs; i++) {
                xPathAction = "//body/div/div/div/div/div/table/tbody/tr[" + i + "]//td[4]";
                String action = driver.findElement(By.xpath(xPathAction)).getText();
                xPathStatus = "//body/div/div/div/div/div/table/tbody/tr[" + i + "]//td[5]";
                String status = driver.findElement(By.xpath(xPathStatus)).getText();
                xPathVolume = "//body/div/div/div/div/div/table/tbody/tr[" + i + "]//td[6]";
                String volume = driver.findElement(By.xpath(xPathVolume)).getText();
                xPathObjectAPIname = "//body/div/div/div/div/div/table/tbody/tr[" + i + "]//td[3]";
                String objectAPIname = driver.findElement(By.xpath(xPathObjectAPIname)).getText();

                if (!action.equals(certainAction) || !status.equals(certainStatus) ||
                        !volume.equals(certainVolume) || !objectAPIname.equalsIgnoreCase(certainObjectAPIname))
                    return false;
            }
            if (pages == 1)
                return true;

            driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
            Thread.sleep(3000);
        }
        return true;
    }

    public boolean isEveryFilterEmpty() {
        String action = driver.findElement(By.id("filters-action")).getAttribute("value");
        String status = driver.findElement(By.id("filters-status")).getAttribute("value");
        String volume = driver.findElement(By.id("filters-volume")).getAttribute("value");
        String objectName = driver.findElement(By.id("filters-object-name")).getAttribute("value");
        String dateFrom = driver.findElement(By.id("filters-start-date")).getAttribute("value");
        String dateTo = driver.findElement(By.id("filters-end-date")).getAttribute("value");
        return action.equals("") & status.equals("") & volume.equals("")
                & objectName.equals("") & dateFrom.equals("") & dateTo.equals("");
    }

    public boolean isCountOfJobsAfterClearMore(int c1, int c2) {
        return c1 <= c2;
    }

    public boolean isCountOfJobsAfterRefreshIncreased(int c1, int c2) {
        return (c2 - c1) == 1;
    }

    public boolean isCountOfJobsEqualTo15onEveryPage() throws InterruptedException {
        int pages = applicationManager.getJobHelper().getCountOfPages();
        boolean b = true;
        for (int p = 1; p <= pages; p++) {
            List<WebElement> list = driver.findElements(By.xpath("//body/div/div/div/div/div/table/tbody/tr//td[4]"));
            int countOfJobs = list.size();
            if (pages == 1 & countOfJobs <= 15) {
                b = true;
            } else if (pages > 1 & countOfJobs == 15) {
                b = true;
            } else if (p == pages & countOfJobs <= 15) {
                b = true;
            } else {
                return false;
            }

            driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
            Thread.sleep(3000);
        }
        return b;
    }

    public boolean areJobDetailsEqual(Job job1, Job job2) throws InterruptedException {
        return (job2.getNumber().contains(job1.getNumber())
                & job2.getTitle().equals(job1.getTitle())
                & job2.getObjectAPIname().equalsIgnoreCase(job1.getObjectAPIname())
                & job2.getAction().equals(job1.getAction())
                & job2.getStatus().equals(job1.getStatus())
                & job2.getVolume().equals(job1.getVolume()) & job2.getStartTime().equals(job1.getStartTime())
                & job2.getEndTime().equals(job1.getEndTime()) & job2.getTotalTime().equals(job1.getTotalTime()));
    }

    public boolean isMappingInTheList(String objectAPIname) throws InterruptedException {
        List<WebElement> list = driver.findElements(By.xpath("//tr//td[1]"));
        for (WebElement element : list) {
            if (element.getText().equalsIgnoreCase(objectAPIname))
                Thread.sleep(5000);
            return true;
        }
        Thread.sleep(5000);
        return false;
    }

    public boolean isTypeChanged(String objectAPIname, String type) throws InterruptedException {
        String type1 = applicationManager.getObjectConfigurationHelper().getObjectConfigurationByObjectAPIName(objectAPIname).getType();
        return !type.equals(type1);
    }

    public boolean isObjectConfigurationReadOnly(String objectAPIname) throws InterruptedException {
        String readOnly = applicationManager.getObjectConfigurationHelper().getObjectConfigurationByObjectAPIName(objectAPIname).getReadOnly();
        return readOnly.equals("true");
    }

    public boolean isAllChildObjectsSelected() {
        List<WebElement> list = driver.findElements(By.xpath("//tr//td[1]"));
        String inputPath;
        int countOfChildObjects = list.size();
        for (int i = 1; i <= countOfChildObjects; i++) {
            inputPath = "//tr[" + i + "]//td[1]//div[1]//label[1]//input[1]";
            try {
                if (!driver.findElement(By.xpath(inputPath)).isSelected()) {
                    return false;
                }
            } catch (NoSuchElementException e) {
                return true;
            }
        }
        return true;
    }

    public boolean isSystemModeEnabled() {
        return (driver.findElement(By.xpath("//th[2]")).getText().equals(tableColumnsViaAlookupColumnInSystemMode.getChildObject()) &&
                driver.findElement(By.xpath("//th[3]")).getText().equals(tableColumnsViaAlookupColumnInSystemMode.getParentField()) &&
                driver.findElement(By.xpath("//th[4]")).getText().equals(tableColumnsViaAlookupColumnInSystemMode.getSoqlQqueryType()) &&
                driver.findElement(By.xpath("//th[5]")).getText().equals(tableColumnsViaAlookupColumnInSystemMode.getAvgChildrenForOneparentRecord()) &&
                driver.findElement(By.xpath("//th[6]")).getText().equals(tableColumnsViaAlookupColumnInSystemMode.getUseCascadeDeletion()) &&
                driver.findElement(By.xpath("//th[7]")).getText().equals(tableColumnsViaAlookupColumnInSystemMode.getIsCascadeDeletionInSF()) &&
                driver.findElement(By.xpath("//th[8]")).getText().equals(tableColumnsViaAlookupColumnInSystemMode.getDeletionRestrictedInSF()));
    }

    public boolean areSOQLQueryTypeAndAVGchildrenForOneParentRecordSaved(Pair<String, String> pair) {
        String soqlQueryType = driver.findElement(By.xpath("//tr[1]//select[@data-select-type='soql-query-type']")).getAttribute("value");
        try {
            String avgChildrenForOneParentRecord = driver.findElement(By.xpath("//input[@data-input-type='avg-children-count-for-parent']")).getAttribute("value");
            return (soqlQueryType.contains(pair.getKey()) && avgChildrenForOneParentRecord.equals(pair.getValue()));
        } catch (NotFoundException e) {
            return (soqlQueryType.contains(pair.getKey()));
        }
    }

    public boolean isAllPseudoChildObjectsSelected() {
        List<WebElement> list = driver.findElements(By.xpath("//div[3]/table/tbody/tr/td//input[@data-checkbox-type='is-selected']"));
        int countOfChildObjects = list.size();
        for (int i = 1; i <= countOfChildObjects; i++) {
            String path = "//div[3]/table/tbody/tr[" + i + "]/td//input[@data-checkbox-type='is-selected']";
            if (!driver.findElement(By.xpath(path)).isSelected()) {
                return false;
            }
        }
        return true;
    }

    public boolean salesforceOrganizationSettingsAreDefault(String defaultSfBaseURL) {
        String sfBaseURL = driver.findElement(By.id("sf-form-url")).getAttribute("value");
        String username = driver.findElement(By.id("sf-form-username")).getAttribute("value");
        String password = driver.findElement(By.id("sf-form-password")).getAttribute("value");
        String token = driver.findElement(By.id("sf-form-token")).getAttribute("value");
        return sfBaseURL.equals(defaultSfBaseURL) && username.equals(Settings.getLogin()) &&
                password.equals("") && token.equals("");
    }

    public boolean herokuAPIsettingsAreDefault(AppSettings herokuAPI) {
        String applicationRegion = driver.findElement(By.id("heroku-api-app-region")).getAttribute("value");
        String herokuConnectId = driver.findElement(By.id("heroku-api-hc-id")).getAttribute("value");
        String herokuUserAPIkey = driver.findElement(By.id("heroku-api-user-api-key")).getAttribute("value");
        return applicationRegion.equals(herokuAPI.getApplicationRegion()) &&
                herokuConnectId.equals(herokuAPI.getHerokuConnectId()) && herokuUserAPIkey.equals("");
    }

    public boolean applicationEnvironmentSettingsAreChanged(AppSettings applicationEnvironment) {
        String applicationBaseURL = driver.findElement(By.id("app-environment-base-url")).getAttribute("value");
        String dynoType = driver.findElement(By.id("app-environment-dyno-type")).getAttribute("value");
        String jobsExecutorDyno = driver.findElement(By.id("app-environment-jobs-executor-dyno")).getAttribute("value");
        String jobsSchedulerDyno = driver.findElement(By.id("app-environment-jobs-scheduler-dyno")).getAttribute("value");
        String jobsSchedulerInterval = driver.findElement(By.id("app-environment-jobs-scheduler-interval-in-min")).getAttribute("value");
        return applicationBaseURL.equals(applicationEnvironment.getApplicationBaseUrl()) && dynoType.equals(applicationEnvironment.getDynoType()) &&
                jobsExecutorDyno.equals(applicationEnvironment.getJobsExecutorDyno()) &&
                jobsSchedulerDyno.equals(applicationEnvironment.getJobsSchedulerDyno()) &&
                jobsSchedulerInterval.equals(applicationEnvironment.getJobsSchedulerInterval());
    }


    public boolean isIgnoredDBtableOnSyncInTheList(String name) {
        List<WebElement> list = driver.findElements(By.xpath("//input[@data-input-type='ignored-db-table']"));
        for (WebElement webElement : list) {
            if (webElement.getAttribute("value").equals(name))
                return true;
        }
        return false;
    }

    public boolean isDBindiceForSyncInTheList(String name) {
        List<WebElement> list = driver.findElements(By.xpath("//input[@data-input-type='ignored-db-index']"));
        for (WebElement webElement : list) {
            if (webElement.getAttribute("value").equals(name))
                return true;
        }
        return false;
    }

    public boolean isMaximumProcessBatchSizeEqualTo(String maximumProcessBatchSize) {
        return driver.findElement(By.id("app-parameters-max-process-batch-size")).getAttribute("value").equals(maximumProcessBatchSize);
    }

    public boolean areJobActionsSelected() {
        List<WebElement> list = driver.findElements(By.xpath("//input[@data-checkbox-type='enabled-job-action']"));
        int i = 1;
        for (WebElement webElement : list) {
            if (i != 1 && webElement.isSelected()) {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean isArchivingOnlyInJobActionList() {
        String actions = driver.findElement(By.id("job-config-form-job-action")).getText();
        return !actions.contains("UNARCHIVING") & !actions.contains("PURGE ARCHIVE");
    }

    public boolean isConsiderRemovalOnSyncSelected() {
        return driver.findElement(By.id("app-parameter-consider-removal-on-sync")).isSelected();
    }

    public boolean isJobConfigsPageOpened() {
        return driver.getCurrentUrl().equals(applicationManager.baseUrl + "job-configs");
    }

    public boolean isCreateJobConfigPageOpened() {
        return driver.getCurrentUrl().equals(applicationManager.baseUrl + "job-configs/new");
    }

    public boolean isJobsPageOpened() {
        return driver.getCurrentUrl().equals(applicationManager.baseUrl + "jobs");
    }

    public boolean isObjectConfigurationPageOpened() {
        return driver.getCurrentUrl().equals(applicationManager.baseUrl + "object-configuration");
    }

    public boolean isAppSettingsPageOpened() {
        return driver.getCurrentUrl().equals(applicationManager.baseUrl + "app-settings");
    }

    public boolean isRelationshipManagerPageOpened() {
        return driver.getCurrentUrl().equals(applicationManager.baseUrl + "relationship-manager");
    }

    public boolean isBulkVolumeAvailable() {
        String volumes = driver.findElement(By.id("filters-volume")).getText();
        return volumes.contains("BULK");
    }

    public boolean isChildRelationshipsEnabled() {
        List<WebElement> list = driver.findElements(By.xpath("//table[@class='slds-table slds-table_cell-buffer slds-table_bordered table__row--height-sm']//td[1]"));
        int countOfChildObjects = list.size();
        for (int i = 1; i <= countOfChildObjects; i++) {
            if (driver.findElement(By.xpath("//tr[" + i + "]//td[1]//div[1]//label[1]//input[1]")).isEnabled()) {
                return true;
            }
        }
        return false;
    }

    public boolean isPseudoChildRelationshipsEnabled() {
        List<WebElement> list = driver.findElements(By.xpath("//table[@class='slds-table slds-table_cell-buffer slds-table_bordered slds-m-bottom_medium table__row--height-sm']//td[1]"));
        int countOfChildObjects = list.size();
        String path;
        for (int i = 1; i <= countOfChildObjects; i++) {
            if (i == 1)
                path = "//div[3]/table/tbody/tr/td/div/label/span[2]/span[3]";
            else
                path = "//div[3]/table/tbody/tr[" + i + "]/td/div/label/span[2]/span[3]";
            if (!driver.findElement(By.xpath(path)).isEnabled()) {
                return true;
            }
        }
        return false;
    }


    public boolean isSaveRelationshipsButtonDisplayed() {
        try {
            driver.findElement(By.id("save-relationships")).click();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean areAppSettingsElementsEnabled() {
        return driver.findElement(By.id("sf-form-password")).isEnabled() && driver.findElement(By.id("sf-form-token")).isEnabled()
                && driver.findElement(By.id("sf-form-username")).isEnabled() && driver.findElement(By.id("sf-form-url")).isEnabled()
                && driver.findElement(By.id("heroku-api-app-region")).isEnabled() && driver.findElement(By.id("heroku-api-user-api-key")).isEnabled()
                && driver.findElement(By.id("heroku-api-hc-id")).isEnabled()
                && driver.findElement(By.id("app-environment-base-url")).isEnabled() && driver.findElement(By.id("app-environment-dyno-type")).isEnabled()
                && driver.findElement(By.id("app-environment-jobs-executor-dyno")).isEnabled() && driver.findElement(By.id("app-environment-jobs-scheduler-dyno")).isEnabled()
                && driver.findElement(By.id("app-parameters-max-process-batch-size")).isEnabled() && driver.findElement(By.id("app-parameter-consider-removal-on-sync")).isEnabled()
//input[@data-checkbox-type="enabled-job-action"]
                ;
    }

    public boolean doAppSettingsButtonsExist() {
        try {
            driver.findElement(By.id("sf-form-save")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        try {
            driver.findElement(By.id("heroku-api-save")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        try {
            driver.findElement(By.id("app-environment-save")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        try {
            driver.findElement(By.id("app-security-save")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        try {
            driver.findElement(By.id("app-parameters-save")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        try {
            driver.findElement(By.id("app-parameter-ignored-db-add")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        try {
            driver.findElement(By.id("app-parameter-db-indices-add")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        return false;
    }

    public boolean doesCreateNewMappingButtonExist() {
        try {
            driver.findElement(By.id("create-new-mapping")).click();
            return true;
        } catch (NoSuchElementException e) {
        }
        return false;
    }

    public boolean areJobConfigPageElementsEnabled() {
        return driver.findElement(By.id("job-config-form-title")).isEnabled()
                && driver.findElement(By.id("job-config-form-job-action")).isEnabled()
                && driver.findElement(By.id("job-config-form-object-name")).isEnabled()
                && driver.findElement(By.id("job-config-form-sql-condition")).isEnabled()
                && driver.findElement(By.id("'job-config-check-condition")).isEnabled();
    }

    public boolean doJobConfigPageButtonsExist() {
        try {
            driver.findElement(By.id("job-config-cancel")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        try {
            driver.findElement(By.id("job-config-delete")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        try {
            driver.findElement(By.id("job-config-save-and-run")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        try {
            driver.findElement(By.id("job-config-save")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        try {
            driver.findElement(By.id("scheduler-create")).click();
            return true;
        } catch (NoSuchElementException e) {
        }
        return false;
    }

    public boolean doesCreateNewJobConfigButtonExist() {
        try {
            driver.findElement(By.id("create-new-job-config")).click();
            return true;
        } catch (NoSuchElementException e) {
        }
        return false;
    }

    public boolean doHomeButtonsExceptJobExist() {
        try {
            driver.findElement(By.id("tile-job-configs")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        try {
            driver.findElement(By.id("tile-object-configuration")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        try {
            driver.findElement(By.id("tile-relationship-manager")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        try {
            driver.findElement(By.id("tile-app-settings")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        return false;
    }

    public boolean hasJobCertainVolumeInJobDetailsPage(String volume) {
        return driver.findElement(By.xpath("//body/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]")).getText().equals(volume);
    }

    public boolean doesConfirmIndividualJobButtonExist() {
        try {
            driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand']")).click();
            return true;
        } catch (NoSuchElementException e) {
        }
        return false;
    }

    public boolean doHomeButtonJobExist() {
        try {
            driver.findElement(By.id("tile-jobs")).click();
            return true;
        } catch (NoSuchElementException e) {
        }

        return false;
    }


    public void downloadArchivedFileByStraigthLink() throws InterruptedException {
        driver.get(applicationManager.baseUrl + "server-utils/files/downloadArchivedFile?schemaName=sf_archive&" +
                "objectName=attachment&sfId=00P3l000012TiL8EAK&blobField=Body&blobSizeField=BodyLength&nameField=Name");
        Thread.sleep(5000);
    }

    public boolean isLoginPageOpened() {
        return driver.getCurrentUrl().contains("login.salesforce.com");
    }


    public boolean isScheduleSavedSucsessfully(Schedule schedule1, Schedule schedule2) {
        return (schedule1.getExpression().equals(schedule2.getExpression()) && schedule1.getStatus().equals(schedule2.getStatus()));
    }

    public boolean isSchedulePaused(Schedule schedule) {
        String s = driver.findElement(By.xpath("//tbody/tr[" + schedule.getRow() + "]/td[3]/button[1]")).getAttribute("title");
        String s1 = driver.findElement(By.xpath("//tbody/tr[" + schedule.getRow() + "]/td[2]")).getText();
        boolean p = driver.findElement(By.xpath("//tbody/tr[" + schedule.getRow() + "]/td[3]/button[1]")).getAttribute("title").equals("Run");
        boolean p1 = driver.findElement(By.xpath("//tbody/tr[" + schedule.getRow() + "]/td[2]")).getText().equals("Disabled");
        return p && p1;
    }
}




