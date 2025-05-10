package PageRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {
	
	@FindBy(xpath="//span[contains(text(),'Checkout: Overview')]")
	private WebElement pageTitle;

	public WebElement getPageTitle()
	{
		return pageTitle;
	}
	
	
	@FindBy(xpath="//div[@data-test='payment-info-value']")
	private WebElement payementInfo;

	public WebElement getPayementInfo()
	{
		return payementInfo;
	}
	
	
	@FindBy(xpath="//div[@data-test='shipping-info-value']")
	private WebElement shippingInfo;

	public WebElement getShippingInfo()
	{
		return shippingInfo;
	}
	
	
	@FindBy(xpath="//div[@data-test='total-label']")
	private WebElement totalPrice;

	public WebElement getTotalPrice()
	{
		return totalPrice;
	}
	
	@FindBy(xpath="//button[contains(text(),'Finish')]")
	private WebElement finishButton;

	public WebElement getFinishButton()
	{
		return finishButton;
	}
	
	

	public CheckoutOverviewPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}


}
