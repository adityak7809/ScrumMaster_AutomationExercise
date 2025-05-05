package Product_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;

@Listeners(Listners_Imp.class)
public class TestCase_26 extends BaseConfig {


	@Test
	public void Verify_Scroll_Up_without_Arrow_button_and_Scroll_Down_functionality() {

		// 1. Launch browser- Script in BaseConfig

		// Javascript Code
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		// 3. Verify that home page is visible successfully
		String actualTitle = driver.getTitle();
		if (actualTitle.equals("Automation Exercise")) {
			System.out.println("Home page is visible successfully");
		} else {
			System.out.println("Home page is not displayed");
		}

		// 4. Scroll down page to bottom
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		System.out.println("Scrolled to bottom of page");

		// 5. Verify 'SUBSCRIPTION' is visible
		WebElement subscription = driver.findElement(By.xpath("//h2[contains(text(),'Subscription')]"));
		if (subscription.isDisplayed()) {
			System.out.println("'SUBSCRIPTION' is visible at bottom of page");
		} else {
			System.out.println("'SUBSCRIPTION' is not visible");
		}

		// 6. Scroll up page to top
		js.executeScript("window.scrollTo(0, 0)");
		System.out.println("Scrolled back to top of page");

		// 7. Verify that page is scrolled up and text is visible
		WebElement headerText = driver.findElement(By.xpath("//h2[contains(.,'Full-Fledged practice website for Automation Engineers')]"));

		if (headerText.isDisplayed()) {
			System.out.println("Page is scrolled up and text is visible: '" + headerText.getText() + "'");
		} else {
			System.out.println("Page scroll up verification failed");
		}


	}
}