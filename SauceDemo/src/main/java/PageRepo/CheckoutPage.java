package PageRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
	
	@FindBy(xpath="//span[contains(text(),'Checkout: Your Information')]")
	private WebElement pageTitle;

	public WebElement getPageTitle()
	{
		return pageTitle;
	}

	
	@FindBy(xpath="//input[@id='first-name']")
	private WebElement firstName;
	
	public WebElement getFirstName()
	{
		return firstName;
	}
	
	
	@FindBy(xpath="//input[@id='last-name']")
	private WebElement lastName;
	
	public WebElement getLastName()
	{
		return lastName;
	}
	
	
	@FindBy(xpath="//input[@id='postal-code']")
	private WebElement zip;
	
	public WebElement getZip()
	{
		return zip;
	}
	
	@FindBy(xpath="//input[@id='continue']")
	private WebElement continueButton;
	
	public WebElement getContinueButton()
	{
		return continueButton;
	}

	
	public CheckoutPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


	
}
