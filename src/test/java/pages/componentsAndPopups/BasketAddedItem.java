package pages.componentsAndPopups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasketAddedItem {
    private WebElement item;

    private By dishName = By.cssSelector("div.product-name");

    public BasketAddedItem(WebElement item) {
        this.item = item;
    }

    public String getDishNameFromBasket(){
        return item.findElement(dishName).getText();
    }

}
