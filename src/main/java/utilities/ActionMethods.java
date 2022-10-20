package utilities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import testBase.DriverFactory;
import testBase.ExtentFactory;

// switch to a window based on title
public class ActionMethods {

	// Send Keys
	
	public void do_sendKeys(WebElement element, String value, String fieldName) {
		try {
			
			element.sendKeys(value);
		ExtentFactory.get_instance().get_extent().log(Status.PASS, "Value key in success for field: " +fieldName);
		}
		catch(Exception e) {
			ExtentFactory.get_instance().get_extent().log(Status.FAIL, "Value key in Failed: "+fieldName+" Exception:"+e);
		}
	}
	
	// mouse left click
	
	public void do_mouseClick(WebElement element, String fieldName) {
		try {
		element.click();
		ExtentFactory.get_instance().get_extent().log(Status.PASS, "Click success for field: " +fieldName);

		}
		catch(Exception e) {
			ExtentFactory.get_instance().get_extent().log(Status.FAIL, "Click for Failed for field: "+fieldName+" Exception:"+e);

		}
	}
	
	
	//clear data from field
		public void do_clear(WebElement element,String fieldName) {
			try {
				element.clear();
				Thread.sleep(250);
				ExtentFactory.get_instance().get_extent().log(Status.PASS, "Data clear successful for field:"+ fieldName);
			} catch (Exception e) {
				ExtentFactory.get_instance().get_extent().log(Status.FAIL, "Data clear failed for field: " +fieldName +" Exception: "+e);

			} 
		}
	
	
		//custom mouseHover 
		public void do_moveToElement(WebElement element,String fieldName){
			try{
				JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.get_instance().get_driver();
				executor.executeScript("arguments[0].scrollIntoView(true);", element);
				Actions actions = new Actions(DriverFactory.get_instance().get_driver());		
				actions.moveToElement(element).build().perform();
				ExtentFactory.get_instance().get_extent().log(Status.PASS, "Mouseover successful for field:"+ fieldName);
				Thread.sleep(1000);
			}catch(Exception e){
				ExtentFactory.get_instance().get_extent().log(Status.FAIL, "Mouseover failed for field: " +fieldName +" Exception: "+e);

			}
		}
		
		
		//check if element is Displayed
		public boolean do_isElementDisplayed(WebElement element,String fieldName){
			boolean flag = false;
			try {
				flag = element.isDisplayed();
				ExtentFactory.get_instance().get_extent().log(Status.PASS, "Field present, field name:"+ fieldName);
				return flag;
			} catch (Exception e) {
				ExtentFactory.get_instance().get_extent().log(Status.FAIL, "Field not present: " +fieldName +" Exception: "+e);
				return flag;
			}
		}
	
		
		
		//Select drop down value value by visibleText
		public void do_electDropDownByVisibleText(WebElement element, String ddVisibleText, String fieldName) {
			try {
				Select s = new Select(element);
				s.selectByVisibleText(ddVisibleText);
				ExtentFactory.get_instance().get_extent().log(Status.PASS, "Dropdown Value selected successfully, field name: "+fieldName+"Selected value:"+ ddVisibleText);
			} catch (Exception e) {
				ExtentFactory.get_instance().get_extent().log(Status.FAIL, "Dropdown value selected failed, field name: " +fieldName +"  Exception: "+e);
			}
		}
		
		
		//Get text from webelement
		public String getText_custom(WebElement element, String fieldName) {
			String text = "";
			try {
				text = element.getText();
				ExtentFactory.get_instance().get_extent().log(Status.PASS, "Text retrieved , field name: "+fieldName+"Text:"+ text);
				return text;
			} catch (Exception e) {		
				ExtentFactory.get_instance().get_extent().log(Status.FAIL, "Text not retrieved, field name:"+fieldName+"Exception: "+ e);

			}
			return text;
		}

	
	///// switch window
	public void do_switch_to_window(String title) {
		//boolean flag=false;
		try {
		Set<String> handles = DriverFactory.get_instance().get_driver().getWindowHandles();
		for(String handle : handles ) {
			if(DriverFactory.get_instance().get_driver().switchTo().window(handle).getTitle().equals(title)) {
				DriverFactory.get_instance().get_driver().switchTo().window(handle);
				//flag=true;
				ExtentFactory.get_instance().get_extent().log(Status.PASS, "Window switch successful, window title is:"+ title);
				break;
			}
		}
		}
		catch(Exception e) {
			ExtentFactory.get_instance().get_extent().log(Status.FAIL, "Window switch failed, window title:" +title+"Exception: "+e);
		}
	}
	////
	
	//wait for an element
	
	public void do_wait(By element) {
		
		WebDriverWait wait = new WebDriverWait(DriverFactory.get_instance().get_driver(),Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
}
