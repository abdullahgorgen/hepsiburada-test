package com.vrgsl.step.helper;

import com.google.gson.reflect.TypeToken;
import com.vrgsl.step.model.ElementInfo;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ElementHelper {

    private static final Map<String, ElementInfo> ELEMENTS = new HashMap<>();

    static {
        List<List<ElementInfo>> files = ResourceHelper.readJsonFiles("element-infos",
                new TypeToken<List<ElementInfo>>() {}.getType());
        files.forEach(elements -> elements.forEach(ElementHelper::register));
    }

    private ElementHelper() {
    }

    private static void register(ElementInfo element) {
        if (ELEMENTS.putIfAbsent(element.key(), element) != null) {
            throw new IllegalStateException("Duplicate element key: " + element.key());
        }
    }

    public static By getBy(String key) {
        ElementInfo element = ELEMENTS.get(key);
        if (element == null) {
            throw new IllegalArgumentException("Element not found: " + key);
        }
        return switch (element.type()) {
            case "id" -> By.id(element.value());
            case "css" -> By.cssSelector(element.value());
            case "xpath" -> By.xpath(element.value());
            default -> throw new IllegalArgumentException("Unsupported locator type: " + element.type());
        };
    }
}
