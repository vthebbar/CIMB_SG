package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetryAnalyzer implements IRetryAnalyzer {
	
	int counter=1;
	int maxRetryLimit=2;
	@Override
	public boolean retry(ITestResult result) {
		
		if(counter<maxRetryLimit) {
			counter++;
			return true;
		}
		return false;
	}

}
