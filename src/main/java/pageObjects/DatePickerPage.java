package pageObjects;

import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutilities.CommonMethods;

public class DatePickerPage extends CommonMethods {

	int curMonth;
	int curYear;
	@FindBy(xpath = "//a[@href='Widgets.html']")
	WebElement widgetsOption;

	@FindBy(xpath = "//a[@href='Datepicker.html']")
	WebElement datePickerOption;

	@FindBy(css = "#aswift_2")
	WebElement frame1;

	@FindBy(css = "#ad_iframe")
	WebElement frame2;

	@FindBy(css = "#dismiss-button")
	WebElement frameDismiss;

	@FindBy(css = "img.imgdp")
	WebElement datePicker;

	@FindBy(className = "ui-datepicker-month")
	WebElement datePickerMonth;

	@FindBy(className = "ui-datepicker-year")
	WebElement datePickerYear;

	@FindBy(xpath = "//span[@class='ui-icon ui-icon-circle-triangle-w']")
	WebElement prevNavigation;

	@FindBy(xpath = "//span[@class='ui-icon ui-icon-circle-triangle-e']")
	WebElement nextNavigation;

	@FindBy(xpath = "//table[@class='ui-datepicker-calendar']/tbody/tr/td")
	List<WebElement> calendarDate;

	public DatePickerPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		PropertyConfigurator.configure("log4j.properties");
	}

	public void navigateToDatePicker() {
		widgetsOption.click();
		datePickerOption.click();

	}

	public void dismissAds() 
	{
		try
		{
			driver.switchTo().frame(frame1);
			driver.switchTo().frame(frame2);
			frameDismiss.click();
			driver.switchTo().defaultContent();
		}catch(NoSuchElementException NoAds)
		{
			System.out.println("No Ads available - Skip method");
		}
		
	}

	public void selectRequiredDate(String dateReq) {

		String[] reqDate = dateReq.split("-");
		int reqDay = Integer.parseInt(reqDate[0]);
		int reqMonth = Integer.parseInt(reqDate[1]);
		int reqYear = Integer.parseInt(reqDate[2]);
		waitTillElementVisible(datePicker, 5);
		datePicker.click();
		curMonth = monthInNum(datePickerMonth.getText());
		curYear = Integer.parseInt(datePickerYear.getText());
		System.out.println("Month " + curMonth + " Year " + curYear);
		selectYear(curYear, reqYear);
		curYear = Integer.parseInt(datePickerYear.getText());
		curMonth = monthInNum(datePickerMonth.getText());
		System.out.println("Month " + curMonth + " Year " + curYear);
		selectMonth(curMonth, reqMonth);
		curYear = Integer.parseInt(datePickerYear.getText());
		curMonth = monthInNum(datePickerMonth.getText());
		System.out.println("Month " + curMonth + " Year " + curYear);
		selectDay(reqDay);

	}

	private void selectDay(int reqDay) 
	{
		for (WebElement curDay : calendarDate) {
			if (!(curDay.getText().isBlank())) // --- Isblank condition is removed
			{
				if (Integer.parseInt(curDay.getText()) == reqDay) 
				{
					System.out.println("Day is " + curDay.getText());
					curDay.click();
					break;
				}
			}
		}

	}

	private void selectYear(int curYear, int reqYear) {
		while (reqYear != curYear) {
			if (reqYear < curYear) {
				waitTillElementVisible(prevNavigation, 5);
				prevNavigation.click();
			} else if (reqYear > curYear) {
				waitTillElementVisible(nextNavigation, 5);
				nextNavigation.click();
			}
			curYear = Integer.parseInt(datePickerYear.getText());
		}

	}

	private void selectMonth(int curMonth, int reqMonth) {
		do {
			if (reqMonth < curMonth) {
				waitTillElementVisible(prevNavigation, 5);
				prevNavigation.click();
			} else if (reqMonth > curMonth) {
				waitTillElementVisible(nextNavigation, 5);
				nextNavigation.click();
			}
			curMonth = monthInNum(datePickerMonth.getText());
		} while (reqMonth != curMonth);
	}
}