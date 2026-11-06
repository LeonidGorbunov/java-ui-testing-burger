package ru.education_services.qa_stellarburgers.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Duration BASE_TIMEOUT = Duration.ofSeconds(20);

    public BasePage (WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, BASE_TIMEOUT);
    }

    protected void click (By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type (By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    protected String getText (By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected boolean isDisplayed (By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Лог: Элемент не появился на странице в течение заданного таймаута. " +
                    "Локатор: " + locator.toString());
            return false;
        }
    }

    protected boolean isContainerScrolledToElement(By elementLocator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            String script =
                    "var container = document.querySelector(\"[class*='BurgerIngredients_ingredients__menuContainer']\"); " +
                    "var target = arguments[0]; " +
                    "var containerRect = container.getBoundingClientRect(); " +
                    "var targetRect = target.getBoundingClientRect(); " +
                    "return Math.abs(targetRect.top - containerRect.top) <= 15;";

            return wait.until(driver -> (Boolean) js.executeScript(script, element));

        } catch (TimeoutException e) {
            System.out.println("Лог: Список не доскроллился до целевого заголовка в течение заданного таймаута. " +
                    "Локатор: " + elementLocator.toString());
            return false;
        }
    }
}
