package PageRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    
    @FindBy(xpath = "//title[text()='Automation Exercise']")
    private WebElement homePageTitle;

    @FindBy(xpath = "//a[contains(@href,'/login')]")
    private WebElement signupLoginLink;

    @FindBy(xpath = "//a[contains(@href,'/logout')]")
    private WebElement logoutLink;
    
    @FindBy(xpath = "(//a[@href='/test_cases'])[1]")
    private WebElement testCasesLink;
    
    @FindBy(xpath = "//a[text()=' Delete Account']")
    private WebElement deleteAccount;

    @FindBy(xpath = "//a[contains(@href,'/contact_us')]")
    private WebElement contactUsLink;

    @FindBy(xpath = "//a[contains(@href,'/products')]")
    private WebElement productsLink;

    @FindBy(xpath = "//a[contains(@href,'/view_cart')]")
    private WebElement cartLink;

    // Logged-in User Info
    @FindBy(xpath = "//a[contains(text(),' Logged in as ')]")
    private WebElement loggedInUsername;
    
    // First Product "View Product" Button
    @FindBy(xpath = "(//a[contains(text(), 'View Product')])[1]")
    private WebElement firstViewProductButton;
    
   // Filter Section
    
   //Catogory page title
    
    @FindBy(xpath = "//div[@class='left-sidebar']//h2[contains(text(),'Category')]")
    private WebElement categoryHeader;
    
    @FindBy(xpath = "//div[@class='left-sidebar']//a[contains(@href, '#Women')]")
    private WebElement womenCategory;

    @FindBy(xpath = "//div[@id='Women']//a[contains(text(), 'Dress ')]")
    private WebElement womenDressSubcategory;
    
    @FindBy(xpath = "//div[@class='left-sidebar']//a[contains(@href, '#Men')]")
    private WebElement menCategory;

    @FindBy(xpath = "//div[@id='Men']//a[contains(text(), 'Tshirts')]")
    private WebElement menTshirtsSubcategory;
    
    @FindBy(xpath = "//h2[contains(@class, 'title')]")
    private WebElement categoryTitle;

    // Subscription Section (Footer)
    @FindBy(xpath = "//h2[text()='Subscription']")
	private WebElement getSubscriptionHeader;
    
    @FindBy(xpath = "//input[@id='susbscribe_email']")
    private WebElement subscribeEmailInput;

    @FindBy(xpath = "//button[@id='subscribe']")
    private WebElement subscribeButton;

    @FindBy(xpath = "//div[@id='success-subscribe']")
    private WebElement subscriptionSuccessMessage;

    // Recommended Products Section
    @FindBy(xpath = "//div[@class='recommended_items']/h2")
    private WebElement recommendedProductsHeader;
    
    public boolean isRecommendedProductsHeaderDisplayed() {
    	return recommendedProductsHeader.isDisplayed();
    }
    
    @FindBy(xpath = "//div[@class='carousel-inner']//a[contains(@class, 'add-to-cart')]")
    private List<WebElement> addToCartButtons;
    
    // Add Product to Cart
    public WebElement addProductToCart(int index) {
        return addToCartButtons.get(index);
    }
    
    @FindBy(xpath = "//u[text()='View Cart']")
    private WebElement viewCartButtons;
    
    // View Cart Button
    public WebElement clickViewCartButtons() {
        return viewCartButtons;
    }

    // Constructor
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // --- Page Actions ---

    
    // Get Page Title
    public String getHomePageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    // Navigate to Signup/Login Page
    public void clickSignupLoginLink() {
        signupLoginLink.click();
    }
    
   // Navigate to Test Cases Page
    public void clicktestCasesLinkLink() {
        testCasesLink.click();
    }

    // Navigate to Products Page
    public void clickProductsLink() {
        productsLink.click();
    }

    // Navigate to Cart Page
    public void clickCartLink() {
        cartLink.click();
    }
    
    public WebElement jsClickCartLink() {
    	return cartLink;
    }

    // Navigate to Contact Us Page
    public void clickContactUsLink() {
        contactUsLink.click();
    }
    
    // Navigate to Delete Account Page
    public void clickOnDeleteAccount() {
        deleteAccount.click();
    }

    // Logout (if logged in)
    public void clickLogoutLink() {
        logoutLink.click();
    }

    // Check if User is Logged In
    public boolean isUserLoggedIn() {
        return loggedInUsername.isDisplayed();
    }
    
    // Click "View Product" for the First Item
    public WebElement clickFirstViewProduct() {
    	return firstViewProductButton;
    }

    // Get Logged-in Username
    public String getLoggedInUsername() {
        return loggedInUsername.getText();
    }
    
    
    // Category Header Displayed
    public boolean isCategoryHeaderDisplayed() {
    	return categoryHeader.isDisplayed();
    }
    
    // Click on Women Category
    public WebElement clickWomenCatogory() {
    	return womenCategory;
    }
    
    // Click on Women Subcategory
    public WebElement clickWomenDressSubcategory() {
    	return womenDressSubcategory;
    }

    // Click on Men Category
    public WebElement clickMenCatogory() {
    	return menCategory;
    }
    
    // Click on Men Subcategory
    public WebElement clickMenTshirtsSubcategory() {
    	return menTshirtsSubcategory;
    }
    
    //
    
    public String getCategoryName() {
    	return categoryTitle.getText();
    }
    
    public boolean isCategoryTitleDisplayed() {
    	return categoryTitle.isDisplayed();
    }


    // Subscribe to Newsletter
    public void subscribeToNewsletter(String email) {
        subscribeEmailInput.sendKeys(email);
        subscribeButton.click();
    }

 // Check Subscription Header is Displayed
    public boolean isSubscriptionHeaderDisplayed() {
        return getSubscriptionHeader.isDisplayed();
    }
    
    // Check if Subscription was Successful
    public boolean isSubscriptionSuccessful() {
        return subscriptionSuccessMessage.isDisplayed();
    }
    
    //Bottom Scroll Up Button
    @FindBy(xpath = "//a[@id='scrollUp']")
    private WebElement bottomScrollUpButton;
    
    public WebElement ScrollUpButton() {
    	return bottomScrollUpButton;
    }
    
    @FindBy(xpath = "//h2[contains(text(),'Full-Fledged practice website for Automation Engineers')]")
    private WebElement topText;
    
    public boolean istopTextDisplayed() {
        return topText.isDisplayed();
    }
    
}