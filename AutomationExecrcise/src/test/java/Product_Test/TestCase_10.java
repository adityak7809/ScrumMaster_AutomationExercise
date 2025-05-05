package Product_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;

@Listeners(Listners_Imp.class)
public class TestCase_10 extends BaseConfig {

	@Test
	public void Verify_Subscription_in_home_page() {

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

		// 4. Scroll down to footer
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");

		// 5. Verify text 'SUBSCRIPTION'
		if (driver.findElement(By.xpath("//h2[text()='Subscription']")).isDisplayed()) {
			System.out.println("'SUBSCRIPTION' text is visible");
		} else {
			System.out.println("'SUBSCRIPTION' text is not visible");
		}

		// 6. Enter email address in input and click arrow button
		String subscriptionEmail = "abc@gmail.com";
		driver.findElement(By.id("susbscribe_email")).sendKeys(subscriptionEmail);
		driver.findElement(By.id("subscribe")).click();

		// 7. Verify success message is visible
		if (driver.findElement(By.xpath("//div[text()='You have been successfully subscribed!']")).isDisplayed()) {
			System.out.println("Success message is visible");
		} else {
			System.out.println("Success message is not visible");
		}

	}
}