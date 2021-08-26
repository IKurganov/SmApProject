package pages.componentsAndPopups;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.MainPage;

public class InfoCard extends BasePage {

    private By container = By.xpath("//div[@class='ProductModal']");
    private By cancelButton = container.xpath(".//button/p[text()='Zurück']");
    private By addButton = container.xpath(".//button[contains(@class,'submit')]/p");
    private By dishTitle = container.xpath(".//h3");

    public InfoCard(WebDriver driver) {
        super(driver);
    }

    @Step("Close info card")
    public MainPage clickCancelButton(){
        driver.findElement(cancelButton).click();
        return new MainPage(driver);
    }

    @Step("Go to adding product form")
    public AddDishToBasketForm clickAddButton(){
        driver.findElement(addButton).click();
        return new AddDishToBasketForm(driver);
    }

    @Step("Get dish title from info card")
    public String getDishTitleFromInfoCard(){
        return driver.findElement(dishTitle).getText();
    }

    @Step("Is info card visible")
    public boolean isVisible(){
        try {
            return driver.findElement(container).isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }
}
