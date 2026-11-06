package ru.education_services.qa_stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    //локаторы
    //кнопка "Войти в аккаунт"
    private final By accountLoginButton = By.xpath("//button[contains(@class, 'button_button') and normalize-space(text())='Войти в аккаунт']");
    //кнопка "Личный кабинет" в хэдере
    private final By personalAccountButton = By.xpath("//p[normalize-space(text())='Личный Кабинет']/parent::a");
    //кнопка "Оформить заказ"
    private final By orderCreateButton = By.xpath("//button[contains(@class, 'button_button') and normalize-space(text())='Оформить заказ']");
    //таб "Булки"
    private final By bunsTab = By.xpath("//span[normalize-space(text())='Булки']/parent::div");
    //таб "Соусы"
    private final By saucesTab = By.xpath("//span[normalize-space(text())='Соусы']/parent::div");
    //таб "Начинки"
    private final By fillingsTab = By.xpath("//span[normalize-space(text())='Начинки']/parent::div");
    //заголовок раздела "Конструктор": "Булки"
    private final By bunsHeader = By.xpath("//h2[normalize-space(text())='Булки']");
    //заголовок раздела "Конструктор": "Соусы"
    private final By saucesHeader = By.xpath("//h2[normalize-space(text())='Соусы']");
    //заголовок раздела "Конструктор": "Начинки"
    private final By fillingsHeader = By.xpath("//h2[normalize-space(text())='Начинки']");

    public MainPage (WebDriver driver) {
        super(driver);
    }

    @Step("Действие: открыть главную страницу стенда Stellar Burgers: {url}")
    public void openMainPage (String url) {
        driver.get(url);
    }

    @Step("Действие: клик по кнопке \"Войти в аккаунт\"")
    public void clickAccountLoginButton () {
        click(accountLoginButton);
    }

    @Step("Действие: клик по кнопке \"Личный Кабинет\" в хэдере")
    public void clickPersonalAccountButton () {
        click(personalAccountButton);
    }

    @Step("⚠️ ПРОВЕРКА: отображается кнопка \"Оформить заказ\" на главной странице")
    public boolean isOrderCreateButtonDisplayed() {
        return isDisplayed(orderCreateButton);
    }

    @Step("Действие: клик по табу \"Булки\"")
    public void clickBunsTab() {
        click(bunsTab);
    }

    @Step("Действие: клик по табу \"Соусы\"")
    public void clickSaucesTab() {
        click(saucesTab);
    }

    @Step("Действие: клик по табу \"Начинки\"")
    public void clickFillingsTab() {
        click(fillingsTab);
    }

    @Step("⚠️ ПРОВЕРКА: Список ингредиентов раздела \"Конструктор\" успешно проскроллен к разделу \"Булки\"")
    public boolean isScrolledToBuns() {
        return isContainerScrolledToElement(bunsHeader);
    }

    @Step("⚠️ ПРОВЕРКА: Список ингредиентов раздела \"Конструктор\" успешно проскроллен к разделу \"Соусы\"")
    public boolean isScrolledToSauces() {
        return isContainerScrolledToElement(saucesHeader);
    }

    @Step("⚠️ ПРОВЕРКА: Список ингредиентов раздела \"Конструктор\" успешно проскроллен к разделу \"Начинки\"")
    public boolean isScrolledToFillings() {
        return isContainerScrolledToElement(fillingsHeader);
    }
}
