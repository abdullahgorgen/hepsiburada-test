package com.vrgsl.step.pages.search;

import org.openqa.selenium.WebElement;

import static com.vrgsl.step.helper.ElementHelper.getBy;

// One card in the product list; its locators are searched only inside the card
public class ProductCard {

    private final WebElement root;

    ProductCard(WebElement root) {
        this.root = root;
    }

    public boolean hasReviews() {
        return !root.findElements(getBy("cardReviewCount")).isEmpty();
    }

    public int getReviewCount() {
        return Integer.parseInt(root.findElement(getBy("cardReviewCount")).getText().replaceAll("[^0-9]", ""));
    }

    public void open() {
        root.findElement(getBy("cardLink")).click();
    }
}
