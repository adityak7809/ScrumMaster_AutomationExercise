package PageRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignupPage {
	
	

	// Page Title
	@FindBy(xpath = "//b[text()= 'Enter Account Information']")
	private WebElement pageTitle;

	// Account Information Fields
	@FindBy(xpath = "//input[@id='id_gender1']")  // Mr.
	private WebElement genderMaleRadio;
	@FindBy(xpath = "//input[@id='id_gender2']")  // Mrs.
	private WebElement genderFemaleRadio;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordInput;

	@FindBy(xpath = "//select[@id='days']")  // Date of Birth (Day)
	private WebElement dayDropdown;
	@FindBy(xpath = "//select[@id='months']")  // Month
	private WebElement monthDropdown;
	@FindBy(xpath = "//select[@id='years']")  // Year
	private WebElement yearDropdown;

	@FindBy(xpath = "//input[@id='newsletter']")
	private WebElement newsletterCheckbox;
	@FindBy(xpath = "//input[@id='optin']")
	private WebElement specialOffersCheckbox;

	// Address Information Fields
	@FindBy(xpath = "//input[@id='first_name']")
	private WebElement firstNameInput;
	@FindBy(xpath = "//input[@id='last_name']")
	private WebElement lastNameInput;
	@FindBy(xpath = "//input[@id='company']")
	private WebElement companyInput;
	@FindBy(xpath = "//input[@id='address1']")
	private WebElement address1Input;
	@FindBy(xpath = "//input[@id='address2']")
	private WebElement address2Input;
	@FindBy(xpath = "//select[@id='country']")
	private WebElement countryDropdown;
	@FindBy(xpath = "//input[@id='state']")
	private WebElement stateInput;
	@FindBy(xpath = "//input[@id='city']")
	private WebElement cityInput;
	@FindBy(xpath = "//input[@id='zipcode']")
	private WebElement zipcodeInput;
	@FindBy(xpath = "//input[@id='mobile_number']")
	private WebElement mobileNumberInput;

	// Submit Button
	@FindBy(xpath = "//button[@data-qa='create-account']")
	private WebElement createAccountButton;

	// Success Elements
	@FindBy(xpath = "//h2[@data-qa='account-created']")
	private WebElement accountCreatedTitle;

	@FindBy(xpath = "//div[@class='pull-right']/a")
	private WebElement continueButton;

	// Constructor
	public SignupPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// --- Methods ---

	// Verify page loaded
	public boolean isPageLoaded() {
		return pageTitle.isDisplayed();
	}

	// Select Gender (Mr. or Mrs.)
	public WebElement selectGender(String gender) {
		if (gender.equalsIgnoreCase("Mr.")) {
			return genderMaleRadio;
			
		} else if (gender.equalsIgnoreCase("Mrs.")) {
			return genderFemaleRadio;
		}
		return null;
	}

	// Set Password
	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}

	// Set Date of Birth
	public void selectDateOfBirth(String day, String month, String year) {
		Select daySelect = new Select(dayDropdown);
		daySelect.selectByVisibleText(day);

		Select monthSelect = new Select(monthDropdown);
		monthSelect.selectByVisibleText(month);

		Select yearSelect = new Select(yearDropdown);
		yearSelect.selectByVisibleText(year);
	}

	// Check Newsletter/Special Offers
	public WebElement toggleNewsletter() {
		return newsletterCheckbox;
	}
	public WebElement toggleSpecialOffers() {;
	    return specialOffersCheckbox;
	}

	// Fill Address Information
	public void enterAddressInfo(
			String firstName, String lastName, String company,
			String address1, String address2, String country,
			String state, String city, String zipcode, String mobileNumber) {

		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		companyInput.sendKeys(company);
		address1Input.sendKeys(address1);
		address2Input.sendKeys(address2);

		Select countrySelect = new Select(countryDropdown);
		countrySelect.selectByVisibleText(country);

		stateInput.sendKeys(state);
		cityInput.sendKeys(city);
		zipcodeInput.sendKeys(zipcode);
		mobileNumberInput.sendKeys(mobileNumber);
	}

	// Submit Account Creation
	public WebElement clickCreateAccount() {
		return createAccountButton;
	}

	// Verify Account Creation Success
	public boolean isAccountCreatedSuccessfully() {
		return accountCreatedTitle.isDisplayed();
	}

	// Click Continue Button
	public void clickContinue() {
		continueButton.click();
	}


}