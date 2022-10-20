package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePageObjects;
import testBase.DriverFactory;
import testBase.TestBase;

public class TestCase_SampleTest extends TestBase {

	HomePageObjects hp = new HomePageObjects();
	
	// Intentionally failed this test case to ensure screenshots are captured in extent report
	@Test
	public void test_verify_title() {
		
		
		String title = DriverFactory.get_instance().get_driver().getTitle();
		Assert.assertEquals(title, "HomePage");
	}
}
