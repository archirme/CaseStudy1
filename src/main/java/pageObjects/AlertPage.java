package pageObjects;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutilities.CommonMethods;

public class AlertPage extends CommonMethods {
	
	Alert alert;
	private WebDriver driver;
	
	@FindBy(xpath="//button[@class='btn btn-danger']") WebElement displayAlertBox;

	@FindBy(css="[class='analystic'][href='#CancelTab']") WebElement alertWithOkNCancel;

	@FindBy(xpath="/html/body/div/div/div/div/div/div/button[@onclick='confirmbox()']") WebElement displayConfirmBox;

	@FindBy(css="[class='analystic'][href='#Textbox']") WebElement alertWithTextbox;

	@FindBy(css="button[onclick='promptbox()'][class='btn btn-info']") WebElement promptbox;

	
	public AlertPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		PropertyConfigurator.configure("log4j.properties");
	}


	public void performAlertWithOk() throws InterruptedException {
		clickElement(displayAlertBox);
		Thread.sleep(2000);
		alert = driver.switchTo().alert();
		Thread.sleep(2000);
		alert.accept();	
	}

	public void performAlertWithOkNCancel() throws InterruptedException {
		clickElement(alertWithOkNCancel);
		Thread.sleep(2000);
		clickElement(displayConfirmBox);
		Thread.sleep(2000);
		alert=driver.switchTo().alert();
		Thread.sleep(2000);
		alert.dismiss();
	}

	public void performAlertWithTextbox(String textboxName) throws InterruptedException {
		clickElement(alertWithTextbox);	
		Thread.sleep(2000);
		clickElement(promptbox);
		Thread.sleep(2000);
		alert=driver.switchTo().alert();
		Thread.sleep(2000);
		alert.sendKeys(textboxName);	
		Thread.sleep(2000);
		alert.accept();
		Thread.sleep(2000);
	}

}
