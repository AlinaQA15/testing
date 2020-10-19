package base;

import helpers.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.fail;

public class ApplicationManager {

    protected WebDriver driver;
    public String baseUrl;
    protected StringBuffer verificationErrors = new StringBuffer();
    private NavigationHelper navigationHelper;
    private JobConfigHelper jobConfigHelper;
    private JobHelper jobHelper;
    private ObjectConfigurationHelper objectConfigurationHelper;
    private RelationshipManagerHelper relationshipManagerHelper;
    private AppSettingsHelper appSettingsHelper;
    private ApplicationSecurityHelper applicationSecurityHelper;
    private ScheduleHelper scheduleHelper;
    private AssertHelper assertHelper;
    private MessageHelper messageHelper;
    private LoginHelper loginHelper;
    private static ThreadLocal<ApplicationManager> app = new ThreadLocal<>();
    private static ApplicationManager applicationManager;

    public static ApplicationManager getInstance() {
        if (applicationManager == null) {
            applicationManager = new ApplicationManager();
        }
        return applicationManager;
    }

    private ApplicationManager() {
        Class<? extends WebDriver> driverClass = ChromeDriver.class;
        WebDriverManager.getInstance(driverClass).setup();
        driver = new ChromeDriver();
        baseUrl = Settings.getBaseUrl();
        navigationHelper = new NavigationHelper(this, baseUrl);
        jobConfigHelper = new JobConfigHelper(this);
        jobHelper = new JobHelper(this);
        objectConfigurationHelper = new ObjectConfigurationHelper(this);
        relationshipManagerHelper = new RelationshipManagerHelper(this);
        appSettingsHelper = new AppSettingsHelper(this);
        applicationSecurityHelper = new ApplicationSecurityHelper(this);
        scheduleHelper = new ScheduleHelper(this);
        assertHelper = new AssertHelper(this);
        messageHelper = new MessageHelper(this);
        loginHelper = new LoginHelper(this);
    }

    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public JobConfigHelper getJobConfigHelper() {
        return jobConfigHelper;
    }

    public ScheduleHelper getScheduleHelper() {
        return scheduleHelper;
    }

    public JobHelper getJobHelper() {
        return jobHelper;
    }

    public ObjectConfigurationHelper getObjectConfigurationHelper() {
        return objectConfigurationHelper;
    }

    public RelationshipManagerHelper getRelationshipManagerHelper() {
        return relationshipManagerHelper;
    }

    public AppSettingsHelper getAppSettingsHelper() {
        return appSettingsHelper;
    }

    public ApplicationSecurityHelper getApplicationSecurityHelper() {
        return applicationSecurityHelper;
    }

    public AssertHelper getAssertHelper() {
        return assertHelper;
    }

    public MessageHelper getMessageHelper() {
        return messageHelper;
    }

    public LoginHelper getLoginHelper() {
        return loginHelper;
    }
}
