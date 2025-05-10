package PageRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(xpath="//div[contains(text(),'Swag Labs')]")
	private WebElement pageTitle;
	
	public WebElement getPageTitle()
	{
		return pageTitle;
	}
	
	
	@FindBy(xpath="//input[@id='user-name']")
	private WebElement username;
	
	public WebElement getUsername()
	{
		return username;
	}
	
	
	@FindBy(xpath="//input[@id='password']")
	private WebElement password;
	
	public WebElement getPassword()
	{
		return password;
	}
	
	
	@FindBy(xpath="//input[@id='login-button']")
	private WebElement loginButton;
	
	public WebElement getLoginButton()
	{
		return loginButton;
	}
	
	
	public LoginPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	

}
