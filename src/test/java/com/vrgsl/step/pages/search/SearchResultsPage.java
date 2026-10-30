package com.vrgsl.step.pages.search;

import com.vrgsl.step.pages.BasePage;
import com.vrgsl.step.pages.product.ProductDetailPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static com.vrgsl.step.helper.ElementHelper.getBy;

public class SearchResultsPage extends BasePage {

    public int getProductCount() {
        return waitForAll("productListItems").size();
    }

    // Clicks are ignored until the filter panel is hydrated
    public void filterBySeller(String seller) {
        waitForVisible("filterPanelReady");
        click("sellerCheckbox", seller);
        wait.until(d -> getAppliedFilters().contains(seller));
    }

    public List<String> getAppliedFilters() {
        return wait.until(d -> d.findElements(getBy("appliedFilterNames")).stream()
                .map(WebElement::getText)
                .toList());
    }

    public void sortBy(String option) {
        click("sortDropdown");
        click("sortOption", option);
        wait.until(ExpectedConditions.textToBe(getBy("sortDropdown"), option));
        // The old list stays on screen under a loading overlay until the sorted one arrives
        wait.until(ExpectedConditions.invisibilityOfElementLocated(getBy("productListLoading")));
    }

    public String getSelectedSort() {
        return getText("sortDropdown");
    }

    public String getFirstProductTitle() {
        return getText("firstProductTitle");
    }

    public String getFirstProductPrice() {
        return getText("firstProductPrice");
    }

    public void addFirstProductToCart() {
        click("firstProductAddToCartButton");
    }

    // The list grows only when its last card is scrolled into view
    public void loadProducts(int minCount) {
        By items = getBy("productListItems");
        wait.until(d -> {
            List<WebElement> loaded = d.findElements(items);
            if (loaded.size() >= minCount) {
                return true;
            }
            if (!loaded.isEmpty()) {
                new Actions(d).scrollToElement(loaded.get(loaded.size() - 1)).perform();
            }
            return false;
        });
    }

    public ProductDetailPage openMostReviewedProduct(int limit) {
        Set<String> tabsBefore = driver.getWindowHandles();
        ProductCard product = wait.until(d -> d.findElements(getBy("productListItems")).stream()
                .limit(limit)
                .map(ProductCard::new)
                .filter(ProductCard::hasReviews)
                .max(Comparator.comparingInt(ProductCard::getReviewCount))
                .orElse(null));
        wait.until(d -> {
            product.open();
            return true;
        });
        switchToNewTab(tabsBefore);
        return new ProductDetailPage();
    }
}
