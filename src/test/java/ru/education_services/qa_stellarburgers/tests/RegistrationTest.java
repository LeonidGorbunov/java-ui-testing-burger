package ru.education_services.qa_stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.education_services.qa_stellarburgers.api.data.UserDataGenerator;
import ru.education_services.qa_stellarburgers.api.models.request.UserRegisterRequest;
import ru.education_services.qa_stellarburgers.pageobject.LoginPage;
import ru.education_services.qa_stellarburgers.pageobject.MainPage;
import ru.education_services.qa_stellarburgers.pageobject.RegisterPage;
import static org.junit.Assert.*;

@Epic("Stellar Burgers")
@Feature("1. Регистрация нового пользователя")
public class RegistrationTest extends BaseTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private UserRegisterRequest userRequest;

    @Before
    public void prepareRegTestData() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);

        userRequest = UserDataGenerator.getDefaultUser();
    }

    @Test
    @DisplayName("1. Успешная регистрация нового пользователя")
    @Description("Позитивный сценарий регистрации нового пользователя. Проверка успешного редиректа на страницу авторизации.")
    public void successfulRegistrationTest() {

        mainPage.openMainPage(BASE_URL);
        mainPage.clickAccountLoginButton();
        loginPage.clickRegisterLink();

        registerPage.registerUser(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());

        assertTrue("Пользователь не был перенаправлен на страницу авторизации после успешной регистрации!",
                loginPage.isLoginHeaderDisplayed());
    }

    @Test
    @DisplayName("2. Появление ошибки валидации пароля при регистрации нового пользователя с использованием невалидного " +
            "пароля (менее шести символов)")
    @Description("Негативный сценарий регистрации нового пользователя. Проверка появления сообщения об ошибке " +
            "\"Некорректный пароль\" при передаче пароля менее шести символов.")
    public void registrationWithShortPasswordShouldShowErrorTest() {

        String shortPassword = userRequest.getPassword().substring(0, 3);
        userRequest.setPassword(shortPassword);

        mainPage.openMainPage(BASE_URL);
        mainPage.clickAccountLoginButton();
        loginPage.clickRegisterLink();

        registerPage.registerUser(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());

        assertEquals("Текст ошибки валидации короткого пароля не совпадает или не отображается!",
                "Некорректный пароль", registerPage.getPasswordErrorText());
    }

    @After
    public void deleteUserAfterTest() {
        userTeardownSteps.deleteUserSafely(null, userRequest.getEmail(), userRequest.getPassword());
    }
}