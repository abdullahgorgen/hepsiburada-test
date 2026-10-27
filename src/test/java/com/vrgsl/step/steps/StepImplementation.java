package com.vrgsl.step.steps;

import com.thoughtworks.gauge.Step;
import com.vrgsl.step.pages.cart.CartPage;
import com.vrgsl.step.pages.home.HomePage;
import com.vrgsl.step.pages.search.SearchResultsPage;

import java.math.BigDecimal;

import static com.vrgsl.step.helper.ValueHelper.getValue;
import static org.assertj.core.api.Assertions.assertThat;

public class StepImplementation {

    private final HomePage homePage = new HomePage();
    private SearchResultsPage searchResultsPage;
    private CartPage cartPage;
    private String notedProductName;
    private String notedProductPrice;

    @Step("Open Hepsiburada home page")
    public void openHomePage() {
        homePage.open();
        homePage.acceptCookies();
    }

    @Step("Search for product <keywordKey>")
    public void search(String keywordKey) {
        searchResultsPage = homePage.search(getValue(keywordKey));
    }

    @Step("Verify search results are displayed")
    public void verifySearchResultsDisplayed() {
        assertThat(searchResultsPage.getProductCount()).as("product count").isPositive();
    }

    @Step("Filter by seller <sellerKey>")
    public void filterBySeller(String sellerKey) {
        searchResultsPage.filterBySeller(getValue(sellerKey));
    }

    @Step("Verify seller filter <sellerKey> is applied")
    public void verifySellerFilterApplied(String sellerKey) {
        assertThat(searchResultsPage.getAppliedFilters()).as("applied filters").contains(getValue(sellerKey));
    }

    @Step("Sort products by <sortKey>")
    public void sortBy(String sortKey) {
        searchResultsPage.sortBy(getValue(sortKey));
    }

    @Step("Verify sort <sortKey> is selected and seller filter <sellerKey> is kept")
    public void verifySortSelectedAndFilterKept(String sortKey, String sellerKey) {
        assertThat(searchResultsPage.getSelectedSort()).as("selected sort").isEqualTo(getValue(sortKey));
        assertThat(searchResultsPage.getAppliedFilters()).as("applied filters").contains(getValue(sellerKey));
    }

    @Step("Note the name and price of the first product")
    public void noteFirstProduct() {
        notedProductName = searchResultsPage.getFirstProductTitle();
        notedProductPrice = searchResultsPage.getFirstProductPrice();
    }

    @Step("Add the first product to cart")
    public void addFirstProductToCart() {
        searchResultsPage.addFirstProductToCart();
    }

    @Step("Verify notification <messageKey> is displayed")
    public void verifyNotification(String messageKey) {
        assertThat(searchResultsPage.getNotificationText()).as("notification").isEqualTo(getValue(messageKey));
    }

    @Step("Verify cart item count is <count>")
    public void verifyCartItemCount(int count) {
        assertThat(searchResultsPage.getCartItemCount()).as("cart counter").isEqualTo(count);
    }

    @Step("Navigate to shopping cart page")
    public void navigateToCart() {
        cartPage = searchResultsPage.goToCart();
    }

    @Step("Verify cart contains <count> item")
    public void verifyCartItems(int count) {
        assertThat(cartPage.getItemCount()).as("cart rows").isEqualTo(count);
        assertThat(cartPage.getProductQuantity()).as("product quantity").isEqualTo(count);
    }

    @Step("Verify cart product matches the noted product and seller is <sellerKey>")
    public void verifyCartProduct(String sellerKey) {
        assertThat(cartPage.getProductName()).as("product name").isEqualTo(notedProductName);
        assertThat(parsePrice(cartPage.getProductPrice())).as("product price")
                .isEqualByComparingTo(parsePrice(notedProductPrice));
        assertThat(cartPage.getSellerName()).as("seller").isEqualTo(getValue(sellerKey));
    }

    // The list shows "1.299 TL" while the cart shows "1.299,00 TL"
    private static BigDecimal parsePrice(String price) {
        return new BigDecimal(price.replaceAll("[^0-9,]", "").replace(',', '.'));
    }
}
