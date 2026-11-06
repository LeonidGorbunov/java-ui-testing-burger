package ru.education_services.qa_stellarburgers.tests;

import io.qameta.allure.Allure;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.education_services.qa_stellarburgers.api.steps.UserTeardownSteps;
import ru.education_services.qa_stellarburgers.driverfactory.WebDriverFactory;
import java.time.Duration;

public abstract class BaseTest {

    // это зеркало гораздо стабильнее стенда из ЯП: https://stellarburgers.education-services.ru
    public static final String BASE_URL = System.getProperty("site.url", "https://qa-stellarburgers.education-services.ru");
    protected WebDriver driver;

    protected UserTeardownSteps userTeardownSteps;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.driverSelect(browser);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        userTeardownSteps = new UserTeardownSteps();

        Allure.parameter("Окружение (браузер)", browser);
    }

    @After
    public void tearDown () {
        if (driver != null) {
            driver.quit();
        }
    }
}
