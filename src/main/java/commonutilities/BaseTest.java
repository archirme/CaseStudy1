package commonutilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTest {

	protected WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest test;
	private Properties prop;
	
	//Initialize the driver for the browser (Chrome, Firefox, Edge) specified
	public WebDriver initializeDriver(String browserName, String driverPath) throws IOException {
		
		switch (browserName)
		{
		case "chrome":
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
			
		case "firefox":
			driver = new FirefoxDriver();
			System.setProperty("webdriver.gecko.driver", driverPath);
			driver.manage().window().maximize();
			break;

		case "edge":
			driver = new EdgeDriver();
			System.setProperty("webdriver.edge.driver", driverPath);
			driver.manage().window().maximize();
			break;
		default:
			System.out.println("browser : " + browserName
					+ " is invalid, Launching chrome as browser of choice..");
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			
		}
		
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		return driver;

	}
	
	//Close the Browser after the tests
	public void closeAll()
	{
//		driver.close();
		driver.quit();
		System.out.println("End of the Test");
		
	}
	
	
	//Launch the URL provided in the Browser
	public void launchPage(String URL, String title)
	{
		driver.get(URL);
		Assert.assertTrue(driver.getTitle().equals(title));
	}
	
	
	//Return the state of the driver
	public WebDriver getDriver() {
	        return driver;
	    }
	
	
	//Get the input details (Browser to run from, URL, Other input details) from the properties file
	public Properties getInputDetails(String filePath) throws IOException
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		fis.close();
		return prop;
	}
	

}
