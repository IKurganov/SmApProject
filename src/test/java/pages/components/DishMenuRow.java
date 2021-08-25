package pages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DishMenuRow {

    private WebElement row;
    private By dishInfoButton = By.xpath("//button[@class='productInfo']");




    public DishMenuRow(WebElement row) {
        this.row = row;
    }

    public WebElement getRowElement() {
        return this.row;
    }

    public InfoCard clickOnInfoAboutDish(){
        row.findElement(dishInfoButton).click();
        return new InfoCard();
    }

    @Step("Is row displayed")
    public boolean isVisible() {
        return row.isDisplayed();
    }

    @Override
    public String toString() {
        return row.getText().replaceAll("\n", ",");
    }
}
