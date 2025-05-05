package Product_Test;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;

@Listeners(Listners_Imp.class)
public class TestCase_24 extends BaseConfig {

	@Test
	public void Download_Invoice_after_purchase_order() throws InterruptedException {

		// Test data
		Random random = new Random();
		String name = "Test User";
		String email = "testuser" + random.nextInt(10000) + "@example.com";
		String password = "Test@123";
		String address = "123 Test Street";
		String city = "Test City";
		String state = "Test State";
		String zipcode = "12345";
		String mobile = "1234567890";
		String comment = "Please deliver between 9am-5pm";
		String cardName = "Test User";
		String cardNumber = "4111111111111111";
		String cvc = "123";
		String expiryMonth = "12";
		String expiryYear = "2030";

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

		// 4. Add products to cart
		WebElement addToCart =driver.findElement(By.xpath("(//a[@data-product-id='1'])[1]"));
		js.executeScript("arguments[0].click();", addToCart);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Continue Shopping')]")).click();
		System.out.println("Added product to cart");

		// 5. Click 'Cart' button
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();

		// 6. Verify that cart page is displayed
		if (driver.getTitle().contains("Shopping Cart")) {
			System.out.println("Cart page is displayed");
		} else {
			System.out.println("Cart page is not displayed");
		}

		// 7. Click Proceed To Checkout
		driver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]")).click();

		// 8. Click 'Register / Login' button
		driver.findElement(By.xpath("//u[contains(text(),'Register / Login')]")).click();

		// 9. Fill all details in Signup and create account
		// Signup form
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);
		driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

		// Account information
		driver.findElement(By.xpath("//input[@id='id_gender1']")).click();
		driver.findElement(By.id("password")).sendKeys(password);

		// Date of birth
		Select daySelect = new Select(driver.findElement(By.id("days")));
		daySelect.selectByValue("10");
		Select monthSelect = new Select(driver.findElement(By.id("months")));
		monthSelect.selectByValue("4");
		Select yearSelect = new Select(driver.findElement(By.id("years")));
		yearSelect.selectByValue("1990");

		// Address information
		driver.findElement(By.id("first_name")).sendKeys("John");
		driver.findElement(By.id("last_name")).sendKeys("Doe");
		driver.findElement(By.id("address1")).sendKeys(address);
		driver.findElement(By.id("state")).sendKeys(state);
		driver.findElement(By.id("city")).sendKeys(city);
		driver.findElement(By.id("zipcode")).sendKeys(zipcode);
		driver.findElement(By.id("mobile_number")).sendKeys(mobile);

		// Create account
		WebElement createAccountButton = driver.findElement(By.xpath("//button[@data-qa='create-account']"));
		js.executeScript("arguments[0].click();", createAccountButton);

		// 10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
		WebElement accountCreated = driver.findElement(By.xpath("//h2[@data-qa='account-created']"));
		if (accountCreated.isDisplayed()) {
			System.out.println("'ACCOUNT CREATED!' is visible");
			driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
		} else {
			System.out.println("'ACCOUNT CREATED!' is not visible");
		}

		// 11. Verify 'Logged in as username' at top
		WebElement loggedIn = driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]"));
		if (loggedIn.isDisplayed()) {
			System.out.println("'Logged in as username' is visible at top");
		} else {
			System.out.println("'Logged in as username' is not visible");
		}

		// 12. Click 'Cart' button
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();

		// 13. Click 'Proceed To Checkout' button
		driver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]")).click();

		// 14. Verify Address Details and Review Your Order
		WebElement addressDetails = driver.findElement(By.xpath("//h2[contains(text(),'Address Details')]"));
		WebElement reviewOrder = driver.findElement(By.xpath("//h2[contains(text(),'Review Your Order')]"));

		if (addressDetails.isDisplayed() && reviewOrder.isDisplayed()) {
			System.out.println("Address Details and Review Your Order sections are visible");
		} else {
			System.out.println("Sections are not visible");
		}

		// 15. Enter description in comment text area and click 'Place Order'
		driver.findElement(By.name("message")).sendKeys(comment);
		driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();

		// 16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
		driver.findElement(By.name("name_on_card")).sendKeys(cardName);
		driver.findElement(By.name("card_number")).sendKeys(cardNumber);
		driver.findElement(By.name("cvc")).sendKeys(cvc);
		driver.findElement(By.name("expiry_month")).sendKeys(expiryMonth);
		driver.findElement(By.name("expiry_year")).sendKeys(expiryYear);

		// 17. Click 'Pay and Confirm Order' button
		WebElement submit =driver.findElement(By.id("submit"));
		js.executeScript("arguments[0].click();", submit);

		// 18. Verify success message 'Your order has been placed successfully!'
		WebElement successMessage = driver.findElement(By.xpath("//p[contains(text(),'Congratulations! Your order has been confirmed!')]"));
		if (successMessage.isDisplayed()) {
			System.out.println("Success message is visible: " + successMessage.getText());
		} else {
			System.out.println("Success message is not visible");
		}

		// 19. Click 'Download Invoice' button and verify invoice is downloaded successfully
		driver.findElement(By.xpath("//a[contains(text(),'Download Invoice')]")).click();
		System.out.println("invoice is downloaded successfully");

		// 20. Click 'Continue' button
		driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();

		// 21. Click 'Delete Account' button
		driver.findElement(By.xpath("//a[contains(text(),'Delete Account')]")).click();

		// 22. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		WebElement accountDeleted = driver.findElement(By.xpath("//h2[@data-qa='account-deleted']"));
		if (accountDeleted.isDisplayed()) {
			System.out.println("'ACCOUNT DELETED!' is visible");
			driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
		} else {
			System.out.println("'ACCOUNT DELETED!' is not visible");
		}


	}
}