package PageRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {



	// Product Information Elements
	@FindBy(xpath = "//div[@class='product-information']/h2")
	private WebElement productName;

	@FindBy(xpath = "//div[@class='product-information']/p[1]")
	private WebElement productCategory;

	@FindBy(xpath = "//div[@class='product-information']//span/span")
	private WebElement productPrice;

	@FindBy(xpath = "//div[@class='product-information']/p[2]")
	private WebElement productAvailability;

	@FindBy(xpath = "//div[@class='product-information']/p[3]")
	private WebElement productCondition;

	@FindBy(xpath = "//div[@class='product-information']/p[4]")
	private WebElement productBrand;

	// Quantity & Add to Cart
	@FindBy(id = "quantity")
	private WebElement quantityInput;

	@FindBy(xpath = "//span/button[@type='button']")
	private WebElement addToCartButton;

	// Success Modal After Adding to Cart
	@FindBy(xpath = "//div[@id='cartModal']//h4[contains(text(), 'Added!')]")
	private WebElement addedToCartModal;

	@FindBy(xpath = "//div[@id='cartModal']//a[contains(@href, '/view_cart')]")
	private WebElement viewCartButton;

	// Review Section Elements (XPath only)

	// Page Elements
	@FindBy(xpath = "//a[contains(text(), 'Write Your Review')]")
	private WebElement writeReviewHeader;

	@FindBy(xpath = "//input[@id='name']")
	private WebElement reviewerNameInput;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement reviewerEmailInput;

	@FindBy(xpath = "//textarea[@id='review']")
	private WebElement reviewTextarea;

	@FindBy(xpath = "//button[@id='button-review']")
	private WebElement submitReviewButton;

	@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(., 'Thank you for your review.')]")
	private WebElement reviewSuccessAlert;

	// Constructor
	public ProductDetailsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// --- Page Actions ---

	// Get Page Title
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	//Check Product details page is loaded
	public boolean isProductDetailsPageDisplayed(WebDriver driver) {
		if(driver.getTitle().equals("Automation Exercise - Product Details"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	// Get Product Details
	public String getProductName() {
		return productName.getText();
	}

	public String getProductCategory() {
		return productCategory.getText().replace("Category: ", "");
	}

	public String getProductPrice() {
		return productPrice.getText();
	}

	public String getProductAvailability() {
		return productAvailability.getText().replace("Availability: ", "");
	}
	
	public String getProductCondition() {
		return productCondition.getText();
	}
	
	public String getProductBrand() {
		return productBrand.getText();
	}

	// Set Quantity
	public void setQuantity(int quantity) {
		quantityInput.clear();
		quantityInput.sendKeys(String.valueOf(quantity));
	}

	// Add to Cart
	public void clickAddToCart() {
		addToCartButton.click();
	}

	// Verify Item Added to Cart (Modal)
	public boolean isAddedToCartModalVisible() {
		return addedToCartModal.isDisplayed();
	}

	// Go to Cart from Modal
	public void clickViewCart() {
		viewCartButton.click();
	}

	// Click "Write Your Review" to expand the form
	public boolean writeYourReviewDisplayed() {
		return writeReviewHeader.isDisplayed();
	}
	
	public WebElement writeYourReviewheaderText() {
		return writeReviewHeader;
	}

	// Submit a review
	public void submitReview(String name, String email, String reviewText) {
		reviewerNameInput.sendKeys(name);
		reviewerEmailInput.sendKeys(email);
		reviewTextarea.sendKeys(reviewText);
	}
	
	public void clickSubmit() {
		submitReviewButton.click();
	}

	// Check if review submission was successful
	public boolean isReviewSubmittedSuccessfully() {
		return reviewSuccessAlert.isDisplayed();
	}


}
