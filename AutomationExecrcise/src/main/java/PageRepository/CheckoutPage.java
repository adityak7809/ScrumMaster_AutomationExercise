package PageRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

	// Fetch Delivery Address Details
	@FindBy(xpath = "//ul[@id='address_delivery']/li[2]")
	private WebElement deliveryFullName;

	@FindBy(xpath = "//ul[@id='address_delivery']/li[3]")
	private WebElement deliveryAddress1;

	@FindBy(xpath = "//ul[@id='address_delivery']/li[4]")
	private WebElement deliveryAddress2;

	@FindBy(xpath = "//ul[@id='address_delivery']/li[5]")
	private WebElement deliveryAddress3;

	@FindBy(xpath = "//ul[@id='address_delivery']/li[6]")
	private WebElement deliveryCityStatePostcode;

	@FindBy(xpath = "//ul[@id='address_delivery']/li[7]")
	private WebElement deliveryCountry;

	@FindBy(xpath = "//ul[@id='address_delivery']/li[8]")
	private WebElement deliveryPhone;




	// Fetch Billing  Address Details
	@FindBy(xpath = "//ul[@id='address_invoice']/li[2]")
	private WebElement billingFullName;

	@FindBy(xpath = "//ul[@id='address_invoice']/li[3]")
	private WebElement billingAddress1;

	@FindBy(xpath = "//ul[@id='address_invoice']/li[4]")
	private WebElement billingAddress2;

	@FindBy(xpath = "//ul[@id='address_invoice']/li[5]")
	private WebElement billingAddress3;

	@FindBy(xpath = "//ul[@id='address_invoice']/li[6]")
	private WebElement billingCityStatePostcode;

	@FindBy(xpath = "//ul[@id='address_invoice']/li[7]")
	private WebElement billingCountry;

	@FindBy(xpath = "//ul[@id='address_invoice']/li[8]")
	private WebElement billingPhone;


	// Comment Box
	@FindBy(xpath = "//textarea[@name='message']")
	private WebElement commentbox;

	// Payment Buttons
	@FindBy(xpath = "//a[contains(text(), 'Place Order')]")
	private WebElement placeOrderButton;

	// Constructor
	public CheckoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// --- Page Actions ---

	// Verify Delivery Address Details
	public String getDelFullName()
	{
		return deliveryFullName.getText();
	}

	public String getDelAddress1()
	{
		return deliveryAddress1.getText();
	}

	public String getDelAddress2()
	{
		return deliveryAddress2.getText();
	}

	public String getDelAddress3()
	{
		return deliveryAddress3.getText();
	}

	public String getDelCityStatePostcode()
	{
		return deliveryCityStatePostcode.getText();
	}

	public String getDelCountry()
	{
		return deliveryCountry.getText();
	}
	public String getDelPhone()
	{
		return deliveryPhone.getText();
	}


	// Verify Billing Address Details
	public String getBilFullName()
	{
		return billingFullName.getText();
	}

	public String getBilAddress1()
	{
		return billingAddress1.getText();
	}

	public String getBilAddress2()
	{
		return billingAddress2.getText();
	}

	public String getBilAddress3()
	{
		return billingAddress3.getText();
	}

	public String getBilCityStatePostcode()
	{
		return billingCityStatePostcode.getText();
	}

	public String getBilCountry()
	{
		return billingCountry.getText();
	}
	public String getBilPhone()
	{
		return billingPhone.getText();
	}

	//Comment in box
	public void clickCommentBox(String msg) {
		commentbox.sendKeys(msg);
		placeOrderButton.click();
	}

	// Proceed to Payment
	public void clickPlaceOrder() {
		placeOrderButton.click();
	}

}