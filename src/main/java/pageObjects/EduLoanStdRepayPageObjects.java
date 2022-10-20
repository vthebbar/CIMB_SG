package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import testBase.DriverFactory;
import testBase.TestBase;

public class EduLoanStdRepayPageObjects extends TestBase {

	By txt_loanAmount = By.xpath("//span[text()='S$']//following-sibling::div//input");
	By txt_loanTenure = By.xpath("//span[text()='Year(s)']//preceding-sibling::div//input");
	By txt_effectiveInteret_rate = By.xpath("//span[text()='Interest Rate']");
	By txt_totalInterestCharged = By.xpath("//span[text()='Total Interest Charged']");
	By txt_totalAmountPayable = By.xpath("//span[text()='Total Payment Amount']");
	By table_repayment_xpath = By.xpath("//table/tbody/tr");
	By table_column_xpath = By.xpath("//table/tbody/tr[1]/th");
	By table_last_row_first_column_xpath = By.xpath("//table/tbody/tr[last()]/td[1]");


	public void switch_to_calculator_page(String title) {
		do_switch_to_window(title);
	}	

	public void input_values_for_calculation(String loanAmount, String loanTenure) {


		WebElement loan_amount = DriverFactory.get_instance().get_driver().findElement(txt_loanAmount);
		WebElement loan_tenure = DriverFactory.get_instance().get_driver().findElement(txt_loanTenure);

		do_moveToElement(loan_amount,"Total Loan Amount");

		do_clear(loan_amount,"Loan Amount");
		do_sendKeys(loan_amount,loanAmount,"Loan Amount");

		do_clear(loan_tenure, "Loan Tenure");
		do_sendKeys(loan_amount,loanAmount,"Loan Amount");

	}

	public void scroll_down_till_interest_rate() {
		WebElement element = DriverFactory.get_instance().get_driver().findElement(txt_effectiveInteret_rate);
		do_moveToElement(element,"Interest Rate");
	}

	public boolean verify_interest_rate_is_displayed() {

		WebElement element = DriverFactory.get_instance().get_driver().findElement(txt_effectiveInteret_rate);
		return do_isElementDisplayed(element,"Interest Rate");
	}

	public boolean verify_total_interest_payable_is_displayed() {
		WebElement element = DriverFactory.get_instance().get_driver().findElement(txt_totalInterestCharged);
		return do_isElementDisplayed(element,"Total Interest Charged");
	}

	public boolean verify_total_amount_payable_is_displayed() {
		WebElement element = DriverFactory.get_instance().get_driver().findElement(txt_totalAmountPayable);
		return do_isElementDisplayed(element,"Total Payment Amount");
	}

	public boolean verify_number_of_rows_in_table() {
		List<WebElement> table = DriverFactory.get_instance().get_driver().findElements(table_repayment_xpath);
		int number_of_rows = table.size();
		if(number_of_rows==11) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean verify_number_of_header_columns_in_table() {
		List<WebElement> table = DriverFactory.get_instance().get_driver().findElements(table_column_xpath);
		int number_of_header_columns= table.size();
		if(number_of_header_columns==5) {
			return true;
		}
		else {
			return false;
		}
	}

	

	public boolean verify_interest_rates_for_each_tenure_are_different() {

		List<WebElement> table = DriverFactory.get_instance().get_driver().findElements(table_repayment_xpath);
		int number_of_rows= table.size();

		String xpath_before="//table/tbody/tr[" ;

		//for row 2 only
		String xpath_after_second_row="]/td[(count(//table/tbody/tr/th/b[text()='Effective Interest Rate³']//parent::th//preceding-sibling::th)+1)]";

		String xpath_after="]/td[(count(//table/tbody/tr/th/b[text()='Effective Interest Rate³']//parent::th//preceding-sibling::th)-1)]";
		String[] interest_rates = new String[number_of_rows-1];
		for(int i=2; i<=number_of_rows ; i++) {

			if(i==2) {
				interest_rates[i-2] =DriverFactory.get_instance().get_driver().findElement(By.xpath(xpath_before+i+xpath_after_second_row)).getText();
			}

			else {
				interest_rates[i-2] = DriverFactory.get_instance().get_driver().findElement(By.xpath(xpath_before+i+xpath_after)).getText();
			}
		}

		boolean flag=true;
		for(int x=0; x<interest_rates.length;x++) {
			for(int y=x+1;y<interest_rates.length;y++) {
				if(interest_rates[x].equals(interest_rates[y])) {
					flag=false;
				}
			}
		}

		return flag;
	}

}