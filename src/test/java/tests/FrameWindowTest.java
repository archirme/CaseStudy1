package tests;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import commonutilities.BaseTest;
import pageObjects.FramePage;

@Listeners(listener.ListenerSupport.class)

public class FrameWindowTest extends BaseTest
{
	private WebDriver driver;
	private String inputPropertiesFilePath = "src\\test\\java\\resources\\frameTest.properties";
	Properties prop ;
	FramePage frameO;

	@BeforeSuite
	public void startsuite() throws IOException
	{
		report= new ExtentReports("reports\\ExtentReports.html");
		System.out.println("Start of the Test Suite - Frame");
	}
	
	@BeforeClass
	public void starclass()
	{
		System.out.println("Start of the FramesWindowTest Class");
	}
	
	@BeforeMethod
	public void setup() throws IOException
	{
		test=report.startTest("Frame Windows Testing");
		System.out.println("Start of Test - Frame");
		prop = getInputDetails(inputPropertiesFilePath);
		driver=initializeDriver(prop.getProperty("browser"),prop.getProperty("chromeDriverPath"));
		super.driver=driver;
		frameO=new FramePage(driver);
		frameO.launchPage(prop.getProperty("registerPageUrl"));
	}
	
	@Test
	public void frameTest() throws InterruptedException 
	{
		frameO.frameTestOption1();
	}

	@AfterMethod
	public void cleanup()
	{
		System.out.println("End of Test - Frame");
		report.endTest(test);
		closeAll();
	}
	
	@AfterClass
	public void afterclass()
	{
		System.out.println("End of the FramesWindowTest Class");
	}
	
	@AfterSuite
	public void endsuite()
	{
		report.flush();
		System.out.println("End of the Test Suite - Frame");

	}

}	

