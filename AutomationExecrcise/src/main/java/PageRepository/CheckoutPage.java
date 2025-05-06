package PageRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

	// Fetch Delivery Address Details
	@FindBy(xpath = "//ul[@id='address_delivery']/li")
	private List<WebElement> deleiveryDetails;

	// Fetch Billing  Address Details
	@FindBy(xpath = "//ul[@id='address_invoice']/li")
	private List<WebElement> billingDetails;

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
	public List<WebElement> getDeliveryDetails() {
		return deleiveryDetails;
	}

	// Verify Billing Address Details
	public List<WebElement> getBillingDetails() {
		return billingDetails;
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