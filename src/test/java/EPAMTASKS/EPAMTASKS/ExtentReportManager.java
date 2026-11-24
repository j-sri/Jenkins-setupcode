package EPAMTASKS.EPAMTASKS;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	 private static ExtentReports extent;

	    public static ExtentReports getReportInstance() {

	        if (extent == null) {

	            ExtentSparkReporter spark =
	                    new ExtentSparkReporter("test-output/ExtentReport.html");

	            spark.config().setDocumentTitle("Automation Test Report");
	            spark.config().setReportName("Selenium Automation Execution");

	            extent = new ExtentReports();
	            extent.attachReporter(spark);

	            extent.setSystemInfo("Tester", "Srividya");
	            extent.setSystemInfo("Environment", "QA");
	            extent.setSystemInfo("Framework", "Selenium + TestNG");
	            extent.setSystemInfo("Browser", "Chrome");
	        }

	        return extent;
	    }
}
