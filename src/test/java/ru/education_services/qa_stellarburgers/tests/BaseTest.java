package ru.education_services.qa_stellarburgers.tests;

import io.qameta.allure.Allure;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.education_services.qa_stellarburgers.api.steps.UserTeardownSteps;
import ru.education_services.qa_stellarburgers.driverfactory.WebDriverFactory;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public abstract class BaseTest {

    // менее стабильное зеркало: https://stellarburgers.education-services.ru
    public static final String BASE_URL = System.getProperty("site.url", "https://qa-stellarburgers.education-services.ru");
    protected WebDriver driver;

    protected UserTeardownSteps userTeardownSteps;

    @Parameterized.Parameter(0)
    public String browser;

    @Parameterized.Parameters(name = "Браузер: {0}")
    public static Collection<Object[]> getEnvironments() {
        String cliBrowser = System.getProperty("browser");

        if (cliBrowser != null && !cliBrowser.isEmpty()) {
            return Arrays.asList(new Object[][] {
                    {cliBrowser.toLowerCase().trim()}
            });
        }

        return Arrays.asList(new Object[][] {
                {"chrome"},
                {"yandex"}
        });
    }

    @Before
    public void setUp() {
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
