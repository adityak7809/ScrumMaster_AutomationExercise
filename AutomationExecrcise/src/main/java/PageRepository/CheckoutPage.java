package PageRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

	// Fetch Delivery Address Details
	@FindBy(xpath = "//ul[@id='address_delivery']/li[2]")
	private WebElement deliveryName;

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
	private WebElement billingName;

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

	// Payment Buttons
	@FindBy(xpath = "//a[contains(text(), 'Place Order')]")
	private WebElement placeOrderButton;

	// Constructor
	public CheckoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// --- Page Actions ---

	// Verify Delivery Address Details
	public String getDeliveryName() {
		return deliveryName.getText();
	}

	public String getDeliveryAddress1() {
		return deliveryAddress1.getText();
	}

	public String getDeliveryAddress2() {
		return deliveryAddress2.getText();
	}

	public String getDeliveryAddress3() {
		return deliveryAddress3.getText();
	}

	public String getDeliveryCityStatePostcode() {
		return deliveryCityStatePostcode.getText();
	}

	public String getDeliveryCountry() {
		return deliveryCountry.getText();
	}

	public String getDeliveryPhone() {
		return deliveryPhone.getText();
	}

	// Verify Billing Address Details
	public String getBillingName() {
		return billingName.getText();
	}

	public String getBillingAddress1() {
		return billingAddress1.getText();
	}

	public String getBillingAddress2() {
		return billingAddress2.getText();
	}

	public String getBillingAddress3() {
		return billingAddress3.getText();
	}

	public String getBillingCityStatePostcode() {
		return billingCityStatePostcode.getText();
	}

	public String getBillingCountry() {
		return billingCountry.getText();
	}

	public String getBillingPhone() {
		return billingPhone.getText();
	}

	// Proceed to Payment
	public void clickPlaceOrder() {
		placeOrderButton.click();
	}

}