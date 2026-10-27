package com.vrgsl.step.pages.search;

import com.vrgsl.step.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.vrgsl.step.helper.ElementHelper.getBy;

public class SearchResultsPage extends BasePage {

    public int getProductCount() {
        return waitForAll("productCards").size();
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

    public String getNotificationText() {
        return getText("addToCartNotification");
    }
}
