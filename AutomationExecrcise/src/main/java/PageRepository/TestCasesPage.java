package PageRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCasesPage {

	
	@FindBy(xpath = "//b[text()='Test Cases']")
    private WebElement pageTitle;
	
	// Constructor
    public TestCasesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    
    // Verify Page Loaded
    public boolean isPageLoaded() {
        return pageTitle.isDisplayed();
    }
}
