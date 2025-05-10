package PageRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {
	
	@FindBy(xpath="//span[contains(text(),'Checkout: Complete!')]")
	private WebElement pageTitle;

	public WebElement getPageTitle()
	{
		return pageTitle;
	}
	
	
	@FindBy(xpath="//h2[@class='complete-header']")
	private WebElement successMsg;

	public WebElement getSuccessMsg()
	{
		return successMsg;
	}
	
	@FindBy(xpath="//button[contains(text(),'Back Home')]")
	private WebElement backHomeButton;

	public WebElement getBackHomeButton()
	{
		return backHomeButton;
	}

	
	public CheckoutCompletePage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

}
