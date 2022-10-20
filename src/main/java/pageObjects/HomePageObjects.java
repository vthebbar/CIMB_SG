package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import testBase.DriverFactory;
import testBase.TestBase;

public class HomePageObjects extends TestBase {
	
	
	//required page elements
	By popup_alertBox = By.xpath("//*[local-name()='svg' and @width='18' and @height='18']");
	By menu_entry = By.xpath("//*[local-name()='svg' and @class='stroke-current']");
	By link_tools = By.linkText("Tools");
	
	
	//methods
	
	public void close_popUp_alert() {
		WebElement element = DriverFactory.get_instance().get_driver().findElement(popup_alertBox);
		do_wait(popup_alertBox);
		if(do_isElementDisplayed(element, "HomePagePopUpAlert")) {
			do_mouseClick(element,"HomePage PopUp Alert");

			//JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.get_instance().get_driver();
			//executor.executeScript("arguments[0].click();", element);
			
			
		}
	}
	
	
	
	public void click_on_menu_icon() {
		WebElement element = DriverFactory.get_instance().get_driver().findElement(menu_entry);
		do_mouseClick(element,"Menu Icon");

	}
	
	public ToolsPageObjects click_on_tools() {
		WebElement element = DriverFactory.get_instance().get_driver().findElement(link_tools);
		do_mouseClick(element,"Tools");
		
		return new ToolsPageObjects();
	}
	
	
}
