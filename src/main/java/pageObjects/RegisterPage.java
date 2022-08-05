package pageObjects;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import commonutilities.CommonMethods;

public class RegisterPage extends CommonMethods {

	private WebElement fname;
	private WebElement lname;
	private WebElement address;
	private WebElement email;
	private WebElement phone;
	private WebElement genderM;
	private WebElement genderF;
	private WebElement hobbyCric;
	private WebElement hobbyMov;
	private WebElement hobbyHock;
	private WebElement lang_sel;
	private WebElement skills;
	private WebElement country;
	private WebElement dobyear;
	private WebElement dobmonth;
	private WebElement dobday;
	private WebElement passw1;
	private WebElement passw2;
	private WebElement submit;
	private WebElement hobbyO;	

	private List<WebElement> langOptions;
	private List<WebElement> countryOptions;

	/*
	 * 
	 * @FindBy(xpath="//div/input[@placeholder='First Name']") WebElement fname;
	 * 
	 * @FindBy(xpath="//div/input[@placeholder='Last Name']") WebElement lname;
	 * 
	 * @FindBy(xpath="//form/div/div/textarea[@ng-model='Adress']") WebElement
	 * address;
	 * 
	 * @FindBy(xpath="//form/div/div/input[@type='email']") WebElement email;
	 * 
	 * @FindBy(xpath="//form/div/div/input[@type='tel']") WebElement phone;
	 * 
	 * @FindBy(xpath="//form/div/div/label/input[@type='radio'] [@value='Male']")
	 * WebElement genderM;
	 * 
	 * @FindBy(xpath="//form/div/div/label/input[@type='radio'] [@value='FeMale']")
	 * WebElement genderF;
	 * 
	 * @FindBy(xpath="//form/div/div/div/input[@type='checkbox'] [@value='Cricket']"
	 * ) WebElement hobbyCric;
	 * 
	 * @FindBy(xpath="//form/div/div/div/input[@type='checkbox'] [@value='Movies']")
	 * WebElement hobbyMov;
	 * 
	 * @FindBy(xpath="//form/div/div/div/input[@type='checkbox'] [@value='Hockey']")
	 * WebElement hobbyHock;
	 * 
	 * @FindBy(xpath="//form/div/div/multi-select/div[@id='msdd']") WebElement
	 * lang_sel;
	 * 
	 * @FindBy(xpath=
	 * "//form/div/div/multi-select/div/ul/li/a[@class='ui-corner-all']") WebElement
	 * lang_Multi;
	 * 
	 * @FindBy(xpath="//form/div/div/select[@id='Skills']") WebElement skills;
	 * 
	 * @FindBy(xpath="//form/div/div/span/span/span[@role='combobox']") WebElement
	 * country;
	 * 
	 * @FindBy(xpath="//form/div/div/select[@type='text'][@id='yearbox']")
	 * WebElement dobyear;
	 * 
	 * @FindBy(xpath="//form/div/div/select[@type='text'][@placeholder='Month']")
	 * WebElement dobmonth;
	 * 
	 * @FindBy(xpath="//form/div/div/select[@type='text'][@id='daybox']") WebElement
	 * dobday;
	 * 
	 * @FindBy(xpath="//form/div/div/input[@id='firstpassword']") WebElement passw1;
	 * 
	 * @FindBy(xpath="//form/div/div/input[@id='secondpassword']") WebElement
	 * passw2;
	 */
	public void createLocators() {
		fname = getElementByXpath("fname");
		lname = getElementByXpath("lname");
		address = getElementByXpath("address");
		email = getElementByXpath("email");
		phone = getElementByXpath("phone");
		genderM = getElementByXpath("genderM");
		genderF = getElementByXpath("genderF");
		hobbyCric = getElementByXpath("hobbyCric");
		hobbyMov = getElementByXpath("hobbyMov");
		hobbyHock = getElementByXpath("hobbyHock");
		lang_sel = getElementByXpath("lang_sel");
		skills = getElementByXpath("skills");
		country = getElementByXpath("country");
		dobyear = getElementByXpath("dobyear");
		dobmonth = getElementByXpath("dobmonth");
		dobday = getElementByXpath("dobday");
		passw1 = getElementByXpath("passw1");
		passw2 = getElementByXpath("passw2");
		submit = getElementByXpath("submit");
		hobbyO = getElementByXpath("hobbyO");		

		langOptions = getElementsByXpath("lang_Multi");
		countryOptions = getElementsByXpath("countryOptions");

	}

	public RegisterPage(WebDriver driver, String locatorsPath) throws IOException {
		super(driver);
		readWebElementLocators(locatorsPath);
//		PageFactory.initElements(getDriver(), this);
	}

	public void enterPersonalDetails(String iFirstName, String iLastName, String iGender, String iDateOfBirth) {
		String[] dobMMDDYYYY;
		enterText(fname, iFirstName);
		enterText(lname, iLastName);
		if (iGender.equals("Male"))
			clickElement(genderM);
		else if (iGender.equals("Female"))
			clickElement(genderF);
		else
			assertTrue(false, "The gender details are not proper. Please check.");
		dobMMDDYYYY = iDateOfBirth.split("-");
		selectFromDropdown(dobday, dobMMDDYYYY[0]);
		selectFromDropdown(dobmonth, dobMMDDYYYY[1]);
		selectFromDropdown(dobyear, dobMMDDYYYY[2]);
	}

	public void enterContactDetails(String iAddress, String iEmail, String iPhone, String iCountry) {
		// TODO Auto-generated method stub
		enterText(address, iAddress);
		enterText(email, iEmail);
		enterText(phone, iPhone);
		focusOnElement(country);
		clickElement(country);
		selectFromWebElementList(countryOptions, iCountry);
		clickElement(country);
	}

	public void enterHobbiesLangSkills(String[] iHobbies, String[] iLanguages, String iMainSkill) {
		for (String hobby : iHobbies) {
			if (hobbyO.getText().equalsIgnoreCase(hobby))
			{	
				clickElement(hobbyCric);
			}
			else if (hobbyO.getText().equalsIgnoreCase(hobby))
			{
				clickElement(hobbyMov);
			}
			else if (hobbyO.getText().equalsIgnoreCase(hobby))
			{
				clickElement(hobbyHock);
			}
			else
			{
				assertTrue(false, "Hobby entered is not from the listed ones");
			}
		}

		focusOnElement(lang_sel);
		clickElement(lang_sel);
		for (String Language : iLanguages) {
			selectFromWebElementList(langOptions, Language);
		}
		selectFromDropdown(skills, iMainSkill);

	}

	public void setPassword(String iPassword) {
		enterText(passw1, iPassword);
		enterText(passw2, iPassword);
	}

	public void submitPage() {
		clickElement(submit);
	}

}
