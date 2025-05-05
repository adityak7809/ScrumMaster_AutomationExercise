package Product_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;

@Listeners(Listners_Imp.class)
public class TestCase_11 extends BaseConfig {

	@Test
	public void Verify_Subscription_in_Cart_page() {

		// 1. Launch browser- Script in BaseConfig

		// Javascript Code
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig
		
		
		// 3. Verify that home page is visible successfully
		String actualPageTitle = driver.getTitle();
		String expectedPageTitle = "Automation Exercise";
		if (actualPageTitle.equals(expectedPageTitle)) {
			System.out.println("Home page is visible successfully");
		} else {
			System.out.println("Home page is not displayed");
		}

		// 4. Click 'Cart' button
		driver.findElement(By.xpath("//a[@href='/view_cart']")).click();

		// 5. Scroll down to footer
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		// 6. Verify text 'SUBSCRIPTION'
		if (driver.findElement(By.xpath("//h2[text()='Subscription']")).isDisplayed()) {
			System.out.println("'SUBSCRIPTION' text is visible");
		} else {
			System.out.println("'SUBSCRIPTION' text is not visible");
		}

		// 7. Enter email address in input and click arrow button
		String subscriberEmail = "abc@gmail.com";
		driver.findElement(By.id("susbscribe_email")).sendKeys(subscriberEmail);
		driver.findElement(By.id("subscribe")).click();

		// 8. Verify success message is visible
		if (driver.findElement(By.xpath("//div[text()='You have been successfully subscribed!']")).isDisplayed()) {
			System.out.println("Subscription success message is visible");
		} else {
			System.out.println("Subscription success message is not visible");
		}


	}
}