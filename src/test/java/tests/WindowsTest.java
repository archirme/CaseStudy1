package tests;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WindowsTest {
	
	@Test(groups="Smoke")
	public void WindowsTest1() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Learning\\Drivers\\Chrome\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.automationtesting.in/Windows.html");
		driver.manage().window().maximize();
		
		//Get and Navigate test
/*		driver.switchTo().alert().dismiss();
		Thread.sleep(2000);
		driver.navigate().to("https://www.facebook.com");
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().forward();
		Thread.sleep(2000);*/
		
		System.out.println("Tabbed Window Example ");
		
		driver.findElement(By.xpath("//a/button[@class='btn btn-info']")).click();
		
		String MainWindow = driver.getWindowHandle();
		System.out.println("Main window is " + MainWindow);
		
		Set<String> AllWindows =  driver.getWindowHandles();
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
		
		System.out.println("Seperate Windows Example ");
		driver.findElement(By.cssSelector("[href='#Seperate'][class=analystic]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		Thread.sleep(3000);
		
		
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
		
		System.out.println("Multiple Windows Example ");
		driver.findElement(By.cssSelector("[href='#Multiple'][class=analystic]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@onclick='multiwindow()']")).click();
		Thread.sleep(3000);
		
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
		
		driver.close();
	}

}
