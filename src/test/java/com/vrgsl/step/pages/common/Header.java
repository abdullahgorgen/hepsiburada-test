package com.vrgsl.step.pages.common;

import com.vrgsl.step.pages.BasePage;
import com.vrgsl.step.pages.cart.CartPage;

// Shared by every page, so steps use it regardless of which page is open
public class Header extends BasePage {

    public int getCartItemCount() {
        return Integer.parseInt(getText("cartItemCount"));
    }

    public String getNotificationText() {
        return getText("addToCartNotification");
    }

    public CartPage goToCart() {
        click("shoppingCartButton");
        return new CartPage();
    }
}
