package pl.traineeship.autoqa.lesson15.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/feature/login.feature"}, glue = {
        "pl.traineeship.autoqa.lesson15.definition"}, plugin = {})
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
