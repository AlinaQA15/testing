package helpers;

import base.ApplicationManager;
import base.HelperBase;
import org.openqa.selenium.By;

public class MessageHelper extends HelperBase {

    public MessageHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public boolean newJobHasBeenQueuedMessage() {
        return driver.findElement(By.xpath("//p")).getText().equals("A new job has been queued.");
    }

    public boolean noAccessToTheRequestedAPIendpointMessage() {
        return driver.findElement(By.xpath("//p")).getText().equals("You do not have access to the requested API endpoint.");
    }

    public boolean applicationSecurityUpdatedSuccessfullyMessage() {
        return driver.findElement(By.xpath("//p")).getText().equals("Application Security settings updated successfully.");
    }

    public boolean forbidUsageMessage() {
        return driver.findElement(By.xpath("//div[@class='slds-notify slds-notify_alert slds-theme_alert-texture slds-theme_error']")).getText().equals("" +
                "You have successfully authenticated to Salesforce, but this application is configured to forbid usage " +
                "for users with your profile. You can request access from your administrator.");
    }

    public boolean applicationParametersUpdatedSuccessfullyMessage() {
        return driver.findElement(By.xpath("//p")).getText().equals("Application settings updated successfully.");
    }

    public boolean applicationEnvironmentSettingsUpdatedSuccessfullyMessage() {
        return driver.findElement(By.xpath("//p")).getText().equals("Application Environment settings updated successfully.");
    }


    public boolean invalidPasswordTokenMessage() {
        return driver.findElement(By.xpath("//p")).getText().contains("Invalid username, password, security token; or user locked out.");
    }

    public boolean serviceUnavailableMessage() throws InterruptedException {
        Thread.sleep(80000);
        return driver.findElement(By.xpath("//p")).getText().contains("status: 503, message: Service Unavailable");
    }

    public boolean resourceNotFoundMessage() {
        return driver.findElement(By.xpath("//p")).getText().contains("Resource not found");
    }

    public boolean invalidOrExpiredTokenMessage() {
        return driver.findElement(By.xpath("//p")).getText().contains("Invalid or expired token");
    }

    public boolean sfOrgDetailsUpdatedSuccessfullyMessage() {
        return driver.findElement(By.xpath("//p")).getText().equals("Salesforce Organization details updated successfully.");
    }

    public boolean herokuAPISettingsUpdatedSuccessfullyMessage() {
        return driver.findElement(By.xpath("//p")).getText().equals("Heroku API settings updated successfully.");
    }

    public boolean relationshipSavedMessage() {
        return driver.findElement(By.xpath("//div[3]/div/div/div/p")).getText().contains("The relationships saved sucessfully.");
    }

    public boolean successfulObjectConfigurationDeleteMessage() {
        return driver.findElement(By.xpath("//p")).getText().contains("Object Configuration deleted successfully.");
    }

    public boolean objectConfigurationSavedMessage() {
        return driver.findElement(By.xpath("//p")).getText().contains("Object Configuration saved successfully.");
    }

    public boolean successfullAutoFixMessage() {
        return driver.findElement(By.xpath("//div/div/div/div/div/div/div[2]/div")).getText().contains("No Database Validation errors")
                && driver.findElement(By.xpath("//div/div/div/div[2]/div[2]/div")).getText().contains("No Job Config warnings");
    }

    public boolean jobConfigurationSavedMessage() {
        return driver.findElement(By.xpath("//div[2]/div/p")).getText().contains("Job Configuration saved successfully.");
    }

    public boolean invalidSQLconditionMessage() {
        return driver.findElement(By.xpath("//div[2]/div/p")).getText().contains("Job condition is invalid");
    }

    public boolean isJobConfigurationDeletedMessage() {
        return driver.findElement(By.xpath("//p")).getText().contains("Job Configuration deleted successfully.");
    }

    public boolean jobConditionValidMessage() {
        return driver.findElement(By.xpath("//p")).getText().contains("Job condition is valid");
    }

    public boolean jobStatusPausedMessage() {
        return driver.findElement(By.xpath("//div[4]/div/p")).getText().equals("The job has been paused successfully.");
    }

    public boolean jobStatusNotAssumePauseMessage() {
        return driver.findElement(By.xpath("//p")).getText().equals("The job has a status that does not assume pause");
    }

    public boolean scheduledJobSavedSuccessfullyMessage() {
        return driver.findElement(By.xpath("//p")).getText().equals("Scheduled Job saved successfully.");
    }

    public boolean scheduledJobDeletedSuccessfullyMessage() {
        return driver.findElement(By.xpath("//div[2]/div/p")).getText().equals("Scheduled Job deleted successfully.");
    }

    public boolean invalidCronExpressionMessage() {
        return driver.findElement(By.xpath("//p")).getText().equals("The Cron Expression is invalid.");
    }

    public boolean scheduledJobStoppedMessage() {
        return driver.findElement(By.xpath("//div[2]/div/p")).getText().equals("Scheduled Job stopped successfully.");
    }
}
