package testBase;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {

	private ExtentFactory() {
	
	}
	
	private static ExtentFactory instance = new ExtentFactory();
	
	public static ExtentFactory get_instance() {
		return instance;
	}
	
	
	ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();
	
	public ExtentTest get_extent() {
		
		return extent.get();
	}
	
	public void set_extent(ExtentTest extentParam) {
		extent.set(extentParam);
	}
	
	public void remove_extent_objet() {
		extent.remove();
	}
}
