package Product_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;

@Listeners(Listners_Imp.class)
public class TestCase_16 extends BaseConfig {

	@Test
	public void Place_Order_Login_before_Checkout() throws InterruptedException {
		// Test data
		String email = "oggy123@gmail.com"; // Replace with your registered email
		String password = "abc123"; // Replace with your password

		// 1. Launch browser- Script in BaseConfig

		// Javascript Code
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig 

		//3. Verify that home page is visible successfully
		String actPageTitle = driver.getTitle();
		String expPageTitle = "Automation Exercise";
		if(actPageTitle.equals(expPageTitle)) {
			System.out.println("Home page is visible successfully");
		} 
		else {
			System.out.println("Home page is not displayed");
		}

		//4. Click 'Signup / Login' button
		driver.findElement(By.xpath("//a[@href='/login']")).click();

		//5. Fill email, password and click 'Login' button
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

		//6. Verify 'Logged in as username' at top
		WebElement loggedIn = driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]"));
		if(loggedIn.isDisplayed()) {
			System.out.println("'Logged in as username' is visible at top");
		} 
		else {
			System.out.println("'Logged in as username' is not visible");
		}

		//7. Add products to cart
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

		//8. Click 'Cart' button
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();

		//9. Verify that cart page is displayed
		String cartPageTitle = driver.getTitle();
		if(cartPageTitle.contains("Shopping Cart")) {
			System.out.println("Cart page is displayed");
		} 
		else {
			System.out.println("Cart page is not displayed");
		}

		//10. Click Proceed To Checkout
		driver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]")).click();

		//11. Verify Address Details and Review Your Order
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

		//12. Enter description in comment text area and click 'Place Order'
		driver.findElement(By.name("message")).sendKeys("Please deliver between 9am-5pm");
		driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();

		//13. Enter payment details: Name on Card, Card Number, CVC, Expiration date
		driver.findElement(By.name("name_on_card")).sendKeys("Test User");
		driver.findElement(By.name("card_number")).sendKeys("4111111111111111");
		driver.findElement(By.name("cvc")).sendKeys("123");
		driver.findElement(By.name("expiry_month")).sendKeys("12");
		driver.findElement(By.name("expiry_year")).sendKeys("2030");

		//14. Click 'Pay and Confirm Order' button
		driver.findElement(By.id("submit")).click();

		//15. Verify success message 'Your order has been placed successfully!'
		WebElement successMessage = driver.findElement(By.xpath("//p[contains(text(),'Congratulations! Your order has been confirmed!')]"));
		if(successMessage.isDisplayed()) {
			System.out.println("Success message 'Your order has been placed successfully!' is visible");
		} 
		else {
			System.out.println("Success message is not visible");
		}

		//16. Click 'Delete Account' button
		driver.findElement(By.xpath("//a[contains(text(),'Delete Account')]")).click();

		//17. Verify 'ACCOUNT DELETED!' and click 'Continue' button
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