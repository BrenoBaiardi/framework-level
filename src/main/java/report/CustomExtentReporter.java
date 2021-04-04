package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import cucumber.api.Scenario;

import java.nio.file.Path;
import java.util.Arrays;

import static gherkin.formatter.model.Result.*;

public class CustomExtentReporter {

    private ExtentSparkReporter sparkReporter;
    private ExtentReports extentReports;
    private ExtentTest test;

    public CustomExtentReporter(Path reportLocation) {
        sparkReporter = new ExtentSparkReporter(reportLocation.toFile());
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }


    public ExtentTest createTest(Scenario scenario) {
        if (scenario != null) {
            switch (scenario.getStatus()) {
                case PASSED:
                    test = extentReports.createTest(getScenarioTitle(scenario)).pass("PASSED");
                    break;
                case FAILED:
                    test = extentReports.createTest(getScenarioTitle(scenario)).fail("FAILED");
                    break;
                default:
                    test = extentReports.createTest(getScenarioTitle(scenario)).skip("SKIPPED");
                    break;
            }
        }
        return test;
    }

    public void writeToReport() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    public String getScenarioTitle(Scenario scenario) {
        return scenario.getName();
    }

    public String getFeatureTitle(Scenario scenario) {
        return Arrays.asList(scenario.getId().split("\\s*;\\s*")).get(0);
    }


}
