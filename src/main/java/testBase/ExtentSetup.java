package testBase;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utilities.ReadProperties;

public class ExtentSetup {
	static ExtentReports extent;
	public static ExtentReports setup_extent_report() {
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date=new Date();
		String actual_date = format.format(date);
		
		String reportPath = System.getProperty("user.dir")+"/Reports/ExecutionReport_"+actual_date+".html";
		
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReport);
		
		sparkReport.config().setDocumentTitle("CIMB Project Test Report");
		sparkReport.config().setTheme(Theme.STANDARD);
		sparkReport.config().setReportName("CIMB Project Report");
		
		extent.setSystemInfo("Executed on Environment:", ReadProperties.get_property_value_by_key("url"));
		extent.setSystemInfo("Executed on Browser:", ReadProperties.get_property_value_by_key("browser"));
		extent.setSystemInfo("Executed OS", System.getProperty("os.name"));
		extent.setSystemInfo("System user name:", System.getProperty("user.name"));
		
		return extent;
	}
}
