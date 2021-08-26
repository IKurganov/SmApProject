package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.componentsAndPopups.*;

import static org.assertj.core.api.Assertions.assertThat;

public class PizzeriaTest extends TestBase {
    private final Logger LOG = LogManager.getLogger(PizzeriaTest.class);

    @Test
    @Disabled("Search by keyword in 'robot' mode doesn't work =( ")
    @Epic("Pizzeria Test")
    @Feature("Search by keyword")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Filtration products (their titles) by keyword")
    public void checkSearchForProductsByKeyword() {
        LOG.info("Let's begin test of product search");

        MainPage mainPage = new MainPage(driver);
        LOG.info("Go to pizzeria's site");
        mainPage.open(baseUrl);

        LOG.info("Insert keyword in search input, then test values");

        mainPage.searchWithTheKeyword("Grill");

        takeScreenshot("Some screenshot", driver);
    }

    @Test
    @Epic("Pizzeria Test")
    @Feature("Adding product to the basket")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Check that product can be successfully added to the basket")
    public void checkAddingDishesToTheBasket() {
        LOG.info("Let's begin test of adding dishes to the basket");
// Step 1 from doc

        MainPage mainPage = new MainPage(driver);

        LOG.info("Go to pizzeria's site and choose category");
        mainPage.open(baseUrl).clickOnCategory("Geflügel");

        assertThat(mainPage.getCountOfDishesFromMainPage())
                .as("List of dishes will contain items")
                .isGreaterThan(0);

        DishMenuRow testDish = mainPage.getDishRowsList().get(0);
        String testDishTitle = testDish.getDishNameFromMainPageMenu();

        InfoCard infoAboutTestDish =
                mainPage.clickOnInfoCardButtonOnDishRow(testDish);

        assertThat(infoAboutTestDish.isVisible())
                .as("Info card was successfully opened")
                .isTrue();

        assertThat(infoAboutTestDish.getDishTitleFromInfoCard())
                .as("Info card contain correct dish title")
                .isEqualTo(testDishTitle);

// Step 2 from doc

        infoAboutTestDish.clickCancelButton();
        assertThat(infoAboutTestDish.isVisible())
                .as("Info card was successfully closed")
                .isFalse();

// Step 3 from doc

        infoAboutTestDish =
                mainPage.clickOnInfoCardButtonOnDishRow(testDish);

        AddDishToBasketForm addTestDishToBasketForm =
                infoAboutTestDish.clickAddButton();

        //TODO отдебажить при свете дня
        addTestDishToBasketForm.clickAddButton();
        assertThat(addTestDishToBasketForm.isVisible())
                .as("Adding dish to basket form was not closed")
                .isTrue();

// Step 4 from doc

        for (RequiredIngredientRow ingredient : addTestDishToBasketForm.getRequiredIngredientRows()) {
            LOG.info("Work with ingredient - {}", ingredient.toString());
            ingredient.switchRow(true);
            ingredient.chooseFirstOption();
        }
        mainPage = addTestDishToBasketForm.clickAddButton();
        assertThat(addTestDishToBasketForm.isVisible())
                .as("'Adding dish to basket' form was closed after adding dish")
                .isFalse();

// Step 5 from doc

        Basket testBasket = mainPage.getBasket();
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(testBasket.countOfAddedItems())
                    .as("There will be only one product in the basket")
                    .isEqualTo(1);
            soft.assertThat(testBasket.getListOfAddedItemsInBasket().get(0).getDishNameFromBasket())
                    .as("Names of test dish and dish from basket will be equal")
                    .isEqualTo(testDishTitle);
        });
    }

    @Test
    @Epic("Pizzeria Test")
    @Feature("Navigation via categories")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Check that navigation via categories works")
    public void checkNavigationViaCategories() {

    }
}
