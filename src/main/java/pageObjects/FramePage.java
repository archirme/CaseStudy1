package pageObjects;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutilities.CommonMethods;

public class FramePage extends CommonMethods {
	
	@FindBy(xpath="//a[@href='SwitchTo.html']") WebElement switchToFramesOption;

	@FindBy(xpath="//a[@href='Frames.html']") WebElement framesOption1;

	@FindBy(css="#aswift_2") WebElement frame1;

	@FindBy(css="#ad_iframe") WebElement frame2;

	@FindBy(css="#dismiss-button") WebElement frameDismiss;
	
	
	public FramePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		PropertyConfigurator.configure("log4j.properties");
	}


	public void frameTestOption1() {
	
		switchToFramesOption.click();
		framesOption1.click();
	    driver.switchTo().frame(frame1);
	    driver.switchTo().frame(frame2);
	    frameDismiss.click();
	    driver.switchTo().defaultContent();
		
	}

}
