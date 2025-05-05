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
public class TestCase_23 extends BaseConfig {

	@Test
	public void Verify_address_details_in_checkout_page() throws InterruptedException {
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

		// 4. Click 'Signup / Login' button
		driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();

		// 5. Fill all details in Signup and create account
		// Enter name and email
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);
		driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

		// Fill account information
		driver.findElement(By.id("id_gender1")).click(); // Title (Mr.)
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

		// Click Create Account button
		WebElement createAccountButton = driver.findElement(By.xpath("//button[@data-qa='create-account']"));
		js.executeScript("arguments[0].click();", createAccountButton);

		// 6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
		WebElement accountCreated = driver.findElement(By.xpath("//h2[@data-qa='account-created']"));
		if (accountCreated.isDisplayed()) {
			System.out.println("'ACCOUNT CREATED!' is visible");
			driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
		} else {
			System.out.println("'ACCOUNT CREATED!' is not visible");
		}

		// 7. Verify 'Logged in as username' at top
		WebElement loggedIn = driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]"));
		if (loggedIn.isDisplayed()) {
			System.out.println("'Logged in as username' is visible at top");
		} else {
			System.out.println("'Logged in as username' is not visible");
		}

		// 8. Add products to cart
		WebElement addToCart = driver.findElement(By.xpath("(//a[@data-product-id='1'])[1]"));
		js.executeScript("arguments[0].click();", addToCart);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Continue Shopping')]")).click();

		// 9. Click 'Cart' button
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();

		// 10. Verify that cart page is displayed
		if (driver.getTitle().contains("Shopping Cart")) {
			System.out.println("Cart page is displayed");
		} else {
			System.out.println("Cart page is not displayed");
		}

		// 11. Click Proceed To Checkout
		driver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]")).click();

		// 12. Verify that the delivery address is same address filled at the time registration of account
		WebElement deliveryAddress = driver.findElement(By.id("address_delivery"));
		if (deliveryAddress.getText().contains(address) && 
				deliveryAddress.getText().contains(city) && 
				deliveryAddress.getText().contains(state) && 
				deliveryAddress.getText().contains(zipcode)) {
			System.out.println("Delivery address matches registration address");
		} else {
			System.out.println("Delivery address does not match registration address");
		}

		// 13. Verify that the billing address is same address filled at the time registration of account
		WebElement billingAddress = driver.findElement(By.id("address_invoice"));
		if (billingAddress.getText().contains(address) && 
				billingAddress.getText().contains(city) && 
				billingAddress.getText().contains(state) && 
				billingAddress.getText().contains(zipcode)) {
			System.out.println("Billing address matches registration address");
		} else {
			System.out.println("Billing address does not match registration address");
		}

		// 14. Click 'Delete Account' button
		driver.findElement(By.xpath("//a[contains(text(),'Delete Account')]")).click();

		// 15. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		WebElement accountDeleted = driver.findElement(By.xpath("//h2[@data-qa='account-deleted']"));
		if (accountDeleted.isDisplayed()) {
			System.out.println("'ACCOUNT DELETED!' is visible");
			driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();
		} else {
			System.out.println("'ACCOUNT DELETED!' is not visible");
		}


	}
}