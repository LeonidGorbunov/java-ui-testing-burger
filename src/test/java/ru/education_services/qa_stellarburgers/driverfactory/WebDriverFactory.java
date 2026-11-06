package ru.education_services.qa_stellarburgers.driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    public static WebDriver driverSelect (String browserName) {

        if (browserName == null) {
            throw new IllegalArgumentException("Укажите chrome или yandex");
        }

        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        switch (browserName.toLowerCase().trim()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");

                if (isHeadless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080", "--disable-gpu", "--no-sandbox");
                }

                return new ChromeDriver(chromeOptions);

            case "yandex":
                String yandexDriverPath = System.getProperty("yandex.driver.path",
                        "C:\\Webdriver\\Yandex\\yandexdriver.exe");
                System.setProperty("webdriver.chrome.driver", yandexDriverPath);

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");

                String yandexBrowserPath = System.getProperty("yandex.browser.path",
                        "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe");
                options.setBinary(yandexBrowserPath);

                if (isHeadless) {
                    options.addArguments("--headless=new");
                    options.addArguments("--window-size=1920,1080", "--disable-gpu", "--no-sandbox");
                }

                return new ChromeDriver(options);

            default:
                throw new IllegalArgumentException("Браузер не поддерживается: " + browserName);
        }
    }
}