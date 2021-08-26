package pages.componentsAndPopups;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DishMenuRow {

    private WebElement row;
    private By dishInfoButton = By.xpath("//button[@class='productInfo']");
    private By dishName = By.cssSelector("h5.productName");



    public DishMenuRow(WebElement row) {
        this.row = row;
    }

    public WebElement getRowElement() {
        return this.row;
    }

    @Step("Click on info card button")
    public void clickOnInfoAboutDish(){
        row.findElement(dishInfoButton).click();
    }

    @Step("Is row displayed")
    public boolean isVisible() {
        return row.isDisplayed();
    }

    @Step("Get (or 'record') name of test dish from main page")
    public String getDishNameFromMainPageMenu(){
        return row.findElement(dishName).getText();
    }

    @Override
    public String toString() {
        return row.getText().replaceAll("\n", ",");
    }
}
