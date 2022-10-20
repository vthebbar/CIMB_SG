package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testBase.DriverFactory;
import testBase.ExtentFactory;
import testBase.ExtentSetup;

public class ListenersImplementation implements ITestListener {
	
	static ExtentReports report;
	   ExtentTest test;
	
	public void onTestStart(ITestResult result) {
				//before each test case
				test = report.createTest(result.getMethod().getMethodName());
				ExtentFactory.get_instance().set_extent(test);
	}

	public void onTestSuccess(ITestResult result) {
		ExtentFactory.get_instance().get_extent().log(Status.PASS, "Test Case: "+result.getMethod().getMethodName()+ " is Passed.");
		ExtentFactory.get_instance().remove_extent_objet();
	}

	public void onTestFailure(ITestResult result) {
		ExtentFactory.get_instance().get_extent().log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+ " is Failed.");
		ExtentFactory.get_instance().get_extent().log(Status.FAIL, result.getThrowable());
		
		//add screenshot for failed test.
				File src = ((TakesScreenshot)DriverFactory.get_instance().get_driver()).getScreenshotAs(OutputType.FILE);
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
				Date date = new Date();
				String actualDate = format.format(date);
				
				String screenshotPath = System.getProperty("user.dir")+
						"/Reports/Screenshots/"+actualDate+".jpeg";
				File dest = new File(screenshotPath);
				
				try {
					FileUtils.copyFile(src, dest);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					ExtentFactory.get_instance().get_extent().addScreenCaptureFromPath(screenshotPath, "Test case failure screenshot");
					ExtentFactory.get_instance().remove_extent_objet();

				} catch (IOException e) {
					e.printStackTrace();
				}

	}

	public void onTestSkipped(ITestResult result) {
		ExtentFactory.get_instance().get_extent().log(Status.SKIP, "Test Case: "+result.getMethod().getMethodName()+ " is skipped.");
		ExtentFactory.get_instance().remove_extent_objet();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
			}

	public void onStart(ITestContext context) {
		try {
			 report = ExtentSetup.setup_extent_report();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context) {
			//remove extent object
				report.flush();
	}

	
	
	
	
}
