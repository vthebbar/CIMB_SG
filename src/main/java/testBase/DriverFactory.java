package testBase;

import org.openqa.selenium.WebDriver;

//Thread safe parallel execution
public class DriverFactory {

	//Singleton and factory design
	private DriverFactory() {

	}
	
	private static DriverFactory instance = new DriverFactory();
	
	public static DriverFactory get_instance() {
		return instance;
	}
	
	// Factory design pattern
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public WebDriver get_driver() {
		return driver.get();
	}
	
	public void set_driver(WebDriver driverParam) {
		driver.set(driverParam);
	}
	
	public void close_browser() {
		driver.get().quit();
		driver.remove();
	}
}
