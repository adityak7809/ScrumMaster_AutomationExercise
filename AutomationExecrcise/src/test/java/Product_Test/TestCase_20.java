package Product_Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;

@Listeners(Listners_Imp.class)
public class TestCase_20 extends BaseConfig {

	@Test
	public void Search_Products_and_Verify_Cart_After_Login() throws InterruptedException {

		// Test data
		String searchProduct = "T-Shirt"; // Product to search
		String email = "oggy123@gmail.com"; // Replace with your email
		String password = "abc123"; // Replace with your password

		// 1. Launch browser- Script in BaseConfig

		// Javascript Code
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		// 3. Click on 'Products' button
		driver.findElement(By.xpath("//a[@href='/products']")).click();
		System.out.println("Clicked on Products button");

		// 4. Verify user is navigated to ALL PRODUCTS page successfully
		WebElement allProductsTitle = driver.findElement(By.xpath("//h2[text()='All Products']"));
		if(allProductsTitle.isDisplayed()) {
			System.out.println("Successfully navigated to ALL PRODUCTS page");
		} else {
			System.out.println("Failed to navigate to ALL PRODUCTS page");
		}

		// 5. Enter product name in search input and click search button
		driver.findElement(By.id("search_product")).sendKeys(searchProduct);
		driver.findElement(By.id("submit_search")).click();
		System.out.println("Searched for product: " + searchProduct);

		// 6. Verify 'SEARCHED PRODUCTS' is visible
		WebElement searchedProductsTitle = driver.findElement(By.xpath("//h2[text()='Searched Products']"));
		if(searchedProductsTitle.isDisplayed()) {
			System.out.println("'SEARCHED PRODUCTS' is visible");
		} else {
			System.out.println("'SEARCHED PRODUCTS' is not visible");
		}

		// 7. Verify all the products related to search are visible
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'productinfo')]"));
		if(products.size() > 0) {
			System.out.println("Found " + products.size() + " products related to search");
			for(WebElement product : products) {
				String productName = product.findElement(By.tagName("p")).getText();
				System.out.println(" - " + productName);
			}
		} 
		else
		{
			System.out.println("No products found for the search");
		}

		// 8. Add those products to cart
		List<WebElement> addToCartButtons = driver.findElements(By.xpath("//div[@class='single-products']/div[@class='productinfo text-center']//a[text()='Add to cart']"));
		for(WebElement button : addToCartButtons) 
		{
			js.executeScript("arguments[0].click();", button);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[contains(text(),'Continue Shopping')]")).click();
		}
		System.out.println("Added " + addToCartButtons.size() + " products to cart");

		// 9. Click 'Cart' button and verify that products are visible in cart
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();

		List<WebElement> cartItems = driver.findElements(By.xpath("//table[@id='cart_info_table']/tbody/tr"));
		if(cartItems.size() == addToCartButtons.size()) 
		{
			System.out.println("All " + cartItems.size() + " products are visible in cart");
		} 
		else
		{
			System.out.println("Mismatch in cart items. Expected: " + addToCartButtons.size() + ", Found: " + cartItems.size());
		}

		// 10. Click 'Signup / Login' button and submit login details
		driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
		System.out.println("Logged in successfully");

		// 11. Again, go to Cart page
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();

		// 12. Verify that those products are visible in cart after login as well
		List<WebElement> cartItemsAfterLogin = driver.findElements(By.xpath("//table[@id='cart_info_table']/tbody/tr"));
		if(cartItemsAfterLogin.size() == cartItems.size()) {
			System.out.println("All " + cartItemsAfterLogin.size() + " products remain in cart after login");
		} else {
			System.out.println("Cart items changed after login. Before: " + cartItems.size() + ", After: " + cartItemsAfterLogin.size());
		}


	}
}