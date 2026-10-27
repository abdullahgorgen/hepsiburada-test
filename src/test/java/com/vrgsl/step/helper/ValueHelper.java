package com.vrgsl.step.helper;

import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ValueHelper {

    private static final Map<String, String> VALUES = new HashMap<>();

    static {
        List<Map<String, String>> files = ResourceHelper.readJsonFiles("value-infos",
                new TypeToken<Map<String, String>>() {}.getType());
        files.forEach(values -> values.forEach(ValueHelper::register));
    }

    private ValueHelper() {
    }

    private static void register(String key, String value) {
        if (VALUES.putIfAbsent(key, value) != null) {
            throw new IllegalStateException("Duplicate value key: " + key);
        }
    }

    public static String getValue(String key) {
        String value = VALUES.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Value not found: " + key);
        }
        return value;
    }
}
