package tests;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.TestConfiguration;
import utils.BrowsersForDriver;
import utils.WebDriverFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    private final Logger LOG = LogManager.getLogger(TestBase.class);
    protected static String baseUrl;
    protected WebDriver driver;


    @BeforeAll
    public static void loadConfig() throws IOException {
        TestConfiguration testConfiguration = new TestConfiguration();
        baseUrl = testConfiguration.getProperty("baseUrl");
    }

    @BeforeEach
    public void initDriver() throws IOException {
        TestConfiguration testConfiguration = new TestConfiguration();

        if (testConfiguration.getProperty("useSelenoid").equals("true")) {
            String selenoidURL = "http://localhost:4444/wd/hub";
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setBrowserName(testConfiguration.getProperty("browser"));
            desiredCapabilities.setVersion(testConfiguration.getProperty("browserVersion"));
            desiredCapabilities.setCapability("enableVNC", Boolean.parseBoolean(testConfiguration.getProperty("enableVNC")));
            desiredCapabilities.setCapability("enableVideo", Boolean.parseBoolean(testConfiguration.getProperty("enableVideo")));
            desiredCapabilities.setCapability("enableLog", Boolean.parseBoolean(testConfiguration.getProperty("enableLog")));
            desiredCapabilities.setCapability("screenResolution", "1920x1080x24");
            try {
                driver = new RemoteWebDriver(new URL(selenoidURL), desiredCapabilities);
                LOG.info("Tests will be launched here {}, and to watch them you can on port 8080", selenoidURL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                LOG.error("Uncorrect URL");
            }
        } else {
            BrowsersForDriver browser = testConfiguration.getWebDriverBrowser();
            driver = WebDriverFactory.getDriverForBrowser(browser);
            LOG.info("webdriver   -     {}", browser.toString());
        }

        //driver.manage().timeouts().pageLoadTimeout(12, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //driver.manage().timeouts().setScriptTimeout(12, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null)
            driver.quit();
    }

    public static void takeScreenshot(String name, WebDriver driver) {
        Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}
