package ru.education_services.qa_stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.education_services.qa_stellarburgers.api.client.UserClient;
import ru.education_services.qa_stellarburgers.api.data.UserDataGenerator;
import ru.education_services.qa_stellarburgers.api.models.request.UserRegisterRequest;
import ru.education_services.qa_stellarburgers.pageobject.ForgotPasswordPage;
import ru.education_services.qa_stellarburgers.pageobject.LoginPage;
import ru.education_services.qa_stellarburgers.pageobject.MainPage;
import ru.education_services.qa_stellarburgers.pageobject.RegisterPage;
import static org.junit.Assert.assertTrue;

@Epic("Stellar Burgers")
@Feature("2. Авторизация пользователя")
public class LoginTest extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private UserRegisterRequest userRequest;
    private String accessToken;

    @Before
    public void prepareAuthTestData() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);

        userRequest = UserDataGenerator.getDefaultUser();

        UserClient clientForSetup = new UserClient();
        Response response = clientForSetup.actionUserRegister(userRequest);
        accessToken = response.path("accessToken");
    }

    @Test
    @DisplayName("1. Успешная авторизация. Точка входа в форму авторизации: кнопка \"Войти в аккаунт\" на главной странице")
    @Description("Позитивный сценарий авторизации пользователя. Проверка отображения кнопки \"Оформить заказ\" на " +
            "главной странице после успешной авторизации через кнопку \"Войти в аккаунт\" на главной странице.")
    public void successfulLoginThroughAccountLoginButtonOnMainPage() {

        mainPage.openMainPage(BASE_URL);
        mainPage.clickAccountLoginButton();

        loginPage.loginUser(userRequest.getEmail(), userRequest.getPassword());

        assertTrue("Кнопка \"Оформить заказ\" не отображается на главной странице!",
                mainPage.isOrderCreateButtonDisplayed());
    }

    @Test
    @DisplayName("2. Успешная авторизация. Точка входа в форму авторизации: кнопка \"Личный Кабинет\" в хэдере главной страницы")
    @Description("Позитивный сценарий авторизации пользователя. Проверка отображения кнопки \"Оформить заказ\" на " +
            "главной странице после успешной авторизации через кнопку \"Личный Кабинет\" в хэдере главной страницы.")
    public void successfulLoginThroughPersonalAccountButtonOnMainPage() {

        mainPage.openMainPage(BASE_URL);
        mainPage.clickPersonalAccountButton();

        loginPage.loginUser(userRequest.getEmail(), userRequest.getPassword());

        assertTrue("Кнопка \"Оформить заказ\" не отображается на главной странице!",
                mainPage.isOrderCreateButtonDisplayed());
    }

    @Test
    @DisplayName("3. Успешная авторизация. Точка входа в форму авторизации: ссылка \"Войти\" в форме регистрации")
    @Description("Позитивный сценарий авторизации пользователя. Проверка отображения кнопки \"Оформить заказ\" на " +
            "главной странице после успешной авторизации через ссылку \"Войти\" в форме регистрации.")
    public void successfulLoginThroughLoginLinkOnRegisterPage() {

        mainPage.openMainPage(BASE_URL);
        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterLink();
        registerPage.clickLoginLink();

        loginPage.loginUser(userRequest.getEmail(), userRequest.getPassword());

        assertTrue("Кнопка \"Оформить заказ\" не отображается на главной странице!",
                mainPage.isOrderCreateButtonDisplayed());
    }

    @Test
    @DisplayName("4. Успешная авторизация. Точка входа в форму авторизации: ссылка \"Войти\" на странице восстановления пароля")
    @Description("Позитивный сценарий авторизации пользователя. Проверка отображения кнопки \"Оформить заказ\" на " +
            "главной странице после успешной авторизации через ссылку \"Войти\" на странице восстановления пароля.")
    public void successfulLoginThroughLoginLinkOnForgotPasswordPage() {

        mainPage.openMainPage(BASE_URL);
        mainPage.clickPersonalAccountButton();
        loginPage.clickForgotPasswordLink();
        forgotPasswordPage.clickLoginLink();

        loginPage.loginUser(userRequest.getEmail(), userRequest.getPassword());

        assertTrue("Кнопка \"Оформить заказ\" не отображается на главной странице!",
                mainPage.isOrderCreateButtonDisplayed());
    }

    @After
    public void deleteUserAfterTest() {
        userTeardownSteps.deleteUserSafely(accessToken, userRequest.getEmail(), userRequest.getPassword());
    }
}
