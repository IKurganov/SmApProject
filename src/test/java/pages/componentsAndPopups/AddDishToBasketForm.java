package pages.componentsAndPopups;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.MainPage;

import java.util.List;
import java.util.stream.Collectors;

public class AddDishToBasketForm extends BasePage {
    private By container = By.xpath("//div[@class='ant-modal-body']");
    private By cancelButton = container.xpath(".//button[@qa-data='extras-back-btn']");
    private By addButton = container.xpath(".//button[@qa-data='extras-order-btn']");
    private By dishTitleOnAddForm = container.xpath(".//h2[@class='modal-title']/div");
    private By ingredientsOfTheDishContainer = container.xpath(".//div[@qa-data='extras-group-component']");
    private By requiredIngredients = ingredientsOfTheDishContainer.xpath(".//div[contains(@class,'ingredients-required')]");

    public AddDishToBasketForm(WebDriver driver) {
        super(driver);
    }

    @Step("Click cancel button in add dish form")
    public MainPage clickCancelButton() {
        driver.findElement(cancelButton).click();
        return new MainPage(driver);
    }

    @Step("Click add to the basket button in add dish form")
    public MainPage clickAddButton() {
        driver.findElement(addButton).click();
        return new MainPage(driver);
    }


    @Step("Get list of required ingredients")
    public List<RequiredIngredientRow> getRequiredIngredientRows() {
        return driver.findElements(requiredIngredients).stream().map(RequiredIngredientRow::new).collect(Collectors.toList());
    }

    @Step("Get dish name on add dish to the basket form")
    public String getDishNameFromAddForm(){
        return driver.findElement(dishTitleOnAddForm).getText();
    }

    @Step("Is add dish to basket form visible")
    public boolean isVisible(){
        return driver.findElement(container).isDisplayed();
    }

}
