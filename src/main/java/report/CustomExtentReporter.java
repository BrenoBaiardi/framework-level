package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import cucumber.api.Scenario;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import static gherkin.formatter.model.Result.*;

public class CustomExtentReporter {

    private ExtentSparkReporter sparkReporter;
    private ExtentReports extentReports;
    private ExtentTest test;

    /**
     * Customized ExtentReport class.<br>
     * After the runs, the report is located in the given path in <i>reportLocation</i>
     *
     * @param reportLocation path to save the folder
     */
    public CustomExtentReporter(Path reportLocation) {
        reportLocation = Paths.get(reportLocation + getCurrentDate());
        sparkReporter = new ExtentSparkReporter(reportLocation.toFile());
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    /**
     * @returns current date String with format YYYY-MM-dd HH:mm:ss
     */
    private String getCurrentDate() {
        DateTime jodaTime = new DateTime();
        DateTimeFormatter formatter = DateTimeFormat.forPattern(" YYYY-MM-dd-HH-mm-ss");
        return formatter.print(jodaTime);
    }

    /**
     * Creates test in ExtentReport
     *
     * @param scenario the current running Scenario
     * @return ExtentTest - to allow insertions of more information to each test
     */
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

    /**
     * Commits the current test results to ExtentReport
     */
    public void writeToReport() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    /**
     * returns the title of the given Scenario
     *
     * @param scenario the current running Scenario
     */
    public String getScenarioTitle(Scenario scenario) {
        return scenario.getName();
    }

    /**
     * returns the title of the Feature given Scenario
     *
     * @param scenario the current running Scenario
     */
    public String getFeatureTitle(Scenario scenario) {
        return Arrays.asList(scenario.getId().split("\\s*;\\s*")).get(0);
    }
}
