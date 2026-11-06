package ru.education_services.qa_stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    //локаторы
    //поле ввода "Имя"
    private final By nameField = By.xpath("//label[normalize-space(text())='Имя']/following-sibling::input");
    //поле ввода "Email"
    private final By emailField = By.xpath("//label[normalize-space(text())='Email']/following-sibling::input");
    //поле ввода "Пароль"
    private final By passwordField = By.xpath("//label[normalize-space(text())='Пароль']/following-sibling::input");
    //кнопка "Зарегистрироваться"
    private final By registerButton = By.xpath("//button[contains(@class, 'button_button') and normalize-space(text())='Зарегистрироваться']");
    //текст ошибки валидации "Некорректный пароль"
    private final By passwordError = By.xpath("//p[contains(@class, 'input__error') and normalize-space(text())='Некорректный пароль']");
    //ссылка "Войти"
    private final By loginLink = By.xpath("//a[normalize-space(text())='Войти']");

    public RegisterPage (WebDriver driver) {
        super(driver);
    }

    @Step("Действие: ввод в поле \"Имя\"")
    public void inputNameField (String name) {
        type(nameField, name);
    }

    @Step("Действие: ввод в поле \"Email\"")
    public void inputEmailField (String email) {
        type(emailField, email);
    }

    @Step("Действие: ввод в поле \"Пароль\"")
    public void inputPasswordField (String password) {
        type(passwordField, password);
    }

    @Step("Действие: клик по кнопке \"Зарегистрироваться\"")
    public void clickRegisterButton () {
        click(registerButton);
    }

    @Step("Сценарий: регистрация нового пользователя")
    public void registerUser (String name, String email, String password) {
        inputNameField(name);
        inputEmailField(email);
        inputPasswordField(password);
        clickRegisterButton();
    }

    @Step("⚠️ ПРОВЕРКА: отображается ошибка валидации \"Некорректный пароль\" на странице регистрации нового пользователя")
    public String getPasswordErrorText() {
        return getText(passwordError);
    }

    @Step("Действие: клик по ссылке \"Войти\"")
    public void clickLoginLink () {
        click(loginLink);
    }
}
