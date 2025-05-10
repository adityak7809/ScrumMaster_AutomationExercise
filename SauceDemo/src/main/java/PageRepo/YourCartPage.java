package PageRepo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourCartPage {
	
	@FindBy(xpath="//span[contains(text(),'Your Cart')]")
	private WebElement pageTitle;

	public WebElement getPageTitle()
	{
		return pageTitle;
	}

	
	@FindBy(xpath="//div[@class='cart_quantity']")
	private  List<WebElement> quantity;

	public WebElement getQuantity(int index)
	{
		return quantity.get(index);
	}

	
	@FindBy(xpath="//div[@class='inventory_item_name']")
	private List<WebElement> productName ;

	public WebElement getProductName(int index)
	{
		return productName.get(index);
	}
	
	
	@FindBy(xpath="//button[@id='checkout']")
	private WebElement checkoutButton;

	public WebElement getCheckoutButton()
	{
		return checkoutButton;
	}
	
	
	public YourCartPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

}
