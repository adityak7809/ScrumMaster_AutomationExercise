package PageRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllProductPage {

	@FindBy(xpath="//span[contains(text(),'Products')]")
	private WebElement pageTitle;
	
	public WebElement getPageTitle()
	{
		return pageTitle;
	}

	
	@FindBy(xpath="//a[@class='shopping_cart_link']")
	private WebElement cartButton;
	
	public WebElement getCartButton()
	{
		return cartButton;
	}

	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-backpack']")
	private WebElement addToCart1Button ;
	
	public WebElement getAddToCart1Button()
	{  
		return addToCart1Button;
	}
	
	@FindBy(xpath="//button[@id='add-to-cart-sauce-labs-bike-light']")
	private WebElement addToCart2Button ;
	
	public WebElement getAddToCart2Button()
	{  
		return addToCart2Button;
	}
	
	
	
	@FindBy(xpath="//button[@id='react-burger-menu-btn']")
	private WebElement menuButton;
	
	public WebElement getMenuButtons()
	{
		return menuButton;
	}
	
	@FindBy(xpath="//a[@id='logout_sidebar_link']")
	private WebElement logoutButton;
	
	public WebElement getLogoutButton()
	{
		return logoutButton;
	}
	
	
	
	public AllProductPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	

}
