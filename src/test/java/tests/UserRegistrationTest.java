package tests;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonutilities.BaseTest;
import pageObjects.RegisterPage;

@Listeners(listener.ListenerSupport.class)
public class UserRegistrationTest extends BaseTest
{
	WebDriver driver;
	private String inputPropertiesFilePath = "src\\test\\java\\resources\\inputRegister.properties";
	Properties prop ;
	

	@Test(groups="Regression",priority=0)
	public void UserRegistration() throws IOException, InterruptedException
	{
		//Fetch all the required input fields and parameters for register page from the properties file
		prop = getInputDetails(inputPropertiesFilePath);
		
		//Initialize the Webdriver by providing the browser type and its executable path
		driver=initializeDriver(prop.getProperty("browser"),prop.getProperty("chromeDriverPath"));
		
		//create instance of the Register Page by providing the driver state and page locators file location 
		RegisterPage rp = new RegisterPage(driver,prop.getProperty("locatorsPath"));
		
		//Launch the registration page
		rp.launchPage(prop.getProperty("registerPageUrl"));
		
		//Create WebElements to be used for performing user operations
		rp.createLocators();
		
		//Enter all personal details for the registration page
		rp.enterPersonalDetails(prop.getProperty("iFirstName"),prop.getProperty("iLastName"),
				prop.getProperty("iGender"), prop.getProperty("iDateOfBirth"));
		
		//Enter all contact details for the registration page
		rp.enterContactDetails(prop.getProperty("iAddress"),prop.getProperty("iEmail"),
				prop.getProperty("iPhone"), prop.getProperty("iCountry"));
		
		//Fetch the Hobby and language list details and pass to the registration page
		String [] iHobbies = prop.getProperty("iHobbies").split(",");
		String [] iLanguages = prop.getProperty("iLanguages").split(",");
		rp.enterHobbiesLangSkills(iHobbies,iLanguages,prop.getProperty("iMainSkill"));
		
		//Share the password to be set
		rp.setPassword(prop.getProperty("iPassword"));
		
		//Enter submit the form
		rp.submitPage();
		
		//Added to check the details on the page before closing the browser
		Thread.sleep(10000);
		
		//Close the browser and any files or resources Open
		closeAll();
		
	}
}	
