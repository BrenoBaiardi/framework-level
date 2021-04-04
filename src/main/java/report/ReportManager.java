package report;

import java.nio.file.Paths;

/**
 * Manages the use of a {@link CustomExtentReporter}.<br>
 *     It is necessary to manage the Report outside of the steps class,
 *     or else it will be instantiated for every test and overwrite the ExtentReport
 */
public class ReportManager {

    private static CustomExtentReporter reporter = null;

    /**
     * Returns a new instance of a CustomExtentReporter if it doesn't
     * already exists, otherwise, returns the same instance
     */
    public static CustomExtentReporter getReporterInstance(){
        if (null == reporter){
            reporter = new CustomExtentReporter(Paths.get("target","output"));
        }
        return reporter;
    }

}
