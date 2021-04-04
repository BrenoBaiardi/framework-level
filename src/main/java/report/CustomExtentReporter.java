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
                    test = extentReports.createTest(getFeatureTitle(scenario)).pass("PASSED").createNode(getScenarioTitle(scenario));
                    break;
                case FAILED:
                    test = extentReports.createTest(getFeatureTitle(scenario)).fail("FAILED").createNode(getScenarioTitle(scenario));
                    break;
                default:
                    test = extentReports.createTest(getFeatureTitle(scenario)).skip("SKIPPED").createNode(getScenarioTitle(scenario));
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
