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
public class TestCase_15 extends BaseConfig {

	@Test
	public void PlaceOrder_Register_before_Checkout() throws InterruptedException {
		// Generate random email
		Random random = new Random();
		int randomNumber = random.nextInt(10000);
		String email = "testuser" + randomNumber + "@example.com";
		String password = "Test@123";
		String name = "Test User";

		// 1. Launch browser- Script in BaseConfig

		// Javascript Code
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		//3. Verify that home page is visible successfully
		String actPageTitle = driver.getTitle();
		String expPageTitle = "Automation Exercise";
		if(actPageTitle.equals(expPageTitle)) {
			System.out.println("Home page is visible successfully");
		} else {
			System.out.println("Home page is not displayed");
		}

		//4. Click 'Signup / Login' button
		driver.findElement(By.xpath("//a[@href='/login']")).click();

		//5. Fill all details in Signup and create account
		// Enter name and email
		driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys(name);
		driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);
		// Click Signup button
		driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

		Thread.sleep(1000);
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

		// Checkboxes
		WebElement checkbox1 = driver.findElement(By.xpath("//div[@class='checkbox']/div/span/input[@id='newsletter']"));
		js.executeScript("arguments[0].click();", checkbox1);

		WebElement checkbox2 = driver.findElement(By.xpath("//div[@class='checkbox']/div/span/input[@id='optin']"));
		js.executeScript("arguments[0].click();", checkbox2);

		// Address information
		driver.findElement(By.id("first_name")).sendKeys("John");
		driver.findElement(By.id("last_name")).sendKeys("Doe");
		driver.findElement(By.id("company")).sendKeys("Test Company");
		driver.findElement(By.id("address1")).sendKeys("123 Test Street");
		driver.findElement(By.id("address2")).sendKeys("Apt 4B");

		Select countrySelect = new Select(driver.findElement(By.id("country")));
		countrySelect.selectByValue("United States");

		driver.findElement(By.id("state")).sendKeys("California");
		driver.findElement(By.id("city")).sendKeys("Los Angeles");
		driver.findElement(By.id("zipcode")).sendKeys("90001");
		driver.findElement(By.id("mobile_number")).sendKeys("1234567890");

		// Click Create Account button
		js.executeScript("window.scrollBy(0, 400)");
		driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();

		//6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
		WebElement accountCreated = driver.findElement(By.xpath("//h2[@data-qa='account-created']"));
		if(accountCreated.isDisplayed()) {
			System.out.println("'ACCOUNT CREATED!' is visible");
		}
		else {
			System.out.println("'ACCOUNT CREATED!' is not visible");
		}
		driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();

		//7. Verify 'Logged in as username' at top
		WebElement loggedIn = driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]"));
		if(loggedIn.isDisplayed()) {
			System.out.println("'Logged in as username' is visible at top");
		} 
		else {
			System.out.println("'Logged in as username' is not visible");
		}

		//8. Add products to cart
		// Add first product
		WebElement addToCart1= driver.findElement(By.xpath("(//a[@data-product-id='1'])[1]"));
		js.executeScript("arguments[0].click();", addToCart1);
		// Click Continue Shopping
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Continue Shopping')]")).click();
		// Add second product
		WebElement addToCart2= driver.findElement(By.xpath("(//a[@data-product-id='2'])[1]"));
		js.executeScript("arguments[0].click();", addToCart2);
		// Click Continue Shopping
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Continue Shopping')]")).click();

		//9. Click 'Cart' button
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();

		//10. Verify that cart page is displayed
		String cartPageTitle = driver.getTitle();
		if(cartPageTitle.contains("Shopping Cart")) {
			System.out.println("Cart page is displayed");
		}
		else {
			System.out.println("Cart page is not displayed");
		}

		//11. Click Proceed To Checkout
		driver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]")).click();

		//12. Verify Address Details and Review Your Order
		WebElement addressDetails = driver.findElement(By.xpath("//h2[contains(text(),'Address Details')]"));
		if(addressDetails.isDisplayed()) {
			System.out.println("Address Details section is visible");
		} 
		else {
			System.out.println("Address Details section is not visible");
		}

		WebElement reviewOrder = driver.findElement(By.xpath("//h2[contains(text(),'Review Your Order')]"));
		if(reviewOrder.isDisplayed()) {
			System.out.println("Review Your Order section is visible");
		} 
		else {
			System.out.println("Review Your Order section is not visible");
		}

		//13. Enter description in comment text area and click 'Place Order'
		driver.findElement(By.name("message")).sendKeys("Please deliver between 9am-5pm");
		driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();

		//14. Enter payment details: Name on Card, Card Number, CVC, Expiration date
		driver.findElement(By.name("name_on_card")).sendKeys("Test User");
		driver.findElement(By.name("card_number")).sendKeys("4111111111111111");
		driver.findElement(By.name("cvc")).sendKeys("123");
		driver.findElement(By.name("expiry_month")).sendKeys("12");
		driver.findElement(By.name("expiry_year")).sendKeys("2030");

		//15. Click 'Pay and Confirm Order' button
		driver.findElement(By.id("submit")).click();

		//16. Verify success message 'Your order has been placed successfully!'
		WebElement successMessage = driver.findElement(By.xpath("//p[contains(text(),'Congratulations! Your order has been confirmed!')]"));
		if(successMessage.isDisplayed()) {
			System.out.println("Success message 'Your order has been placed successfully!' is visible");
		} 
		else {
			System.out.println("Success message is not visible");
		}

		//17. Click 'Delete Account' button
		driver.findElement(By.xpath("//a[contains(text(),'Delete Account')]")).click();

		//18. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		WebElement accountDeleted = driver.findElement(By.xpath("//h2[@data-qa='account-deleted']"));
		if(accountDeleted.isDisplayed()) {
			System.out.println("'ACCOUNT DELETED!' is visible");
		}
		else {
			System.out.println("'ACCOUNT DELETED!' is not visible");
		}
		driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();


	}
}