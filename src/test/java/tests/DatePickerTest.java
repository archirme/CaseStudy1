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
import pageObjects.DatePickerPage;

@Listeners(listener.ListenerSupport.class)

public class DatePickerTest extends BaseTest
{
	private WebDriver driver;
	private String inputPropertiesFilePath = "src\\test\\java\\resources\\datePickerTest.properties";
	Properties prop ;
	DatePickerPage dateP;

	@BeforeSuite
	public void startsuite() throws IOException
	{
		report= new ExtentReports("reports\\ExtentReports.html");
		System.out.println("Start of the Test Suite");
	}
	
	@BeforeClass
	public void starclass()
	{
		test=report.startTest("Date Picker Test Started");
	}
	
	@BeforeMethod
	public void setup() throws IOException
	{
		test=report.startTest("Date Picker Testing");
		System.out.println("Start of Test");
		prop = getInputDetails(inputPropertiesFilePath);
		driver=initializeDriver(prop.getProperty("browser"),prop.getProperty("chromeDriverPath"));
		super.driver=driver;
		dateP=new DatePickerPage(driver);
		dateP.launchPage(prop.getProperty("registerPageUrl"));
	}
	
	@Test (priority = 1)
	public void pickDatefromCalender() 
	{
		
		dateP.navigateToDatePicker();
		dateP.dismissAds();
		dateP.selectRequiredDate(prop.getProperty("dateReq"));
		

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

