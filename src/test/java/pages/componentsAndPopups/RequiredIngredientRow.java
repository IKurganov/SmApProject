package pages.componentsAndPopups;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class RequiredIngredientRow {
    private WebElement row;

    private By ingredientOptions = By.xpath("//div[@qa-data='extra-product-size']");

    public RequiredIngredientRow(WebElement row) {
        this.row = row;
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
    public void chooseFirstOption() {
        switchOption(true);
    }

    private void switchOption(boolean optionState) {
        while (isOptionChosen() == !optionState) {
            row.findElements(ingredientOptions).get(0).click();
        }
        assertThat(isOptionChosen())
                .as("Option state should be equal to requested")
                .isEqualTo(optionState);
    }

    private boolean isOptionChosen(){
        return row.findElements(ingredientOptions).get(0).getAttribute("class").contains("button-size-active");
    }



}
