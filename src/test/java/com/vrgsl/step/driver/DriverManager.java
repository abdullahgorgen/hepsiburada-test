package com.vrgsl.step.driver;

import com.vrgsl.step.helper.ConfigHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public final class DriverManager {

    private static WebDriver driver;

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(ConfigHelper.<List<String>>get("chrome.args"));
        if (ConfigHelper.<Boolean>get("chrome.headless")) {
            options.addArguments("--headless=new");
        }
        options.setExperimentalOption("excludeSwitches", ConfigHelper.get("chrome.excludeSwitches"));

        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigHelper.<Integer>get("timeouts.pageLoadTimeout")));
    }

    public static void quitDriver() {
        if (driver == null) {
            return;
        }
        try {
            driver.quit();
        } finally {
            driver = null;
        }
    }
}
