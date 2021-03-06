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
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.componentsAndPopups.*;

import java.util.ArrayList;
import java.util.List;

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

        mainPage.clickPickup();

        //TODO отдебажить при свете дня
        addTestDishToBasketForm.clickAddButton();
        assertThat(addTestDishToBasketForm.isVisible())
                .as("Adding dish to basket form was not closed")
                .isTrue();

// Step 4 from doc

        List<RequiredIngredientRow> testIngredients = addTestDishToBasketForm.getRequiredIngredientRows();
        for (int i = 0; i < testIngredients.size(); i++){
            LOG.info("Work with ingredient - {}", testIngredients.get(i).toString());
            testIngredients.get(i).switchRow(true);
            testIngredients.get(i).chooseFirstOption(i);
        }

        /*for (RequiredIngredientRow ingredient : addTestDishToBasketForm.getRequiredIngredientRows()) {
        }*/

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
    @Severity(SeverityLevel.BLOCKER)
    @Tag("Smoke")
    @DisplayName("Check that navigation via categories works")
    public void checkNavigationViaCategories() {
        //TODO отдебажить при свете дня - 2
        LOG.info("Let's begin test of navigation via categories");
// Step 1 from doc
        MainPage mainPage = new MainPage(driver);

        LOG.info("Go to pizzeria's site");
        mainPage.open(baseUrl);

        LOG.info("Take all categories, count them and check that they are inactive");
        assertThat(mainPage.getCountOfCategoriesOnMainPage()).isEqualTo(18);

        for (CategoryRow cat : mainPage.getCategoriesList()) {
            assertThat(cat.isCategoryActive())
                    .as("Before the user starts selecting category, the tabs are not active")
                    .isFalse();
        }

// Step 2 from doc
        //TODO May be change to parametrized test with @ParameterizedTest, @MethodSource and static Stream<Arguments>
        ArrayList<String> testCategories = new ArrayList<String>();
        testCategories.add("Getränke");
        testCategories.add("Rumpsteaks");
        testCategories.add("Suppen");
        for (String catName : testCategories) {
            mainPage.clickOnCategory(catName);
            SoftAssertions.assertSoftly(soft -> {
                soft.assertThat(mainPage.getExactlyCategoryRow(catName).isCategoryActive())
                        .as("One of test categories will be active after click")
                        .isTrue();
                soft.assertThat(mainPage.getExactlyCategoryRow("Fisch").isCategoryActive())
                        .as("Category tab not from list remains inactive")
                        .isFalse();
                soft.assertThat(mainPage.getCategoryNameFromMainPageMenu())
                        .as("Menu was rebuilt getting category name")
                        .isEqualTo(catName);
            });
        }
    }
}
