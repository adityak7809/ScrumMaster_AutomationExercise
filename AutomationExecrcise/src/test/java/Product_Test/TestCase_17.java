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
public class TestCase_17 extends BaseConfig {

	@Test
	public void Remove_Products_From_Cart() throws InterruptedException {

		// 1. Launch browser- Script in BaseConfig

		// Javascript Code
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		// 3. Verify that home page is visible successfully
		String actPageTitle = driver.getTitle();
		String expPageTitle = "Automation Exercise";
		if(actPageTitle.equals(expPageTitle)) {
			System.out.println("Home page is visible successfully");
		} else {
			System.out.println("Home page is not displayed");
		}

		// 4. Add products to cart
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

		// 5. Click 'Cart' button
		driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();

		// 6. Verify that cart page is displayed
		String cartPageTitle = driver.getTitle();
		if(cartPageTitle.contains("Shopping Cart")) {
			System.out.println("Cart page is displayed");
		} 
		else {
			System.out.println("Cart page is not displayed");
		}

		// 7. Click 'X' button corresponding to particular product
		// Get initial count of products in cart
		List<WebElement> cartItemsBefore = driver.findElements(By.xpath("//tbody/tr"));
		int initialItemCount = cartItemsBefore.size();
		System.out.println("Initial items in cart: " + initialItemCount);

		// Click delete button for first product (using first available delete button)
		if(initialItemCount > 0) {
			driver.findElement(By.xpath("(//a[@class='cart_quantity_delete'])[1]")).click();
			Thread.sleep(2000);

			// 8. Verify that product is removed from the cart
			List<WebElement> cartItemsAfter = driver.findElements(By.xpath("//tbody/tr"));
			int updatedItemCount = cartItemsAfter.size();
			System.out.println("Updated items in cart: " + updatedItemCount);

			if(updatedItemCount == initialItemCount - 1) {
				System.out.println("Product was successfully removed from cart");
			} else {
				System.out.println("Product was not removed from cart");
			}
		} else {
			System.out.println("No products in cart to remove");
		}

	}
}