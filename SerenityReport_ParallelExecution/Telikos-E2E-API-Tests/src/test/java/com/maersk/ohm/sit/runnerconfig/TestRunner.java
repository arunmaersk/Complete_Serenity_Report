package com.maersk.ohm.sit.runnerconfig;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty"},
        glue={"com.maersk.ohm.sit.stepdefinition"},
        publish = true, stepNotifications = true)

public class TestRunner{

}
