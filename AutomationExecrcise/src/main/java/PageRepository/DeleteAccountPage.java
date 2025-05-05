package PageRepository;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteAccountPage {


    // Account Deletion Elements
    @FindBy(xpath = "//h2[@class='title text-center']/b")
    private WebElement accountDeletedTitle;

    @FindBy(xpath = "//div[@class='pull-right']/a")
    private WebElement continueButton;

    // Constructor
    public DeleteAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // --- Page Actions ---


    // Verify Account Deletion
    public boolean isAccountDeleted() {
        return accountDeletedTitle.isDisplayed() && 
               accountDeletedTitle.getText().contains("ACCOUNT DELETED!");
    }

    // Click Continue After Deletion
    public void clickContinue() {
        continueButton.click();
    }


}
