package testBase;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ReadProperties;

public class BrowserFactory {
	
	public WebDriver launch_browser(String browserName) throws MalformedURLException {
		
		RemoteWebDriver driver=null;
		
		String grid_status=ReadProperties.get_property_value_by_key("run_on_grid");
	
	if(grid_status.equals("OFF")) 
	{
		if(browserName.equalsIgnoreCase("chrome")) {
			
			ChromeOptions opt = new ChromeOptions();
			//opt.addArguments("--incognito");
			driver = (RemoteWebDriver) WebDriverManager.chromedriver().capabilities(opt).create();
		}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
			
			FirefoxOptions fopt = new FirefoxOptions();
			//fopt.addArguments("-private");
			driver = (RemoteWebDriver) WebDriverManager.firefoxdriver().capabilities(fopt).create();
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			
			EdgeOptions eopt = new EdgeOptions();
			//eopt.addArguments("-private");
			driver = (RemoteWebDriver) WebDriverManager.edgedriver().create();
		}
		
		else {
			System.out.println("Invalid browser name, Please check browser name in properties file");
		}
		
		
		}
	
	else if(grid_status.equals("ON")) {
		
		
         if(browserName.equalsIgnoreCase("chrome")) {
			
        	 DesiredCapabilities dc = new DesiredCapabilities();
     		dc.setBrowserName("chrome");
     		URL url = new URL(ReadProperties.get_property_value_by_key("grid_url"));
     		 driver = new RemoteWebDriver(url, dc);
		}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
			

			DesiredCapabilities dc = new DesiredCapabilities();
    		dc.setBrowserName("firefox");
    		URL url = new URL(ReadProperties.get_property_value_by_key("grid_url"));
    		 driver = new RemoteWebDriver(url, dc);
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			

       	 	DesiredCapabilities dc = new DesiredCapabilities();
    		dc.setBrowserName("MicrosoftEdge");
    		URL url = new URL(ReadProperties.get_property_value_by_key("grid_url"));
    		 driver = new RemoteWebDriver(url, dc);
		}
		
		else {
			System.out.println("Invalid browser name, Please check browser name in properties file");
		}
		
		
		}
		
	return driver;
		
		}
	
	}
	
