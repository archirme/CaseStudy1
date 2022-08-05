package pageObjects;

import java.util.Set;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutilities.CommonMethods;

public class WindowsPage extends CommonMethods {
	
	String MainWindow = null;
	Set<String> AllWindows = null;
	
	@FindBy(xpath="//a/button[@class='btn btn-info']") WebElement tabbedWindowsClickButton;
	
	@FindBy(xpath="//button[@class='btn btn-primary']") WebElement seperateWindowsClickButton;
	
	@FindBy(xpath="//button[@onclick='multiwindow()']") WebElement multipleWindowsClickButton;
	
	@FindBy(css="[href='#Seperate'][class=analystic]") WebElement seperateWindowsExample;

	@FindBy(css="[href='#Multiple'][class=analystic]") WebElement multipleWindowsExample;
	
	
	public WindowsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		PropertyConfigurator.configure("log4j.properties");
	}


	public void performTabbedWinodwsTest() throws InterruptedException {
		tabbedWindowsClickButton.click();
		MainWindow = driver.getWindowHandle();
		System.out.println("Main window is " + MainWindow);
		
		AllWindows =  driver.getWindowHandles();
		for(String Window: AllWindows)
		{
			String ChildWindow=Window;
			if (!MainWindow.equals(ChildWindow))
			{
				System.out.println("Child Window Open is " + ChildWindow);
				driver.switchTo().window(ChildWindow);
				System.out.println("Child Window Title is " + driver.switchTo().window(ChildWindow).getTitle());
				driver.close();
			}
		}
		Thread.sleep(3000);
		driver.switchTo().window(MainWindow);	
		
	}


	public void performSeperateWindowsTest() {
		seperateWindowsExample.click();
		seperateWindowsClickButton.click();
		MainWindow = driver.getWindowHandle();
		System.out.println("Main window is " + MainWindow);
		
		AllWindows =  driver.getWindowHandles();
		for(String Window: AllWindows)
		{
			String ChildWindow=Window;
			if (!MainWindow.equals(ChildWindow))
			{
				System.out.println("Child Window Open is " + ChildWindow);
				driver.switchTo().window(ChildWindow);
				System.out.println("Child Window Title is " + driver.switchTo().window(ChildWindow).getTitle());
				driver.close();
			}
		}
		
		driver.switchTo().window(MainWindow);
		
	}


	public void performMultipleWindowsTest() {
		multipleWindowsExample.click();
		multipleWindowsClickButton.click();
		MainWindow = driver.getWindowHandle();
		System.out.println("Main window is " + MainWindow);
		
		AllWindows =  driver.getWindowHandles();
		for(String Window: AllWindows)
		{
			String ChildWindow=Window;
			if (!MainWindow.equals(ChildWindow))
			{
				System.out.println("Child Window Open is " + ChildWindow);
				driver.switchTo().window(ChildWindow);
				System.out.println("Child Window Title is " + driver.switchTo().window(ChildWindow).getTitle());
				driver.close();
			}
		}
		
		driver.switchTo().window(MainWindow);
		
	}


	public void performNavigateTest(String navigatePageUrl) throws InterruptedException {
		driver.navigate().to(navigatePageUrl);
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().forward();
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
	}

}

