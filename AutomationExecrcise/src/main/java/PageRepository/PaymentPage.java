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
    @FindBy(xpath = "(//div[contains(@class, 'alert-success')])[1]")
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
    public void clickPayAndConfirmOrder() {
        payAndConfirmOrderButton.click();
    }

    // Verify Order Confirmation
    public boolean isOrderConfirmed() {
        return orderConfirmationMessage.isDisplayed();
    }


    // Download Invoice
    public void clickDownloadInvoice() {
        downloadInvoiceButton.click();
        System.out.println("invoice is downloaded successfully");
    }

    // Complete Payment Workflow
    public void completePayment(String name, String cardNumber, String cvc, String month, String year) {
        enterPaymentDetails(name, cardNumber, cvc, month, year);
        clickPayAndConfirmOrder();
        assert isOrderConfirmed();
    }
}
