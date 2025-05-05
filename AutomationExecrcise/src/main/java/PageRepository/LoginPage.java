package PageRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    
    // Page Elements
    @FindBy(xpath = "//h2[contains(text(),'Login to your account')]")
    private WebElement loginHeader;
    
    @FindBy(name = "email")
    private WebElement emailInput;
    
    @FindBy(name = "password")
    private WebElement passwordInput;
    
    @FindBy(xpath = "//button[contains(text(),'Login')]")
    private WebElement loginButton;
    
    @FindBy(xpath = "//p[contains(text(),'Your email or password is incorrect!')]")
    private WebElement errorMessage1;
    
    @FindBy(xpath = "//p[contains(text(),'Email Address already exist!')]")
    private WebElement errorMessage2;
    
    @FindBy(xpath = "//a[contains(text(),'Signup / Login')]")
    private WebElement signupLoginLink;
    
    @FindBy(xpath = "//h2[contains(text(),'New User Signup!')]")
    private WebElement newUserSignupHeader;
    
    @FindBy(name = "name")
    private WebElement signupNameInput;
    
    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement signupEmailInput;
    
    @FindBy(xpath = "//button[contains(text(),'Signup')]")
    private WebElement signupButton;
    
    // Constructor
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    
    public boolean isLoginPageSectionDisplayed() {
        return loginHeader.isDisplayed();
    }
    
    public void enterEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }
    
    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }
    
    public void clickLoginButton() {
        loginButton.click();
    }
    
    public boolean isErrorMessageDisplayed() {
        return errorMessage1.isDisplayed();
    }
    
    public String getErrorMessageText() {
        return errorMessage1.getText();
    }
    
    public boolean isErrorMessage2Displayed() {
        return errorMessage2.isDisplayed();
    }
    
    public String getErrorMessage2Text() {
        return errorMessage2.getText();
    }
    
    public boolean isNewUserSignupSectionDisplayed() {
        return newUserSignupHeader.isDisplayed();
    }
    
    public void enterSignupName(String name) {
        signupNameInput.clear();
        signupNameInput.sendKeys(name);
    }
    
    public void enterSignupEmail(String email) {
        signupEmailInput.clear();
        signupEmailInput.sendKeys(email);
    }
    
    public void clickSignupButton() {
        signupButton.click();
    }
    
 
}
