package base;

import config.AppiumDriverConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.CreateContactPage;
import pages.DetailContactPage;
import pages.ListContactsPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class AndroidBaseTest extends AppiumDriverConfig {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public ListContactsPage listContactsPage;
    public CreateContactPage createContactPage;
    public DetailContactPage detailContactPage;

    @BeforeClass()
    public void ConfigureAppium() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//data.properties");
        prop.load(fis);
        String ipAddress = prop.getProperty("ipAddress");
        String port = prop.getProperty("port");

        service = startAppiumServer(ipAddress, Integer.parseInt(port));

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(prop.getProperty("AndroidDeviceNames"));
        options.setAppPackage("com.google.android.contacts");
        options.setAppActivity("com.android.contacts.activities.PeopleActivity");

        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        listContactsPage = new ListContactsPage(driver);
        createContactPage = new CreateContactPage(driver);
        detailContactPage = new DetailContactPage(driver);
    }


    @AfterClass()
    public void tearDown() {
        driver.quit();
        service.stop();
    }
}
