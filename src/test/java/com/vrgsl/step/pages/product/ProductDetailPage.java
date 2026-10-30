package com.vrgsl.step.pages.product;

import com.vrgsl.step.pages.BasePage;
import com.vrgsl.step.pages.cart.CartPage;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.vrgsl.step.helper.ElementHelper.getBy;

public class ProductDetailPage extends BasePage {

    public String getTitle() {
        return getText("detailProductTitle");
    }

    public String getPrice() {
        return getText("detailProductPrice");
    }

    // The button is disabled while a request is in progress and ignores clicks
    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(getBy("detailAddToCartButton")));
        click("detailAddToCartButton");
        waitForVisible("popupGoToCartButton");
    }

    // The confirmation popup covers the header cart button
    public CartPage goToCartFromPopup() {
        click("popupGoToCartButton");
        return new CartPage();
    }
}
