package pages.componentsAndPopups;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class CategoryRow {
    private WebElement row;

    public CategoryRow(WebElement row) {
        this.row = row;
    }

    @Step("Is category tab highlighted")
    public boolean isCategoryActive(){
        return row.getAttribute("class").contains("active");
    }


}
