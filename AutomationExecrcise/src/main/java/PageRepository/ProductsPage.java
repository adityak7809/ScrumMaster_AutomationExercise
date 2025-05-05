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
    private WebElement searchRelatedProduct;
    

    // Filter Section
    
    @FindBy(xpath = "//div[@class='left-sidebar']//a[contains(@href, '#Women')]")
    private WebElement womenCategory;

    @FindBy(xpath = "//div[@id='Women']//a[contains(text(), 'Dress ')]")
    private WebElement womenDressSubcategory;
    
    @FindBy(xpath = "//div[@class='left-sidebar']//a[contains(@href, '#Men')]")
    private WebElement menCategory;

    @FindBy(xpath = "//div[@id='Men']//a[contains(text(), 'Tshirts')]")
    private WebElement menTshirtsSubcategory;


    @FindBy(xpath = "//div[@class='productinfo text-center']/p")
    private List<WebElement> productNames;

    @FindBy(xpath = "//div[@class='productinfo text-center']/h2")
    private List<WebElement> productPrices;

    @FindBy(xpath = "//div[@class='productinfo text-center']/a[contains(@class, 'add-to-cart')]")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//div[contains(@class, 'features_items')]//a[contains(text(), 'View Product')]")
    private List<WebElement> viewProductButtons;

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
    
 //   public String searchedproductName(List<WebElement> )
    
    
   // Click on Women Category
    public void clickWomenCatogory() {
    	womenCategory.click();
    }
    
    // Click on Women Subcategory
    public void clickWomenDressSubcategory() {
    	womenDressSubcategory.click();
    }

    // Click on Men Category
    public void clickMenCatogory() {
        menCategory.click();
    }
    
    // Click on Men Subcategory
    public void clickMenTshirtsSubcategory() {
       menTshirtsSubcategory.click();
    }


    // Get Product Details
    public String getProductName(int index) {
        return productNames.get(index).getText();
    }

    public String getProductPrice(int index) {
        return productPrices.get(index).getText();
    }

    // Add Product to Cart
    public void addProductToCart(int index) {
        addToCartButtons.get(index).click();
    }

    // View Product Details
    public WebElement viewProductDetails(int index) {
        return viewProductButtons.get(index);
    }

}
