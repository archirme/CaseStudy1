package tests;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import commonutilities.BaseTest;
import pageObjects.AlertPage;

@Listeners(listener.ListenerSupport.class)

public class AlertWindowTest extends BaseTest
{
	private WebDriver driver;
	private String inputPropertiesFilePath = "src\\test\\java\\resources\\alertTest.properties";
	Properties prop ;
	AlertPage alertO;


	@BeforeSuite
	public void startsuite() throws IOException
	{
		report= new ExtentReports("reports\\ExtentReports.html");
		System.out.println("Start of the Test Suite");
	}
	
	@BeforeClass
	public void starclass()
	{
//		test=report.startTest(" Tests");
	}
	
	@BeforeMethod
	public void setup() throws IOException
	{
		test=report.startTest("Alert Windows Testing");
		System.out.println("Start of Test");
		prop = getInputDetails(inputPropertiesFilePath);
		driver=initializeDriver(prop.getProperty("browser"),prop.getProperty("chromeDriverPath"));
		super.driver=driver;
		alertO = new AlertPage(driver);
		alertO.launchPage(prop.getProperty("alertPageUrl"));
	}
	
	@Test (priority = 1)
	public void alertWithOk() throws InterruptedException
	{
		alertO.performAlertWithOk();
	}

	
	@Test (priority = 2)
	public void alertWithOkNCancel() throws InterruptedException
	{
		alertO.performAlertWithOkNCancel();
	}

	
	@Test (priority = 3)
	public void alertWithTextBox() throws InterruptedException
	{
		System.out.println("Alert Text Name" + prop.getProperty("alertTextName"));
		alertO.performAlertWithTextbox(prop.getProperty("alertTextName"));

	}

	@AfterMethod
	public void cleanup()
	{
		System.out.println("End of Test");
		report.endTest(test);
		closeAll();
	}
	
	@AfterClass
	public void afterclass()
	{
//		report.endTest(test);
	}
	
	@AfterSuite
	public void endsuite()
	{
		report.flush();
		System.out.println("End of the Test Suite");

	}

}	

