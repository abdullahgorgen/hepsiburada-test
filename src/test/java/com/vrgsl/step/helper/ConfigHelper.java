package com.vrgsl.step.helper;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Map;

public final class ConfigHelper {

    private static final Map<String, Object> CONFIG = new HashMap<>();

    static {
        load("browser-options.yaml");
        load("qa-web.yaml");
    }

    private ConfigHelper() {
    }

    private static void load(String fileName) {
        try (InputStream stream = ConfigHelper.class.getClassLoader().getResourceAsStream(fileName)) {
            if (stream == null) {
                throw new IllegalStateException("Config file not found: " + fileName);
            }
            Map<String, Object> config = new Yaml().load(stream);
            config.forEach(ConfigHelper::register);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static void register(String key, Object value) {
        if (CONFIG.putIfAbsent(key, value) != null) {
            throw new IllegalStateException("Duplicate config key: " + key);
        }
    }

    // Resolves nested keys with dot notation, e.g. "timeouts.explicitWait"
    @SuppressWarnings("unchecked")
    public static <T> T get(String path) {
        Object current = CONFIG;
        for (String key : path.split("\\.")) {
            current = current instanceof Map<?, ?> map ? map.get(key) : null;
        }
        if (current == null) {
            throw new IllegalArgumentException("Config key not found: " + path);
        }
        return (T) current;
    }
}
