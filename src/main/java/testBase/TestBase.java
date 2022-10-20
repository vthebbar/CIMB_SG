package testBase;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import freemarker.log.Logger;
import utilities.ActionMethods;
import utilities.ReadProperties;

public class TestBase extends ActionMethods {

	BrowserFactory bf=new BrowserFactory();
	public static Logger logger;

	@BeforeMethod
	public void launch_application() throws MalformedURLException {

		String browser = ReadProperties.get_property_value_by_key("browser");
		String url=ReadProperties.get_property_value_by_key("url");

		//set
		DriverFactory.get_instance().set_driver(bf.launch_browser(browser));	

		DriverFactory.get_instance().get_driver().manage().window().maximize();
		DriverFactory.get_instance().get_driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		DriverFactory.get_instance().get_driver().get(url);


		// Loggin object
		logger = Logger.getLogger("MyLogger");
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/main/resources/log4j.properties");
	}



	@AfterMethod
	public void tear_down() {
		DriverFactory.get_instance().close_browser();
	}



	// Automatic docker infrastructure setup and shutdown


	@SuppressWarnings("deprecation")
	@BeforeTest
	public void start_docker_grid() throws IOException, InterruptedException {

		if(ReadProperties.get_property_value_by_key("run_on_grid").equalsIgnoreCase("ON")) 
		{
			System.out.println("Setting Up: Dockerised Selenium Grid Infrastructure");
			Runtime.getRuntime().exec("cmd /c start start_docker_grid.bat");
			Thread.sleep(20000);
		}


	}

	@SuppressWarnings("deprecation")
	@AfterTest
	public void stop_docker_grid() throws IOException, InterruptedException {

		if(ReadProperties.get_property_value_by_key("run_on_grid").equals("ON")) {
			Thread.sleep(4000);
			System.out.println("Shutting Down: Dockerised Selenium Grid Infrastructure");

			Runtime.getRuntime().exec("cmd /c start stop_docker_grid.bat");
			Thread.sleep(8000);
			// close command prompt
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		}
	}

}
