package pages.componentsAndPopups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.MainPage;

public class AddDishToBasketForm extends BasePage {
    private By container = By.xpath("//div[@class='ant-modal-body']");
    private By cancelButton = container.xpath(".//div[@qa-data='extras-back-btn']");

    public AddDishToBasketForm(WebDriver driver) {
        super(driver);
    }

    public MainPage clickCancelButton() {
        driver.findElement(cancelButton).click();
        return new MainPage(driver);
    }


}
