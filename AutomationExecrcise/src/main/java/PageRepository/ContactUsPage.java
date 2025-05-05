package PageRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage {

	// Get In Touch Header
	@FindBy(xpath = "//h2[text()='Get In Touch']")
	private WebElement getInTouchHeader;

	@FindBy(xpath = "//input[@name='name']")
	private WebElement nameInput;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailInput;

	@FindBy(xpath = "//input[@name='subject']")
	private WebElement subjectInput;

	@FindBy(xpath = "//textarea[@name='message']")
	private WebElement messageTextarea;

	@FindBy(xpath = "//input[@name='upload_file']")
	private WebElement uploadFileInput;

	@FindBy(xpath = "//input[@name='submit']")
	private WebElement submitButton;

	// Success/Failure Messages
	@FindBy(xpath = "//div[contains(@class, 'status alert alert-success')]")
	private WebElement successMessage;

	// Home Button
	@FindBy(xpath = "//div[@id='form-section']/a/span")
	private WebElement homeButton;

	// Constructor
	public ContactUsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// --- Page Actions ---

	// Verify Success Message
	public boolean getInTouchHeaderDisplayed() {
		return getInTouchHeader.isDisplayed();
	}

	// Fill Contact Form
	public void fillContactForm(String name, String email, String subject, String message) {
		nameInput.sendKeys(name);
		emailInput.sendKeys(email);
		subjectInput.sendKeys(subject);
		messageTextarea.sendKeys(message);
	}

	// Upload File
	public void uploadFile(String filePath) {
		uploadFileInput.sendKeys(filePath);
	}

	// Submit Form
	public WebElement clickSubmit() {
		return submitButton;
	}

	// Verify Success Message
	public boolean isSuccessMessageDisplayed() {
		return successMessage.isDisplayed();
	}


	// Return to Home Page
	public void clickHomeButton() {
		homeButton.click();
	}

	// Complete Contact Form Workflow
	public void submitContactForm(String name, String email, String subject, String message, String filePath) {
		fillContactForm(name, email, subject, message);
		if (filePath != null) {
			uploadFile(filePath);
		}
		clickSubmit();
	}
}