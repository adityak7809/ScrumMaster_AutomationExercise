package PageRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {


    // Payment Form Elements
    @FindBy(xpath = "//input[@name='name_on_card']")
    private WebElement nameOnCardInput;

    @FindBy(xpath = "//input[@name='card_number']")
    private WebElement cardNumberInput;

    @FindBy(xpath = "//input[@name='cvc']")
    private WebElement cvcInput;

    @FindBy(xpath = "//input[@name='expiry_month']")
    private WebElement expiryMonthInput;

    @FindBy(xpath = "//input[@name='expiry_year']")
    private WebElement expiryYearInput;

    @FindBy(xpath = "//button[@id='submit']")
    private WebElement payAndConfirmOrderButton;

    // Order Confirmation Elements
    @FindBy(xpath = "//p[text()='Congratulations! Your order has been confirmed!']")
    private WebElement orderConfirmationMessage;

    @FindBy(xpath = "//a[contains(@href, '/download_invoice')]")
    private WebElement downloadInvoiceButton;


    // Constructor
    public PaymentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // --- Page Actions ---

    // Enter Payment Details
    public void enterPaymentDetails(String name, String cardNumber, String cvc, String month, String year) {
        nameOnCardInput.sendKeys(name);
        cardNumberInput.sendKeys(cardNumber);
        cvcInput.sendKeys(cvc);
        expiryMonthInput.sendKeys(month);
        expiryYearInput.sendKeys(year);
    }

    // Submit Payment
    public WebElement clickPayAndConfirmOrder() {
        return payAndConfirmOrderButton;
    }

    // Verify Order Confirmation
    public WebElement isOrderConfirmed() {
        return orderConfirmationMessage;
    }


    // Download Invoice
    public void clickDownloadInvoice() {
        downloadInvoiceButton.click();
    }
    
    //Continue Button
    @FindBy(xpath = "//a[text()='Continue']")
    private WebElement continueButton;
    
    public void clickContinueButton() {
    	continueButton.click();
    }

}
