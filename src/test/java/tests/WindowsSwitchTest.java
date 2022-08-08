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
import pageObjects.WindowsPage;

@Listeners(listener.ListenerSupport.class)

public class WindowsSwitchTest extends BaseTest
{
	private WebDriver driver;
	private String inputPropertiesFilePath = "src\\test\\java\\resources\\windowsTest.properties";
	Properties prop ;
	WindowsPage winPg;


	@BeforeSuite
	public void startsuite() throws IOException
	{
		report= new ExtentReports("reports\\ExtentReports.html");
		System.out.println("Start of the Test Suite - Windows");
	}
	
	@BeforeClass
	public void starclass()
	{
		System.out.println("Start of the WindowsSwitchTest Class");
	}
	
	@BeforeMethod
	public void setup() throws IOException
	{
		test=report.startTest("Windows Switch Testing");
		System.out.println("Start of Test");
		prop = getInputDetails(inputPropertiesFilePath);
		driver=initializeDriver(prop.getProperty("browser"),prop.getProperty("chromeDriverPath"));
		super.driver=driver;
		winPg = new WindowsPage(driver);
	}

	@Test (priority = 0)
	public void navigateTest() throws InterruptedException
	{
		winPg.launchPage(prop.getProperty("windowsPageUrl"));
		Thread.sleep(2000);
		System.out.println("Get and Navigate Window Example ");
		winPg.performNavigateTest(prop.getProperty("navigatePageUrl"));
	}

	
	@Test (priority = 1)
	public void tabbedWindowsTest() throws InterruptedException
	{
		winPg.launchPage(prop.getProperty("windowsPageUrl"));
		System.out.println("Tabbed Window Example ");
		winPg.performTabbedWinodwsTest();
	}

	
	@Test (priority = 2)
	public void seperateWindowsTest() throws InterruptedException
	{
		winPg.launchPage(prop.getProperty("windowsPageUrl"));
		System.out.println("Seperate Window Example ");
		winPg.performSeperateWindowsTest();
	}

	
	@Test (priority = 3)
	public void multipleWindowsTest() throws InterruptedException
	{
		winPg.launchPage(prop.getProperty("windowsPageUrl"));
		System.out.println("Multiple Window Example ");
		winPg.performMultipleWindowsTest();

	}

	@AfterMethod
	public void cleanup()
	{
		System.out.println("End of Test - Windows");
		report.endTest(test);
		driver.quit();
	}
	
	@AfterClass
	public void afterclass()
	{
		System.out.println("End of the WindowsSwitchTest Class");
	}
	
	@AfterSuite
	public void endsuite()
	{
		report.flush();
		System.out.println("End of the Test Suite - Windows");

	}

}	

