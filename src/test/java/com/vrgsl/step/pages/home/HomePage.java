package com.vrgsl.step.pages.home;

import com.vrgsl.step.helper.ConfigHelper;
import com.vrgsl.step.pages.BasePage;
import com.vrgsl.step.pages.search.SearchResultsPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;

import static com.vrgsl.step.helper.ElementHelper.getBy;

public class HomePage extends BasePage {

    public void open() {
        driver.get(ConfigHelper.get("baseUrl"));
    }

    public void acceptCookies() {
        SearchContext banner = wait.until(d -> d.findElement(getBy("cookieBanner")).getShadowRoot());
        wait.until(d -> {
            banner.findElement(getBy("cookieAcceptButton")).click();
            return true;
        });
    }

    public SearchResultsPage search(String keyword) {
        click("searchBox");
        // The box re-renders into a new input; text typed before it gets focus is lost
        wait.until(d -> d.findElement(getBy("searchInput")).equals(d.switchTo().activeElement()));
        type("searchInput", keyword, Keys.ENTER);
        return new SearchResultsPage();
    }
}
