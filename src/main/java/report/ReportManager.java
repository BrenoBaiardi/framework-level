package report;

import java.nio.file.Paths;

public class ReportManager {

    private static CustomExtentReporter reporter = null;

    public static CustomExtentReporter getReporterInstance(){
        if (null == reporter){
            reporter = new CustomExtentReporter(Paths.get("target","output"));
        }
        return reporter;
    }

}
