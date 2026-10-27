package com.vrgsl.step.pages;

import com.vrgsl.step.driver.DriverManager;
import com.vrgsl.step.helper.ConfigHelper;
import com.vrgsl.step.pages.cart.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static com.vrgsl.step.helper.ElementHelper.getBy;

public abstract class BasePage {

    protected final WebDriver driver = DriverManager.getDriver();
    protected final WebDriverWait wait = new WebDriverWait(driver,
            Duration.ofSeconds(ConfigHelper.<Integer>get("timeouts.explicitWait")));

    protected BasePage() {
        // The site re-renders elements and briefly covers them with overlays, so interactions are retried
        wait.ignoreAll(List.of(StaleElementReferenceException.class, ElementNotInteractableException.class));
    }

    protected void waitForVisible(String key) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(getBy(key)));
    }

    protected List<WebElement> waitForAll(String key) {
        return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(getBy(key), 0));
    }

    // Reads inside the wait so a re-render between finding and reading is retried
    protected <T> T read(String key, Function<WebElement, T> reader) {
        waitForVisible(key);
        By by = getBy(key);
        return wait.until(d -> reader.apply(d.findElement(by)));
    }

    protected String getText(String key) {
        return read(key, WebElement::getText);
    }

    protected void click(String key, Object... args) {
        By by = getBy(key, args);
        wait.until(d -> {
            d.findElement(by).click();
            return true;
        });
    }

    protected void type(String key, CharSequence... text) {
        By by = getBy(key);
        wait.until(d -> {
            d.findElement(by).sendKeys(text);
            return true;
        });
    }

    public int getCartItemCount() {
        return Integer.parseInt(getText("cartItemCount"));
    }

    public CartPage goToCart() {
        click("shoppingCartButton");
        return new CartPage();
    }
}
