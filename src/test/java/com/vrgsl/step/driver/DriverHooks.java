package com.vrgsl.step.driver;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;

public class DriverHooks {

    @BeforeScenario
    public void startDriver() {
        DriverManager.createDriver();
    }

    @AfterScenario
    public void quitDriver() {
        DriverManager.quitDriver();
    }
}
