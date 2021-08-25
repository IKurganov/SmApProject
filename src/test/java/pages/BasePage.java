package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement waitForElementVisibility(WebElement element) {
        return new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected boolean waitForLoader() {
        try {
            return new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[contains(@class,'loader')]"))));
        } catch (Exception e) {
            return true;
        }

    }


}
