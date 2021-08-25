package tests;

import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

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
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Check that product can be successfully added to the basket")
    public void checkAddingDishesToTheBasket() {
        LOG.info("Let's begin test of adding dishes to the basket");
        MainPage mainPage = new MainPage(driver);

        LOG.info("Go to pizzeria's site");
        mainPage.open(baseUrl);



    }

}
