package ru.education_services.qa_stellarburgers.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {

    //ссылка "Войти"
    private final By loginLink = By.xpath("//a[normalize-space(text())='Войти']");

    public ForgotPasswordPage (WebDriver driver) {
        super(driver);
    }

    @Step("Действие: клик по ссылке \"Войти\"")
    public void clickLoginLink () {
        click(loginLink);
    }
}