package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerSupport implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getClass()  +"Started Test");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getClass()  +"Finished Test Successfully");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(result.getClass()  +"Failed Test");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getClass()  +"Skipped Test");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(result.SUCCESS_PERCENTAGE_FAILURE  +"Success and Failed Percentage");
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println(context.getStartDate()  +" Started");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println(context.getStartDate()  +" Finished");
		
	}

}
