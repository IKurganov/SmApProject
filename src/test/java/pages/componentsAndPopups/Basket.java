package pages.componentsAndPopups;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import java.util.List;
import java.util.stream.Collectors;

public class Basket extends BasePage {
    //private By locatorsForAmountAndEtc;
    private By containerWithDishes = By.xpath("//div[@class='shopping-list']");
    private By dishItems = containerWithDishes.xpath(".//div[@id='basket-item']");

    public Basket(WebDriver driver) {
        super(driver);
    }

    @Step("Get count of added dishes to basket")
    public int countOfAddedItems(){
        return driver.findElements(dishItems).size();
    }

    @Step("Get list of added items - dishes in basket")
    public List<BasketAddedItem> getListOfAddedItemsInBasket() {
        return driver.findElements(dishItems).stream().map(BasketAddedItem::new).collect(Collectors.toList());
    }


}
