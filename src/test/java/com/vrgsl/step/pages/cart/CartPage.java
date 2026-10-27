package com.vrgsl.step.pages.cart;

import com.vrgsl.step.pages.BasePage;

public class CartPage extends BasePage {

    public int getItemCount() {
        return waitForAll("cartItems").size();
    }

    public String getProductName() {
        return getText("cartProductName");
    }

    public String getProductPrice() {
        return getText("cartProductPrice");
    }

    public int getProductQuantity() {
        return read("cartProductQuantity", element -> Integer.parseInt(element.getDomProperty("value")));
    }

    public String getSellerName() {
        return getText("cartSellerName");
    }
}
