package utils;

import java.io.IOException;
import java.util.Properties;

public class TestConfiguration {
    private Properties properties;

    public TestConfiguration() throws IOException {
        this(System.getProperty("application.properties"));
    }

    public TestConfiguration(String props) throws IOException {
        properties = new Properties();
        properties.load(TestConfiguration.class.getResourceAsStream(props));
    }

    public String getProperty(String propName) {
        if (System.getProperty(propName) != null) {
            return System.getProperty(propName);
        } else {
            return properties.getProperty(propName);
        }
    }

    public BrowsersForDriver getWebDriverBrowser() {
        if (System.getProperty("browser") != null) {
            return BrowsersForDriver.fromString(System.getProperty("browser"));
        } else {
            return BrowsersForDriver.fromString(properties.getProperty("browser"));
        }

    }
}
