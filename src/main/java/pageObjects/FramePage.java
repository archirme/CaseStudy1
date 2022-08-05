package pageObjects;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutilities.CommonMethods;

public class FramePage extends CommonMethods {

	boolean NoAd = false;
	@FindBy(xpath = "//a[@href='SwitchTo.html']")
	WebElement switchToFramesOption;

	@FindBy(xpath = "//a[@href='Frames.html']")
	WebElement framesOption1;

	@FindBy(css = "#aswift_2")
	WebElement frame1;

	@FindBy(css = "#ad_iframe")
	WebElement frame2;

	@FindBy(css = "#dismiss-button")
	WebElement frameDismiss1;

	@FindBy(xpath = "//div[@id='ad_position_box']//div[@id='dismiss-button']")
	WebElement frameDismiss2;

	// div[@id="ad_position_box"]//div[@id="dismiss-button"]

	public FramePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		PropertyConfigurator.configure("log4j.properties");
	}

	public void frameTestOption1() throws InterruptedException {

		switchToFramesOption.click();
		framesOption1.click();
		driver.switchTo().frame(frame1);
		try 
		{
			driver.switchTo().frame(frame2);
		} catch (NoSuchElementException e) 
		{
			System.out.println("No ad is found - Continue");
			NoAd = true;
		}

		try 
		{
			if (!NoAd) 
			{
				frameDismiss1.click();
			}
		} catch (NoSuchElementException e) 
		{
			System.out.println("No ad is found - Continue");
			frameDismiss2.click();
			
		}
		driver.switchTo().defaultContent();
	}

}
