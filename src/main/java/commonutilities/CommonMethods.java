package commonutilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class CommonMethods {

	protected WebDriver driver=null;
	private Properties prop=null;
	FileInputStream fs=null;
	protected static final Logger Log = LogManager.getLogger(CommonMethods.class.getName());

	
	//Assign the driver state received
	public CommonMethods(WebDriver driver) {
		this.driver = driver;
	}

	//Fetch the value of the property file by key
	public String getProp(String key) {
		return prop.getProperty(key);
	}
	
	//Return WebElement for the Xpath provided
	public WebElement getElementByXpath(String key) 
	{
//		System.out.println(getProp(key));
		return driver.findElement(By.xpath(getProp(key)));
	}
	
	//Return the WebElement List for the XPath Provided
	public List<WebElement> getElementsByXpath(String key) 
	{
		return driver.findElements(By.xpath(getProp(key)));
	}
	
	//Click on the option provided by the key from the Drop-down (non-select) or the list.
	public void selectFromWebElementList(List<WebElement> Options, String key) 
	{
		for(WebElement Option: Options)
	    {
	    	if (Option.getText().contains(key))
	    	{
	    		Option.click();
	    		System.out.println(key + " Selected");
	    		break;
	    	}
	    }	    
	}
	
	//Click on the option provided by the key from the Drop-down(Select) or the list.
	public void selectFromDropdown(WebElement ele, String key)
	{
		Select sel = new Select(ele);
		List<WebElement> selOptions = sel.getOptions();
		System.out.println("Number of Options count is " + selOptions.size());		
		for(WebElement Option: selOptions)
	    {
			String Optionname=Option.getText();
	    	if (Optionname.equalsIgnoreCase(key))
	    	{
	    		sel.selectByVisibleText(key);
	    		System.out.println(key + " Selected");
	    		break;
	    	}
	    }	    
	}
	
	
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;

	}
	
	public void readWebElementLocators(String filePath) throws IOException
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		fis.close();
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
	
	// Launch the URL Passed in the Browser
	public void launchPage(String URL)
	{
		driver.get(URL);
	}

	//Click Action performed on the WebElement
	public void clickElement(WebElement ele)
	{
		ele.click();
	}
	
	// Enter or Pass the required text in the WebElement
	public void enterText(WebElement ele, String text)
	{
		ele.sendKeys(text);
	}

	//Bring focus on the WebElement 
	public void focusOnElement(WebElement ele)
	{
		((RemoteWebDriver) driver).executeScript("arguments[0].scrollIntoView(true);", ele);

	}
	
	//Get the text of the WebElement passed
	public String showtext(WebElement ele) {
		return ele.getText();
	}
	
	//Return the integer representation of the Month ( Like March is 03)
	public  int monthInNum(String monthS) {
		// TODO Auto-generated method stub
		switch(monthS)
    	{
	    	case "January":
	    		return 1;
	    	case "February":
	    		return 2;
	    	case "March":
	    		return 3;
	    	case "April":
	    		return 4;
	    	case "May":
	    		return 5;
	    	case "June":
	    		return 6;
	    	case "July":
	    		return 7;
	    	case "August":
	    		return 8;
	    	case "September":
	    		return 9;
	    	case "October":
	    		return 10;
	    	case "November":
	    		return 11;
	    	case "December":
	    		return 12;
	    	default:
	    		return 0;   		
	      	}
	}
	
	//Wait for the Element to be available until the number of seconds provided as input are passed
	public void waitTillElementVisible(WebElement webEle, int seconds)
	{
		 WebDriverWait wait = new WebDriverWait(driver,seconds);
		    wait.until(ExpectedConditions.visibilityOf(webEle));
	}

}
