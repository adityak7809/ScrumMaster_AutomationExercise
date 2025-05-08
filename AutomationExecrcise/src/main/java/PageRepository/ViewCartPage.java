package PageRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewCartPage{

	// Total Number of products
	@FindBy(xpath = "//tbody/tr")
	private List<WebElement> totalProduct;

	// Product Columns (per item)
	@FindBy(xpath = "//td[@class='cart_description']/h4/a")
	private List<WebElement> productNames;

	@FindBy(xpath = "//td[@class='cart_price']/p")
	private List<WebElement> productPrices;

	@FindBy(xpath = "//td[@class='cart_quantity']/button")
	private List<WebElement> productQuantities;

	@FindBy(xpath = "//td[@class='cart_total']/p")
	private List<WebElement> productTotals;

	// Action Buttons
	@FindBy(xpath = "//a[contains(@class, 'check_out')]")
	private WebElement proceedToCheckoutButton;

	@FindBy(xpath = "(//a[@href='/login'])[2]")
	private WebElement registerLoginButton;

	@FindBy(xpath = "//i[@class='fa fa-times']")
	private List<WebElement> deleteButtons;

	// Empty Cart Message
	@FindBy(xpath = "//b[contains(text(), 'Cart is empty!')]")
	private WebElement emptyCartMessage;

	// Shopping cart Page title
	@FindBy(xpath = "//li[text()='Shopping Cart']")
	private WebElement shoppingPageTitle;

	// Constructor
	public ViewCartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


	// --- Page Actions ---

	// Verify Shopping page title
	public boolean isShoppingPageDisplayed() {
		return shoppingPageTitle.isDisplayed();
	}
	
	@FindBy(xpath = "//tbody/tr")
	private WebElement showProduct;

	// Verify product is addes
	public boolean isProductDisplayed() {
		return showProduct.isDisplayed();
	}

	// Get total product count
	public int getTotalProductCount() {
		return totalProduct.size();
	}

	// Get Product Details
	public String getProductName(int index) {
		return productNames.get(index).getText();
	}

	public String getProductPrice(int index) {
		return productPrices.get(index).getText();
	}

	public String getProductQuantity(int index) {
		return productQuantities.get(index).getText();
	}

	public String getProductTotal(int index) {
		return productTotals.get(index).getText();
	}

	// Remove Item from Cart
	public void removeItem(int index) {
		deleteButtons.get(index).click();
	}

	// Verify Cart is Empty
	public boolean isCartEmpty() {
		return emptyCartMessage.isDisplayed();
	}

	// Proceed to Checkout
	public void clickProceedToCheckout() {
		proceedToCheckoutButton.click();
	}

	// Click 'Register/Login' (for guest users)
	public void clickRegisterLogin() {
		registerLoginButton.click();
	}



}