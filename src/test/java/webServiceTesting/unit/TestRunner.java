package webServiceTesting.unit;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = {"webServiceTesting", "browserTesting"}
)

public class TestRunner {
    // Exists only for grouping features in run
}