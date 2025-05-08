package PageRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	
	// Page Title
	@FindBy(xpath = "//h2[text()='All Products']")
    private WebElement allproductHeader;
	
	//All products list 
	@FindBy(xpath = "//div[@class='features_items']")
    private WebElement allproductList;

    // Search Section
    @FindBy(xpath = "//input[@id='search_product']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@id='submit_search']")
    private WebElement searchButton;
    
    @FindBy(xpath = "//h2[text()='Searched Products']")
    private WebElement searchProductTitle;
    
    @FindBy(xpath = "//div[@class='single-products']/div/p")
    private List<WebElement> searchRelatedProduct;
    
    
    @FindBy(xpath = "//div[@class='brands_products']//h2")
    private WebElement brandHeader;
    
    public boolean isBrandHeaderDisplayed() {
    	return brandHeader.isDisplayed();
    }
    
    //Brand Polo
    @FindBy(xpath = "//a[@href='/brand_products/Polo']")
    private WebElement brandPolo;
    
    public WebElement clickBrandPolo() {
    	return brandPolo;
    }
    
    //Brand H_and_M
    @FindBy(xpath = "//a[@href='/brand_products/H&M']")
    private WebElement brandH_and_M;
    
    public WebElement clickBrandH_and_M() {
    	return brandH_and_M;
    }
    
    @FindBy(xpath = "//div[@class='features_items']/h2")
    private WebElement brandTitle;
    
    public String getBrandTitleName() {
    	return brandTitle.getText();
    }
    
    public boolean isBrandTitledisplayed() {
    	return brandTitle.isDisplayed();
    }
    
    
    
    

    @FindBy(xpath = "//div[@class='productinfo text-center']/p")
    private List<WebElement> productNames;

    @FindBy(xpath = "//div[@class='productinfo text-center']/h2")
    private List<WebElement> productPrices;

    @FindBy(xpath = "//div[@class='productinfo text-center']/a[contains(@class, 'add-to-cart')]")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//div[contains(@class, 'features_items')]//a[contains(text(), 'View Product')]")
    private List<WebElement> viewProductButtons;
    
    @FindBy(xpath = "//u[text()='View Cart']")
    private WebElement viewCartButtons;
    
    @FindBy(xpath = "//button[text()='Continue Shopping']")
    private WebElement continueShoppingButtons;
    
    

    // Constructor
    public ProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // --- Page Actions ---

    //Check All Products Header is Displayed
    public boolean isAllproductHeaderDisplayed() {
        return allproductHeader.isDisplayed();
    }
    
    //All Products List is Displayed
    public boolean isAllproductListDisplayed() {
        return allproductList.isDisplayed();
    }

    // Search for a Product
    public void searchProduct(String productName) {
        searchInput.sendKeys(productName);
        searchButton.click();
    }
    
    // Searched Product Title is Displayed 
    public boolean isSeachProductTitleDisplayed() {
    	return searchProductTitle.isDisplayed();
    }
    
    //Get Searched Products 
    public List<WebElement> searchedproductName() {
    	return searchRelatedProduct;
    }
    
    
   

    // Get Product Details
    public String getProductName(int index) {
        return productNames.get(index).getText();
    }

    public String getProductPrice(int index) {
        return productPrices.get(index).getText();
    }

    // Add Product to Cart
    public WebElement addProductToCart(int index) {
        return addToCartButtons.get(index);
    }
    
    public List<WebElement> addAllproductTocart() {
    	return addToCartButtons;
    }
    
    // First Product "View Product" Button
    @FindBy(xpath = "(//a[contains(text(), 'View Product')])[1]")
    private WebElement firstViewProductButton;
    
   // Click "View Product" for the First Item
    public WebElement clickFirstViewProduct() {
    	return firstViewProductButton;
    }
    
    // View Cart Button
    public WebElement clickViewCartButtons() {
        return viewCartButtons;
    }
    
    // Continue Shopping Buttons
    public WebElement clickContinueShoppingButtons() {
    	return continueShoppingButtons;
    }
    
 
    // View Product Details
    public WebElement viewProductDetails(int index) {
        return viewProductButtons.get(index);
    }
    
    

}
