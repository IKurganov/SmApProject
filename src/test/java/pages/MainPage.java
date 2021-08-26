package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import pages.componentsAndPopups.Basket;
import pages.componentsAndPopups.DishMenuRow;
import pages.componentsAndPopups.InfoCard;

import java.util.List;
import java.util.stream.Collectors;

public class MainPage extends BasePage {
    private final Logger LOG = LogManager.getLogger(MainPage.class);
    private By searchIcon = By.xpath("//div[@qa-data='search-icon']");
    private By searchInput = By.xpath("//input[@qa-data='input-search']");

    private By dishesOnPage = By.xpath("//div[@qa-data='product']");


    private String locatorForCategory = "//span[text()='%s']";
    private By goToTheMenuButton = By.xpath("//div[text()='Speisekarte ansehen']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Go to pizzeria's site - {baseUrl}")
    public MainPage open(String baseUrl) {
        driver.get(baseUrl);
        LOG.info("Successful navigation to {} ", baseUrl);
        try {
            driver.findElement(goToTheMenuButton).click();
        } catch (Exception e) {
        }
        return this;
    }

    //IT DOESN'T WORK
    @Step("insert value to search input")
    public MainPage searchWithTheKeyword(String keyword) {
        WebElement inputButton = waitForElementVisibility(driver.findElement(searchIcon));
        WebElement input = waitForElementVisibility(driver.findElement(searchInput));
        inputButton.click();
        input.sendKeys(keyword);
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

    @Step("Get count of dish rows")
    public int getCountOfDishesFromMainPage() {
        return driver.findElements(dishesOnPage).size();
    }

    @Step("Get list of dish rows")
    public List<DishMenuRow> getDishRowsList() {
        return driver.findElements(dishesOnPage).stream().map(DishMenuRow::new).collect(Collectors.toList());
    }

    @Step("Get info card about dish")
    public InfoCard clickOnInfoCardButtonOnDishRow(DishMenuRow dishRow) {
        dishRow.clickOnInfoAboutDish();
        return new InfoCard(driver);
    }

    @Step("Get Basket Panel from main page")
    public Basket getBasket() {
        return new Basket(driver);
    }

}
