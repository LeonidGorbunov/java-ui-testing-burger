package ru.education_services.qa_stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.education_services.qa_stellarburgers.pageobject.MainPage;
import static org.junit.Assert.assertTrue;

@Epic("Stellar Burgers")
@Feature("3. Раздел \"Конструктор\"")
public class ConstructorTest extends BaseTest {

    private MainPage mainPage;

    @Before
    public void prepareTestData() {
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("1. Раздел \"Соусы\". Проверка перехода к разделу \"Соусы\" при клике на таб \"Соусы\".")
    @Description("Позитивный сценарий перехода к разделу соусов в \"Конструкторе\". " +
            "Проверка скролла ленты Конструктора до заголовка \"Соусы\" при переключении таба.")
    public void testTransitionToSaucesSection() throws InterruptedException {

        mainPage.openMainPage(BASE_URL);

        mainPage.clickSaucesTab();

        assertTrue("Список ингредиентов не доскроллился до раздела \"Соусы\"!",
                mainPage.isScrolledToSauces());
    }

    @Test
    @DisplayName("2. Раздел \"Начинки\". Проверка перехода к разделу \"Начинки\" при клике на таб \"Начинки\".")
    @Description("Позитивный сценарий перехода к разделу начинок в \"Конструкторе\". " +
            "Проверка скролла ленты Конструктора до заголовка \"Начинки\" при переключении таба.")
    public void testTransitionToFillingsSection() throws InterruptedException {

        mainPage.openMainPage(BASE_URL);

        mainPage.clickFillingsTab();

        assertTrue("Список ингредиентов не доскроллился до раздела \"Начинки\"!",
                mainPage.isScrolledToFillings());
    }

    @Test
    @DisplayName("3. Раздел \"Булки\". Проверка перехода к разделу \"Булки\" при клике на таб \"Булки\"")
    @Description("Позитивный сценарий перехода к разделу булок в \"Конструкторе\". " +
            "Проверка скролла ленты Конструктора до заголовка \"Булки\" при переключении таба.")
    public void testTransitionToBunsSection() throws InterruptedException {

        mainPage.openMainPage(BASE_URL);
        mainPage.clickSaucesTab();

        mainPage.clickBunsTab();

        assertTrue("Список ингредиентов не доскроллился до раздела \"Булки\"!",
                mainPage.isScrolledToBuns());
    }
}
