package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testBase.DriverFactory;
import testBase.TestBase;

public class ToolsPageObjects extends TestBase {

	By menu_education_load_standard_repayment = By.xpath("//h3[text()='EDUCATION LOAN CALCULATOR (STANDARD REPAYMENT)']");
	
	
	public EduLoanStdRepayPageObjects click_on_education_load_standard_repay_menu() {
		
		
		WebElement element = DriverFactory.get_instance().get_driver().findElement(menu_education_load_standard_repayment);	
		do_moveToElement(element,"Education Loan Standard Repayment Calculator");
		do_mouseClick(element, "Education Loand Standard Repayment Calculator");
		return new EduLoanStdRepayPageObjects();
	}
}
