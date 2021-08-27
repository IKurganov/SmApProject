package pages.componentsAndPopups;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RequiredIngredientRow {
    private WebElement row;

    //private By ingredientsContainer = By.xpath("//div[@class='product-options__wrap']");
    private By ingredientOptions = By.xpath(".//div[@qa-data='extra-product-size']");

    //private WebElement ingredientsContainer;
    //private List<WebElement> ingredientOptions;

    public RequiredIngredientRow(WebElement row) {
        this.row = row;
    }

    public WebElement getRowElement() {
        return this.row;
    }

    //main goals for ingredient row
    @Step("Open/close required ingredient row")
    public void switchRow(boolean rowState) {
        while (isIngredientRowOpen() == !rowState) {
            row.findElement(By.cssSelector("div.product-options__accordion__header")).click();
        }
        assertThat(isIngredientRowOpen())
                .as("Row state should be equal to requested")
                .isEqualTo(rowState);
    }

    private boolean isIngredientRowOpen(){
        return row.getAttribute("class").contains("active");
    }

    // METHODS FOR OPTIONS
    // very strong abstraction - just make first option in required ingredient active
    @Step("Choose first option in required ingredient")
    public void chooseFirstOption(int index) {
        switchOption(true, index);
    }

    private void switchOption(boolean optionState, int index) {
        while (isOptionChosen(index) == !optionState) {
            row.findElements(By.xpath("//div[@class='product-options__wrap']"))
                    .get(index)
                    .findElement(ingredientOptions) // take first
                    .click();
        }
        assertThat(isOptionChosen(index))
                .as("Option state should be equal to requested")
                .isEqualTo(optionState);
    }

    private boolean isOptionChosen(int index){
        return row.findElements(By.xpath("//div[@class='product-options__wrap']"))
                .get(index)
                .findElement(ingredientOptions) // take first
                .getAttribute("class").contains("button-size-active");
    }

}
