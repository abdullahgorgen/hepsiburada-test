package com.vrgsl.step.driver;

import com.thoughtworks.gauge.screenshot.CustomScreenshotWriter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

// Gauge picks this up automatically; captures the browser instead of the whole desktop on failure
public class ScreenshotGrabber implements CustomScreenshotWriter {

    @Override
    public String takeScreenshot() {
        String fileName = "screenshot-" + UUID.randomUUID() + ".png";
        byte[] image = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        try {
            Files.write(Path.of(System.getenv("gauge_screenshots_dir"), fileName), image);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return fileName;
    }
}
