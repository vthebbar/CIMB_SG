package testCases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.EduLoanStdRepayPageObjects;
import pageObjects.HomePageObjects;
import pageObjects.ToolsPageObjects;
import testBase.ExtentFactory;
import testBase.TestBase;
import utilities.ExcelOperations;

public class TestCase_Scenario_0002 extends TestBase {

	HomePageObjects hp = new HomePageObjects();
	ExcelOperations excel = new ExcelOperations("EduLoanCalc");
	
	@Test(dataProvider="loanData")
	public void test_education_loan_standard_repayment_calculator_scn002(Object obj1) {
		
			HashMap<String,String> testData = (HashMap<String, String>) obj1;
		
			ExtentFactory.get_instance().get_extent().info("Test data used is:" + testData);
			
		/*  Environment: https://www.cimb.com.sg/en/personal/home.html 
			Scenario 2:
			
			Given I’m on CIMB page
			And I navigate to Tools page from menu
			When I access to Property  Education Loan Repayment Calculator(Standard Repayment) from menu  [Property Loan option not found, hence selected Education Loan]
			And I have inputted all necessary values
			Then I will be able to see the Effective Interest Rate, Total Interest Payable and Total Amount Payable
			And I will be able to see the loan repayment table with total loan tenure that I’ve entered 
			And different interest rates for different year based on my input
		 */
		
		
		logger.info("****Started test for test_education_loan_standard_repayment_calculator_scn002****");
		hp.close_popUp_alert();	
		hp.click_on_menu_icon();
		ToolsPageObjects tp = hp.click_on_tools();
		
		EduLoanStdRepayPageObjects eduLoanStdobj = tp.click_on_education_load_standard_repay_menu();
		
		eduLoanStdobj.switch_to_calculator_page("Education Loan Calculator - Standard | Tools | CIMB SG");
		
		eduLoanStdobj.input_values_for_calculation(testData.get("LoanAmount"),testData.get("Tenure"));
		eduLoanStdobj.scroll_down_till_interest_rate();
		
		
		boolean interestRate_isDisplayed = eduLoanStdobj.verify_interest_rate_is_displayed();
		boolean total_interest_isDisplayed= eduLoanStdobj.verify_total_interest_payable_is_displayed();
		boolean total_amount_payable_isDisplayed = eduLoanStdobj.verify_total_amount_payable_is_displayed();
		
		boolean number_of_rows_is_correct = eduLoanStdobj.verify_number_of_rows_in_table();
		boolean number_of_columns_is_correct = eduLoanStdobj.verify_number_of_header_columns_in_table();
		
		
		boolean interest_rate_different_for_each_year=eduLoanStdobj.verify_interest_rates_for_each_tenure_are_different();
		
		Assert.assertEquals(true, interestRate_isDisplayed,"Effective interest rate is not dislayed");
		Assert.assertEquals(true, total_interest_isDisplayed,"Total interest payable is not displayed");
		Assert.assertEquals(true, total_amount_payable_isDisplayed,"Total amount payable is not displayed");
		Assert.assertEquals(true, number_of_rows_is_correct,"Total number of rows in table are incorrect");
		Assert.assertEquals(true, number_of_columns_is_correct,"Total number of columns in table are incorrect");
		Assert.assertEquals(true, interest_rate_different_for_each_year,"Interest rates are not different for each year");

		logger.info("****Completed test for test_education_loan_standard_repayment_calculator_scn002****");


	}
	
	@DataProvider(name="loanData")
	public Object[][] get_loan_test_data() throws Exception{
		
		Object[][] obj = new Object[excel.getRowCount()][1];
		for(int i=1;i<= excel.getRowCount();i++) {
			HashMap<String, String> testData = excel.getTestDataInMap(i);
			obj[i-1][0]=testData;
		}
		return obj;
	}
}
