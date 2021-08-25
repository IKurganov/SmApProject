package utils;

public enum BrowsersForDriver {
    FIREFOX("firefox"),
    CHROME("chrome");

    private String browserName;

    BrowsersForDriver(String browserName) {
        this.browserName = browserName;
    }

    @Override
    public String toString() {
        return this.browserName;
    }

    public static BrowsersForDriver fromString(String browserName) {
        if (browserName.equalsIgnoreCase(CHROME.toString())) {
            return CHROME;
        } else if (browserName.equalsIgnoreCase(FIREFOX.toString())) {
            return FIREFOX;
        } else throw new RuntimeException("Unknown browser" + browserName);
    }

}
