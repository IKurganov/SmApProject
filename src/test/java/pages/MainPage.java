package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import pages.components.DishMenuRow;

import java.util.List;
import java.util.stream.Collectors;

public class MainPage extends BasePage {
    private final Logger LOG = LogManager.getLogger(MainPage.class);
    private By searchInput = By.xpath("//input[@qa-data='input-search']");

    private By dishesOnPage = By.xpath("//div[@qa-data='product']");


    private String locatorForCategory = "//a[contains(@class,'category-wrapper')]/i/span[text()='%s']";
    private By goToTheMenuButton = By.xpath("//div[text()='Speisekarte ansehen']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Go to pizzeria's site - {baseUrl}")
    public void open(String baseUrl) {
        driver.get(baseUrl);
        LOG.info("Successful navigation to {} ", baseUrl);
        try {
            driver.findElement(goToTheMenuButton).click();
        } catch (Exception e) {

        }
    }

    //IT DOESN'T WORK
    @Step("insert value to search input")
    public MainPage searchWithTheKeyword(String keyword) {
        WebElement input = waitForElementVisibility(driver.findElement(searchInput));
        Actions actions = new Actions(driver);
        actions.moveToElement(input).pause(500L).click().sendKeys(keyword).build().perform();
        LOG.info("Insert {} in the input, then begin search", keyword);
        return this;
    }


    @Step("Choose category - {category}")
    public MainPage clickOnCategory(String category) {
        waitForElementVisibility(
                driver.findElement(
                        By.xpath(
                                String.format(locatorForCategory, category))))
                .click();
        return this;
    }

    @Step("Get list of dish rows")
    public List<DishMenuRow> getDishRowsList() {
        return driver.findElements(dishesOnPage).stream().map(DishMenuRow::new).collect(Collectors.toList());
    }

}
