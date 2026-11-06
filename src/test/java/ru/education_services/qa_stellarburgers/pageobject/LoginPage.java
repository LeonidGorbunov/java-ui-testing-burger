package ru.education_services.qa_stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    //локаторы
    //ссылка "Зарегистрироваться"
    private final By registerLink = By.xpath("//a[normalize-space(text())='Зарегистрироваться']");
    //заголовок "Вход"
    private final By loginHeader = By.xpath("//h2[normalize-space(text())='Вход']");
    //поле ввода "Email"
    private final By emailField = By.xpath("//label[normalize-space(text())='Email']/following-sibling::input");
    //поле ввода "Пароль"
    private final By passwordField = By.xpath("//label[normalize-space(text())='Пароль']/following-sibling::input");
    //кнопка "Войти"
    private final By loginButton = By.xpath("//button[contains(@class, 'button_button') and normalize-space(text())='Войти']");
    //ссылка "Восстановить пароль"
    private final By forgotPasswordLink = By.xpath("//a[normalize-space(text())='Восстановить пароль']");

    public LoginPage (WebDriver driver) {
        super(driver);
    }

    @Step("Действие: клик по ссылке \"Зарегистрироваться\"")
    public void clickRegisterLink () {
        click(registerLink);
    }

    @Step("⚠️ ПРОВЕРКА: отображается заголовок \"Вход\" на странице авторизации пользователя")
    public boolean isLoginHeaderDisplayed() {
        return isDisplayed(loginHeader);
    }

    @Step("Действие: ввод в поле \"Email\"")
    public void inputEmailField (String email) {
        type(emailField, email);
    }

    @Step("Действие: ввод в поле \"Пароль\"")
    public void inputPasswordField (String password) {
        type(passwordField, password);
    }

    @Step("Действие: клик по кнопке \"Войти\"")
    public void clickLoginButton () {
        click(loginButton);
    }

    @Step("Сценарий: логин пользователя")
    public void loginUser (String email, String password) {
        inputEmailField(email);
        inputPasswordField(password);
        clickLoginButton();
    }

    @Step("Действие: клик по ссылке \"Восстановить пароль\"")
    public void clickForgotPasswordLink () {
        click(forgotPasswordLink);
    }
}
