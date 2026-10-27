package com.vrgsl.step.helper;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public final class ResourceHelper {

    private static final Gson GSON = new Gson();

    private ResourceHelper() {
    }

    // Parses every .json file under the given classpath directory, including subdirectories
    public static <T> List<T> readJsonFiles(String directory, Type type) {
        URL url = ResourceHelper.class.getClassLoader().getResource(directory);
        if (url == null) {
            throw new IllegalStateException("Resource directory not found: " + directory);
        }
        try (Stream<Path> paths = Files.walk(Path.of(url.toURI()))) {
            return paths.filter(path -> path.toString().endsWith(".json"))
                    .map(path -> ResourceHelper.<T>readJson(path, type))
                    .toList();
        } catch (IOException | URISyntaxException e) {
            throw new IllegalStateException("Cannot read resource directory: " + directory, e);
        }
    }

    private static <T> T readJson(Path path, Type type) {
        try (Reader reader = Files.newBufferedReader(path)) {
            return GSON.fromJson(reader, type);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
