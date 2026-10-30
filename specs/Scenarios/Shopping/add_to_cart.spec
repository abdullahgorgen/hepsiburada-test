# Product Search and Add to Cart Suite

## TC001 – Add the first product to cart after filtering by Hepsiburada seller and sorting by best sellers
Tags: shopping_cart, smoke, critical, regression

* Open home page and search for "searchKeywordBook"
* Filter by seller "sellerHepsiburada"
* Verify seller filter "sellerHepsiburada" is applied
* Sort products by "sortBestSellers"
* Verify sort "sortBestSellers" is selected and seller filter "sellerHepsiburada" is kept
* Note the name and price of the first product
* Add the first product to cart
* Verify notification "addToCartMessage" is displayed
* Verify cart item count is "1"
* Navigate to shopping cart page
* Verify cart contains "1" item
* Verify cart product matches the noted product
* Verify cart seller is "sellerHepsiburada"

## TC002 – Add the most reviewed book among the first 50 to cart from the product detail page
Tags: shopping_cart, product_detail, regression

* Open home page and search for "searchKeywordBook"
* Load at least "50" products by scrolling
* Open the product with the most reviews among the first "50"
* Note the name and price on the product detail page
* Add the product to cart from the product detail page
* Verify cart item count is "1"
* Navigate to shopping cart from the confirmation popup
* Verify cart contains "1" item
* Verify cart product matches the noted product
