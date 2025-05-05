package PageRepository;

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
    @FindBy(xpath = "//li[10]/a")
    private WebElement loggedInUsername;
    
    // First Product "View Product" Button
    @FindBy(xpath = "(//a[contains(text(), 'View Product')])[1]")
    private WebElement firstViewProductButton;

    // Subscription Section (Footer)
    @FindBy(xpath = "//h2[text()='Subscription']")
	private WebElement getSubscriptionHeader;
    
    @FindBy(xpath = "//input[@id='susbscribe_email']")
    private WebElement subscribeEmailInput;

    @FindBy(xpath = "//button[@id='subscribe']")
    private WebElement subscribeButton;

    @FindBy(xpath = "//div[@id='success-subscribe']")
    private WebElement subscriptionSuccessMessage;

    // Featured Products Section
    @FindBy(xpath = "//h2[contains(text(),'Features Items')]")
    private WebElement featuredProductsTitle;

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
    public void clickFirstViewProduct() {
        firstViewProductButton.click();
    }

    // Get Logged-in Username
    public String getLoggedInUsername() {
        return loggedInUsername.getText();
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
}